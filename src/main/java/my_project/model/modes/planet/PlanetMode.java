package my_project.model.modes.planet;

import KAGO_framework.view.DrawTool;
import my_project.model.modes.Mode;
import my_project.model.modes.planet.Tilesystem.TileMap;

public class PlanetMode extends Mode {
    TileMap tileMap;
    public PlanetMode() {
        tileMap = new TileMap(2);
    }
    @Override
    public void draw(DrawTool drawTool) {
        drawTool.push();
        drawTool.setScale(5);
            tileMap.draw(drawTool);
        drawTool.pop();
    }
}
