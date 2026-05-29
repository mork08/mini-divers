package my_project.model.Tilesystem;

import KAGO_framework.view.DrawTool;

public class Chunk {
    public static final int CHUNK_SIZE = 16;
    private Tile[][] tiles;
    private boolean loaded = true;
    public Chunk(){
        tiles = new Tile[CHUNK_SIZE][CHUNK_SIZE];
    }
    public Tile getTile(int x, int y){
        if (x < 0 || y < 0) return null;
        try {
            return tiles[x%Chunk.CHUNK_SIZE][y%Chunk.CHUNK_SIZE];
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
            return null;
        }
    }
    public void setTile(int x, int y, Tile tile){
        try {
            tiles[x][y] = tile;
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();

        }
    }
    public void draw(DrawTool dt){
        for (Tile[] tx : tiles){
            for(Tile t : tx){
                t.draw(dt);
            }
        }
    }
}
