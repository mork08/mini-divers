package my_project.model.modes;

import KAGO_framework.view.DrawTool;

public abstract class Mode {

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
}
