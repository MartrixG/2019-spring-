package circularOrbit;

import centralObject.AtomicNucleus;
import exception.DependencyException;
import exception.GramarException;
import exception.InputException;
import javafx.util.Pair;
import memento.Caretaker;
import memento.Originator;
import physicalObject.Electronic;
import position.Position;
import relationship.RelationShip;
import track.ElectronShells;
import track.Track;
import types.ApplicationType;
import types.atomStructureTypes.AtomStructureParamType;
import types.ioType.IOType;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import static java.lang.Integer.valueOf;

public class AtomStructure extends ConcreteCircularOrbit<AtomicNucleus, Electronic> {
    private int numOfNewFile = 0;
    private Originator originator = new Originator();
    private Caretaker caretaker = new Caretaker(originator);

    static AtomStructure creat(String fileName, IOType type) throws InputException, DependencyException, GramarException {
        AtomStructure reA = new AtomStructure();
        reA.init(reA.processParameter(fileName, type));
        return reA;
    }

    public void checkRep() {
        super.checkRep();
    }

    @Override
    public Track trans(Electronic object, Track t) throws GramarException {
        if (!physicalObjects.contains(object)) {
            throw new GramarException("Selected track does not have a electronic.");
        } else if (!tracks.contains((t))) {
            throw new GramarException("The track does not exist.");
        } else {
            originator.setState(new Pair<>(object, new Pair<>(belongs.get(object), t)));
            caretaker.createMemento();
            checkRep();
            return doTrans(object, t);
        }
    }

    private Track doTrans(Electronic object, Track t) {
        positionOfPhysicalObjects.put(object, new Position(((ElectronShells) t).getRadius(), new Random().nextDouble() * 2 * Math.PI, 2));
        Track oldTrack = belongs.get(object);
        trackContains.get(oldTrack).remove(object);
        trackContains.get(t).add(object);
        belongs.remove(object);
        belongs.put(object, t);
        checkRep();
        return oldTrack;
    }

    public boolean undo(int index) throws GramarException {
        int cur = caretaker.getIndex();
        if (index > cur) {
            throw new GramarException("Version number overflow.");
        }
        while (index != cur) {
            cur--;
            caretaker.restoreMemento(cur);
            caretaker.removeMemento(cur);
            Pair<Electronic, Pair<Track, Track>> oldState = originator.getStates();
            if ((!tracks.contains(oldState.getValue().getKey())) || (!tracks.contains(oldState.getValue().getValue()))) {
                return false;
            }
            doTrans(oldState.getKey(), oldState.getValue().getKey());
        }
        checkRep();
        return true;
    }

    private void init(List<Pair<String, List<String>>> paramList) throws InputException, DependencyException, GramarException {
        type = ApplicationType.AtomStructure;
        for (Pair<String, List<String>> eachPair : paramList) {
            if (eachPair.getKey().equals(AtomStructureParamType.ElementName.toString())) {
                Logger.getGlobal().info("Add a Atomic Nucleus.");
                try {
                    this.addCenterObject(new AtomicNucleus(eachPair.getValue().get(0)));
                } catch (Exception e) {
                    //Logger.getGlobal().warning(e.getLocalizedMessage());
                    throw new InputException(e.getLocalizedMessage());
                }

            } else if (eachPair.getKey().equals(AtomStructureParamType.NumberOfTracks.toString())) {
                Logger.getGlobal().info("Add tracks.");
                try {
                    int numShells = valueOf(eachPair.getValue().get(0));
                    if (tracks.size() > 0 && tracks.size() != numShells) {
                        throw new DependencyException("Track number illegal.");
                    }
                    for (int i = 1; i <= numShells; i++) {
                        this.addTrack(new ElectronShells(i));
                    }
                } catch (Exception e) {
                    throw new InputException("Track number illegal.");
                }

            } else if (eachPair.getKey().equals(AtomStructureParamType.NumberOfElectron.toString())) {
                Logger.getGlobal().info("Add Electronics.");
                if (tracks.size() == 0) {
                    int numShells = eachPair.getValue().size();
                    for (int i = 1; i <= numShells; i++) {
                        this.addTrack(new ElectronShells(i));
                    }
                }
                if (tracks.size() > 0 && eachPair.getValue().size() != tracks.size()) {
                    throw new DependencyException("Track number illegal.");
                }
                try {
                    int numberOfElectronic = 0;
                    for (String eachString : eachPair.getValue()) {
                        String[] twoPart = eachString.split("/");
                        for (int i = 1; i <= valueOf(twoPart[1]); i++) {
                            int numOfShells = valueOf(twoPart[0]) - 1;
                            Electronic addedElectronic = new Electronic(++numberOfElectronic);
                            this.addPhysicalObject(tracks.get(numOfShells), addedElectronic);
                            setPosition(addedElectronic);
                        }
                    }
                } catch (Exception e) {
                    throw new InputException("Electronic number illegal.");
                }
            } else {
                throw new InputException("Wrong file opened.");
            }
        }
        checkRep();
    }

    @Override
    public int getLogicalDistance(String a, String b) {
        return 10000;
    }

    @Override
    public String fileName() {
        String s = "savedAtomStructure";
        numOfNewFile++;
        s += numOfNewFile;
        return s;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("ElementName ::= ");
        s.append(centerObject.toString());
        s.append("\n");

        s.append("NumberOfTracks ::= ");
        s.append(tracks.size());
        s.append("\n");

        s.append("NumberOfElectron ::= ");
        for (int i = 0; i < tracks.size(); i++) {
            for (Track eachT : tracks) {
                if (eachT.getNoNumber() == i + 1) {
                    s.append(i + 1);
                    s.append("/");
                    s.append(trackContains.get(eachT).size());
                    s.append(";");
                    break;
                }
            }
        }
        s.replace(s.length() - 1, s.length(), "");
        s.append("\n");
        return s.toString();
    }

    @Override
    public boolean addRelationC2P(AtomicNucleus centerObject, Electronic object, RelationShip r) {
        return false;
    }

    @Override
    public boolean addRelationP2P(Electronic object1, Electronic object2, RelationShip r) {
        return false;
    }
}
