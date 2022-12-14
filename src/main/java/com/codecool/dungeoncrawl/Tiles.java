package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();

    public static class Tile {
        public final int x, y, w, h;

        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(27, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("mace", new Tile(10, 30));
        tileMap.put("shield", new Tile(5, 25));
        tileMap.put("health", new Tile(18, 28));
        tileMap.put("key", new Tile(17, 23));
        tileMap.put("monster", new Tile(29, 2));
        tileMap.put("ghost", new Tile(22, 23));
        tileMap.put("teleport", new Tile(30, 28));
        tileMap.put("door", new Tile(29, 3));
        tileMap.put("openDoor", new Tile(4, 30));
        tileMap.put("ocean", new Tile(8, 5));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int screenX, int screenY) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                screenX * TILE_WIDTH, screenY * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
