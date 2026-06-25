package my_project.model.modes.galaxyMap;

import KAGO_framework.view.DrawTool;
import my_project.control.ModeController;
import my_project.model.modes.*;

import java.awt.*;
import java.awt.event.MouseEvent;

public class GalaxyMapMode extends Mode {
    GalaxyMapPlanetController galaxyMapPlanetController;
    private static double translateX, translateY;
    private static int xTranslationDirection, yTranslationDirection;
    private static double scale = 1;
    private static int scaleDirection = 0;
    private GalaxyMapPlanet currentGalaxyMapPlanet;
    private SpaceShip spaceShip;

    public GalaxyMapMode(ModeController modeController) {
        super(modeController);
        galaxyMapPlanetController = new GalaxyMapPlanetController(this);
        spaceShip = new SpaceShip(this);
        translateX = -currentPlanet.getX() + 500;
        translateY = -currentPlanet.getY() + 500;
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(new Color(0,0,0));
        drawTool.drawFilledRectangle(0-drawTool.getTranslationX(),0-drawTool.getTranslationY(),1000/drawTool.getScaleX(),1000/drawTool.getScaleY());
        galaxyMapPlanetController.draw(drawTool);
        spaceShip.draw(drawTool);
    }

    @Override
    public void update(double dt) {
        if(xTranslationDirection != 0) {
            translateX += xTranslationDirection * dt * 600 / scale;
        }
        if(yTranslationDirection != 0) {
            translateY += yTranslationDirection * dt * 600 / scale;
        }
        if(scaleDirection != 0) {
            scale += scaleDirection * dt * 1 * scale;
            translateX -= (scaleDirection  * 50/scale) / 3;
            translateY -= (scaleDirection  * 50/scale) / 3;
        }
        galaxyMapPlanetController.update(dt);
        spaceShip.update(dt);
    }

    public void manageMouseClick(MouseEvent e){
        galaxyMapPlanetController.checkForContactOnClick(e);
    }
    public void manageMouseMove(MouseEvent e) {
        galaxyMapPlanetController.checkForHover(e);
    }

    public static double getTranslateX(){return translateX;}
    public static double getTranslateY(){return translateY;}
    public static void setTranslateX(int x){xTranslationDirection = x;}
    public static void setTranslateY(int y){yTranslationDirection = y;}
    public static double getScale() {return scale;}
    public static void setScale(int s) {scaleDirection = s;}
    public GalaxyMapPlanet getCurrentPlanet() {return currentGalaxyMapPlanet;}
    public void setCurrentPlanet(GalaxyMapPlanet currentGalaxyMapPlanet) {this.currentGalaxyMapPlanet = currentGalaxyMapPlanet;}
    public void startMission() { // - BunterNinja2609
        switchMode("GalaxyMapPlanet");
    }
    public SpaceShip getSpaceShip() {return spaceShip;}
}
