package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

import java.util.Objects;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int shield;
    private int strength;
    private int health = 10;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if(!Objects.equals(nextCell.getTileName(), "wall")){
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;}
    }

    public int getHealth() {
        return health;
    }
    public int getStrength() {
        return strength;
    }
    public int getSheild() {
        return shield;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
}
