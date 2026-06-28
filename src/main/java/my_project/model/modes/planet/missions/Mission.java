package my_project.model.modes.planet.missions;

import KAGO_framework.view.DrawTool;
import my_project.model.modes.galaxyMap.GalaxyMapPlanet;

import java.awt.*;

public abstract class Mission {
    protected String shortDescription;
    protected String longDescription;
    boolean isCompleted;
    double completion = 0;
    public boolean isCompleted() {
        if (completion >= 1) isCompleted = true;
        return isCompleted;
    }
    public void progress(String Type, double progress) {
        switch (Type) {
            case "addValue":
                completion += progress;
            break;
            case "setValue":
                completion = progress;
            break;
            case "step":
                customStep(progress);
            break;
        }
    }
    protected abstract void customStep(double progress);
    public String getShortDescription() {
        return shortDescription;
    }
    public String getLongDescription() {
        return longDescription;
    }

    public void draw(DrawTool drawTool, double x, double y, GalaxyMapPlanet planet) {
        double scale = 4;
        double width = planet.getPlanetCard().getWidth() * scale;
        double height = planet.getPlanetCard().getHeight() * scale;
        double margin = width*0.15;
        double topMargin = height*0.4;
        double textLineHeight = 16;


        drawTool.drawTransformedImage(planet.getPlanetCard(), x, y, 0, 4);
        drawTool.setCurrentColor(new Color(255, 255, 255));
        drawTool.drawText(x + margin, topMargin + y, "Planet: " + planet.getPlanetName());
        drawTool.drawText(x + margin, topMargin + y + textLineHeight, "Occupation: " + planet.getOccupation());
        drawTool.drawText(x + margin, topMargin + y + textLineHeight * 2, "Terrain: " + planet.getTerrainType());
        drawTool.setCurrentColor(new Color(24, 20, 37));
        drawTool.drawFilledRectangle(x+margin, y+height-topMargin/2, width-margin*2, textLineHeight);
        drawTool.setCurrentColor(new Color(247, 118, 34));
        drawTool.drawFilledRectangle(x+margin, y+height-topMargin/2, width-margin*2 * Math.min(1, completion), textLineHeight);
        drawTool.setCurrentColor(new Color(255, 255, 255));
        drawTool.drawRectangle(x+margin, y+height-topMargin/2, width-margin*2, textLineHeight);

    }
}
