package my_project.model.modes.planet.entity.projectile;

import KAGO_framework.view.DrawTool;
import my_project.model.modes.planet.entity.Entity;
import my_project.model.newerColliderSystem.Cage;

import java.awt.*;
import java.util.UUID;

public class EntityProjectile extends Entity {

    private static final double SIZE = 3;
    private static final double SPEED = 260;
    private static final double MAX_LIFETIME = 3.0;

    private final Entity<?> target;
    private final double damage;

    private double velocityX;
    private double velocityY;
    private double lifetime;
    private Color color;

    public EntityProjectile(double startX, double startY, double targetX, double targetY, Entity<?> target, double damage, Color color) {
        super(
                UUID.randomUUID().toString(),
                null,
                new Cage(startX - SIZE / 2, startY - SIZE / 2, SIZE, SIZE, 0, 2),
                startX - SIZE / 2,
                startY - SIZE / 2,
                SIZE,
                SIZE
        );

        this.color = color;

        this.target = target;
        this.damage = damage;
        this.health = 1;

        double dx = targetX - startX;
        double dy = targetY - startY;
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance == 0) {
            this.velocityX = 0;
            this.velocityY = 0;
        } else {
            this.velocityX = dx / distance * SPEED;
            this.velocityY = dy / distance * SPEED;
        }

        this.colliderCage.setVelocity(this.velocityX, this.velocityY);
    }

    @Override
    public void update(double dt) {
        this.lifetime += dt;

        if (this.lifetime >= MAX_LIFETIME) {
            this.destroy();
            return;
        }

        this.colliderCage.setVelocity(this.velocityX, this.velocityY);

        super.update(dt);

        boolean hitWall =
                Math.abs(this.velocityX) > 0.01 && Math.abs(this.colliderCage.getVelocity().x) < 0.01
                        || Math.abs(this.velocityY) > 0.01 && Math.abs(this.colliderCage.getVelocity().y) < 0.01;

        if (hitWall) {
            this.destroy();
            return;
        }

        this.colliderCage.setVelocity(this.velocityX, this.velocityY);

        if (this.target != null && overlaps(this.target)) {
            this.target.damage(this.damage);
            this.destroy();
        }
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(Color.WHITE);
        drawTool.drawFilledCircle(
                this.getX() + this.getWidth() / 2,
                this.getY() + this.getHeight() / 2,
                this.getWidth() / 2
        );
        drawTool.setCurrentColor(color);
        drawTool.drawCircle(
                this.getX() + this.getWidth() / 2,
                this.getY() + this.getHeight() / 2,
                this.getWidth() / 2
        );
    }

    private boolean overlaps(Entity<?> other) {
        return this.getX() < other.getX() + other.getWidth()
                && this.getX() + this.getWidth() > other.getX()
                && this.getY() < other.getY() + other.getHeight()
                && this.getY() + this.getHeight() > other.getY();
    }
}
