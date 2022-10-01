package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

import java.util.Objects;

public class Ghost extends Actor {
    private int health = 15;
    private int strength = 5;

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


    public Ghost(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "ghost";
    }


    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.getActor() != null) {
            if (!Objects.equals(nextCell.getActor().getTileName(), "player")) {
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;
            } else {
                cell.getActor().setHealth(cell.getActor().getHealth() - nextCell.getActor().getStrength());
                nextCell.getActor().setHealth(nextCell.getActor().getHealth() - cell.getActor().getStrength());
                if (cell.getActor().getHealth() <= 0) {
                    cell.setType(CellType.FLOOR);
                    cell.setActor(null);
                }
            }

        } else {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }


}

