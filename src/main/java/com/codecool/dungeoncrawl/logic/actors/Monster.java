package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;


public class Monster extends Actor{
    private Cell cell;

    public Monster(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "monster";
    }

    @Override
    public void move(int dx, int dy) {




    }
}
