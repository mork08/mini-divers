package my_project.model.modes.map;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import java.awt.*;

public class Planet extends GraphicalObject {
    private String planetName;
    private boolean showName = false;
    private String terrainType;
    private String occupation = "MiniEarth";//MiniEarth, Terminis, Iluminis, MiniBots

    public Planet(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        planetName = PlanetNames.generateName();
    }

    @Override
    public void draw(DrawTool drawTool) {
        if(occupation.equals("MiniEarth")) {
            drawTool.setCurrentColor(new Color(0, 166, 255));
        }else if(occupation.equals("Terminis")) {
            drawTool.setCurrentColor(new Color(228, 149, 34));
        }else if(occupation.equals("Iluminis")) {
            drawTool.setCurrentColor(new Color(122, 1, 181));
        }else if(occupation.equals("MiniBots")) {
            drawTool.setCurrentColor(new Color(163, 7, 7));
        }

        drawTool.drawFilledCircle(x ,y , radius);
        if(showName) {
            drawTool.setCurrentColor(new Color(50, 50, 50, 121));
            drawTool.drawFilledRectangle(x - 120 ,y + 60 , 240, 100);
            drawTool.setCurrentColor(new Color(255, 255, 255));
            drawTool.formatText("Monospaced", Font.PLAIN, (int) (16 * drawTool.getScaleX()));
            drawTool.drawText(x - 110, y + 80, planetName);
            drawTool.drawText(x - 110, y + 80 + 30, "Occupation: " + occupation);
            drawTool.drawText(x - 110, y + 80 + 60, "Terrain: " + terrainType);
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
