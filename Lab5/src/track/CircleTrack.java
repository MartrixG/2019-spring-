package track;

import types.TrackType;

/**
 * A implement of abstract class - Track.This implement is focus on the track looks like a circle.
 * It only contains radius to distinguish from a oval track.
 *
 * @author Martrix
 */
public abstract class CircleTrack extends Track {
    protected Double radius;

    public CircleTrack(int n) {
        this.noNumber = n;
        this.Type = TrackType.CIRCLE;
        this.radius = Double.valueOf(n);
    }

    public Double getRadius() {
        return radius;
    }
}
