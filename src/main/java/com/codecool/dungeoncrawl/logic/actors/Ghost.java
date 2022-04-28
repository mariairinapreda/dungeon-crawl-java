package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

import java.util.Objects;

public class Ghost extends Actor{
    private int health;
    private int strength;

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
        cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        if(nextCell.getActor() != null && Objects.equals(nextCell.getActor().getTileName(), "player")){
            attack(nextCell);
        }
    }


    private void attack(Cell nextCell) {
//        if(cell.getActor().getHealth()>=nextCell.getActor().getStrength()){
        cell.getActor().setHealth(cell.getActor().getHealth()-nextCell.getActor().getStrength());
        nextCell.getActor().setHealth(nextCell.getActor().getHealth()-cell.getActor().getStrength());
        if(nextCell.getActor().getHealth() == 0){
            nextCell.setActor(null);
            nextCell.setType(CellType.FLOOR);
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
//            }
        }
    }
}
