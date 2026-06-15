package my_project.model.modes.map;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import java.awt.*;

public class Planet extends GraphicalObject {
    private String planetName;
    private boolean showName = false;
    //TODO: Namen der Planeten generieren und beim darüberhovern diesen anzeigen

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
            drawTool.setCurrentColor(new Color(255, 255, 255));
            drawTool.formatText("Monospaced", Font.PLAIN, (int) (14 * drawTool.getScaleX()));
            drawTool.drawText(x - radius - 20, y - radius, planetName);
        }
    }

    public void setNameShowing(boolean showName) {
        this.showName = showName;
    }
}
