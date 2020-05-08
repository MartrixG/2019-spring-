package track;

/**
 * A implement of abstract class - Track.This implement is focus on the track looks like a oval.
 * It contains semi-long-axis and semi-short-axis to distinguish from a circle
 * @author Martrix
 */
public abstract class OvalTrack extends Track {
    private Double semiLongAxis;
    private Double semiShortAxis;

    protected void setAxis(Double A, Double B){
        this.semiLongAxis=A;
        this.semiShortAxis=B;
    }
}
