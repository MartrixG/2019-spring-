package circularOrbit;

import javafx.util.Pair;
import position.Position;
import relationship.RelationShip;
import track.Track;
import types.ApplicationType;

import java.util.Iterator;
import java.util.List;

/**
 * A mutable ADT of a circular orbit with a center object and some objects on its track.
 * @author Martrix
 * @param <L> type of center object in this system.It must be immutable.
 * @param <E> type of object in this system's track.It must be immutable.
 */
public interface CircularOrbit<L, E> {
    /**
     * Create an new orbit with a String of file name.
     * @return A new empty orbit
     */
	public static CircularOrbit newCircularOrbit(ApplicationType type, String fileName){
		switch (type){
			case AtomStructure:
				return AtomStructure.creat(fileName);
			case TrackGame:
				return TrackGame.creat(fileName);
			case SocialNetwork:
				return SocialNetwork.creat(fileName);
		}
        throw new IllegalArgumentException();
    }
    /**
     * Add a new track in this system.
     * @param T label for the new track
     * @return True if the track is successfully inserted in this system.
     *          Else return false.
     */
    public boolean addTrack(Track T);

    /**
     *  Remove a track from this system,all the objects in this track
     *  is also removed.
     * @param T label of the track to remove.
     * @return True if the track and all the objects are removed.
     *          Else return false
     */
	public boolean removeTrack(Track T);

    /**
     * Add a center object in this system.
     * @param CenterObject label of the center object to add
     * @return True if the center object does not exit before.
     *          Else return false.
     */
	public boolean addCenterObject(L CenterObject);

    /**
     * Add an object in track T.
     * @param T label of the track.
     * @param object label of object to add in
     * @return  True if the object does not exit in this system before and the track T must
     *          exist. Else return false.
     */
	public boolean addPhysicalObject(Track T, E object);

	/**
	 * Remove an object in this system.
	 * @param object label of object to remove
	 * @return  True if the object does not exit in this system before and the track T must
	 *          exist. Else return false.
	 */
	public boolean removePhysicalObject(E object);

    /**
     * Build a relationship between object in tracks and center object
     * @param CenterObject label of center object
     * @param object label of the object in tracks
     * @param R label of the relationship
     * @return  If the two objects have no relationship before, return the now relationship.
     *          Else return the relation before, and replace the relationship.
     *          When the Relationship is null means delete the relationship.
     */
	public boolean addRelationC2P(L CenterObject, E object, RelationShip R);

    /**
     * build a relationship between two objects in tracks.
     * @param Object1 label of the first object
     * @param Object2 label of the second object
     * @param R label of the relationship
     * @return  If the two objects have no relationship before, return the now relationship.
     *          Else return the relation before, and replace the relationship.
     *          When the Relationship is null means delete the relationship.
     */
	public boolean addRelationP2P(E Object1, E Object2, RelationShip R);

    /**
     * Transform the Object from its track before to the new track T
     * @param object label of the object to move
     * @param T label of the track
     * @return The object's old track.
     */
	public Track trans(E object, Track T);

	/**
	 * This method is to process a list of all the param from a file name.
	 * @param fileName label of the file to open
	 */
	public List<Pair<String, List<String>>> processParameter(String fileName);

	/**
	 * Get a set of all the physical objects in this system.
	 * @return a set of objects
	 */
	public List<E> getPhysicalObjects();

	/**
	 * Get a set of all the physical objects in this system.
	 * @return a set of objects
	 */
	public List<E> getPhysicalObjects(Track T);

	/**
	 * Get the position of a physical object.
	 * @param object label of the phyical object
	 * @return a Position
	 */
	public Position getPhysicalObjectPosition(E object);

	public void setPosition(E object);

	/**
	 * Get the central object in this system.
	 * @return a center object
	 */
	public L getCenterObject();

	/**
	 * get the copy of all tracks.
	 * @return a set of tracks.
	 */
	public List<Track> getTracks();

	public List<Pair<Position, Position>> getStraight();

	public ApplicationType getType();

	public double getShang();

	public Iterator<E> iterator();

	public double getPhysicalDistance(String a,String b);

	public int getLogicalDistance(String a,String b);
}
