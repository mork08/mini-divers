package my_project.model.modes.planet.entity.enemy;

import my_project.model.modes.planet.entity.Entity;
import my_project.model.newerColliderSystem.Cage;
import my_project.model.spritesheetSystem.animation.AnimationRenderer;
import my_project.model.spritesheetSystem.animation.entity.EntityDirection;
import my_project.model.spritesheetSystem.animation.entity.EntityState;
import my_project.model.spritesheetSystem.animation.entity.IEntityAnimationState;

public abstract class EntityEnemy<T extends Enum<T> & IEntityAnimationState> extends Entity<T> {
    private static int deathCounter = 0;
    protected Entity<?> target;
    protected int range;

    public EntityEnemy(String id, AnimationRenderer<T> renderer, Cage colliderCage, double x, double y, double width, double height, int range) {
        super(id, renderer, colliderCage, x, y, width, height);
        this.range = range;
    }

    @Override
    public void update(double dt) {
        if(health <= 0){
            deathCounter++;
        }
        super.update(dt);
        this.walkToTarget();
    }

    private void walkToTarget() {
        if (this.target == null || this.colliderCage == null) {
            return;
        }

        double speed = 120;

        double dx = this.target.getX() - this.getX();
        double dy = this.target.getY() - this.getY();

        double distance = Math.sqrt(dx * dx + dy * dy);

        this.updateDirectionToTarget(dx, dy);
        if (distance <= this.range) {
            this.colliderCage.setVelocity(0, 0);
            this.setIdleAnimation();
            return;
        }

        double dirX = dx / distance;
        double dirY = dy / distance;

        double velX = dirX * speed;
        double velY = dirY * speed;

        this.colliderCage.setVelocity(velX, velY);

        this.x = this.colliderCage.getX();
        this.y = this.colliderCage.getY();

        EntityState state = velX == 0 && velY == 0
                ? EntityState.IDLE
                : EntityState.WALKING;

        T animationState = getStateForEntityState(this.direction, state);

        if (animationState != null) {
            this.renderer.switchState(animationState);
        }
    }

    private void updateDirectionToTarget(double dx, double dy) {
        if (Math.abs(dx) > Math.abs(dy)) {
            if (dx > 0) {
                this.direction = EntityDirection.RIGHT;

            } else if (dx < 0) {
                this.direction = EntityDirection.LEFT;
            }
        } else {
            if (dy > 0) {
                this.direction = EntityDirection.DOWN;

            } else if (dy < 0) {
                this.direction = EntityDirection.UP;

            }
        }
    }

    protected abstract void setIdleAnimation();

    public void setTarget(Entity<?> target) {
        this.target = target;
    }

    public Entity<?> getTarget() {
        return this.target;
    }

    public static int getDeathCounter() {
        return deathCounter;
    }
    public static void resetDeathCounter() {
        deathCounter = 0;
    }
}
