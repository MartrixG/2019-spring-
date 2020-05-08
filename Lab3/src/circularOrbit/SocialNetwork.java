package circularOrbit;

import centralObject.CentralUser;
import graph.ConcreteVerticesGraph;
import javafx.util.Pair;
import person.Person;
import physicalObject.Friend;
import position.Position;
import relationship.RelationShip;
import relationship.SocialNetworkRelationship;
import track.SocialNetworkTrack;
import track.Track;
import types.ApplicationType;
import types.socialNetworkTypes.SocialNetworkParamType;

import java.util.*;

public class SocialNetwork extends ConcreteCircularOrbit<CentralUser, Friend> {
    private ConcreteVerticesGraph<Person> G=new ConcreteVerticesGraph<>();

    public void init(List<Pair<String, List<String>>> paramList){
        type= ApplicationType.SocialNetwork;
        List<List<String>> tilePair=new ArrayList<>();
        for(Pair<String, List<String>> eachPair:paramList){
            if(eachPair.getKey().equals(SocialNetworkParamType.CentralUser.toString())){
                this.addCenterObject(new CentralUser(eachPair.getValue()));
                G.add(centerObject);
            }else if(eachPair.getKey().equals(SocialNetworkParamType.Friend.toString())){
                Friend newFriend=new Friend(eachPair.getValue());
                physicalObjects.add(newFriend);
                G.add(newFriend);
            }else if(eachPair.getKey().equals(SocialNetworkParamType.SocialTie.toString())){
                tilePair.add(eachPair.getValue());
            }
        }

        for(List<String> eachList:tilePair) {
            Person p1 = null;
            Person p2 = null;
            if (eachList.get(0).equals(this.centerObject.getName())) {
                p1 = this.centerObject;
            }
            if (eachList.get(1).equals(this.centerObject.getName())) {
                p2 = this.centerObject;
            }
            for (Friend eachFriend : physicalObjects) {
                if (eachList.get(0).equals(eachFriend.getName())) {
                    p1 = eachFriend;//.clone();
                }
                if (eachList.get(1).equals(eachFriend.getName())) {
                    p2 = eachFriend;//.clone();
                }
            }
            G.set(p1, p2, Double.valueOf(eachList.get(2)));
           // G.set(p2, p1, Double.valueOf(eachList.get(2)));
        }
        physicalObjects.clear();
        this.buildGraph();
    }

    public boolean addFriendInG(Friend f){
        if(physicalObjects.contains(f)){
            G.add(f);
            return true;
        }else {
            return false;
        }
    }

    public boolean buildGraph(){
        Map<Person, Pair<Integer, Set<Person>>> buildMap=G.BFS(centerObject);
        Map<Integer, Track> toTrack=new HashMap<>();
        for(Person eachP:buildMap.keySet()){
            if(eachP!=centerObject){
                Pair<Integer, Set<Person>> tmpP=buildMap.get(eachP);
                if(toTrack.containsKey(tmpP.getKey())){
                    this.addPhysicalObject(toTrack.get(tmpP.getKey()), (Friend)eachP);
                }else{
                    Track newTrack=new SocialNetworkTrack(tmpP.getKey());
                    toTrack.put(tmpP.getKey(), newTrack);
                    this.addTrack(newTrack);
                    this.addPhysicalObject(toTrack.get(tmpP.getKey()), (Friend)eachP);
                }
            }
        }
        for(Friend eachP:physicalObjects){
            setPosition(eachP);
        }
        for(Person eachP:buildMap.keySet()){
            for(Person toP:buildMap.get(eachP).getValue()){
                if(positionOfPhysicalObjects.get(eachP)!=null&&positionOfPhysicalObjects.get(toP)!=null)
                    straightLine.add(new Pair<>(positionOfPhysicalObjects.get(eachP), positionOfPhysicalObjects.get(toP)));
                else if(positionOfPhysicalObjects.get(eachP)==null&&positionOfPhysicalObjects.get(toP)!=null){
                    straightLine.add(new Pair<>(new Position((double)0,(double)0,1), positionOfPhysicalObjects.get(toP)));
                }else if(positionOfPhysicalObjects.get(eachP)==null&&positionOfPhysicalObjects.get(toP)!=null){
                    straightLine.add(new Pair<>(positionOfPhysicalObjects.get(eachP), new Position((double)0,(double)0,1)));
                }
            }
        }
        return true;
    }

    @Override
    public boolean removePhysicalObject(Friend object) {
        if(!physicalObjects.contains(object)){
            return false;
        }else{
            physicalObjects.clear();
            tracks.clear();
            positionOfPhysicalObjects.clear();
            belongs.clear();
            trackContains.clear();
            straightLine.clear();
            G.remove(object);
            this.buildGraph();
            return true;
        }
    }

    @Override
    public boolean removeTrack(Track T) {
        if(!tracks.contains(T)){
            return false;
        }else{
            for(Person eachP:trackContains.get(T)){
                G.remove(eachP);
            }
            physicalObjects.clear();
            tracks.clear();
            positionOfPhysicalObjects.clear();
            belongs.clear();
            trackContains.clear();
            straightLine.clear();
            this.buildGraph();
            return true;
        }
    }

    public static SocialNetwork creat(String fileName){
        SocialNetwork reNetwork=new SocialNetwork();
        reNetwork.init(reNetwork.processParameter(fileName));
        return reNetwork;
    }

    public Set<Person> informationDiffusion(Friend source){
        Set<Person> res=G.informationDiffusion(source);
        res.remove(centerObject);
        return res;
    }

    @Override
    public int getLogicalDistance(String a,String b){
        if(centerObject.equals(a)){
            Friend bf=new Friend(b);
            return belongs.get(bf).getNoNumber();
        }else if(centerObject.equals(b)){
            Friend af=new Friend(a);
            return belongs.get(af).getNoNumber();
        }else{
            Friend af=new Friend(a);
            Friend bf=new Friend(b);
            return Math.abs(belongs.get(af).getNoNumber()-belongs.get(bf).getNoNumber());
        }
    }

    public boolean addRelation(Friend f1,Friend f2,RelationShip R){
        if (f1.getName().equals(centerObject.getName())) {
            return addRelationC2P(centerObject, f2, R);
        } else if (f2.getName().equals(centerObject.getName())) {
            return addRelationC2P(centerObject, f1, R);
        } else {
            return addRelationP2P(f1, f2, R);
        }
    }

    @Override
    public boolean addRelationC2P(CentralUser CenterObject, Friend object, RelationShip R) {
        G.add(object);
        G.set(centerObject,object,((SocialNetworkRelationship)R).getIntimacy());
        G.set(object,centerObject,((SocialNetworkRelationship)R).getIntimacy());
        physicalObjects.clear();
        tracks.clear();
        belongs.clear();
        trackContains.clear();
        positionOfPhysicalObjects.clear();
        straightLine.clear();
        buildGraph();
        return true;
    }

    @Override
    public boolean addRelationP2P(Friend Object1, Friend Object2, RelationShip R) {
        G.set(Object1,Object2,((SocialNetworkRelationship)R).getIntimacy());
        G.set(Object2,Object1,((SocialNetworkRelationship)R).getIntimacy());
        physicalObjects.clear();
        tracks.clear();
        belongs.clear();
        trackContains.clear();
        positionOfPhysicalObjects.clear();
        straightLine.clear();
        buildGraph();
        return true;
    }

    public boolean removeRelationship(Friend f1, Friend f2){
        if(physicalObjects.contains(f1)||f1.getName().equals(centerObject.getName())) {
            G.set(f1, f2, (double) 0);
            G.set(f2, f1, (double) 0);
            physicalObjects.clear();
            tracks.clear();
            belongs.clear();
            trackContains.clear();
            positionOfPhysicalObjects.clear();
            straightLine.clear();
            buildGraph();
            return true;
        }else if(physicalObjects.contains(f2)||f2.getName().equals(centerObject.getName())){
            G.set(f1,f2,(double)0);
            G.set(f2,f1,(double)0);
            physicalObjects.clear();
            tracks.clear();
            belongs.clear();
            trackContains.clear();
            positionOfPhysicalObjects.clear();
            straightLine.clear();
            buildGraph();
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Track trans(Friend object, Track T) {
        return null;
    }
}
