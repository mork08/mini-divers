package my_project.model.Tilesystem;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.control.Mouse;

public class Map extends GraphicalObject {
    private int mapSize; //in Chunks
    private Chunk[][] chunks;

    public Map(int mapSize){
        this.mapSize = mapSize;
        chunks = new Chunk[mapSize][mapSize];
        for (Chunk[] ca : chunks) {
            for (int i = 0; i < ca.length; i++) {
                ca[i] = new Chunk();
            }
        }
        for (int x = 0; x < mapSize*Chunk.CHUNK_SIZE; x++) {
            for (int y = 0; y < mapSize*Chunk.CHUNK_SIZE; y++) {
                createTile(x,y);
            }
        }
    }
    public Tile getTileByPosition(double x, double y){
    return getTile((int) x/Tile.TILE_SIZE, (int) y/Tile.TILE_SIZE);
    }
    public Tile getTile(int x, int y){
        if (getChunkByTile(x, y) == null) return null;
        return getChunkByTile(x, y).getTile(x, y);
    }
    public Chunk getChunkByTile(int x, int y){

        int chunkX = (int)x / Chunk.CHUNK_SIZE;
        int chunkY = (int)y / Chunk.CHUNK_SIZE;
        /*
        System.out.println("");
        System.out.println("    > Chunk x: " + chunkX);
        System.out.println("    > Chunk y: " + chunkY);
         */
        if (chunkX < chunks.length && chunkX >= 0 && chunkY < chunks[0].length && chunkY >= 0){
            return chunks[chunkX][chunkY];
        } else {
            //System.err.println("    > Chunk x|y: " + chunkX + "|" + chunkY + " out of bounds for chunk of length " + chunks.length + "|" + chunks[0].length);
            return null;
        }

    }
    public Chunk getChunkByPosition(double x, double y){

        int chunkX = (int)x / Chunk.CHUNK_SIZE;
        int chunkY = (int)y / Chunk.CHUNK_SIZE;
        System.out.println("");
        System.out.println("    > Chunk x: " + chunkX);
        System.out.println("    > Chunk y: " + chunkY);
        if (chunkX < chunks.length && chunkX >= 0 && chunkY < chunks[0].length && chunkY >= 0){
            return chunks[chunkX][chunkY];
        } else {
            System.err.println("    > Chunk x|y: " + chunkX + "|" + chunkY + " out of bounds for chunk of length " + chunks.length + "|" + chunks[0].length);
            return null;
        }

    }
    public void setTile(int x, int y, Tile tile){
        if (getChunkByTile(x, y) != null){
            getChunkByTile(x, y).setTile(x%Chunk.CHUNK_SIZE, y%Chunk.CHUNK_SIZE, tile);
            tile.setMap(this);
        }

    }
    @Override
    public void draw(DrawTool drawTool){
        getTileByPosition(Mouse.getTranslatedPosition().x, Mouse.getTranslatedPosition().y).highlight(true);
        //drawTool.setTranslate(Mouse.getPosition().x, Mouse.getPosition().y);
        for (Chunk[] cx : chunks){
            for (Chunk c : cx){
                c.draw(drawTool);
            }
        }
    }
    public void createTile(int x, int y){
        /*
        System.out.println("-----Creating new Tile-------");
        System.out.println("  > x: " + x);
        System.out.println("  > y: " + y);
        */
        setTile(x, y, new Tile(x, y));
    }
}
