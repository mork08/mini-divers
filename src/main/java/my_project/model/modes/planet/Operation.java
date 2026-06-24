package my_project.model.modes.planet;

import KAGO_framework.view.DrawTool;
import my_project.model.modes.planet.Tilesystem.TileMap;
import my_project.model.modes.planet.missions.Mission;

public class Operation {
    TileMap tileMap;
    Mission mission;
    String terrainType;
    String occupation;
    public Operation(String terrainType, String occupation, Mission mission) {
        this.tileMap = new TileMap(8);
        this.mission = mission;
        this.terrainType = terrainType;
        this.occupation = occupation;
    }
    public TileMap getTilemap() {
        return tileMap;
    }
    public void update(double dt){
        if(mission.isCompleted()){
            extract();
        }
    }
    public void draw(DrawTool dt){
        tileMap.draw(dt);
    }
    public void extract(){

    }
}
