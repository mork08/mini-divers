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
    private boolean waitingInRange = false;

    public EntityEnemy(String id, AnimationRenderer<T> renderer, Cage colliderCage, double x, double y, double width, double height, int range) {
        super(id, renderer, colliderCage, x, y, width, height);
        this.range = range;
    }

    @Override
    public void update(double dt) {
        if (this.health <= 0) {
            deathCounter++;
            this.destroy();
            return;
        }

        this.walkToTarget();

        if (this.renderer != null) {
            if (!this.renderer.isRunning()) this.renderer.start();
            this.renderer.update(dt);
        }

        if (this.colliderCage != null) {
            this.colliderCage.update(dt);
            this.adjustPositionToTexture();
        }
    }

    private void walkToTarget() {
        if (this.target == null || this.colliderCage == null) {
            return;
        }

        double speed = 120;

        double dx = this.target.getX() - this.getX();
        double dy = this.target.getY() - this.getY();

        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance <= this.range) {
            this.colliderCage.setVelocity(0, 0);
            this.updateDirectionToTarget(dx, dy);
            this.renderer.switchState(this.getStateForEntityState(this.direction, EntityState.IDLE));
            return;
        }

        double dirX = dx / distance;
        double dirY = dy / distance;

        double velX = dirX * speed;
        double velY = dirY * speed;

        this.colliderCage.setVelocity(velX, velY);

        this.adjustPositionToTexture();

        EntityState state = this.colliderCage.getVelocity().x == 0 && this.colliderCage.getVelocity().y == 0
                ? EntityState.IDLE
                : EntityState.WALKING;

        this.updateDirectionToTarget(dx, dy);
        T animationState = getStateForEntityState(this.direction, state);

        if (animationState != null) {
            this.renderer.switchState(animationState);
        }
    }

    protected void updateDirectionToTarget(double dx, double dy) {
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

    protected abstract void adjustPositionToTexture();

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
