package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

import java.util.Objects;




public class Monster extends Actor {

    private int health = 15;
    private int strength = 5;

    @Override
    public Cell getCell() {
        return cell;
    }


    public void setCell(Cell cell) {
        this.cell = cell;
    }

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

    public Monster(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "monster";
    }

    public boolean canMove(int dx, int dy) {
        try{
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (!Objects.equals(nextCell.getTileName(), "wall") &&
                !Objects.equals(nextCell.getTileName(), "empty") &&
                nextCell.getActor() == null) {
            return true;
        } else return false;
    }catch (IndexOutOfBoundsException e){
            return false;
        }
    }



    public void move(int dx, int dy) {
        cell.setActor(null);
        cell.getNeighbor(dx, dy).setActor(this);
        cell = cell.getNeighbor(dx, dy);
    }


}




