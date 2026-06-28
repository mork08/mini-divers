package my_project.model.modes.planet.entity;

import KAGO_framework.view.DrawTool;
import my_project.model.newerColliderSystem.Cage;
import my_project.model.spritesheetSystem.animation.AnimationRenderer;
import my_project.model.spritesheetSystem.animation.entity.EntityDirection;
import my_project.model.spritesheetSystem.animation.entity.EntityState;
import my_project.model.spritesheetSystem.animation.entity.IEntityAnimationState;

import java.util.UUID;

public abstract class Entity<T extends Enum<T> & IEntityAnimationState> {

    protected final String id;
    protected final AnimationRenderer renderer;
    protected final Cage colliderCage;
    protected double health;
    protected double x;
    protected double y;
    protected double width;
    protected double height;
    protected EntityDirection direction;

    private boolean destroy = false;

    public Entity(AnimationRenderer renderer, Cage colliderCage, double x, double y, double width, double height) {
        this(UUID.randomUUID().toString(), renderer, colliderCage, x, y, width, height);
    }

    public Entity(String id, AnimationRenderer<T> renderer, Cage colliderCage, double x, double y, double width, double height) {
        this.id = id;
        this.renderer = renderer;
        this.colliderCage = colliderCage;
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
        if (this.colliderCage != null) {
            colliderCage.update(dt);
            this.x = this.colliderCage.getX();
            this.y = this.colliderCage.getY();
        }
        if (health <= 0){
            this.destroy();
        }
    }

    public void draw(DrawTool drawTool) {
        if (this.renderer != null && this.renderer.getCurrentFrame() != null) {
            //colliderCage.draw(drawTool);
            drawTool.drawImageToSize(this.renderer.getCurrentFrame(), (int) this.getX(), (int) this.getY(), (int) this.width, (int) this.height);
        }
    }

    public boolean isCurrentAnimation(EntityState state) {
        var anim = (IEntityAnimationState) this.renderer.getCurrentAnimation().getState();
        return anim.getState() == state;
    }

    public void destroy() {
        this.destroy = true;
        EntityManager.unregister(this);
    }

    public boolean isDestroy() {
        return this.destroy;
    }

    public Cage getColliderCage() {
        return this.colliderCage;
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

    protected T getStateForEntityState(EntityDirection direction, EntityState state) {
        T[] values = (T[]) this.renderer.getCurrentAnimation().getState().getDeclaringClass().getEnumConstants();

        for (T anim : values) {
            if (anim.getDirection() == direction && anim.getState() == state) {
                return anim;
            }
        }
        return null;
    }

    public void damage(double damage) {
        health -= damage;
    }

    public double getHealth() {
        return health;
    }
}
