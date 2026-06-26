package my_project.model.modes.planet;

import KAGO_framework.view.DrawTool;
import my_project.model.modes.planet.Tilesystem.TileMap;
import my_project.model.modes.planet.Tilesystem.VisualTileRepresentation;
import my_project.model.modes.planet.entity.EntityManager;
import my_project.model.modes.planet.missions.Mission;
import my_project.model.newerColliderSystem.CollisionHandler;

public class Operation {
    TileMap tileMap;
    Mission mission;
    String terrainType;
    String occupation;
    PlanetMode planetMode;
    public Operation(PlanetMode planetMode, String terrainType, String occupation, Mission mission) {
        this.tileMap = new TileMap(8);
        this.mission = mission;
        this.terrainType = terrainType;
        this.occupation = occupation;
        this.planetMode = planetMode;
        VisualTileRepresentation.setTerrain(terrainType);
        CollisionHandler.setOperation(Operation.this);
    }
    public TileMap getTilemap() {
        return tileMap;
    }
    public void update(double dt){
        if(mission.isCompleted()){
            extract();
        }
        EntityManager.updateAll(dt);

    }
    public void draw(DrawTool dt){
        tileMap.draw(dt);
        EntityManager.drawAll(dt);
    }
    public void extract(){
        planetMode.freePlanet();
    }

    public TileMap getTileMap() {
        return tileMap;
    }
}
