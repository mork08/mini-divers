package my_project.model.modes.galaxyMap;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.model.abitur.datenstrukturen.List;
import KAGO_framework.model.abitur.datenstrukturen.Vertex;
import KAGO_framework.view.DrawTool;

import java.awt.image.BufferedImage;

public class SpaceShip extends GraphicalObject {
    GalaxyMapMode galaxyMapMode;
    BufferedImage image;
    double degrees = 0;
    boolean orbit = true;
    double speed = 200;
    private List<Vertex<GalaxyMapPlanet>> path;

    public SpaceShip(GalaxyMapMode galaxyMapMode) {
        this.galaxyMapMode = galaxyMapMode;
        image = DrawTool.getNewImage("src/main/resources/graphic/spaceship_v2.png");
    }

    @Override
    public void draw(DrawTool drawTool) {
        if(orbit) drawTool.drawTransformedImage(image, x, y, degrees + 180, 2);
        else drawTool.drawTransformedImage(image, x - image.getWidth()/2, y - image.getHeight()/2, Math.toDegrees(degrees) + 90, 2);
    }

    @Override
    public void update(double dt) {
        GalaxyMapPlanet currentGalaxyMapPlanet = galaxyMapMode.getCurrentPlanet();
        if(orbit) {
            radius = currentGalaxyMapPlanet.getRadius() + 40;
            x = currentGalaxyMapPlanet.getX()-16 - image.getWidth()/2 + Math.cos(degrees * Math.PI / 180) * radius;
            y = currentGalaxyMapPlanet.getY()-16 - image.getHeight()/2 + Math.sin(degrees * Math.PI / 180) * radius;
            degrees += dt * 40;
            if(degrees > 360) degrees -= 360;
        }else {
            GalaxyMapPlanet nextPlanet = path.getContent().getContent();
            degrees = Math.atan2(nextPlanet.getY() - y - image.getHeight()/2, nextPlanet.getX() - x - image.getWidth()/2);
            x += Math.cos(degrees) * speed*dt;
            y += Math.sin(degrees) * speed*dt;
            if(this.getDistanceTo(nextPlanet) < 33) {
                x = nextPlanet.getX() - image.getWidth() / 2;
                y = nextPlanet.getY() - image.getHeight() / 2;
                path.next();
                if(!path.hasAccess()) {
                      orbit = true;
                      degrees = 0;
                }
            }
        }
    }

    public void moveOnPath(List<Vertex<GalaxyMapPlanet>> path) {
        this.path = path;
        this.path.toFirst();
        this.path.next();
        orbit = false;
    }

    public boolean getReady() {
        return orbit;
    }
}
