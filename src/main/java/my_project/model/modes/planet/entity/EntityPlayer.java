package my_project.model.modes.planet.entity;

import KAGO_framework.control.ViewController;
import KAGO_framework.view.DrawTool;
import my_project.model.newerColliderSystem.Cage;
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
            new Cage(x,y,10,10,1,2),
            x,
            y,
            width,
            height
        );
        this.direction = EntityDirection.DOWN;
    }

    @Override
    public void update(double dt) {
        colliderCage.update(dt);
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

        this.colliderCage.setVelocity(velX, velY);

        this.x = this.colliderCage.getX()+colliderCage.getWidth()/2;
        this.y = this.colliderCage.getY()+colliderCage.getHeight()/2;

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

            drawTool.drawImageToSize(this.renderer.getCurrentFrame(), (int) this.getX()-this.width/2, (int) this.getY()-this.height/2, (int) this.width, (int) this.height);
            colliderCage.draw(drawTool);
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
