package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;

public class Cell implements Drawable {
    private CellType type;
    private Actor actor;
    private GameMap gameMap;
<<<<<<< HEAD
    private int x, y;

    Cell(GameMap gameMap, int x, int y, CellType type) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
=======
    private int worldX, worldY;
    private Door door;

    Cell(GameMap gameMap, int worldX, int worldY, CellType type) {
        this.gameMap = gameMap;
        this.worldX = worldX;
        this.worldY = worldY;
>>>>>>> repo-old/development
        this.type = type;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Actor getActor() {
        return actor;
    }

    public Cell getNeighbor(int dx, int dy) {
<<<<<<< HEAD
        return gameMap.getCell(x + dx, y + dy);
=======
        return gameMap.getCell(worldX + dx, worldY + dy);
>>>>>>> repo-old/development
    }

    @Override
    public String getTileName() {
        return type.getTileName();
    }

    public int getX() {
<<<<<<< HEAD
        return x;
    }

    public int getY() {
        return y;
=======
        return worldX;
    }

    public int getY() {
        return worldY;
    }

    public Door getDoor() {
        return door;
    }

    public void setDoor(Door door) {
        this.door = door;
>>>>>>> repo-old/development
    }
}
