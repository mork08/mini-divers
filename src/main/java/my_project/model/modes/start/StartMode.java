package my_project.model.modes.start;

import KAGO_framework.view.DrawTool;
import my_project.Config;
import my_project.control.ModeController;
import my_project.model.modes.Mode;

import java.awt.image.BufferedImage;

public class StartMode extends Mode {
    double timer = 0.0;
    double launchTime = 3.7;
    BufferedImage startScreen;
    public StartMode(ModeController modeController) {
        super(modeController);
        startScreen = DrawTool.getNewImage("src/main/resources/graphic/startscreen.png");
    }

    @Override
    public void update(double dt){
        timer += dt;
        if (timer > launchTime){
            switchMode("Map");
        }
    }
    @Override
    public void draw(DrawTool dt){
        double s = (double) Config.WINDOW_WIDTH /startScreen.getWidth();
        dt.drawTransformedImage(startScreen, 0,0-(startScreen.getHeight()*s) /2 +Config.WINDOW_HEIGHT/2, 0, s);
    }
    @Override
    public void launch() {
        timer = 0;
    }
}
