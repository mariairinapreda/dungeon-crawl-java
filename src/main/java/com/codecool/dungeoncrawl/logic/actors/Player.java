package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Player extends Actor {
    private int shield;
    private int strength;
    public Player(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "player";
    }



    public boolean hasKey(){
     return false;
    }
}
