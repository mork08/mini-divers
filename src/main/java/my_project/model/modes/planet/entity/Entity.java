package my_project.model.modes.planet.entity;

import KAGO_framework.view.DrawTool;
import my_project.model.modes.planet.collisionSystem.Collider;
import my_project.model.modes.planet.collisionSystem.CollisionManager;
import my_project.model.spritesheetSystem.animation.AnimationRenderer;
import my_project.model.spritesheetSystem.animation.entity.EntityState;
import my_project.model.spritesheetSystem.animation.entity.IEntityAnimationState;

import java.util.UUID;

public abstract class Entity<T extends Enum<T> & IEntityAnimationState> {

    protected final String id;
    protected final AnimationRenderer renderer;
    protected final Collider collider;
    protected double x;
    protected double y;
    protected double width;
    protected double height;

    public Entity(AnimationRenderer renderer, Collider collider, double x, double y, double width, double height) {
        this(UUID.randomUUID().toString(), renderer, collider, x, y, width, height);
    }

    public Entity(String id, AnimationRenderer<T> renderer, Collider collider, double x, double y, double width, double height) {
        this.id = id;
        this.renderer = renderer;
        this.collider = collider;
        if (this.collider != null) CollisionManager.addCollider(this.collider);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        EntityManager.register(this);
    }

    public void update(double dt) {
        if (this.renderer != null) {
            if (!this.renderer.isRunning()) this.renderer.start();
            this.renderer.update(dt);
        }
        if (this.collider != null) {
            this.x = this.collider.getX();
            this.y = this.collider.getY();
        }
    }

    public void draw(DrawTool drawTool) {
        if (this.renderer != null && this.renderer.getCurrentFrame() != null) {
            drawTool.push();
            drawTool.getGraphics2D().drawImage(this.renderer.getCurrentFrame(), (int) this.getX(), (int) this.getY(), (int) this.width, (int) this.height, null);
            drawTool.pop();
        }
    }

    public void keypressed(int key) {}

    public boolean isCurrentAnimation(EntityState state) {
        var anim = (IEntityAnimationState) this.renderer.getCurrentAnimation().getState();
        return anim.getState() == state;
    }

    public Collider getCollider() {
        return this.collider;
    }

    public AnimationRenderer getRenderer() {
        return this.renderer;
    }

    public String getId() {
        return this.id;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }
}
