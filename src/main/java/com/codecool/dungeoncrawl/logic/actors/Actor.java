package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

import java.util.Objects;

public abstract class Actor implements Drawable {
    protected Cell cell;
    private int shield;
    private int strength;


    private int health;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);

    }

    abstract void move(int dx, int dy);


    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setHealth(int health) {
        this.health = health;
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

    public void setShield(int shield) {
        this.shield = shield;
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
