package com.codecool.dungeoncrawl.logic;

public class Door implements Drawable {
    private Cell cell;
    private boolean isOpen = false;

    public Door(Cell cell) {
        this.cell = cell;
        cell.setDoor(this);
    }


    @Override
    public String getTileName() {
        if (isOpen) return "openDoor";
        else return "door";
    }

    public Cell getCell() {
        return cell;
    }

    public void setOpen() {
        isOpen = true;
    }
}
