package my_project.control;

import KAGO_framework.model.InteractiveGraphicalObject;
import KAGO_framework.view.DrawTool;
import beckerStructures.BeckerMap;
import my_project.Config;
import my_project.model.modes.Mode;
import my_project.model.modes.start.StartMode;

public class ModeController extends InteractiveGraphicalObject {
    BeckerMap<String, Mode> modes;
    String currentMode = "Start";
    public ModeController() {
        modes = new BeckerMap<>();
        modes.add("Start", new StartMode());
    }
    @Override
    public void update(double dt){
        if (modes.contains(currentMode)) {
            //Updates the current mode
            modes.get(currentMode).update(dt);
        }
    }
    @Override
    public void draw(DrawTool drawTool){
        if (modes.contains(currentMode)) {

            //Draws the Draw method of the current mode
            modes.get(currentMode).draw(drawTool);

            //Draws the UI on top of the current mode with 0 translation or scaling
            drawTool.push();
            drawTool.setTranslate(0, 0);
            drawTool.setScale(1);
            modes.get(currentMode).drawUI(drawTool);
            if (Config.DEBUG) drawTool.drawText(0,12, "Mode: " + currentMode);
            drawTool.pop();
        }
    }
}
