package my_project.model.modes.planet;

import KAGO_framework.view.DrawTool;
import my_project.model.modes.Mode;
import my_project.model.modes.planet.Tilesystem.TileMap;
import my_project.model.modes.planet.collisionSystem.CollisionManager;
import my_project.model.modes.planet.missions.ExterminationMission;

public class PlanetMode extends Mode {
    TileMap tileMap;
    Operation operation;
    public PlanetMode() {
        operation = new Operation("Mars", "minibots", new ExterminationMission());
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
        drawTool.setScale(1);
            operation.draw(drawTool);
        drawTool.pop();
    }
}
