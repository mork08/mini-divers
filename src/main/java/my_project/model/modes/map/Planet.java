package my_project.model.modes.map;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import java.awt.*;

public class Planet extends GraphicalObject {
    private String planetName;
    //TODO: Namen der Planeten generieren und beim darüberhovern diesen anzeigen

    public Planet(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(new Color(41, 76, 191));
        drawTool.drawFilledCircle(x ,y , radius);
    }
}
