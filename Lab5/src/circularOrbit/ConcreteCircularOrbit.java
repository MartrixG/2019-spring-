package circularOrbit;

import exception.DependencyException;
import exception.GramarException;
import exception.InputException;
import javafx.util.Pair;
import position.Position;
import reader.Reader;
import track.CircleTrack;
import track.Track;
import types.ApplicationType;
import types.ioType.IOType;
import writer.Writer;

import java.util.*;

public abstract class ConcreteCircularOrbit<L, E> implements CircularOrbit<L, E> {
    List<E> physicalObjects = new ArrayList<>();
    List<Track> tracks = new ArrayList<>();
    Map<E, Position> positionOfPhysicalObjects = new HashMap<>();
    Map<E, Track> belongs = new HashMap<>();
    Map<Track, List<E>> trackContains = new HashMap<>();
    L centerObject = null;
    protected ApplicationType type;
    List<Pair<Position, Position>> straightLine = new ArrayList<>();
    private Random random = new Random();

    public void checkRep() {
        for (Track eachT : tracks) {
            assert (eachT != null);
        }
        for (E eachE : physicalObjects) {
            assert (eachE != null);
            //assert (belongs.get(eachE)!=null);
        }
    }

    @Override
    public double getShang() {
        double ans = 0;
        int num = 0;
        for (Track eachT : trackContains.keySet()) {
            num++;
            ans += num * trackContains.get(eachT).size();
        }
        ans /= physicalObjects.size();
        return ans;
    }

    @Override
    public ApplicationType getType() {
        return type;
    }

    @Override
    public List<Pair<Position, Position>> getStraight() {
        return straightLine;
    }

    @Override
    public boolean addTrack(Track t) throws GramarException {
        if (tracks.contains(t)) {
            throw new GramarException("The Track to add has existed.");
        } else {
            tracks.add(t);
            trackContains.put(t, new ArrayList<>());
            checkRep();
            return true;
        }
    }

    @Override
    public boolean removeTrack(Track t) throws GramarException, DependencyException {
        if (!tracks.contains(t)) {
            throw new GramarException("The track input does not exist.");
        } else {
            for (E eachObject : trackContains.get(t)) {
                positionOfPhysicalObjects.remove(eachObject);
                physicalObjects.remove(eachObject);
                belongs.remove(eachObject);
            }
            trackContains.remove(t);
            tracks.remove(t);
            checkRep();
            return true;
        }
    }

    @Override
    public boolean addCenterObject(L centerObject) throws InputException {
        if (this.centerObject == null) {
            this.centerObject = centerObject;
            checkRep();
            return true;
        } else {
            throw new InputException("Too many center object");
        }
    }

    @Override
    public boolean addPhysicalObject(Track t, E object) throws GramarException, DependencyException {
        if (!tracks.contains(t)) {
            throw new GramarException("Input track does not exist.");
        } else {
            physicalObjects.add(object);
            belongs.put(object, t);
            trackContains.get(t).add(object);
            checkRep();
            return true;
        }
    }

    @Override
    public boolean removePhysicalObject(E object) throws GramarException, DependencyException {
        if (!physicalObjects.contains(object)) {
            throw new GramarException("No such element.");
        } else {
            for (Track eachTrack : tracks) {
                trackContains.get(eachTrack).remove(object);
            }
            positionOfPhysicalObjects.remove(object);
            physicalObjects.remove(object);
            belongs.remove(object);
            checkRep();
            return true;
        }
    }

    @Override
    public List<E> getPhysicalObjects() {
        return new ArrayList<>(physicalObjects);
    }

    @Override
    public List<E> getPhysicalObjects(Track t) {
        return trackContains.get(t);
    }

    @Override
    public Position getPhysicalObjectPosition(E object) throws GramarException {
        if (positionOfPhysicalObjects.get(object) == null) {
            throw new GramarException("No such element.");
        } else {
            return positionOfPhysicalObjects.get(object);
        }
    }

    @Override
    public void setPosition(E object) {
        positionOfPhysicalObjects.put(object, new Position(((CircleTrack) belongs.get(object)).getRadius(), random.nextDouble() * 2 * Math.PI, 2));
        checkRep();
    }

    @Override
    public L getCenterObject() {
        return centerObject;
    }

    @Override
    public List<Track> getTracks() {
        return new ArrayList<>(tracks);
    }

    @Override
    public List<Pair<String, List<String>>> processParameter(String fileName, IOType type) {
        return Reader.getArgs(fileName, type);
    }

    @Override
    public Iterator<E> iterator() {
        return new Citr();
    }

    private class Citr implements Iterator<E> {
        int cur = 0;

        @Override
        public boolean hasNext() {
            return cur < physicalObjects.size();
        }

        @Override
        public E next() {
            if (cur < physicalObjects.size()) {
                cur++;
                return physicalObjects.get(cur - 1);
            } else {
                return null;
            }
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }
    }

    @Override
    public void outToFile() {
        Writer.writeToFile(this.toString(), this.fileName());
    }

    @Override
    public void outToFile(String fileName) {
        Writer.writeToFile(this.toString(), fileName);
    }

    @Override
    public double getPhysicalDistance(String a, String b) {
        return -1;
    }
}
