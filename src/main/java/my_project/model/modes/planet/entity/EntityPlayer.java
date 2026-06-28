package my_project.model.modes.planet.entity;

import KAGO_framework.control.ViewController;
import KAGO_framework.view.DrawTool;
import my_project.control.Mouse;
import my_project.model.modes.planet.entity.projectile.EntityPlayerProjectile;
import my_project.model.newerColliderSystem.Cage;
import my_project.model.spritesheetSystem.animation.AnimationRenderer;
import my_project.model.spritesheetSystem.animation.entity.EntityDirection;
import my_project.model.spritesheetSystem.animation.entity.EntityState;
import my_project.model.spritesheetSystem.animation.states.PlayerAnimationState;

import java.awt.*;
import java.awt.event.KeyEvent;

public class EntityPlayer extends Entity<PlayerAnimationState> {

    private double targetX;
    private double targetY;

    private int shotsPerMagazine = 70;
    private int shotCounter = 70;
    private double reloadCooldown = 2.6;
    private double reloadTimer = 0.0;
    private double shotCooldown = 0.05;
    private double shotTimer = 0.0;
    public EntityPlayer(String id, double x, double y, double width, double height) {
        super(
                id,
                new AnimationRenderer<>(
                        "/graphic/entities/player.png",
                        5,
                        4,
                        32,
                        32,
                        PlayerAnimationState.IDLE_DOWN
                ),
                new Cage(x, y, 10, 10, 1, 2),
                x,
                y,
                width,
                height
        );
        this.direction = EntityDirection.DOWN;
        this.health = 100;
    }

    @Override
    public void update(double dt) {
        super.update(dt);
        reloadTimer -= dt;
        shotTimer -= dt;

        if (ViewController.isKeyDown(KeyEvent.VK_SPACE)) {
            if (reloadTimer < 0) {
                if (shotTimer < 0) {
                    new EntityPlayerProjectile(this.x, this.y, targetX, targetY, 15, new Color(44, 232, 245));
                    shotTimer = shotCooldown;
                    shotCounter--;
                }
            }
        }
        if (shotCounter < 0) {
            reloadTimer = reloadCooldown;
            shotCounter = shotsPerMagazine;
        }

        this.walk();
    }

    @Override
    public void destroy() {
        super.destroy();
        // TODO: Lose Screen
    }

    private void walk() {



        double speed = 120;
        double velX = 0;
        double velY = 0;

        if (ViewController.isKeyDown(KeyEvent.VK_W)) {
            velY -= speed;
            this.direction = EntityDirection.UP;
        }

        if (ViewController.isKeyDown(KeyEvent.VK_S)) {
            velY += speed;
            this.direction = EntityDirection.DOWN;
        }

        if (ViewController.isKeyDown(KeyEvent.VK_A)) {
            velX -= speed;
            this.direction = EntityDirection.LEFT;
        }

        if (ViewController.isKeyDown(KeyEvent.VK_D)) {
            velX += speed;
            this.direction = EntityDirection.RIGHT;
        }



        this.colliderCage.setVelocity(velX, velY);

        this.x = this.colliderCage.getX() + colliderCage.getWidth() / 2;
        this.y = this.colliderCage.getY() + colliderCage.getHeight() / 2;

        EntityState state = velX == 0 && velY == 0
                ? EntityState.IDLE
                : EntityState.WALKING;

        this.renderer.switchState(getStateForEntityState(this.direction, state));
    }

    @Override
    public void draw(DrawTool drawTool) {
        if (this.renderer != null && this.renderer.getCurrentFrame() != null) {
            int drawX = (int) (this.getX() - this.width / 2), drawY = (int) (this.getY() - this.height / 2);
            int offsetX = -5;

            drawTool.setCurrentColor(Color.DARK_GRAY);
            drawTool.drawFilledRectangle(drawX - offsetX, drawY + 5, (this.width + offsetX * 2), 2);

            drawTool.setCurrentColor(this.health < 60 ? this.health < 20 ? Color.RED : Color.YELLOW : Color.GREEN);
            drawTool.drawFilledRectangle(drawX - offsetX, drawY + 5, (this.width + offsetX * 2) * (this.health / 100), 2);

            drawTool.setCurrentColor(Color.WHITE);

            drawTool.drawImageToSize(this.renderer.getCurrentFrame(), drawX, drawY, (int) this.width, (int) this.height);
        }
        targetX = Mouse.getTranslatedPosition().x;
        targetY = Mouse.getTranslatedPosition().y;
    }
}