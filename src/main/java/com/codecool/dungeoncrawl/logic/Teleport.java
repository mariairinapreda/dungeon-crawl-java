package com.codecool.dungeoncrawl.logic;

public class Teleport implements Drawable {
    private Cell cell;

    public void setCell(Cell cell) {
        this.cell = cell;
    }


    @Override
    public String getTileName() {
        return "teleport";
    }
}
