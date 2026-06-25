package my_project.model.modes.planet.entity;

import KAGO_framework.control.ViewController;
import KAGO_framework.view.DrawTool;
import my_project.model.modes.planet.collisionSystem.Collider;
import my_project.model.spritesheetSystem.animation.AnimationRenderer;
import my_project.model.spritesheetSystem.animation.entity.EntityDirection;
import my_project.model.spritesheetSystem.animation.entity.EntityState;
import my_project.model.spritesheetSystem.animation.states.CharacterAnimationState;

import java.awt.event.KeyEvent;

public class EntityPlayer extends Entity<CharacterAnimationState> {

    private EntityDirection direction;

    public EntityPlayer(String id, double x, double y, double width, double height) {
        super(
            id,
            new AnimationRenderer<>(
                    "/graphic/entities/player.png",
                    2,
                    4,
                    32,
                    32,
                    CharacterAnimationState.IDLE_DOWN
            ),
            new Collider(x, y, width, height),
            x,
            y,
            width,
            height
        );
        this.direction = EntityDirection.DOWN;
    }

    @Override
    public void update(double dt) {
        super.update(dt);
        this.walk();
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

        this.collider.setLinVel(velX, velY);

        this.x = this.collider.getX();
        this.y = this.collider.getY();

        EntityState state = velX == 0 && velY == 0
                ? EntityState.IDLE
                : EntityState.WALKING;

        CharacterAnimationState animationState = getStateForEntityState(this.direction, state);

        if (animationState != null) {
            this.renderer.switchState(animationState);
        }
    }

    @Override
    public void draw(DrawTool drawTool) {
        if (this.renderer != null && this.renderer.getCurrentFrame() != null) {

            drawTool.drawImageToSize(this.renderer.getCurrentFrame(), (int) this.getX(), (int) this.getY(), (int) this.width, (int) this.height);

        }
    }

    private CharacterAnimationState getStateForEntityState(EntityDirection direction, EntityState state) {
        for (CharacterAnimationState anim : CharacterAnimationState.values()) {
            if (anim.getDirection() == direction && anim.getState() == state) {
                return anim;
            }
        }
        return null;
    }
}
