package my_project.model.modes.map;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import java.awt.*;

public class Planet extends GraphicalObject {
    public Planet(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(new Color(41, 76, 191));
        drawTool.drawFilledCircle(x ,y , 20);
    }
}
