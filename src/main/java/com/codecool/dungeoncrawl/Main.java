package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.*;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Objects;
import java.util.Random;


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
    Label userName = new Label("Cheat:");
    TextField userTextField = new TextField();
    Button cheatCode=new Button("OK");
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }




    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setOnMousePressed(e -> ui.requestFocus());
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
        ui.add(userName, 0, 12);
        ui.add(userTextField, 1, 12);
        ui.add(cheatCode,0,14);
        userTextField.requestFocus();


        cheatCode.setOnAction(actionEvent -> {
            setText(userTextField.getText());
            userTextField.setDisable(true);
        });



        sound();
        button.setFocusTraversable(false);
        button.setOnAction(actionEvent -> {
            System.out.println("merge butonul");
            int x =map.getPlayer().getCell().getX();
            int y = map.getPlayer().getCell().getY();
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
    private void showAlertWithHeaderText() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("GAME INFO");
        alert.setHeaderText("Results:");
        alert.setContentText("YOU LOST!");

        alert.showAndWait();
    }


    public void moveToward() {
        int leftDist = map.getGhost().getX() - map.getPlayer().getX();
        int upDist = map.getGhost().getY() - map.getPlayer().getY();
        if (upDist == 0 && leftDist == 0) {
            return;
        }
        if (upDist > 0 && upDist >= leftDist) {
            map.getGhost().move(0, -1);
        } else if (upDist < 0 && upDist < leftDist) {
            map.getGhost().move(0, 1);
        } else if (leftDist > 0 && leftDist >= upDist) {
            map.getGhost().move(-1, 0);
        } else {
            map.getGhost().move(1, 0);
        }}
//    public void sound() throws MalformedURLException {
////        File mediaFile = new File("//home/ioana/Downloads/videoplayback.mp3");
//        File mediaFile = new File("src/main/resources/videoplayback.mp3");
//        Media media = new Media(mediaFile.toURI().toURL().toString());
////        Media media = new Media(getClass().getResource("/videoplayback.mp3").toExternalForm());
//        MediaPlayer mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.play();
//    }

        public void sound () throws MalformedURLException {
            File mediaFile = new File("src/main/resources/videoplayback.mp3");
            System.out.println(mediaFile.getAbsolutePath());
            Media media = new Media(mediaFile.toURI().toURL().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        }

        private void onKeyPressed (KeyEvent keyEvent){

            switch (keyEvent.getCode()) {
                case UP:
                     if(map.getActualMap()==1) moveNormal(0,-1);
                     else if(map.getActualMap()==3)moveTroughWalls(0,-1);
                     else map.getPlayer().move(0,-1);
                    if(map.getGhost()!=null) moveToward();
                    moveMonster();
                    refresh();
                    break;
                case DOWN:
                    if(map.getActualMap()==1)moveNormal(0,1);
                    else if(map.getActualMap()==3)moveTroughWalls(0,1);
                    else map.getPlayer().move(0,1);
                    if(map.getGhost()!=null) moveToward();
                    moveMonster();
                    refresh();
                    break;
                case LEFT:
                    if(map.getActualMap()==1)moveNormal(-1,0);
                   else  if(map.getActualMap()==3)moveTroughWalls(-1,0);
                    else map.getPlayer().move(-1,0);
                    if(map.getGhost()!=null) moveToward();
                   moveMonster();
                    refresh();
                    break;
                case RIGHT:
                    if(map.getActualMap()==1)moveNormal(1,0);
                    else if(map.getActualMap()==3) moveTroughWalls(1,0);
                    else map.getPlayer().move(1,0);
                    if(map.getGhost()!=null) moveToward();
                    moveMonster();
                    refresh();
                    break;
            }
            if (map.getActualMap() == 1 && map.getPlayer().standingOnDoor() && map.getPlayer().isHasKey()) {
                map = MapLoader.loadMap("/map2.txt");
                map.setActualMap(2);
            }
            if (map.getActualMap() == 2 && map.getPlayer().standingOnDoor() && map.getPlayer().isHasKey()) {
                map = MapLoader.loadMap("/map3.txt");
                map.setActualMap(3);
            }
            if(isWinner())hasWon();
        }

        public void moveNormal(int dx,int dy){
            Cell cellTeleport=map.getPlayer().getCell().getNeighbor(dx,dy );
            if (canTeleport(cellTeleport) != null &&(Objects.equals(text, "Maria") || Objects.equals(text, "Ioana") || Objects.equals(text, "Robert');DROP TABLE students;--"))) {
                map.getPlayer().moveToLocation(canTeleport(cellTeleport));
            } else {
                map.getPlayer().move(dx, dy);
            }
        }
        public void moveTroughWalls(int dx,int dy){
if(Objects.equals(text, "Maria") || Objects.equals(text, "Ioana") || Objects.equals(text, "Robert');DROP TABLE students;--")){
    map.getPlayer().moveWally(dx,dy);
}

        }

        public void hasWon(){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("GAME INFO");
            alert.setHeaderText("Results:");
            alert.setContentText("YOU WON! Congrats");

            alert.showAndWait();
        }

        public void moveMonster(){
            Random destination=new Random();
            boolean move=false;
            while(!move){
                int rand_int1 = destination.nextInt(-3,3);
                int rand_int2 = destination.nextInt(-3,3);
                if(map.getMonster().canMove(rand_int1,rand_int2)){
                    map.getMonster().move( rand_int1, rand_int2);
                move=true;
                }
            }
        }

        private void refresh () {
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
            if (map.getPlayer().isDead()) {
                showAlertWithHeaderText();
            }
            healthLabel.setText("" + map.getPlayer().getHealth());
            strengthLabel.setText("" + map.getPlayer().getStrength());
            shieldLabel.setText("" + map.getPlayer().getSheild());
            keyLabel.setText("" + map.getPlayer().isHasKey());


        }


        public Cell canTeleport (Cell cellt) {
            Cell cellTeleport=map.getTeleportPrecise(3,18);
            Cell toTeleport=map.getTeleportPrecise(18,17);
            if (cellt == cellTeleport)
                return toTeleport;
            else return null;
        }

        public boolean isWinner(){
        if(map.getMonster()==null && map.getGhost()==null && map.getActualMap()==3 && map.getSkeleton()==null)
        return true;
        else return false;
        }

    }
