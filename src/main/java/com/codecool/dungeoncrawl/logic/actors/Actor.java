package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

import java.util.Objects;

public abstract class Actor implements Drawable {
    protected Cell cell;
    private int strength;
    private int health;
    private Boolean isDead=false;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);

    }

    public Boolean getDead() {
        return isDead;
    }

    public void setDead(Boolean dead) {
        isDead = dead;
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
