package my_project.model.spritesheetSystem.animation.states;

import my_project.model.spritesheetSystem.animation.RangeInt;
import my_project.model.spritesheetSystem.animation.entity.EntityDirection;
import my_project.model.spritesheetSystem.animation.entity.EntityState;
import my_project.model.spritesheetSystem.animation.entity.IEntityAnimationState;

public enum MinirobotAnimationState implements IEntityAnimationState {
    ;

    @Override
    public EntityDirection getDirection() {
        return null;
    }

    @Override
    public EntityState getState() {
        return null;
    }

    @Override
    public int getRowIndex() {
        return 0;
    }

    @Override
    public RangeInt getColumnRange() {
        return null;
    }

    @Override
    public int getFrames() {
        return 0;
    }

    @Override
    public double getDuration() {
        return 0;
    }

    @Override
    public boolean isLoop() {
        return false;
    }

    @Override
    public boolean isReverse() {
        return false;
    }

    @Override
    public int getFrameWidth() {
        return 0;
    }

    @Override
    public int getFrameHeight() {
        return 0;
    }
}
