package my_project.model.Tilesystem;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.control.Mouse;

public class Tile extends GraphicalObject {
    private Map map;
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

    public void setMap(Map map){
        this.map = map;
    }

    @Override
    public void draw(DrawTool dt){
        adjustHeight();
        visualTileRepresentation.drawAt(dt, x, y);
        highlighted = false;
    }
    public void highlight(boolean highlighted){
        this.highlighted = highlighted;
    };

    public boolean isHighlighted() {
        return highlighted;
    }

    public Tile getRelative(int rx, int ry){
        if (map != null){
            if(map.getTile(indexX, indexY) == this){
                return  map.getTile(indexX + rx, indexY + ry);
            }
        }
        return null; //TODO figure out good way to give Tile acces to Map
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
        Tile[] tiles = getRelatives(false);
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

}
