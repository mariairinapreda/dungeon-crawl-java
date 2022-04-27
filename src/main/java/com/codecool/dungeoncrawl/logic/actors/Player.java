package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

import java.util.Objects;

public class Player extends Actor {

    private int health = 20;
    private int strength = 5;
    private boolean hasKey;



    public Player(Cell cell) {
        super(cell);
    }

    
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
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    public void move(int dx, int dy) {
            Cell nextCell = cell.getNeighbor(dx, dy);
            if(!Objects.equals(nextCell.getTileName(), "wall") &&
                    !Objects.equals(nextCell.getTileName(), "empty") &&
                    nextCell.getActor() == null ){

                if(Objects.equals(cell.getNeighbor(dx, dy).getTileName(), "shield")){
                    System.out.println("here is the shield");
                }
                if(Objects.equals(cell.getNeighbor(dx, dy).getTileName(), "health")){
                    System.out.println("here is the health potion");
                }
                if(Objects.equals(cell.getNeighbor(dx, dy).getTileName(), "")){
                    System.out.println("here is the health potion");
                }
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;}
            else if(nextCell.getActor() != null && Objects.equals(nextCell.getActor().getTileName(), "skeleton")){
                attack(nextCell);
            }
            else if(nextCell.getActor() != null && Objects.equals(nextCell.getActor().getTileName(), "monster")){
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
    public boolean isDead(){
        if(health>0)return false;
        else return true;
    }
}
