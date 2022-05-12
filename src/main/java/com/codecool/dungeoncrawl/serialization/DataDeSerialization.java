package com.codecool.dungeoncrawl.serialization;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.sql.Date;


public class DataDeSerialization implements JsonDeserializer<GameState> {


    @Override
    public GameState deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        JsonElement jsonPlayerName= jsonObject.get("player_name");
        JsonElement jsonPlayerHealth = jsonObject.get("player_health");
        JsonElement jsonPlayerStrength = jsonObject.get("player_strength");
        JsonElement jsonPlayerX = jsonObject.get("player_x_position");
        JsonElement jsonPlayerY = jsonObject.get("player_y_position");
        JsonElement jsonPlayerHasKey = jsonObject.get("player_has_key");
        JsonElement jsonGameMap = jsonObject.get("map_address");
        PlayerModel playerModel = new PlayerModel(jsonPlayerName.toString(), jsonPlayerHealth.getAsInt(), jsonPlayerX.getAsInt(),
                jsonPlayerY.getAsInt(), jsonPlayerStrength.getAsInt(), jsonPlayerHasKey.getAsBoolean());
        return new GameState(jsonGameMap.toString(), new Date(System.currentTimeMillis()), playerModel);
    }
}
