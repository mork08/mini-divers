package my_project.control;

import KAGO_framework.model.InteractiveGraphicalObject;
import com.sun.javafx.geom.Vec2d;

import java.awt.event.MouseEvent;

public class Mouse extends InteractiveGraphicalObject {
    private static Vec2d position;
    private static boolean[] button;
    private static Vec2d offset;
    private static Vec2d scale;
    private static Vec2d focusOffset;

    public Mouse() {
        position = new Vec2d();
        offset = new Vec2d(0,0);
        scale = new Vec2d(1,1);
        focusOffset = new Vec2d(0,0);
        button = new boolean[6];
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        position.x = e.getX();
        position.y = e.getY();
        //System.out.println("Mouse Moved to: " + position.x + "|" + position.y);
    }
    @Override
    public void mousePressed(MouseEvent e) {
        button[e.getButton()] = true;
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        button[e.getButton()] = false;
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        position.x = e.getX();
        position.y = e.getY();
        //System.out.println("Mouse Dragged to: " + position.x + "|" + position.y);
    }
    public static Vec2d getPosition() {
        return position;
    }
    public static Vec2d getTranslatedPosition() {
        return new Vec2d((position.x-focusOffset.x)/scale.x - offset.x, (position.y-focusOffset.y)/scale.y - offset.y);
    }
    public static boolean isDown(int b) {
        return button[b];
    }
    public static void setTranslationAndScale(double tx, double ty, double sx, double sy, double fx, double fy) {
        scale.x = sx;
        scale.y = sy;
        offset.x = tx;
        offset.y = ty;
        focusOffset.x = fx;
        focusOffset.y = fy;
    }
}
