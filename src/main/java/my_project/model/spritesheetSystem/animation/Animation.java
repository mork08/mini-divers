package my_project.model.spritesheetSystem.animation;

import beckerStructures.BeckerList;

import java.awt.image.BufferedImage;

/***
 * @author Mark
 */
public class Animation<T extends Enum<T> & IAnimationState> {

    private final T state;
    private final BeckerList<BufferedImage> frames;
    private final double duration;
    private final boolean loop;
    private final boolean reverse;
    private final double durationPerFrame;

    /**
     * Erstellt eine Animation aus Frames und Parametern.
     *
     * @param state Animations-State
     * @param frames Frame-Liste
     * @param duration Gesamtdauer
     * @param loop Loop-Flag
     * @param reverse Reverse-Flag
     */
    public Animation(T state, BeckerList<BufferedImage> frames, double duration, boolean loop, boolean reverse) {
        this.state = state;
        this.frames = frames;
        this.duration = duration;
        this.loop = loop;
        this.reverse = reverse;
        this.durationPerFrame = duration / frames.getLength();
    }

    /**
     * @return Animations-State
     */
    public T getState() {
        return this.state;
    }

    /**
     * @return Frames der Animation
     */
    public BeckerList<BufferedImage> getFrames() {
        return this.frames;
    }

    /**
     * @return Gesamtdauer der Animation
     */
    public double getDuration() {
        return this.duration;
    }

    /**
     * @return true, wenn reverse aktiviert ist
     */
    public boolean isReverse() {
        return this.reverse;
    }

    /**
     * @return true, wenn loop aktiviert ist
     */
    public boolean isLoop() {
        return this.loop;
    }

    /**
     * @return Dauer pro Frame
     */
    public double getDurationPerFrame() {
        return this.durationPerFrame;
    }

    /**
     * @return Debug-String der Animation
     */
    @Override
    public String toString() {
        return "Animation{" +
                "state=" + state +
                ", duration=" + duration +
                ", loop=" + loop +
                ", reverse=" + reverse +
                ", durationPerFrame=" + durationPerFrame +
                ", frames=" + frames.getLength() +
                '}';
    }
}
