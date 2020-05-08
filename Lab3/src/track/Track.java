package track;
import types.TrackType;

/**
 * A immutable ADT to describe a track in a circular orbit.In this ADT.
 * @author Martrix
 */

public abstract class Track {
    protected Integer noNumber;
    protected TrackType Type;

    /**
     * This method means to get the TrackType of this track.
     * @return when TrackType==CIRCLE the return a String of "CIRCLE".
     *         when TrackType==OVAL the return a String of "OVAL".
     */
    public String getTrackType(){
        switch (Type){
            case CIRCLE:
                return "CIRCLE";
            case OVAL:
                return "OVAL";
        }
        throw new IllegalArgumentException();
    }

    /**
     * Get the noNumber.
     * @return noNumber
     */
    public Integer getNoNumber(){
        return noNumber;
    }

}
