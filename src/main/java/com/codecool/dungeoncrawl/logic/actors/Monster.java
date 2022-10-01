package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

import java.util.Objects;
import java.util.Random;


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
    void move(int dx, int dy) {

    }

    @Override
    public String getTileName() {
        return "monster";
    }

    public boolean canMove(int dx, int dy) {
        try {
            Cell nextCell = cell.getNeighbor(dx, dy);
            if (!Objects.equals(nextCell.getTileName(), "wall") &&
                    !Objects.equals(nextCell.getTileName(), "empty") &&
                    !Objects.equals(nextCell.getTileName(), "door") &&
                    !Objects.equals(nextCell.getTileName(), "health") &&
                    !Objects.equals(nextCell.getTileName(), "shield") &&
                    !Objects.equals(nextCell.getTileName(), "mace") &&
                    !Objects.equals(nextCell.getTileName(), "teleport") &&
                    !Objects.equals(nextCell.getTileName(), "key") &&
                    nextCell.getActor() == null) {
                return true;
            } else return false;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }


    public void move(int actualMap) {
        Random destination = new Random();
        boolean move = false;
        while (!move) {
            int rand_int1 = destination.nextInt(-3, 3);
            int rand_int2 = destination.nextInt(-3, 3);
            if (canMove(rand_int1, rand_int2)) {
                if ((cell.getY() == 3 || cell.getY() == 4) && (actualMap == 3 || actualMap == 2)) {
                    move = true;
                    cell.setActor(null);
                    cell.setType(CellType.OCEAN);
                    cell.getNeighbor(rand_int1, rand_int2).setActor(this);
                    cell = cell.getNeighbor(rand_int1, rand_int2);
                } else {
                    move = true;
                    cell.setActor(null);
                    cell.setType(CellType.FLOOR);
                    cell.getNeighbor(rand_int1, rand_int2).setActor(this);
                    cell = cell.getNeighbor(rand_int1, rand_int2);
                }
            }
        }
    }

}




