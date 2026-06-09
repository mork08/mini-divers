package my_project.model.modes.map;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpaceShip extends GraphicalObject {
    MapMode mapMode;
    BufferedImage image;
    double degrees = 0;

    public SpaceShip(MapMode mapMode) {
        this.mapMode = mapMode;
        image = DrawTool.getNewImage("src/main/resources/graphic/spaceship.png");
    }

    @Override
    public void draw(DrawTool drawTool) {

        drawTool.drawTransformedImage(image, x, y, degrees + 90, 1);
    }

    @Override
    public void update(double dt) {
        Planet currentPlanet = mapMode.getCurrentPlanet();
        radius = currentPlanet.getRadius() + 40;
        x = currentPlanet.getX() - image.getWidth()/2 + Math.cos(degrees * Math.PI / 180) * radius;
        y = currentPlanet.getY() - image.getHeight()/2 + Math.sin(degrees * Math.PI / 180) * radius;
        degrees += dt * 40;
        //if(degrees > 360) degrees -= 360;
    }
}
