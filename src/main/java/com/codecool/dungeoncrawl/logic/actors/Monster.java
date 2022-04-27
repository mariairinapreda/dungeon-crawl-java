package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

import java.util.Objects;


public class Monster extends Actor{

    private int health=15;
    private int strength=5;



    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health =health;

    }

    @Override
    public int getStrength() {
        return strength;
    }

    @Override
    public void setStrength(int strength) {
        this.strength = strength;
    }

    public Monster(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "monster";
    }


    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if(!Objects.equals(nextCell.getTileName(), "wall") &&
                !Objects.equals(nextCell.getTileName(), "empty") &&
                nextCell.getActor() == null ){
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;}




    }
}
