package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.GameState;

import java.util.List;

public interface GameStateDao {
    void add(GameState state, int playerId);
    void update(GameState state, int id, int playerId);
    GameState get(int id);
    GameState get(String name);
    List<GameState> getAll();
    int getPlayerId(int game_id);
}
