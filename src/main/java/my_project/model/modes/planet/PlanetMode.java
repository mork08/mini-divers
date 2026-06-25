package my_project.model.modes.planet;

import KAGO_framework.view.DrawTool;
import my_project.control.ModeController;
import my_project.model.modes.Mode;
import my_project.model.modes.planet.Tilesystem.TileMap;
import my_project.model.modes.planet.missions.ExterminationMission;

import java.awt.*;

public class PlanetMode extends Mode {
    TileMap tileMap;
    Operation operation;
    public PlanetMode(ModeController modeController) {
        super(modeController);

    }
    @Override
    public void draw(DrawTool drawTool) {
        drawTool.push();
        drawTool.setScale(4);
            operation.draw(drawTool);
        drawTool.pop();
    }
    @Override
    public void drawUI(DrawTool drawTool) {
        drawTool.setCurrentColor(new Color(0, 0, 0));
        drawTool.drawFilledRectangle(50,100,200,300);
        drawTool.setCurrentColor(new Color(255, 255, 255));
        drawTool.drawText(60, 150, "Planet: " + controller.getCurrentPlanet().getPlanetName());
        drawTool.drawText(60, 170, "Occupation: " + controller.getCurrentPlanet().getOccupation());
        drawTool.drawText(60, 190, "Terrain: " + controller.getCurrentPlanet().getTerrainType());
    }

    @Override
    public void launch() {
        if (controller.getCurrentPlanet() != null) {
            operation = new Operation(this, controller.getCurrentPlanet().getTerrainType(), controller.getCurrentPlanet().getOccupation(), new ExterminationMission());

        }
    }
    public void freePlanet() {
        controller.getCurrentPlanet().setOccupation("MiniEarth");
    }
}
