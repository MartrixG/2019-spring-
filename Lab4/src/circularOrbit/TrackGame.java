package circularOrbit;

import centralObject.TrackGameNull;
import exception.DependencyException;
import exception.GramarException;
import exception.InputException;
import javafx.util.Pair;
import physicalObject.Athlete;
import position.Position;
import relationship.RelationShip;
import strategy.RandomStrategy;
import strategy.RecordStrategy;
import track.CircleTrack;
import track.GameTrack;
import track.Track;
import types.ApplicationType;
import types.trackGameTypes.GameType;
import types.trackGameTypes.TrackGameParamType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class TrackGame extends ConcreteCircularOrbit<TrackGameNull, Athlete> {
    private Integer gameType;
    private Integer numOfTracks;
    private Integer numOfGroup;
    private double angle;
    private Map<Athlete, Integer> belongsGroup = new HashMap<>();
    private Map<Integer, List<Athlete>> groupContian = new HashMap<>();

    public void checkRep(){
        super.checkRep();
        for(Track eachT:trackContains.keySet()){
            //assert (trackContains.get(eachT).size()<=numOfGroup);
        }
    }

    private void init(List<Pair<String, List<String>>> paramList) throws InputException, DependencyException {
        type= ApplicationType.TrackGame;
        for(Pair<String, List<String>> eachPair:paramList){
            if(eachPair.getKey().equals(TrackGameParamType.Athlete.toString())){
                Logger.getGlobal().info("Add athletes.");
                Athlete newA=new Athlete(eachPair.getValue());
                if(physicalObjects.contains(newA)){
                    throw new DependencyException("File has the same athlete.");
                }
                physicalObjects.add(newA);
            }else if(eachPair.getKey().equals(TrackGameParamType.Game.toString())){
                Logger.getGlobal().info("Confirm game type.");
                this.gameType=Integer.valueOf(eachPair.getValue().get(0));
            }else if(eachPair.getKey().equals(TrackGameParamType.NumOfTracks.toString())){
                try{
                    Logger.getGlobal().info("Add game tracks.");
                    this.numOfTracks=Integer.valueOf(eachPair.getValue().get(0));
                    for(int i=1;i<=numOfTracks;i++){
                        this.tracks.add(new GameTrack(i, (double) (i * gameType / 100)));
                    }
                }catch (Exception e){
                    throw new InputException("Track number illegal.");
                }
            }else{
                throw new InputException("Wrong file opened.");
            }
        }
        checkRep();
    }

    static TrackGame creat(String fileName) throws DependencyException, InputException {
        TrackGame reGame=new TrackGame();
        reGame.init(reGame.processParameter(fileName));
        return reGame;
    }

    private Map<Athlete, Pair<Integer, Integer>> getTrackGroup(List<Athlete> athletes, int numOfTracks, GameType type){
        switch (type){
            case Random:
                return new RandomStrategy().doOperation(athletes, numOfTracks);
            case Records:
                return new RecordStrategy().doOperation(athletes, numOfTracks);
        }
        throw new IllegalArgumentException();
    }

    public void setAthlete(GameType type){
        Map<Athlete, Pair<Integer, Integer>> operation=getTrackGroup(this.physicalObjects, numOfTracks, type);
        int numOfGroup=0;
        for(Athlete eachA:physicalObjects){
            Pair<Integer, Integer> eachPair=operation.get(eachA);
            belongsGroup.put(eachA, eachPair.getKey());
            groupContian.computeIfAbsent(eachPair.getKey(), k -> new ArrayList<>());
            groupContian.get(eachPair.getKey()).add(eachA);
            numOfGroup=Math.max(numOfGroup, eachPair.getKey());

            belongs.put(eachA, tracks.get(eachPair.getValue()));
            trackContains.computeIfAbsent(tracks.get(eachPair.getValue()), k -> new ArrayList<>());
            trackContains.get(tracks.get(eachPair.getValue())).add(eachA);
        }
        this.numOfGroup=groupContian.keySet().size();
        this.angle=2*Math.PI/(this.numOfGroup);
        for(Athlete eachA:physicalObjects){
            setPosition(eachA);
        }
        checkRep();
    }
    
    public boolean changeGroup(Athlete object1, Athlete object2) throws GramarException {
        if (physicalObjects.contains(object1) && physicalObjects.contains(object2)) {
            int group1, group2;
            group1 = belongsGroup.get(object1);
            group2 = belongsGroup.get(object2);
            belongsGroup.put(object1, group2);
            belongsGroup.put(object2, group1);
            groupContian.get(group1).remove(object1);
            groupContian.get(group1).add(object2);
            groupContian.get(group2).remove(object2);
            groupContian.get(group2).add(object1);
            Track oldTrack = belongs.get(object1);
            trans(object1, belongs.get(object2));
            trans(object2, oldTrack);
            checkRep();
            return true;
        } else {
            throw new GramarException("Input athletes do not exist.");
        }
    }

    @Override
    public boolean addPhysicalObject(Track T, Athlete object) throws DependencyException {
        if(trackContains.get(T).size()==this.numOfGroup){
            throw new DependencyException("The number of athletes is more than the group.");
        }else{
            int flag=1;
            physicalObjects.add(object);
            for(int i=0;i<numOfGroup;i++){
                if(groupContian.get(i).size()!=numOfTracks){
                        for(int j=0;j<groupContian.get(i).size();j++){
                        if(belongs.get(groupContian.get(i).get(j)).equals(T)){
                            flag=0;
                        }
                    }
                    if(flag==1){
                        groupContian.get(i).add(object);
                        belongsGroup.put(object, i);
                        trackContains.get(T).add(object);
                        belongs.put(object, T);
                        break;
                    }
                }
            }
            setPosition(object);
            checkRep();
            return true;
        }
    }

    @Override
    public void setPosition(Athlete ob){
        positionOfPhysicalObjects.put(ob, new Position(((CircleTrack)belongs.get(ob)).getRadius(), (belongsGroup.get(ob))*angle, 2));
        checkRep();
    }

    @Override
    public String toString(){
        StringBuilder reString= new StringBuilder();
        //reString+='\t';
        for(int i=1;i<=numOfTracks;i++){
            reString.append("\t\t" + "Track").append(i);
        }
        reString.append('\n');
        for(int i=0;i<groupContian.keySet().size();i++){
            reString.append("Group").append(i);
            for(Track eachTrack:tracks){
                int flag=0;
                for(int j=0;j<groupContian.get(i).size();j++){
                    if(belongs.get(groupContian.get(i).get(j)).equals(eachTrack)){
                        reString.append("\t");
                        reString.append((groupContian.get(i).get(j)).getName());
                        reString.append("\t");
                        flag=1;
                    }
                }
                if(flag==0){
                    reString.append("\t\t\t");
                }
            }
            reString.append('\n');
        }
        return reString.toString();
    }

    @Override
    public Track trans(Athlete object, Track T) {
    	if(!physicalObjects.contains(object)) {
    		throw new IllegalArgumentException();
    	}
    	if(!tracks.contains(T)) {
    		throw new IllegalArgumentException();
    	}else {
    		Track oldTrack=belongs.get(object);
    		belongs.put(object, T);
    		trackContains.get(oldTrack).remove(object);
    		trackContains.get(T).add(object);
    		setPosition(object);
            checkRep();
    		return oldTrack;
    	}
    }

    @Override
    public boolean removeTrack(Track T) throws GramarException, DependencyException {
    	for(Athlete eachAthlete:physicalObjects) {
    		if(belongs.get(eachAthlete).equals(T)) {
    			groupContian.get(belongsGroup.get(eachAthlete)).remove(eachAthlete);
    			belongsGroup.remove(eachAthlete);
    		}
    	}
        checkRep();
    	return super.removeTrack(T);
    }

    @Override
    public boolean removePhysicalObject(Athlete object) throws GramarException, DependencyException {
        if(!physicalObjects.contains(object)){
            throw new GramarException("Input person does not exist in athlete.");
        }
    	belongsGroup.remove(object);
    	for(Integer eachGroup:groupContian.keySet()) {
            groupContian.get(eachGroup).remove(object);
    	}
    	numOfTracks--;
        checkRep();
    	return super.removePhysicalObject(object);
    }

    @Override
    public int getLogicalDistance(String a,String b){
        return 10000;
    }

    @Override
    public boolean addRelationC2P(TrackGameNull CenterObject, Athlete object, RelationShip R) {
        return false;
    }

    @Override
    public boolean addRelationP2P(Athlete Object1, Athlete Object2, RelationShip R) {
        return false;
    }
}
