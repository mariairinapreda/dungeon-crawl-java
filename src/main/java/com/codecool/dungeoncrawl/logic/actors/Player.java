package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

import java.util.Objects;

public class Player extends Actor {
    private int strength;
    private boolean hasKey;
    private int mace;
    private Cell cell;

    public Player(Cell cell) {
        super(cell);
    }

//    public void setCell(Cell newCell) {
//        cell = newCell;
//
//    }
    
    public String getTileName() {
        return "player";
    }


    public boolean isHasKey() {
        return hasKey;
    }

    public void setHasKey(boolean hasKey) {
        this.hasKey = hasKey;
    }
    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
    @Override
    public void move(int dx, int dy) {
            Cell nextCell = cell.getNeighbor(dx, dy);
            if(!Objects.equals(nextCell.getTileName(), "wall") &&
                    !Objects.equals(nextCell.getTileName(), "empty") &&
                    nextCell.getActor() == null ){
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;}
        //        if(Objects.equals(cell.getNeighbor(dx, dy).getType().getTileName(), "key")){
//            System.out.println("here is the key");
//        }
//        if(Objects.equals(cell.getNeighbor(dx, dy).getType().getTileName(), "shield")){
//            System.out.println("here is the shield");
//        }
//        if(Objects.equals(cell.getNeighbor(dx, dy).getType().getTileName(), "health")){
//            System.out.println("here is the health potion");
//        }
    }
}
