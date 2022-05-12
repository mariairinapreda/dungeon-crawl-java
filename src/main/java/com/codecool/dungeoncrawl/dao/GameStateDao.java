package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.GameState;

import java.util.List;

public interface GameStateDao {
    void add(GameState state, int player_id);
    void update(GameState state, int id, int player_id);
    GameState get(int id);
    List<GameState> getAll();
    GameState get(String name);
    int getPlayerId(int game_id);
}
