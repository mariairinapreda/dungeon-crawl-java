package com.codecool.dungeoncrawl.serialization;

import com.codecool.dungeoncrawl.model.GameState;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;


public class DataDeSerialization implements JsonDeserializer<GameState> {


    @Override
    public GameState deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return null;
    }
}
