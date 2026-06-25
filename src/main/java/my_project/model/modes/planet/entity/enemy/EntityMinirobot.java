package my_project.model.modes.planet.entity.enemy;

import my_project.model.modes.planet.collisionSystem.Collider;
import my_project.model.spritesheetSystem.animation.AnimationRenderer;
import my_project.model.spritesheetSystem.animation.entity.EntityDirection;
import my_project.model.spritesheetSystem.animation.entity.EntityState;
import my_project.model.spritesheetSystem.animation.states.MinirobotAnimationState;

public class EntityMinirobot extends EntityEnemy<MinirobotAnimationState> {

    public EntityMinirobot(String id, double x, double y, double width, double height) {
        super(
            id,
            new AnimationRenderer<>(
                    "/graphic/entities/minirobot.png",
                    2,
                    4,
                    32,
                    32,
                    MinirobotAnimationState.IDLE_DOWN
            ),
            new Collider(x, y, width, height),
            x,
            y,
            width,
            height,
            100
        );
        this.direction = EntityDirection.DOWN;
    }

    @Override
    protected void setIdleAnimation() {
        this.renderer.switchState(this.getStateForEntityState(this.direction, EntityState.IDLE));
    }

    @Override
    public void update(double dt) {
        super.update(dt);
        System.out.println("UPDATE");
    }
}
