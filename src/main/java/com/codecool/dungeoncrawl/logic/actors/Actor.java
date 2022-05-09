package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

<<<<<<< HEAD
public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 10;
=======
import java.util.Objects;

public abstract class Actor implements Drawable {
    protected Cell cell;
    private int strength;
    private int health;
>>>>>>> repo-old/development

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
<<<<<<< HEAD
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
    }

    public int getHealth() {
        return health;
    }
=======

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

>>>>>>> repo-old/development

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
