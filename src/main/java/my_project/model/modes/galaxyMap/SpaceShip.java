package my_project.model.modes.galaxyMap;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import java.awt.image.BufferedImage;

public class SpaceShip extends GraphicalObject {
    GalaxyMapMode galaxyMapMode;
    BufferedImage image;
    double degrees = 0;

    public SpaceShip(GalaxyMapMode galaxyMapMode) {
        this.galaxyMapMode = galaxyMapMode;
        image = DrawTool.getNewImage("src/main/resources/graphic/spaceship_v2.png");
    }

    @Override
    public void draw(DrawTool drawTool) {

        drawTool.drawTransformedImage(image, x, y, degrees + 180, 2);
    }

    @Override
    public void update(double dt) {
        GalaxyMapPlanet currentGalaxyMapPlanet = galaxyMapMode.getCurrentPlanet();
        radius = currentGalaxyMapPlanet.getRadius() + 40;
        x = currentGalaxyMapPlanet.getX()-16 - image.getWidth()/2 + Math.cos(degrees * Math.PI / 180) * radius;
        y = currentGalaxyMapPlanet.getY()-16 - image.getHeight()/2 + Math.sin(degrees * Math.PI / 180) * radius;
        degrees += dt * 40;
        if(degrees > 360) degrees -= 360;
    }
}
