package my_project.model.modes.map;

import KAGO_framework.view.DrawTool;
import my_project.model.modes.Mode;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MapMode extends Mode {
    PlanetController planetController;
    private static double translateX, translateY;
    private static int xTranslationDirection, yTranslationDirection;

    public MapMode() {
        this.planetController = new PlanetController();
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(new Color(0,0,0));
        drawTool.drawFilledRectangle(0-drawTool.getTranslationX(),0-drawTool.getTranslationY(),1000/drawTool.getScaleX(),1000/drawTool.getScaleY());
        planetController.draw(drawTool);
    }

    @Override
    public void update(double dt) {
        if(xTranslationDirection != 0) {
            translateX += xTranslationDirection * dt * 200;
        }
        if(yTranslationDirection != 0) {
            translateY += yTranslationDirection * dt * 200;
        }
        planetController.update(dt);
    }

    public void manageMouse(MouseEvent e){
        planetController.checkForContact(e);
    }

    public static double getTranslateX(){return translateX;}
    public static double getTranslateY(){return translateY;}
    public static void setTranslateX(int x){xTranslationDirection = x;}
    public static void setTranslateY(int y){yTranslationDirection = y;}
}
