package my_project.model.modes.planet.Tilesystem;

import KAGO_framework.view.DrawTool;
import my_project.model.spritesheetSystem.*;

import java.awt.image.BufferedImage;

public class VisualTileRepresentation {
    private Tile tile;
    private static final int BORDER_SIZE = 8;
    private static PlanetSheet planetSheet = TileTextureContainer.getMars();
    public VisualTileRepresentation(Tile tile){
        setTile(tile);
    }
    public void setTile(Tile tile){
        this.tile = tile;
    }
    public void update(){
        
    }
    public void drawAt(DrawTool dt, double x, double y){

        TileSheet currentHeightSheet;
        if (tile != null){
            switch (tile.levelHeight){
                case -1:
                    dt.setCurrentColor(255, 0, 0, 255);
                    currentHeightSheet = planetSheet.getTileSheet(2);
                break;
                case 0:
                    dt.setCurrentColor(0, 255, 0, 255);
                    currentHeightSheet = planetSheet.getTileSheet(1);
                break;
                case 1:
                    dt.setCurrentColor(0, 0, 255, 255);
                    currentHeightSheet = planetSheet.getTileSheet(0);
                break;
                default:
                    currentHeightSheet = planetSheet.getTileSheet(0);
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

            if(currentHeightSheet != null) {
                //Center
                dt.drawImage(currentHeightSheet.getCenter(), x + BORDER_SIZE, y + BORDER_SIZE);
                ///*
                //Edges
                // > Up
                int heightDifferenceUp = tile.getRelative(0, -1) != null ? tile.getRelative(0, -1).getLevelHeight() - tile.getLevelHeight() : 1;
                dt.drawImage(currentHeightSheet.getEdge("up", heightDifferenceUp), x + BORDER_SIZE, y);
                // > Down
                int heightDifferenceDown = tile.getRelative(0, 1) != null ? tile.getRelative(0, 1).getLevelHeight() - tile.getLevelHeight() : 1;
                dt.drawImage(currentHeightSheet.getEdge("down", heightDifferenceDown), x + BORDER_SIZE, y + Tile.TILE_SIZE - BORDER_SIZE);
                // > Left
                int heightDifferenceLeft = tile.getRelative(-1, 0) != null ? tile.getRelative(-1, 0).getLevelHeight() - tile.getLevelHeight() : 1;
                dt.drawImage(currentHeightSheet.getEdge("left", heightDifferenceLeft), x, y + BORDER_SIZE);
                // > Right
                int heightDifferenceRight = tile.getRelative(1, 0) != null ? tile.getRelative(1, 0).getLevelHeight() - tile.getLevelHeight() : 1;
                dt.drawImage(currentHeightSheet.getEdge("right", heightDifferenceRight), x + Tile.TILE_SIZE - BORDER_SIZE, y + BORDER_SIZE);
                //*/
                //Corners
                int heightDifferenceUpLeft = tile.getRelative(-1, -1) != null ? tile.getRelative(-1, -1).getLevelHeight() - tile.getLevelHeight() : 1;
                int heightDifferenceUpRight = tile.getRelative(1, -1) != null ? tile.getRelative(1, -1).getLevelHeight() - tile.getLevelHeight() : 1;
                int heightDifferenceDownLeft = tile.getRelative(-1, 1) != null ? tile.getRelative(-1, 1).getLevelHeight() - tile.getLevelHeight() : 1;
                int heightDifferenceDownRight = tile.getRelative(1, 1) != null ? tile.getRelative(1, 1).getLevelHeight() - tile.getLevelHeight() : 1;

                dt.drawImage(currentHeightSheet.getCorner("topLeft", heightDifferenceLeft, heightDifferenceUp, heightDifferenceUpLeft), x, y);
                dt.drawImage(currentHeightSheet.getCorner("topRight", heightDifferenceUp, heightDifferenceRight, heightDifferenceUpRight), x + Tile.TILE_SIZE - BORDER_SIZE, y);
                dt.drawImage(currentHeightSheet.getCorner("downRight", heightDifferenceRight, heightDifferenceDown, heightDifferenceDownRight), x + Tile.TILE_SIZE - BORDER_SIZE, y + Tile.TILE_SIZE - BORDER_SIZE);
                dt.drawImage(currentHeightSheet.getCorner("downLeft", heightDifferenceDown, heightDifferenceLeft, heightDifferenceDownLeft), x, y + Tile.TILE_SIZE - BORDER_SIZE);
            }
        }
    }
}
