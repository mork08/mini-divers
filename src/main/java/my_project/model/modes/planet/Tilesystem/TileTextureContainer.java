package my_project.model.modes.planet.Tilesystem;

import KAGO_framework.view.DrawTool;
import my_project.model.spritesheetSystem.PlanetSheet;

public class TileTextureContainer {
    private static PlanetSheet debugPlanet = new PlanetSheet(3, DrawTool.getNewImage("src/main/resources/graphic/tilesheets/debug.png"), 8);

    public static PlanetSheet getDebugPlanet() {
        return debugPlanet;
    }
}
