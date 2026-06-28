package my_project.model.spritesheetSystem.animation.states;

import my_project.model.spritesheetSystem.animation.RangeInt;
import my_project.model.spritesheetSystem.animation.entity.EntityDirection;
import my_project.model.spritesheetSystem.animation.entity.EntityState;
import my_project.model.spritesheetSystem.animation.entity.IEntityAnimationState;

public enum PlayerAnimationState implements IEntityAnimationState {
    IDLE_LEFT(EntityDirection.LEFT, EntityState.IDLE, 0, new RangeInt(1, 1), 1, 0.5),
    IDLE_UP(EntityDirection.UP, EntityState.IDLE, 0, new RangeInt(2, 2), 1, 0.5),
    IDLE_RIGHT(EntityDirection.RIGHT, EntityState.IDLE, 0, new RangeInt(3, 3), 1, 0.5),
    IDLE_DOWN(EntityDirection.DOWN, EntityState.IDLE, 0, new RangeInt(0, 0), 1, 0.5),

    WALK_LEFT(EntityDirection.LEFT, EntityState.WALKING, 2, new RangeInt(0, 3), 4, 0.4),
    WALK_UP(EntityDirection.UP, EntityState.WALKING, 3, new RangeInt(0, 3), 4, 0.4),
    WALK_RIGHT(EntityDirection.RIGHT, EntityState.WALKING, 4, new RangeInt(0, 3), 4, 0.4),
    WALK_DOWN(EntityDirection.DOWN, EntityState.WALKING, 1, new RangeInt(0, 3), 4, 0.4);

    private final EntityDirection direction;
    private final EntityState state;
    private final int rowIndex;
    private final RangeInt columnRange;
    private final int frames;
    private final double duration;
    private final boolean loop;
    private final boolean reverse;
    private final int frameWidth;
    private final int frameHeight;

    PlayerAnimationState(EntityDirection direction, EntityState state, int rowIndex, RangeInt columnRange, int frames, double duration) {
        this(direction, state, rowIndex, columnRange, frames, duration, true);
    }

    PlayerAnimationState(EntityDirection direction, EntityState state, int rowIndex, RangeInt columnRange, int frames, double duration, boolean loop) {
        this(direction, state, rowIndex, columnRange, frames, duration, loop, false);
    }

    PlayerAnimationState(EntityDirection direction, EntityState state, int rowIndex, RangeInt columnRange, int frames, double duration, boolean loop, boolean reverse) {
        this(direction, state, rowIndex, columnRange, frames, duration, loop, reverse, 0, 0);
    }

    PlayerAnimationState(EntityDirection direction, EntityState state, int rowIndex, RangeInt columnRange, int frames, double duration, boolean loop, boolean reverse, int frameWidth, int frameHeight) {
        this.direction = direction;
        this.state = state;
        this.rowIndex = rowIndex;
        this.columnRange = columnRange;
        this.frames = frames;
        this.duration = duration;
        this.loop = loop;
        this.reverse = reverse;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
    }

    @Override
    public EntityDirection getDirection() {
        return this.direction;
    }

    @Override
    public EntityState getState() {
        return this.state;
    }

    @Override
    public int getRowIndex() {
        return this.rowIndex;
    }

    @Override
    public RangeInt getColumnRange() {
        return this.columnRange;
    }

    @Override
    public int getFrames() {
        return this.frames;
    }

    @Override
    public double getDuration() {
        return this.duration;
    }

    @Override
    public boolean isLoop() {
        return this.loop;
    }

    @Override
    public boolean isReverse() {
        return this.reverse;
    }

    @Override
    public int getFrameWidth() {
        return this.frameWidth;
    }

    @Override
    public int getFrameHeight() {
        return this.frameHeight;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
