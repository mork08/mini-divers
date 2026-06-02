package my_project.model.modes.map;

import KAGO_framework.view.DrawTool;
import my_project.model.modes.Mode;

public class MapMode extends Mode {
    PlanetController planetController;
    private static double translateX, translateY;

    public MapMode() {
        this.planetController = new PlanetController();
    }

    @Override
    public void draw(DrawTool drawTool) {
        planetController.draw(drawTool);
    }

    @Override
    public void update(double dt) {
        planetController.update(dt);
    }

    public static double getTranslateX(){return translateX;}
    public static double getTranslateY(){return translateY;}
    public static void setTranslateX(double x){translateX = x;}
    public static void setTranslateY(double y){translateY = y;}
}
