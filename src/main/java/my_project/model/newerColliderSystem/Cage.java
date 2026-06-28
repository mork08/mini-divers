package my_project.model.newerColliderSystem;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.control.ProgramController;

import java.awt.*;

public class Cage extends GraphicalObject {
    GraphicalObject parent;
    double distance;
    double thickness;
    Collider upCollider;
    Collider downCollider;
    Collider leftCollider;
    Collider rightCollider;
    Vec2d velocity;
    public Cage(double x, double y, double width, double height, double distance, double thickness) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.distance = distance;
        this.thickness = thickness;
        this.velocity = new Vec2d(0, 0);
        createCollider();
    }
    public Cage(GraphicalObject parent, double distance, double thickness) {
        this.parent = parent;
        this.distance = distance;
        this.thickness = thickness;
        createCollider();
    }

    @Override
    public void update(double dt) {
        processMovement(dt);
        updatePosition();
        upCollider.update(dt);
        downCollider.update(dt);
        leftCollider.update(dt);
        rightCollider.update(dt);
    }
    public void updatePosition() {
        if (parent != null) {
            x = parent.getX();
            y = parent.getY();
            width = parent.getWidth();
            height = parent.getHeight();
        }
        upCollider.updatePosition();
        downCollider.updatePosition();
        leftCollider.updatePosition();
        rightCollider.updatePosition();
    }
    @Override
    public void draw(DrawTool drawTool) {
        upCollider.draw(drawTool);
        downCollider.draw(drawTool);
        leftCollider.draw(drawTool);
        rightCollider.draw(drawTool);
    }
    private void createCollider() {
        upCollider = new Collider(this, "up", distance, thickness);
        downCollider = new Collider(this, "down", distance, thickness);
        leftCollider = new Collider(this, "left", distance, thickness);
        rightCollider = new Collider(this, "right", distance, thickness);

    }
    public Collider getUpCollider() {
        return upCollider;
    }
    public Collider getDownCollider() {
        return downCollider;
    }
    public Collider getLeftCollider() {
        return leftCollider;
    }
    public Collider getRightCollider() {
        return rightCollider;
    }


    protected void pathFind(double targetX,double targetY){

    }

    public void drawHitbox(DrawTool drawTool) {
        drawTool.drawFilledRectangle(x, y, width, height);
    }
    protected void move(double x, double y,double dt){
        moveY(y, dt);
        moveX(x, dt);
    }
    protected void moveX(double mx,double dt){
        if (mx != 0) {
            float precision = 0.01f;
            double dir = ProgramController.clamp(-1, 1, Math.abs(mx) / mx);
            int maxIterations = 1000;
            int iteration = 0;
            Collider colliderToCheck = this.getLeftCollider();
            if (mx > 0) {
                colliderToCheck = this.getRightCollider();
            }
            if (!CollisionHandler.collidesWithTile(colliderToCheck)) {
                x += mx * dt;
            }

            while (CollisionHandler.collidesWithTile(colliderToCheck) && iteration < maxIterations) {
                x -= precision * dir;
                velocity.x = 0;
                iteration++;
            }
        }


        //System.out.println("iteration x: " + iteration);
    }
    protected void moveY(double my,double dt){
        if (my != 0) {
            float precision = 0.1f;
            double dir = ProgramController.clamp(-1, 1, Math.abs(my) / my);
            int maxIterations = 1000;
            int iteration = 0;
            Collider colliderToCheck = this.getUpCollider();
            if (my > 0) {
                colliderToCheck = this.getDownCollider();
            }
            if (!CollisionHandler.collidesWithTile(colliderToCheck)) {
                y += my * dt;
            }

            while (CollisionHandler.collidesWithTile(colliderToCheck) && iteration < maxIterations) {
                y -= precision * dir;

                velocity.y = 0;
                iteration++;
            }
        }

        //System.out.println("iteration y: " + iteration);
    }
    protected void processMovement(double dt){
        double frictionX = 0.7;
        double frictionY = 0.99;

        move(velocity.x, velocity.y, dt);


        velocity.x *= frictionX;
        velocity.y *= frictionY;
    }

    public void setVelocity(double x, double y) {
        setVelocityX(x);
        setVelocityY(y);
    }

    public void setVelocityX(double x) {
        this.velocity.x = x;
    }
    public void setVelocityY(double y) {
        this.velocity.y = y;
    }

    public Vec2d getVelocity() {
        return velocity;
    }
}
