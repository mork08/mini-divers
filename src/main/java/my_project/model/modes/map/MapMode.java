package my_project.model.modes.map;

import KAGO_framework.view.DrawTool;
import my_project.model.modes.Mode;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MapMode extends Mode {
    PlanetController planetController;
    private static double translateX, translateY;
    private static int xTranslationDirection, yTranslationDirection;
    private static double scale = 1;
    private static int scaleDirection = 0;
    private Planet currentPlanet;
    private SpaceShip spaceShip;

    public MapMode() {
        planetController = new PlanetController(this);
        spaceShip = new SpaceShip(this);
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(new Color(0,0,0));
        drawTool.drawFilledRectangle(0-drawTool.getTranslationX(),0-drawTool.getTranslationY(),1000/drawTool.getScaleX(),1000/drawTool.getScaleY());
        planetController.draw(drawTool);
        spaceShip.draw(drawTool);
    }

    @Override
    public void update(double dt) {
        if(xTranslationDirection != 0) {
            translateX += xTranslationDirection * dt * 200 / scale;
        }
        if(yTranslationDirection != 0) {
            translateY += yTranslationDirection * dt * 200 / scale;
        }
        if(scaleDirection != 0) {
            scale += scaleDirection * dt * 1 * scale;
        }
        planetController.update(dt);
        spaceShip.update(dt);
    }

    public void manageMouse(MouseEvent e){
        planetController.checkForContact(e);
    }

    public static double getTranslateX(){return translateX;}
    public static double getTranslateY(){return translateY;}
    public static void setTranslateX(int x){xTranslationDirection = x;}
    public static void setTranslateY(int y){yTranslationDirection = y;}
    public static double getScale() {return scale;}
    public static void setScale(int s) {scaleDirection = s;}
    public Planet getCurrentPlanet() {return currentPlanet;}
    public void setCurrentPlanet(Planet currentPlanet) {this.currentPlanet = currentPlanet;}
}
