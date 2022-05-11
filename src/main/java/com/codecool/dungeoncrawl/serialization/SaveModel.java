package com.codecool.dungeoncrawl.serialization;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.GameState;
import com.google.gson.annotations.Expose;

public class SaveModel {
    @Expose
    private Player player;
    @Expose
    private GameMap map;

    public SaveModel(Player player, GameMap map) {
        this.player = player;
        this.map = map;
    }

    public Player getPlayer() {
        return player;
    }

    public GameMap getGameState() {
        return map;
    }
}
