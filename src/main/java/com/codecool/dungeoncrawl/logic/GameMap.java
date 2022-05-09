package com.codecool.dungeoncrawl.logic;

<<<<<<< HEAD
import com.codecool.dungeoncrawl.logic.actors.Player;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;

    private Player player;

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
=======
import com.codecool.dungeoncrawl.logic.actors.*;
import com.codecool.dungeoncrawl.logic.actors.Monster;

import java.util.Objects;

public class GameMap {
    private int worldX;
    private int worldY;




    private int actualMap = 1;
    private Cell[][] cells;

    private Player player;
    private Monster monster;
    private Teleport teleport;
    private Skeleton skeleton;
    public Ghost ghost;

    public Skeleton getSkeleton() {
        return skeleton;
    }



    public Cell getTeleportPrecise(int x,int y) {
        if(Objects.equals(getCell(x, y).getTileName(), "teleport"))
        return getCell(x,y);
        else return null;
    }

    public void setTeleport(Teleport teleport) {
        this.teleport = teleport;
    }


    public Ghost getGhost() {
        return ghost;
    }

    public void setGhost(Ghost ghost) {
        this.ghost = ghost;
    }



    public void setMonster(Monster monster) {
        this.monster = monster;
    }




    public GameMap(int width, int height, CellType defaultCellType) {
        this.worldX = width;
        this.worldY = height;

>>>>>>> repo-old/development
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

<<<<<<< HEAD
=======
    public int getActualMap() {
        return actualMap;
    }

    public void setActualMap(int actualMap) {
        this.actualMap = actualMap;
    }

>>>>>>> repo-old/development
    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
<<<<<<< HEAD

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
=======
    public Monster getMonster(){
        return monster;
    }



    public int getWidth() {
        return worldX;
    }

    public int getHeight() {
        return worldY;
>>>>>>> repo-old/development
    }
}
