package my_project.model.spritesheetSystem.animation.entity;

import my_project.model.spritesheetSystem.animation.IAnimationState;

public interface IEntityAnimationState extends IAnimationState {
    EntityDirection getDirection();
    EntityState getState();
}
