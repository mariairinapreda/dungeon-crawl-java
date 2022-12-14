package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.actors.Player;

import java.util.List;

public class PlayerModel extends BaseModel {
    private String playerName;
    private int hp;
    private int strength;
    private int x;
    private int y;
    private boolean key;
    private List<String> gameNames;


    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public boolean isKey() {
        return key;
    }

    public void setKey(boolean key) {
        this.key = key;
    }


    public PlayerModel(String playerName, int hp, int x, int y, int strength, boolean key) {
        this.playerName = playerName;
        this.hp = hp;
        this.x = x;
        this.y = y;
        this.strength = strength;
        this.key = key;

    }

    public PlayerModel(Player player) {
        this.playerName = player.getName();
        this.hp = player.getHealth();
        this.x = player.getX();
        this.y = player.getY();
        this.strength = player.getStrength();
        this.key = player.isHasKey();
    }


    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
