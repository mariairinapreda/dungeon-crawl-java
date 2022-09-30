package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.*;
import com.codecool.dungeoncrawl.logic.actors.Monster;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class GameMap {
    private int worldX;
    private int worldY;




    private int actualMap = 1;
    private Cell[][] cells;

    private Player player;
    private List<Monster> monsters=new ArrayList<>();
    private Teleport teleport;
    private List<Skeleton> skeletons=new ArrayList<>();
    public Ghost ghost;

    public List<Skeleton> getSkeleton() {
        return skeletons;
    }


    public void addMonsters(Monster monster){
        monsters.add(monster);
    }
    public void killMonsters(){
        monsters.removeIf(this::checkIfMonstersAreDead);
    }

    public Optional<Monster> getMonsterByPosition(int x, int y){
        return monsters.stream().filter(monster -> monster.getX()==x && monster.getY()==y).findFirst();
    }

    public Boolean checkIfMonstersAreDead(Monster monster){
        return monster.getDead();
    }
    public void addSkeletons(Skeleton skeleton){
        skeletons.add(skeleton);
    }
    public void killSkeletons(){
        skeletons.removeIf(this::checkIfSkeletonsAreDead);
    }
    public Optional<Skeleton> getSkeletonByPosition(int x, int y){
        return skeletons.stream().filter(skeleton -> skeleton.getX()==x && skeleton.getY()==y).findFirst();
    }

    public void makeTheMonstersMove(){
        for (Monster monster : monsters) {
            monster.move(actualMap);
        }
    }
    public Boolean checkIfSkeletonsAreDead(Skeleton skeleton){
        return skeleton.getDead();
    }

    public Cell getTeleportPrecise(int x,int y) {
        if(Objects.equals(getCell(x, y).getTileName(), "teleport"))
        return getCell(x,y);
        else return null;
    }

    public void setTeleport(Teleport teleport) {
        this.teleport = teleport;
    }


    public Ghost getGhost() {
        return ghost;
    }

    public void setGhost(Ghost ghost) {
        this.ghost = ghost;
    }



    public void setMonster(List<Monster> monsters) {
        this.monsters = monsters;
    }




    public GameMap(int width, int height, CellType defaultCellType) {
        this.worldX = width;
        this.worldY = height;

        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public int getActualMap() {
        return actualMap;
    }

    public void setActualMap(int actualMap) {
        this.actualMap = actualMap;
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
    public List<Monster> getMonster(){
        return monsters;
    }



    public int getWidth() {
        return worldX;
    }

    public int getHeight() {
        return worldY;
    }
}
