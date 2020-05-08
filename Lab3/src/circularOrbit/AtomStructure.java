package circularOrbit;

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

public class AtomStructure extends ConcreteCircularOrbit<AtomicNucleus, Electronic> {
    public Originator originator=new Originator();
    public Caretaker caretaker=new Caretaker(originator);

    public static AtomStructure creat(String fileName){
        AtomStructure reA=new AtomStructure();
        reA.init(reA.processParameter(fileName));
        return reA;
    }

    @Override
    public Track trans(Electronic object, Track T) {
        if(!physicalObjects.contains(object)){
            return null;
        }
        else if(!tracks.contains((T))){
            return null;
        }
        else{
            originator.setState(new Pair<>(object, new Pair<>(belongs.get(object), T)));
            caretaker.createMemento();
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
        return oldTrack;
    }

    public boolean UNDO(int index){
        int cur=caretaker.getIndex();
        if(index>cur){
            return false;
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
        return true;
    }

    public void init(List<Pair<String, List<String>>> paramList) {
        type= ApplicationType.AtomStructure;
        for(Pair<String, List<String>> eachPair:paramList){
            if(eachPair.getKey().equals(atomStructureParamType.ElementName.toString())){
                this.addCenterObject(new AtomicNucleus(eachPair.getValue().get(0)));
            }else if(eachPair.getKey().equals(atomStructureParamType.NumberOfTracks.toString())){
                int numShells=Integer.valueOf(eachPair.getValue().get(0));
                for(int i=1;i<=numShells;i++){
                    this.addTrack(new ElectronShells(i));
                }
            }else if(eachPair.getKey().equals(atomStructureParamType.NumberOfElectron.toString())){
                Random random=new Random();
                int numberOfElectronic=0;
                for(String eachString:eachPair.getValue()){
                    String twoPart[]=eachString.split("/");
                    for(int i=1;i<=Integer.valueOf(twoPart[1]);i++){
                        Integer numOfShells=Integer.valueOf(twoPart[0])-1;
                        Electronic addedElectronic=new Electronic(++numberOfElectronic);
                        this.addPhysicalObject(tracks.get(numOfShells), addedElectronic);
                        setPosition(addedElectronic);
                        //positionOfPhysicalObjects.put(addedElectronic, new Position(((ElectronShells)tracks.get(numOfShells)).getRadius(), random.nextDouble()*2*Math.PI, 2));
                    }
                }
            }
        }
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
    public boolean addRelationP2P(Electronic Object1, Electronic Object2, RelationShip R) {
        return false;
    }
}
