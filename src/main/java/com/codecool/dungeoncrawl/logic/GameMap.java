package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.*;
import com.codecool.dungeoncrawl.logic.actors.Monster;

import java.util.Objects;

public class GameMap {
    private int width;
    private int height;


    private int actualMap = 1;
    private Cell[][] cells;

    private Player player;
    private Monster monster;
    private Teleport teleport;
    private Skeleton skeleton;

    public Skeleton getSkeleton() {
        return skeleton;
    }

    public void setSkeleton(Skeleton skeleton) {
        this.skeleton = skeleton;
    }




    public Teleport getTeleport() {
        return teleport;
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

    private Ghost ghost;

    public void setMonster(Monster monster) {
        this.monster = monster;
    }




    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public int getActualMap() {
        return actualMap;
    }

    public void setActualMap(int actualMap) {
        this.actualMap = actualMap;
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
    public Monster getMonster(){
        return monster;
    }



    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
