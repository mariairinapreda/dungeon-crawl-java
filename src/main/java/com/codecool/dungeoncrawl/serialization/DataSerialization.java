package com.codecool.dungeoncrawl.serialization;

import com.codecool.dungeoncrawl.model.GameState;
import com.google.gson.*;

import java.lang.reflect.Type;

public class DataSerialization implements JsonSerializer<GameState> {

    @Override
    public JsonElement serialize(GameState gameState, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("player_name", gameState.getPlayer().getPlayerName());
        jsonObject.addProperty("player_health", gameState.getPlayer().getHp());
        jsonObject.addProperty("player_strength", gameState.getPlayer().getStrength());
        jsonObject.addProperty("player_x_position", gameState.getPlayer().getX());
        jsonObject.addProperty("player_y_position", gameState.getPlayer().getY());
        jsonObject.addProperty("player_has_key", gameState.getPlayer().isKey());
        jsonObject.addProperty("map_address", gameState.getCurrentMap());
        jsonObject.addProperty("map_actualMap", gameState.getActualMap());
        jsonObject.addProperty("map_name", gameState.getName());
        return jsonObject;
    }
}


