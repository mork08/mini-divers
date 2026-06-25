package my_project.model.spritesheetSystem.animation;

/***
 * @author Mark
 */
public record RangeInt(int start, int end) {

    public boolean contains(int value) {
        return this.start <= value && this.end >= value;
    }
}
