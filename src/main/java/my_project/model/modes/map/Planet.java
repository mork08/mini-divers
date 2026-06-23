package my_project.model.modes.map;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.model.modes.planet.missions.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Planet extends GraphicalObject {
    private String planetName;
    private boolean showName = false;
    private String terrainType;
    private String occupation = "MiniEarth";//MiniEarth, Terminis, Iluminis, MiniBots
    private BufferedImage planetCard;
    private boolean isPlanetCardLoaded = false;

    private Mission mission; // - BunterNinja2609

    public Planet(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        planetName = PlanetInfoContainer.generateName();
        mission = Math.random() > 0.7 ? new ExtractionMission() : new ExterminationMission(); //RANDOM SELCTED MISSION TYPE - BunterNinja2609
        terrainType = PlanetInfoContainer.getTerrainType();
    }

    @Override
    public void draw(DrawTool drawTool) {
        if(!isPlanetCardLoaded) {
            planetCard = DrawTool.getNewImage("src/main/resources/graphic/" + occupation + ".png");
            isPlanetCardLoaded = true;
        }
        switch (occupation) {
            case "MiniEarth":
                drawTool.setCurrentColor(new Color(0, 166, 255));
                break;
            case "Terminis":
                drawTool.setCurrentColor(new Color(228, 149, 34));
                break;
            case "Iluminis":
                drawTool.setCurrentColor(new Color(122, 1, 181));
                break;
            case "MiniBots":
                drawTool.setCurrentColor(new Color(163, 7, 7));
                break;
        }
        //drawTool.drawFilledCircle(x ,y , radius);
        BufferedImage planetTexture = PlanetInfoContainer.getPlanetTexture(terrainType, radius);
        if (planetTexture != null) drawTool.drawTransformedImage(planetTexture, x - planetTexture.getHeight() ,y - planetTexture.getHeight() , 0, 2);
    }

    public void drawUI(DrawTool drawTool) {
        if(showName) {
            drawTool.setCurrentColor(new Color(0, 0, 0, 121));
            drawTool.drawTransformedImage(planetCard, x - 110 , y + 60, 0, 4);
            //drawTool.setCurrentColor(new Color(50, 50, 50, 121));
            //drawTool.drawFilledRectangle(x - 120 ,y + 60 , 240, 100);
            drawTool.setCurrentColor(new Color(255, 255, 255));
            drawTool.formatText("Monospaced", Font.PLAIN, (int) (14 * drawTool.getScaleX()));
            drawTool.drawText(x - 90, y + 170, planetName);
            drawTool.drawText(x - 90, y + 170 + 30, "Occupation: " + occupation);
            drawTool.drawText(x - 90, y + 170 + 60, "Terrain: " + terrainType);
        }
    }

    public void setNameShowing(boolean showName) {
        this.showName = showName;
    }
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
    public String getOccupation() {
        return occupation;
    }

}
