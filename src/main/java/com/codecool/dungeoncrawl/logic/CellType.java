package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    MACE("mace"),
    SHIELD("shield"),
    HEALTH("health"),
    KEY("key"),
    MONSTER("monster"),
    TELEPORT("teleport");


    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
