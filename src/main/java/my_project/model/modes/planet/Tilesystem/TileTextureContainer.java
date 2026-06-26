package my_project.model.modes.planet.Tilesystem;

import KAGO_framework.view.DrawTool;
import my_project.model.spritesheetSystem.PlanetSheet;

public class TileTextureContainer {
    private static PlanetSheet debugPlanet = new PlanetSheet(3, DrawTool.getNewImage("src/main/resources/graphic/tilesheets/debugSheet.png"), 8);
    private static PlanetSheet sandy = new PlanetSheet(3, DrawTool.getNewImage("src/main/resources/graphic/tilesheets/sandy.png"), 8);
    private static PlanetSheet earthlike = new PlanetSheet(3, DrawTool.getNewImage("src/main/resources/graphic/tilesheets/earthlike.png"), 8);
    private static PlanetSheet rocky = new PlanetSheet(3, DrawTool.getNewImage("src/main/resources/graphic/tilesheets/rocky.png"), 8);
    public static PlanetSheet getDebugPlanet() {
        return debugPlanet;
    }
    public static PlanetSheet getSandy() {
        return sandy;
    }
    public static PlanetSheet getEarthlike() {return earthlike;}

    public static PlanetSheet getRocky() {return rocky;}
}
