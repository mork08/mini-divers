package my_project.model.modes;

import KAGO_framework.view.DrawTool;
import my_project.control.ModeController;

public abstract class Mode {
    private String wantedMode;
    protected ModeController controller;
    public Mode(ModeController modeController) {
        controller = modeController;
    }
    public void update(double dt){

    }
    /* Draws content on top of the

     */
    public void draw(DrawTool drawTool){

    }
    /* Draws on top of the draw method without translation or scale

     */
    public void drawUI(DrawTool drawTool){

    }
    public void switchMode(String mode){
        wantedMode = mode;
    }
    public String getWantedMode(){
        return wantedMode;
    }
    public abstract void launch();
}

