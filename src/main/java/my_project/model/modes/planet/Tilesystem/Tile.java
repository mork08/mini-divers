package my_project.model.modes.planet.Tilesystem;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.control.Mouse;

public class Tile extends GraphicalObject {
    private TileMap tileMap;
    public static final int TILE_SIZE = 32;
    int levelHeight; // in range [-1, 1]
    int indexX;
    int indexY;
    boolean highlighted = false;
    VisualTileRepresentation visualTileRepresentation;
    public Tile(int x, int y){
        levelHeight = (int)(Math.random()*3)-1;
        width = TILE_SIZE;
        height = TILE_SIZE;
        setPosition(x, y);
        visualTileRepresentation = new VisualTileRepresentation(this);
    }
    public void setPosition(int x, int y){
        indexX = x;
        indexY = y;
        this.x = indexX *  TILE_SIZE;
        this.y = indexY *  TILE_SIZE;
    }

    public void setMap(TileMap tileMap){
        this.tileMap = tileMap;
    }

    @Override
    public void draw(DrawTool dt){
        adjustHeight();
        update(0); //TODO man sieht hier direkt das Problem, ne?;
        visualTileRepresentation.drawAt(dt, x, y);
        highlighted = false;
    }
    @Override
    public void update(double dt) {
        if(highlighted){
            if(Mouse.isDown(0)){
                levelHeight = 0;
            }
            if(Mouse.isDown(1)){
                levelHeight = -1;
            }
        }
    }
    public void highlight(boolean highlighted){
        this.highlighted = highlighted;
    };

    public boolean isHighlighted() {
        return highlighted;
    }

    public Tile getRelative(int rx, int ry){
        if (tileMap != null){
            if(tileMap.getTile(indexX, indexY) == this){
                return  tileMap.getTile(indexX + rx, indexY + ry);
            }
        }
        return this; //TODO figure out good way to give Tile acces to TileMap
    }
    public Tile[] getRelatives(){
        return new Tile[]{
            getRelative(0,-1),
            getRelative(1,-1),
            getRelative(1,0),
            getRelative(1,1),
            getRelative(0,1),
            getRelative(-1,1),
            getRelative(-1,0),
            getRelative(-1,-1)
        };
    }
    public Tile[] getRelatives(boolean includeDiagonals){
        if (includeDiagonals)getRelatives();
        return new Tile[]{
                getRelative(0,-1),
                getRelative(1,0),
                getRelative(0,1),
                getRelative(-1,0),
        };
    }
    private void adjustHeight(){

        Tile[] tiles = getRelatives(true);
        if (this.levelHeight == 1) {
            for (int i = 0; i < tiles.length; i++) {
                if (tiles[i] != null) {
                    if (tiles[i].levelHeight == -1) {
                        this.levelHeight = 0;
                        return;
                    }
                }
            }
        }


    }

    public int getLevelHeight() {
        return levelHeight;
    }
}
