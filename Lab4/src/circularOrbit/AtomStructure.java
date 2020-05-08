package circularOrbit;

import exception.DependencyException;
import exception.GramarException;
import exception.InputException;
import memento.Caretaker;
import memento.Originator;
import centralObject.AtomicNucleus;
import javafx.util.Pair;
import physicalObject.Electronic;
import position.Position;
import relationship.RelationShip;
import track.ElectronShells;
import track.Track;
import types.ApplicationType;
import types.atomStructureTypes.atomStructureParamType;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import static java.lang.Integer.*;

public class AtomStructure extends ConcreteCircularOrbit<AtomicNucleus, Electronic> {
    private Originator originator=new Originator();
    private Caretaker caretaker=new Caretaker(originator);

    static AtomStructure creat(String fileName) throws InputException, DependencyException, GramarException {
        AtomStructure reA=new AtomStructure();
        reA.init(reA.processParameter(fileName));
        return reA;
    }

    public void checkRep(){
        super.checkRep();
    }

    @Override
    public Track trans(Electronic object, Track T) throws GramarException {
        if(!physicalObjects.contains(object)){
            throw new GramarException("Selected track does not have a electronic.");
        }
        else if(!tracks.contains((T))){
            throw new GramarException("The track does not exist.");
        }
        else{
            originator.setState(new Pair<>(object, new Pair<>(belongs.get(object), T)));
            caretaker.createMemento();
            checkRep();
            return doTrans(object, T);
        }
    }

    private Track doTrans(Electronic object, Track T){
        positionOfPhysicalObjects.put(object, new Position(((ElectronShells)T).getRadius(), new Random().nextDouble()*2*Math.PI, 2));
        Track oldTrack=belongs.get(object);
        trackContains.get(oldTrack).remove(object);
        trackContains.get(T).add(object);
        belongs.remove(object);
        belongs.put(object, T);
        checkRep();
        return oldTrack;
    }

    public boolean UNDO(int index) throws GramarException {
        int cur=caretaker.getIndex();
        if(index>cur){
            throw new GramarException("Version number overflow.");
        }
        while(index!=cur){
            cur--;
            caretaker.restoreMemento(cur);
            caretaker.removeMemento(cur);
            Pair<Electronic, Pair<Track, Track>> oldState=originator.getStates();
            if((!tracks.contains(oldState.getValue().getKey()))||(!tracks.contains(oldState.getValue().getValue()))){
                return false;
            }
            doTrans(oldState.getKey(),oldState.getValue().getKey());
        }
        checkRep();
        return true;
    }

    private void init(List<Pair<String, List<String>>> paramList) throws InputException, DependencyException, GramarException {
        type= ApplicationType.AtomStructure;
        for(Pair<String, List<String>> eachPair:paramList){
            if(eachPair.getKey().equals(atomStructureParamType.ElementName.toString())){
                Logger.getGlobal().info("Add a Atomic Nucleus.");
                try {
                    this.addCenterObject(new AtomicNucleus(eachPair.getValue().get(0)));
                }catch(Exception e){
                    //Logger.getGlobal().warning(e.getLocalizedMessage());
                    throw new InputException(e.getLocalizedMessage());
                }

            }else if(eachPair.getKey().equals(atomStructureParamType.NumberOfTracks.toString())){
                Logger.getGlobal().info("Add tracks.");
                try{
                    int numShells= valueOf(eachPair.getValue().get(0));
                    if(tracks.size()>0&&tracks.size()!=numShells){
                        throw new DependencyException("Track number illegal.");
                    }
                    for(int i=1;i<=numShells;i++){
                        this.addTrack(new ElectronShells(i));
                    }
                } catch (Exception e){
                    throw new InputException("Track number illegal.");
                }

            }else if(eachPair.getKey().equals(atomStructureParamType.NumberOfElectron.toString())){
                Logger.getGlobal().info("Add Electronics.");
                if(tracks.size()==0){
                    int numShells=eachPair.getValue().size();
                    for(int i=1;i<=numShells;i++){
                        this.addTrack(new ElectronShells(i));
                    }
                }
                if(tracks.size()>0&&eachPair.getValue().size()!=tracks.size())
                    throw new DependencyException("Track number illegal.");
                try{
                    int numberOfElectronic=0;
                    for(String eachString:eachPair.getValue()){
                        String[] twoPart = eachString.split("/");
                        for(int i = 1; i<= valueOf(twoPart[1]); i++){
                            int numOfShells= valueOf(twoPart[0])-1;
                            Electronic addedElectronic=new Electronic(++numberOfElectronic);
                            this.addPhysicalObject(tracks.get(numOfShells), addedElectronic);
                            setPosition(addedElectronic);
                            //positionOfPhysicalObjects.put(addedElectronic, new Position(((ElectronShells)tracks.get(numOfShells)).getRadius(), random.nextDouble()*2*Math.PI, 2));
                        }
                    }
                }catch(Exception e){
                    throw new InputException("Electronic number illegal.");
                }
            }else{
                throw new InputException("Wrong file opened.");
            }
        }
        checkRep();
    }

    @Override
    public int getLogicalDistance(String a,String b){
        return 10000;
    }
    @Override
    public boolean addRelationC2P(AtomicNucleus CenterObject, Electronic Object, RelationShip R) {
        return false;
    }

    @Override
    public boolean addRelationP2P(Electronic Object1, Electronic Object2, RelationShip R) { return false; }
}
