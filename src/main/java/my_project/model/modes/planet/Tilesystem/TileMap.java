package my_project.model.modes.planet.Tilesystem;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.model.abitur.datenstrukturen.Queue;
import KAGO_framework.view.DrawTool;
import beckerStructures.BeckerMap;
import com.sun.javafx.geom.Vec2d;
import my_project.control.Mouse;
import my_project.model.modes.planet.entity.EntityPlayer;
import my_project.model.modes.planet.entity.enemy.EntityMinirobot;

import java.awt.image.BufferedImage;

public class TileMap extends GraphicalObject {
    private int mapSize; //in Chunks
    private Chunk[][] chunks;
    private Queue<Vec2d> renderPositions;
    private int renderDistance = 1;
    private static EntityPlayer player;

    public TileMap(int mapSize){
        renderPositions = new Queue<>();
        this.mapSize = mapSize;
        chunks = new Chunk[mapSize][mapSize];
        for (Chunk[] ca : chunks) {
            for (int i = 0; i < ca.length; i++) {
                ca[i] = new Chunk();
            }
        }
        for (int x = 0; x < mapSize*Chunk.CHUNK_SIZE; x++) {
            for (int y = 0; y < mapSize*Chunk.CHUNK_SIZE; y++) {
                createTile(x,y,1);
            }
        }
        setMap(SurfaceMapContainer.getMap(0));
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

        return getChunkByTile((int) x/Tile.TILE_SIZE, (int) y/Tile.TILE_SIZE);

    }
    public void setTile(int x, int y, Tile tile){
        if (getChunkByTile(x, y) != null){
            getChunkByTile(x, y).setTile(x%Chunk.CHUNK_SIZE, y%Chunk.CHUNK_SIZE, tile);
            tile.setMap(this);
        }

    }
    @Override
    public void draw(DrawTool drawTool){
        // getTileByPosition(Mouse.getTranslatedPosition().x, Mouse.getTranslatedPosition().y).highlight(true);

        addRenderPosition(Mouse.getTranslatedPosition().x, Mouse.getTranslatedPosition().y);
        for (int i = -renderDistance; i <= renderDistance; i++) {
            for (int j = -renderDistance; j <= renderDistance; j++) {
                addRenderPosition(Mouse.getTranslatedPosition().x + i * Tile.TILE_SIZE * Chunk.CHUNK_SIZE, Mouse.getTranslatedPosition().y + j * Tile.TILE_SIZE * Chunk.CHUNK_SIZE);
            }
        }
        //drawTool.setTranslate(Mouse.getPosition().x, Mouse.getPosition().y);
        /*for (Chunk[] cx : chunks){
            for (Chunk c : cx){
                c.draw(drawTool);
            }
        }
         */

        while (!renderPositions.isEmpty()) {
            Vec2d pos = renderPositions.front();
            if (getChunkByPosition(pos.x, pos.y) != null) getChunkByPosition(pos.x, pos.y).draw(drawTool);
            renderPositions.dequeue();
        }
    }

    public void createTile(int x, int y, int height){
        /*
        System.out.println("-----Creating new Tile-------");
        System.out.println("  > x: " + x);
        System.out.println("  > y: " + y);
        */
        Tile t = new Tile(x, y);
        t.setLevelHeight(height);
        setTile(x, y, t);
    }
    public void addRenderPosition(double x, double y){
        renderPositions.enqueue(new Vec2d(x, y));
    }

    private void setMap(BufferedImage map){
        System.out.println(map == null ? "null" : "not null apearrently");
        //if (map == null) return;
        int width = map.getWidth();
        int height = map.getHeight();

        for (int row = 0; row < height; row++) {
            System.out.println("");
            for (int col = 0; col < width; col++) {
                switch (Integer.toBinaryString(map.getRGB(col, row))) {
                    case "11111111000000000000000000000000": //black
                        // Player spawn point
                        //createTile(col, row, 0);
                        getTile(col, row).setLevelHeight(0);
                        player = new EntityPlayer("player", col*Tile.TILE_SIZE, row*Tile.TILE_SIZE, 32, 32);

                        // new EntityMinirobot("test-near-player", col*Tile.TILE_SIZE, row*Tile.TILE_SIZE, 32, 32);
                        break;
                    case "11111111111111110000000011111111": //magenta
                        //createTile(col, row, -1);
                        getTile(col, row).setLevelHeight(-1);
                        System.out.print(" ");
                        break;
                    case "11111111111111110000000000000000": //red
                        //createTile(col, row, 0);
                        getTile(col, row).setLevelHeight(0);
                        System.out.print(":");
                        if (Math.random() < 0.01) {
                            System.out.print("Summoned robot");
                            new EntityMinirobot("minirob"+col+" "+row, col*Tile.TILE_SIZE, row*Tile.TILE_SIZE, 32, 32);
                        }
                        break;
                    case "11111111111111111111111100000000": //yellow
                        //createTile(col, row, 1);
                        getTile(col, row).setLevelHeight(1);
                        System.out.print("#");
                        break;
                    case "11111111000000001111111100000000": //green
                        //objective spawn point
                        createTile(col, row, 0);
                        getTile(col, row).setLevelHeight(0);
                        System.out.print("Y");
                        //TODO SPAWN OBJECTIVE
                        break;
                    case "11111111000000001111111111111111": //cyan
                        break;
                    case "11111111000000000000000011111111": //blue

                        break;
                    case "11111111111111111111111111111111": //white
                        //createTile(col, row, 0);
                        getTile(col, row).setLevelHeight(0);
                        System.out.print("!");
                        //TODO SPAWN EXTRACT ON WHITE SQUARE
                        break;
                }
            }
        }
    }
    public static EntityPlayer getPlayer(){
        return player;
    }
}
