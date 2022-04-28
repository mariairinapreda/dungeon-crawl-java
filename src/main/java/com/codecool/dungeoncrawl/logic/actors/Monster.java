package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

import java.util.Objects;




public class Monster extends Actor{

    private int health=15;
    private int strength=5;

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
        else if(nextCell.getActor() != null && Objects.equals(nextCell.getActor().getTileName(), "player")){
            attack(nextCell);
        }
       else{
           move(-dx,-dy);
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

