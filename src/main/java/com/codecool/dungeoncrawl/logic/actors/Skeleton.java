package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Actor {
<<<<<<< HEAD
=======
    private int health=10;
    private int strength=2;


    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getStrength() {
        return strength;
    }

    @Override
    public void setStrength(int strength) {
        this.strength = strength;
    }


>>>>>>> repo-old/development
    public Skeleton(Cell cell) {
        super(cell);
    }

    @Override
<<<<<<< HEAD
    public String getTileName() {
        return "skeleton";
    }
=======
    public void move(int dx, int dy) {

    }

    @Override
    public String getTileName() {
        return "skeleton";
    }


>>>>>>> repo-old/development
}
