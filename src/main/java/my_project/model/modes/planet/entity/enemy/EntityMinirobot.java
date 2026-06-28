package my_project.model.modes.planet.entity.enemy;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.model.modes.planet.Tilesystem.TileMap;
import my_project.model.modes.planet.entity.projectile.EntityProjectile;
import my_project.model.newerColliderSystem.Cage;
import my_project.model.spritesheetSystem.animation.AnimationRenderer;
import my_project.model.spritesheetSystem.animation.entity.EntityDirection;
import my_project.model.spritesheetSystem.animation.states.MinirobotAnimationState;

import java.awt.*;

public class EntityMinirobot extends EntityEnemy<MinirobotAnimationState> {

    private static int OFFSET_TEXTURE_COLLIDER = 10;
    private static final double FIRE_COOLDOWN = 3;
    private static final double SHOOT_RANGE = 100;
    private static final double DAMAGE = 5;

    private double fireTimer = 0;

    public EntityMinirobot(String id, double x, double y, double width, double height) {
        super(
            id,
            new AnimationRenderer<>(
                    "/graphic/entities/minirobot.png",
                    5,
                    4,
                    32,
                    32,
                    MinirobotAnimationState.IDLE_DOWN
            ),
            new Cage(x + OFFSET_TEXTURE_COLLIDER, y + OFFSET_TEXTURE_COLLIDER, 10, 10, 1, 2),
            x,
            y,
            width,
            height,
            80
        );
        this.direction = EntityDirection.DOWN;
        this.health = 50;
    }

    @Override
    public void update(double dt) {
        if (target == null) setTarget(TileMap.getPlayer());
        super.update(dt);
        fireTimer -= dt;

        if (canShoot()) {
            shoot();
            fireTimer = FIRE_COOLDOWN;
        }
    }

    private boolean canShoot() {
        if (target == null) return false;
        if (fireTimer > 0) return false;

        double dx = target.getX() - this.getX();
        double dy = target.getY() - this.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);

        return distance <= SHOOT_RANGE;
    }

    private void shoot() {
        if (target == null) return;

        Vec2d start = getProjectileStart();
        Vec2d targetPosition = new Vec2d(target.getX(), target.getY());

        new EntityProjectile(
                start.x,
                start.y,
                targetPosition.x,
                targetPosition.y,
                target,
                DAMAGE
        );
    }

    public Vec2d getProjectileStart() {
        Vec2d vec = new Vec2d();
        double minX = this.x + OFFSET_TEXTURE_COLLIDER, minY = this.y + OFFSET_TEXTURE_COLLIDER;

        switch (this.direction) {
            case UP -> vec.set(minX + 7.5, minY - 6);
            case DOWN -> vec.set(minX + 4, minY + this.colliderCage.getHeight() + 7);
            case LEFT -> vec.set(minX - 6.5, minY + 4.5);
            case RIGHT -> vec.set(minX + this.colliderCage.getWidth() + 7.5, minY + 6.5);
        }
        return vec;
    }

    @Override
    protected void adjustPositionToTexture() {
        this.x = this.colliderCage.getX() - OFFSET_TEXTURE_COLLIDER;
        this.y = this.colliderCage.getY() - OFFSET_TEXTURE_COLLIDER;
    }
}
