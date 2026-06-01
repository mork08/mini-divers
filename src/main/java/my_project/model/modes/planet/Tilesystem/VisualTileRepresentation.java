package my_project.model.modes.planet.Tilesystem;

import KAGO_framework.view.DrawTool;

import java.awt.image.BufferedImage;

public class VisualTileRepresentation {
    private Tile tile;
    private static final int BORDER_SIZE = 8;
    private static BufferedImage texture = DrawTool.getNewImage("src/main/resources/graphic/tilesheets/debug.png");
    public VisualTileRepresentation(Tile tile){
        setTile(tile);
    }
    public void setTile(Tile tile){
        this.tile = tile;
    }
    public void update(){

    }
    public void drawAt(DrawTool dt, double x, double y){
        if (tile != null){
            switch (tile.levelHeight){
                case -1:
                    dt.setCurrentColor(255, 0, 0, 255);
                break;
                case 0:
                    dt.setCurrentColor(0, 255, 0, 255);
                break;
                case 1:
                    dt.setCurrentColor(0, 0, 255, 255);
                break;
            }

            dt.drawFilledRectangle(x, y, Tile.TILE_SIZE, Tile.TILE_SIZE);

            if (tile.isHighlighted()) {
                dt.setCurrentColor(255, 255, 100, 255);
                dt.drawFilledRectangle(x, y, Tile.TILE_SIZE, Tile.TILE_SIZE);
            }



            //top
            if (tile.getRelative(0, -1) != null) {
                if (tile.getRelative(0, -1).levelHeight == tile.levelHeight) {
                    dt.setCurrentColor(0, 0, 0, 0);
                } else if (tile.getRelative(0, -1).levelHeight > tile.levelHeight) {
                    dt.setCurrentColor(0, 0, 0, 200);
                } else if (tile.getRelative(0, -1).levelHeight < tile.levelHeight) {
                    dt.setCurrentColor(255, 255, 255, 200);
                }
                dt.drawFilledRectangle(x + BORDER_SIZE, y, Tile.TILE_SIZE - BORDER_SIZE * 2, BORDER_SIZE);
            }
            //right
            if (tile.getRelative(1, 0) != null) {
                if (tile.getRelative(1, 0).levelHeight == tile.levelHeight) {
                    dt.setCurrentColor(0, 0, 0, 0);
                } else if (tile.getRelative(1, 0).levelHeight > tile.levelHeight) {
                    dt.setCurrentColor(0, 0, 0, 200);
                } else if (tile.getRelative(1, 0).levelHeight < tile.levelHeight) {
                    dt.setCurrentColor(255, 255, 255, 200);
                }
                dt.drawFilledRectangle(x + Tile.TILE_SIZE - BORDER_SIZE, y + BORDER_SIZE, BORDER_SIZE, Tile.TILE_SIZE - BORDER_SIZE * 2);
            }
            //bottom
            if (tile.getRelative(0, 1) != null) {
                if (tile.getRelative(0, 1).levelHeight == tile.levelHeight) {
                    dt.setCurrentColor(0, 0, 0, 0);
                } else if (tile.getRelative(0, 1).levelHeight > tile.levelHeight) {
                    dt.setCurrentColor(0, 0, 0, 200);
                } else if (tile.getRelative(0, 1).levelHeight < tile.levelHeight) {
                    dt.setCurrentColor(255, 255, 255, 200);
                }
                dt.drawFilledRectangle(x + BORDER_SIZE, y + Tile.TILE_SIZE - BORDER_SIZE, Tile.TILE_SIZE - BORDER_SIZE * 2, BORDER_SIZE);
            }
            //left
            if (tile.getRelative(-1, 0) != null) {
                if (tile.getRelative(-1, 0).levelHeight == tile.levelHeight) {
                    dt.setCurrentColor(0, 0, 0, 0);
                } else if (tile.getRelative(-1, 0).levelHeight > tile.levelHeight) {
                    dt.setCurrentColor(0, 0, 0, 200);
                } else if (tile.getRelative(-1, 0).levelHeight < tile.levelHeight) {
                    dt.setCurrentColor(255, 255, 255, 200);
                }
                dt.drawFilledRectangle(x, y + BORDER_SIZE, BORDER_SIZE, Tile.TILE_SIZE - BORDER_SIZE * 2);
            }
            dt.setCurrentColor(0,0,0,0);
            //top left corner
            dt.drawFilledRectangle(x, y, BORDER_SIZE, BORDER_SIZE);
            //top right corner
            dt.drawFilledRectangle(x + Tile.TILE_SIZE - BORDER_SIZE, y, BORDER_SIZE, BORDER_SIZE);
            //bottom right corner
            dt.drawFilledRectangle(x + Tile.TILE_SIZE - BORDER_SIZE, y + Tile.TILE_SIZE - BORDER_SIZE, BORDER_SIZE, BORDER_SIZE);
            //bottom left corner
            dt.drawFilledRectangle(x, y + Tile.TILE_SIZE - BORDER_SIZE, BORDER_SIZE, BORDER_SIZE);
            //dt.drawImageToSize(texture, x, y, Tile.TILE_SIZE, Tile.TILE_SIZE);
            dt.setCurrentColor(0,0,0,255);

            //top
            dt.drawRectangle(x + BORDER_SIZE, y, Tile.TILE_SIZE - BORDER_SIZE*2, BORDER_SIZE);
            //right
            dt.drawRectangle(x + Tile.TILE_SIZE - BORDER_SIZE, y + BORDER_SIZE, BORDER_SIZE, Tile.TILE_SIZE - BORDER_SIZE*2);
            //bottom
            dt.drawRectangle(x + BORDER_SIZE, y + Tile.TILE_SIZE - BORDER_SIZE, Tile.TILE_SIZE - BORDER_SIZE*2, BORDER_SIZE);
            //left
            dt.drawRectangle(x, y + BORDER_SIZE, BORDER_SIZE, Tile.TILE_SIZE - BORDER_SIZE*2);
            dt.setCurrentColor(100,100,100,255);
            //top left corner
            dt.drawRectangle(x, y, BORDER_SIZE, BORDER_SIZE);
            //top right corner
            dt.drawRectangle(x + Tile.TILE_SIZE - BORDER_SIZE, y, BORDER_SIZE, BORDER_SIZE);
            //bottom right corner
            dt.drawRectangle(x + Tile.TILE_SIZE - BORDER_SIZE, y + Tile.TILE_SIZE - BORDER_SIZE, BORDER_SIZE, BORDER_SIZE);
            //bottom left corner
            dt.drawRectangle(x, y + Tile.TILE_SIZE - BORDER_SIZE, BORDER_SIZE, BORDER_SIZE);
        }
    }
}
