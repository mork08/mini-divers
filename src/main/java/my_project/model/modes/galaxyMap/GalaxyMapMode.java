package my_project.model.modes.galaxyMap;

import KAGO_framework.control.ViewController;
import KAGO_framework.view.DrawTool;
import my_project.Config;
import my_project.control.ModeController;
import my_project.control.Mouse;
import my_project.model.modes.*;

import java.awt.*;
import java.awt.event.KeyEvent;
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
        translateX = -currentGalaxyMapPlanet.getX();
        translateY = -currentGalaxyMapPlanet.getY();
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setTranslateAndScale(0,0,1,1);
        drawTool.setFocalPoint(0,0);
        drawTool.setCurrentColor(new Color(0,0,0));
        drawTool.drawFilledRectangle(0,0,Config.WINDOW_WIDTH,Config.WINDOW_HEIGHT);
        drawTool.push();
            drawTool.setScale(scale);
            drawTool.setTranslate(translateX, translateY);
            drawTool.setFocalPoint(Config.WINDOW_WIDTH / 2, Config.WINDOW_HEIGHT / 2);
            galaxyMapPlanetController.draw(drawTool);
           galaxyMapPlanetController.setMousePos(Mouse.getTranslatedPosition().x, Mouse.getTranslatedPosition().y);
        spaceShip.draw(drawTool);drawTool.pop();
    }

    @Override
    public void launch() {

    }

    @Override
    public void update(double dt) {
        double speed = 600;
        if(ViewController.isKeyDown(KeyEvent.VK_W)) translateY +=  dt * speed / scale;
        if(ViewController.isKeyDown(KeyEvent.VK_S)) translateY -=  dt * speed / scale;
        if(ViewController.isKeyDown(KeyEvent.VK_D)) translateX -=  dt * speed / scale;
        if(ViewController.isKeyDown(KeyEvent.VK_A)) translateX +=  dt * speed / scale;
        if(ViewController.isKeyDown(KeyEvent.VK_E)) scale += dt * scale;
        if(ViewController.isKeyDown(KeyEvent.VK_Q)) scale -= dt * scale;
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
        controller.setCurrentPlanet(currentGalaxyMapPlanet);
        switchMode("GalaxyMapPlanet");
    }
    public SpaceShip getSpaceShip() {return spaceShip;}
}
