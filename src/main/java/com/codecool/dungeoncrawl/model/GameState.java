package com.codecool.dungeoncrawl.model;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class GameState extends BaseModel {
    private Date savedAt;
    private String currentMap;
    private String name;
    private List<String> discoveredMaps = new ArrayList<>();
    private PlayerModel player;
    private int actualMap;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDiscoveredMaps(List<String> discoveredMaps) {
        this.discoveredMaps = discoveredMaps;
    }

    public int getActualMap() {
        return actualMap;
    }

    public void setActualMap(int actualMap) {
        this.actualMap = actualMap;
    }

    public GameState(String currentMap, Date savedAt, PlayerModel player, String name, int actualMap) {
        this.currentMap = currentMap;
        this.savedAt = savedAt;
        this.player = player;
        this.name=name;
        this.actualMap=actualMap;
    }

    public Date getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(Date savedAt) {
        this.savedAt = savedAt;
    }

    public String getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(String currentMap) {
        this.currentMap = currentMap;
    }

    public List<String> getDiscoveredMaps() {
        return discoveredMaps;
    }

    public void addDiscoveredMap(String map) {
        this.discoveredMaps.add(map);
    }

    public PlayerModel getPlayer() {
        return player;
    }

    public void setPlayer(PlayerModel player) {
        this.player = player;
    }
}
