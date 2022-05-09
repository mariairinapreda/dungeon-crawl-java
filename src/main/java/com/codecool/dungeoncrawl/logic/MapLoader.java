package com.codecool.dungeoncrawl.logic;

<<<<<<< HEAD
=======
import com.codecool.dungeoncrawl.logic.actors.Ghost;
import com.codecool.dungeoncrawl.logic.actors.Monster;
>>>>>>> repo-old/development
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
<<<<<<< HEAD
    public static GameMap loadMap() {
        InputStream is = MapLoader.class.getResourceAsStream("/map.txt");
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
=======
    public static GameMap loadMap(String name) {
        InputStream is = MapLoader.class.getResourceAsStream(name);
        Scanner scanner = new Scanner(is);
        int worldX = scanner.nextInt();
        int worldY = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(worldX, worldY, CellType.EMPTY);
        for (int y = 0; y < worldY; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < worldX; x++) {
>>>>>>> repo-old/development
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
<<<<<<< HEAD
=======
                        case 'W':
                            cell.setType(CellType.KEY);
                            break;
                        case 'M':
                            cell.setType(CellType.MACE);
                            break;
                        case 'K':
                            cell.setType(CellType.SHIELD);
                            break;
                        case 'T':
                            cell.setType(CellType.TELEPORT);
                            map.setTeleport(new Teleport());
                            break;
                        case 'P':
                            cell.setType(CellType.OCEAN);
                            break;
                        case 'E':
                            cell.setType(CellType.MONSTER);
                            map.setMonster(new Monster(cell));
                            break;
                        case 'G':
                            cell.setType(CellType.GHOST);
                            map.setGhost(new Ghost(cell));
                        case 'O':
                            cell.setType(CellType.HEALTH);
                            break;
>>>>>>> repo-old/development
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
<<<<<<< HEAD
                        case 's':
                            cell.setType(CellType.FLOOR);
=======
                        case 'D':
                            cell.setType(CellType.DOOR);
                            break;
                        case 's':
                            cell.setType(CellType.SKELETON);
>>>>>>> repo-old/development
                            new Skeleton(cell);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
<<<<<<< HEAD
                            map.setPlayer(new Player(cell, "Player"));
=======
                            map.setPlayer(new Player(cell));
>>>>>>> repo-old/development
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
