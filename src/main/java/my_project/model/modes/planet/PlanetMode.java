package my_project.model.modes.planet;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.Config;
import my_project.control.ModeController;
import my_project.model.modes.Mode;
import my_project.model.modes.planet.Tilesystem.TileMap;
import my_project.model.modes.planet.collisionSystem.CollisionManager;
import my_project.model.modes.planet.entity.enemy.EntityMinirobot;
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
        CollisionManager.update(dt);
        operation.update(dt);
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.push();
        drawTool.setScale(4);
        drawTool.setFocalPoint(Config.WINDOW_WIDTH / 2, Config.WINDOW_HEIGHT / 2);
        setCameraPosition(TileMap.getPlayer().getX(), TileMap.getPlayer().getY());
        drawTool.setTranslate(-cameraPosition.x, -cameraPosition.y);
            if (operation != null) operation.draw(drawTool);
        drawTool.pop();
    }
    @Override
    public void drawUI(DrawTool drawTool) {
        int x = 900;
        drawTool.setCurrentColor(new Color(0, 0, 0));
        drawTool.drawFilledRectangle(x - 20,100,200,300);
        drawTool.setCurrentColor(new Color(255, 255, 255));
        drawTool.drawText(x, 150, "Planet: " + controller.getCurrentPlanet().getPlanetName());
        drawTool.drawText(x, 170, "Occupation: " + controller.getCurrentPlanet().getOccupation());
        drawTool.drawText(x, 190, "Terrain: " + controller.getCurrentPlanet().getTerrainType());
        drawTool.drawText(x, 210, "X: " + TileMap.getPlayer().getX());
        drawTool.drawText(x, 230, "Y: " + TileMap.getPlayer().getY());
    }

    @Override
    public void launch() {
        cameraPosition = new Vec2d(0,0);
        if (controller.getCurrentPlanet() != null) {
            System.out.println("Planet: " + controller.getCurrentPlanet().getPlanetName());
            operation = new Operation(this, controller.getCurrentPlanet().getTerrainType(), controller.getCurrentPlanet().getOccupation(), new ExterminationMission());
            new EntityMinirobot("test", 10, 10, 32, 32).setTarget(TileMap.getPlayer());
        }
    }
    public void freePlanet() {
        controller.getCurrentPlanet().setOccupation("MiniEarth");
    }
    public void setCameraPosition(double x, double y) {
        cameraPosition.set(Math.floor(x), Math.floor(y));
    }
}
