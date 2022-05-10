package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.dao.GameStateDao;
import com.codecool.dungeoncrawl.dao.GameStateDaoJdbc;
import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.dao.PlayerDao;
import com.codecool.dungeoncrawl.logic.*;


import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.GameState;
import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Window;
import javafx.util.Duration;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Objects;

import static javafx.scene.input.KeyCode.CONTROL;
import static javafx.scene.input.KeyCode.S;


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
    Label keyLabel = new Label();
    Button button=new Button("Accept");
    Label userName = new Label("Cheat:");
    TextField userTextField = new TextField();
    Button cheatCode=new Button("OK");
    MediaPlayer mediaPlayer;
    MenuBar menuBar = new MenuBar();
    VBox vBox = new VBox(menuBar);
    Menu menuFile = new Menu("Load :)");

    MenuItem menuItem =new MenuItem("First Loading Game");


    private String text;

GameDatabaseManager gameDatabaseManager=setDataBase();
    GameStateDao gameStateDao=gameDatabaseManager.getGameStateDao();
    int numberOfStates=gameStateDao.getAll().size();

    public void setText(String text) {
        this.text = text;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        sound();

        setDataBase();

        GridPane ui = new GridPane();
        ui.setOnMousePressed(e -> ui.requestFocus());
        ui.setPrefWidth(270);
        ui.setPadding(new Insets(10));
        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);
        ui.add(new Label("Strength: "), 0, 2);
        ui.add(strengthLabel, 1, 2);
        ui.add(new Label("Key: "), 0, 8);
        ui.add(keyLabel, 1, 8);
        ui.add(button, 0,9);
        ui.add(userName, 0, 12);
        ui.add(userTextField, 1, 12);
        ui.add(cheatCode,0,14);
        userTextField.requestFocus();
        cheatCode.setFocusTraversable(false);
        cheatCode.setOnAction(actionEvent -> {
            setText(userTextField.getText());
            userTextField.setDisable(true);
        });

        button.setFocusTraversable(false);
        button.setOnAction(actionEvent -> {

            int x =map.getPlayer().getCell().getX();
            int y = map.getPlayer().getCell().getY();
            if(map.getPlayer().getCell() ==
                    ((Objects.equals(map.getCell(x, y).getTileName(), "key") ? map.getCell(x,y): ""))){
                map.getCell(x, y).setType(CellType.FLOOR);
                map.getPlayer().setHasKey(true);
            }
            if(map.getPlayer().getCell() ==
                    ((Objects.equals(map.getCell(x, y).getTileName(), "shield") ? map.getCell(x,y): ""))){
                map.getCell(x, y).setType(CellType.FLOOR);
                int oneShield = 1;
                map.getPlayer().setStrength(map.getPlayer().getStrength()+oneShield);
            }
            if(map.getPlayer().getCell() ==
                    ((Objects.equals(map.getCell(x, y).getTileName(), "health") ? map.getCell(x,y): ""))){
                map.getCell(x, y).setType(CellType.FLOOR);
                int potion = 10;
                map.getPlayer().setHealth(map.getPlayer().getHealth()+potion);
            }
            if(map.getPlayer().getCell() ==
                    ((Objects.equals(map.getCell(x, y).getTileName(), "mace") ? map.getCell(x,y): ""))){
                map.getCell(x, y).setType(CellType.FLOOR);
                int mace = 1;
                map.getPlayer().setStrength(map.getPlayer().getStrength()+mace);
            }

        });



        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(canvas);
        borderPane.setTop(vBox);
        borderPane.setRight(ui);
        menuFile.getItems().add(menuItem);
        menuBar.getMenus().add(menuFile);


        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
//        primaryStage.initModality(Modality.APPLICATION_MODAL);
//        primaryStage.setTitle("Swing in JavaFX");
//        primaryStage.setScene(new Scene(borderPane, 250, 150));
//        primaryStage.show();

        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);


        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
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
        } else if (leftDist > 0) {
            map.getGhost().move(-1, 0);
        } else {
            map.getGhost().move(1, 0);
        }}

public GameDatabaseManager setDataBase(){
        try {
            GameDatabaseManager gameDatabaseManager=new GameDatabaseManager();
            gameDatabaseManager.setup();
            return gameDatabaseManager;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
}
    //TODO: loop for never-ending music
        public void sound ()  {
            File mediaFile = new File("src/main/resources/music.mp3");
            Media media = null;
            try {
                media = new Media(mediaFile.toURI().toURL().toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            assert media != null;
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        }

        private void onKeyPressed (KeyEvent keyEvent){
            System.out.println(keyEvent.getCode());
            switch (keyEvent.getCode()) {
                case UP:
                     if(map.getActualMap()==1) moveNormal(0,-1);
                     else if(map.getActualMap()==3)moveTroughWalls(0,-1);
                     else map.getPlayer().move(0,-1);
                    if(map.getGhost() != null && map.getGhost().getHealth()>0) moveToward();
                    else map.setGhost(null);
                    if(map.getMonster() != null && map.getMonster().getHealth()>0) map.getMonster().move();
                    else map.setMonster(null);
                    refresh();
                    if (mediaPlayer.isMute()) {
                        sound();
                    }
                    break;
                case DOWN:
                    if(map.getActualMap()==1)moveNormal(0,1);
                    else if(map.getActualMap()==3)moveTroughWalls(0,1);
                    else map.getPlayer().move(0,1);
                    if(map.getGhost() != null && map.getGhost().getHealth()>0) moveToward();
                    else map.setGhost(null);
                    if(map.getMonster() != null && map.getMonster().getHealth()>0) map.getMonster().move();
                    if(map.getMonster() != null && map.getMonster().getHealth()>0) map.getMonster().move();
                    else map.setMonster(null);
                    refresh();
                    if (mediaPlayer.isMute()) {
                        sound();
                    }
                    break;
                case LEFT:
                    if(map.getActualMap()==1)moveNormal(-1,0);
                   else  if(map.getActualMap()==3)moveTroughWalls(-1,0);
                    else map.getPlayer().move(-1,0);
                    if(map.getGhost() != null && map.getGhost().getHealth()>0) moveToward();
                    else map.setGhost(null);
                    if(map.getMonster() != null && map.getMonster().getHealth()>0)map.getMonster().move();
                    else map.setMonster(null);
                    refresh();
                    if (mediaPlayer.isMute()) {
                        sound();
                    }
                    break;
                case RIGHT:
                    if(map.getActualMap()==1)moveNormal(1,0);
                    else if(map.getActualMap()==3) moveTroughWalls(1,0);
                    else map.getPlayer().move(1,0);
                    if(map.getGhost() != null && map.getGhost().getHealth()>0) moveToward();
                    else map.setGhost(null);
                    if(map.getMonster() != null && map.getMonster().getHealth()>0) map.getMonster().move();
                    else map.setMonster(null);
                    refresh();
                    if (mediaPlayer.isMute()) {
                        sound();
                    }
                    break;
                case CONTROL:
                    KeyCharacterCombination combination = new KeyCharacterCombination(String.valueOf(S), KeyCombination.CONTROL_ANY);
                    Stage dialog = new Stage();
//                    dialog.initOwner(Arrays.stream();
//                    dialog.initModality(Modality.ApplicationModal);
//                    dialog.showAndWait();
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



    private void refresh() {
        context.setFill(Color.BLACK);
        int screenX = (int) (map.getPlayer().getX() / 2 - 0.5);
        int screenY = (int) (map.getPlayer().getY() / 2 - 0.5);
        if(screenY >5){
            screenY = screenY - (screenY-5);
        }
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
        if (map.getPlayer().isDead()) {
            System.out.println("you lost");
            hasLost();
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
        strengthLabel.setText("" + map.getPlayer().getStrength());
        keyLabel.setText("" + map.getPlayer().isHasKey());

    }
    public void hasLost(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("GAME INFO");
        alert.setHeaderText("Results:");
        alert.setContentText("YOU LOST! Congrats");
        alert.showAndWait();
    }
        public Cell canTeleport (Cell cellt) {
            Cell cellTeleport=map.getTeleportPrecise(3,18);
            Cell toTeleport=map.getTeleportPrecise(18,17);
            if (cellt == cellTeleport)
                return toTeleport;
            else return null;
        }

        public boolean isWinner(){
            return map.getMonster() == null && map.getGhost() == null && map.getActualMap() == 3 && map.getSkeleton() == null;
        }





    }
