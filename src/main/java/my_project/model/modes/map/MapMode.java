package my_project.model.modes.map;

import KAGO_framework.view.DrawTool;
import my_project.model.modes.Mode;

public class MapMode extends Mode {
    PlanetController planetController;
    private static double translateX, translateY;
    private static int xTranslationDirection, yTranslationDirection;

    public MapMode() {
        this.planetController = new PlanetController();
    }

    @Override
    public void draw(DrawTool drawTool) {
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

    public static double getTranslateX(){return translateX;}
    public static double getTranslateY(){return translateY;}
    public static void setTranslateX(int x){xTranslationDirection = x;}
    public static void setTranslateY(int y){yTranslationDirection = y;}
}
