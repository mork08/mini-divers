package my_project.control;

import KAGO_framework.model.InteractiveGraphicalObject;
import KAGO_framework.view.DrawTool;
import beckerStructures.BeckerMap;
import my_project.Config;
import my_project.model.modes.Mode;
import my_project.model.modes.map.MapMode;
import my_project.model.modes.planet.PlanetMode;
import my_project.model.modes.start.StartMode;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ModeController extends InteractiveGraphicalObject {
    double fps;
    BeckerMap<String, Mode> modes;
    String currentModeKey = Config.STARTING_MODE;
    Mode currentMode;
    public ModeController() {
        modes = new BeckerMap<>();
        modes.add("Start", new StartMode());
        modes.add("Planet", new PlanetMode());
        modes.add("Map", new MapMode());
        selectMode(currentModeKey);
    }
    @Override
    public void update(double dt){
        fps = 1/dt;

        //Updates the current mode
        currentMode.update(dt);

    }
    @Override
    public void draw(DrawTool drawTool){
        //Draws the Draw method of the current mode
        currentMode.draw(drawTool);

        //Draws the UI on top of the current mode with 0 translation or scaling
        drawTool.push();
        drawTool.setTranslate(0, 0);
        drawTool.setScale(1);
        currentMode.drawUI(drawTool);
        if (Config.DEBUG) {
            drawTool.setCurrentColor(new Color(255, 255, 255, 203));
            drawTool.drawFilledRectangle(0, 0, Config.WINDOW_WIDTH, 24 + 2);
            drawTool.setCurrentColor(Color.BLACK);
            drawTool.drawText(0, 12, "Mode: " + currentModeKey);
            drawTool.drawText(0, 24, "FPS: " + Math.floor(fps*10)/10);
        }
        drawTool.pop();
    }
    public void selectMode(String mode){
        currentModeKey = mode;
        currentMode = modes.get(mode);
    }

    public String getCurrentModeKey(){
        return currentModeKey;
    }
    @Override
    public void mousePressed(MouseEvent e){
        if(e.getButton() == 1){
            if(currentModeKey.equals("Map")){
                ((MapMode)modes.get("Map")).manageMouseClick(e);
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(currentModeKey.equals("Map")){
            ((MapMode)modes.get("Map")).manageMouseMove(e);
        }
    }
}