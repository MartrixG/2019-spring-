package circularOrbit;

import exception.DependencyException;
import exception.GramarException;
import exception.InputException;
import javafx.util.Pair;
import position.Position;
import relationship.RelationShip;
import track.Track;
import types.ApplicationType;
import types.ioType.IOType;

import java.util.Iterator;
import java.util.List;

/**
 * A mutable ADT of a circular orbit with a center object and some objects on its track.
 *
 * @param <L> type of center object in this system.It must be immutable.
 * @param <E> type of object in this system's track.It must be immutable.
 * @author Martrix
 */
public interface CircularOrbit<L, E> {
    /**
     * Create an new orbit with a String of file name.
     *
     * @return A new empty orbit
     */
    static CircularOrbit newCircularOrbit(ApplicationType type, String fileName, IOType iotype)
            throws InputException, DependencyException, GramarException {
        switch (type) {
            case AtomStructure:
                return AtomStructure.creat(fileName, iotype);
            case TrackGame:
                return TrackGame.creat(fileName, iotype);
            case SocialNetwork:
                return SocialNetwork.creat(fileName, iotype);
            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     * Add a new track in this system.
     *
     * @param t label for the new track
     * @return True if the track is successfully inserted in this system.
     * Else return false.
     */
    boolean addTrack(Track t) throws GramarException;

    /**
     * Remove a track from this system,all the objects in this track
     * is also removed.
     *
     * @param t label of the track to remove.
     * @return True if the track and all the objects are removed.
     * Else return false
     */
    boolean removeTrack(Track t) throws GramarException, DependencyException;

    /**
     * Add a center object in this system.
     *
     * @param centerObject label of the center object to add
     * @return True if the center object does not exit before.
     * Else return false.
     */
    boolean addCenterObject(L centerObject) throws InputException;

    /**
     * Add an object in track T.
     *
     * @param t      label of the track.
     * @param object label of object to add in
     * @return True if the object does not exit in this system before and the track T must
     * exist. Else return false.
     */
    boolean addPhysicalObject(Track t, E object) throws GramarException, DependencyException;

    /**
     * Remove an object in this system.
     *
     * @param object label of object to remove
     * @return True if the object does not exit in this system before and the track T must
     * exist. Else return false.
     */
    boolean removePhysicalObject(E object) throws GramarException, DependencyException;

    /**
     * Build a relationship between object in tracks and center object
     *
     * @param centerObject label of center object
     * @param object       label of the object in tracks
     * @param r            label of the relationship
     * @return If the two objects have no relationship before, return the now relationship.
     * Else return the relation before, and replace the relationship.
     * When the Relationship is null means delete the relationship.
     */
    boolean addRelationC2P(L centerObject, E object, RelationShip r) throws GramarException, DependencyException;

    /**
     * build a relationship between two objects in tracks.
     *
     * @param object1 label of the first object
     * @param object2 label of the second object
     * @param r       label of the relationship
     * @return If the two objects have no relationship before, return the now relationship.
     * Else return the relation before, and replace the relationship.
     * When the Relationship is null means delete the relationship.
     */
    boolean addRelationP2P(E object1, E object2, RelationShip r) throws GramarException, DependencyException;

    /**
     * Transform the Object from its track before to the new track T
     *
     * @param object label of the object to move
     * @param t      label of the track
     * @return The object's old track.
     */
    Track trans(E object, Track t) throws GramarException;

    /**
     * This method is to process a list of all the param from a file name.
     *
     * @param fileName label of the file to open
     */
    List<Pair<String, List<String>>> processParameter(String fileName, IOType type);

    /**
     * Get a set of all the physical objects in this system.
     *
     * @return a set of objects
     */
    List<E> getPhysicalObjects();

    /**
     * Get a set of all the physical objects in this system.
     *
     * @return a set of objects
     */
    List<E> getPhysicalObjects(Track t);

    /**
     * Get the position of a physical object.
     *
     * @param object label of the phyical object
     * @return a Position
     */
    Position getPhysicalObjectPosition(E object) throws GramarException;

    void setPosition(E object);

    /**
     * Get the central object in this system.
     *
     * @return a center object
     */
    L getCenterObject();

    /**
     * get the copy of all tracks.
     *
     * @return a set of tracks.
     */
    List<Track> getTracks();

    List<Pair<Position, Position>> getStraight();

    ApplicationType getType();

    double getShang();

    Iterator<E> iterator();

    double getPhysicalDistance(String a, String b) throws GramarException;

    int getLogicalDistance(String a, String b) throws GramarException;

    void outToFile();

    void outToFile(String fileName);

    String fileName();
}
