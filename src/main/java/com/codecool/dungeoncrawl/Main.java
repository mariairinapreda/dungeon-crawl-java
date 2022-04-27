package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import javafx.application.Application;
import javafx.geometry.Insets;
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

import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;


public class Main extends Application {
    GameMap map = MapLoader.loadMap("/map.txt");
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
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

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);


        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
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
        if(map.getPlayer().standingOnDoor() && map.getPlayer().isHasKey()){
            map = MapLoader.loadMap("/map2.txt");
        }
    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
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
