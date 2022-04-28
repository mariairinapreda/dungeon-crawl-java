package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.Camera;
import javax.sound.sampled.AudioInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Scanner;


public class Main extends Application {

    private final int mapSizeX = 15;
    private final int mapSizeY = 15;
    GameMap map = MapLoader.loadMap("/map.txt");
//    Canvas canvas = new Canvas(
//            map.getWidth() * Tiles.TILE_WIDTH,
//            map.getHeight() * Tiles.TILE_WIDTH);

    Canvas canvas = new Canvas(
            mapSizeX * Tiles.TILE_WIDTH,
            mapSizeY * Tiles.TILE_WIDTH);

    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label strengthLabel = new Label();
    Label shieldLabel = new Label();
    Label keyLabel = new Label();
    Button button=new Button("Accept");
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));
        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);
        ui.add(new Label("Strength: "), 0, 2);
        ui.add(strengthLabel, 1, 2);
        ui.add(new Label("Shield: "), 0, 4);
        ui.add(shieldLabel, 1, 4);
        ui.add(new Label("Key: "), 0, 8);
        ui.add(keyLabel, 1, 8);
        ui.add(button, 0,9);
        sound();
        button.setFocusTraversable(false);
        button.setOnAction(actionEvent -> {
            System.out.println("merge butonul");
            int x =map.getPlayer().getCell().getX();
            int y = map.getPlayer().getCell().getY();
            System.out.println(x);
            System.out.println(y);
            if(map.getPlayer().getCell() ==
                    ((Objects.equals(map.getCell(x, y).getTileName(), "key") ? map.getCell(x,y): ""))){
                System.out.println("am gasit cheia");
                map.getCell(x, y).setType(CellType.FLOOR);
                map.getPlayer().setHasKey(true);
            }
            if(map.getPlayer().getCell() ==
                    ((Objects.equals(map.getCell(x, y).getTileName(), "shield") ? map.getCell(x,y): ""))){
                System.out.println("am gasit scutul");
                map.getCell(x, y).setType(CellType.FLOOR);
                int oneShield = 1;
                map.getPlayer().setShield(map.getPlayer().getSheild()+oneShield);
            }
            if(map.getPlayer().getCell() ==
                    ((Objects.equals(map.getCell(x, y).getTileName(), "health") ? map.getCell(x,y): ""))){
                System.out.println("am gasit potiunea");
                map.getCell(x, y).setType(CellType.FLOOR);
                int potion = 10;
                map.getPlayer().setHealth(map.getPlayer().getHealth()+potion);
            }
            if(map.getPlayer().getCell() ==
                    ((Objects.equals(map.getCell(x, y).getTileName(), "mace") ? map.getCell(x,y): ""))){
                System.out.println("am gasit buzduganul");
                map.getCell(x, y).setType(CellType.FLOOR);
                int mace = 1;
                map.getPlayer().setStrength(map.getPlayer().getStrength()+mace);
            }
        });


        BorderPane borderPane = new BorderPane();
//        int half = map.getHeight() /2  - map.getPlayer().getX();
        borderPane.setCenter(canvas);
//        borderPane.setMaxSize(half, half);
//        borderPane.setMinSize(half, half);

        borderPane.setRight(ui);


        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);

//        final Camera camera = new PerspectiveCamera(true);
//
//        camera.setFarClip(1000);
//        camera.setTranslateX(-15);
//        camera.setTranslateY(-15);
//        camera.setTranslateZ(-150);
////        camera.setTranslateZ(-500);
////        camera.setNearClip(0.1);
////        camera.setFarClip(2000.0);


//
//        scene.setCamera(camera);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);


        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }
//    public void sound() throws MalformedURLException {
////        File mediaFile = new File("//home/ioana/Downloads/videoplayback.mp3");
//        File mediaFile = new File("src/main/resources/videoplayback.mp3");
//        Media media = new Media(mediaFile.toURI().toURL().toString());
////        Media media = new Media(getClass().getResource("/videoplayback.mp3").toExternalForm());
//        MediaPlayer mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.play();
//    }

    public void sound() throws MalformedURLException {
        File mediaFile = new File("src/main/resources/videoplayback.mp3");
        System.out.println(mediaFile.getAbsolutePath());
        Media media = new Media(mediaFile.toURI().toURL().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1,0);
                refresh();
                break;
        }
        if(map.getActualMap() == 1 && map.getPlayer().standingOnDoor() && map.getPlayer().isHasKey()){
            map = MapLoader.loadMap("/map2.txt");
            map.setActualMap(2);
        }
        if(map.getActualMap() == 2 && map.getPlayer().standingOnDoor() && map.getPlayer().isHasKey()){
            map = MapLoader.loadMap("/map3.txt");
            map.setActualMap(3);
        }
    }

    private void refresh() {
        context.setFill(Color.BLACK);
        int screenX = map.getPlayer().getX() / 2- 1;
        int screenY = map.getPlayer().getY() / 2 - 1;

        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (int x = 0; x < map.getWidth(); x++) {
            int relativeX = x - screenX;
            for (int y = 0; y < map.getHeight(); y++) {
                int relativeY = y - screenY;
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), relativeX, relativeY);
                } else {
                    Tiles.drawTile(context, cell, relativeX, relativeY);
                }
            }
        }
        if(map.getPlayer().isDead()){
            System.out.println("you lost");
            System.exit(0);
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
        strengthLabel.setText("" + map.getPlayer().getStrength());
        shieldLabel.setText("" + map.getPlayer().getSheild());
        keyLabel.setText("" + map.getPlayer().isHasKey());


    }
}
