package my_project.model.spritesheetSystem.animation;

import beckerStructures.BeckerList;

/***
 * @author Mark
 */
public interface IAnimationState {
    int getRowIndex();
    RangeInt getColumnRange();
    int getFrames();
    double getDuration();
    boolean isLoop();
    boolean isReverse();
    int getFrameWidth();
    int getFrameHeight();

    static <T extends Enum<T> & IAnimationState> BeckerList<T> fetch(Class<T> enumClass, int row, int column) {
        if (!enumClass.isEnum()) return new BeckerList<>();
        BeckerList<T> fetch = new BeckerList<>();
        for (var state : enumClass.getEnumConstants()) {
            if (state.getRowIndex() == row && state.getColumnRange().contains(column)) {
                fetch.append(state);
            }
        }
        return fetch;
    }
}
