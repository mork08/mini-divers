package my_project.model.modes.planet;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.Config;
import my_project.control.ModeController;
import my_project.model.modes.Mode;
import my_project.model.modes.planet.Tilesystem.TileMap;
import my_project.model.modes.planet.missions.ExterminationMission;

import java.awt.*;

public class PlanetMode extends Mode {
    TileMap tileMap;
    Operation operation;
    Vec2d cameraPosition;
    public PlanetMode(ModeController modeController) {
        super(modeController);
        cameraPosition = new Vec2d();
    }

    @Override
    public void update(double dt) {
        super.update(dt);
        if (operation != null) operation.update(dt);
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.push();
        drawTool.setScale(4);
        drawTool.setFocalPoint(Config.WINDOW_WIDTH / 2, Config.WINDOW_HEIGHT / 2);
        if (TileMap.getPlayer() != null) setCameraPosition(TileMap.getPlayer().getX(), TileMap.getPlayer().getY());

        drawTool.setTranslate(-cameraPosition.x, -cameraPosition.y);
            if (operation != null) operation.draw(drawTool);
        drawTool.pop();
    }
    @Override
    public void drawUI(DrawTool drawTool) {
        drawTool.setCurrentColor(new Color(0, 0, 0));
        operation.getMission().draw(drawTool, 50, 100, controller.getCurrentPlanet());
    }

    @Override
    public void launch() {
        cameraPosition = new Vec2d(0,0);
        if (controller.getCurrentPlanet() != null) {
            System.out.println("Planet: " + controller.getCurrentPlanet().getPlanetName());
            operation = new Operation(this, controller.getCurrentPlanet().getTerrainType(), controller.getCurrentPlanet().getOccupation(), new ExterminationMission());

        }
    }
    public void freePlanet() {
        controller.getCurrentPlanet().setOccupation("MiniEarth");
        switchMode("Map");
    }
    public void setCameraPosition(double x, double y) {
        cameraPosition.set(Math.floor(x), Math.floor(y));
    }
}
