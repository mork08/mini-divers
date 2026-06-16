package my_project.model.modes.map;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import java.awt.*;

public class Planet extends GraphicalObject {
    private String planetName;
    private boolean showName = false;
    private String terrainType;

    public Planet(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        planetName = PlanetNames.generateName();
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(new Color(41, 76, 191));
        drawTool.drawFilledCircle(x ,y , radius);
        if(showName) {
            drawTool.setCurrentColor(new Color(50, 50, 50, 121));
            drawTool.drawFilledRectangle(x - 100 ,y + 60 , 200, 100);
            drawTool.setCurrentColor(new Color(255, 255, 255));
            drawTool.formatText("Monospaced", Font.PLAIN, (int) (16 * drawTool.getScaleX()));
            drawTool.drawText(x - 90, y + 80, planetName);
        }
    }

    public void setNameShowing(boolean showName) {
        this.showName = showName;
    }
}
