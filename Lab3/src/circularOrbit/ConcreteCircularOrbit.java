package circularOrbit;

import javafx.util.Pair;
import position.Position;
import reader.Reader;
import track.CircleTrack;
import track.Track;
import types.ApplicationType;

import java.util.*;

public abstract class ConcreteCircularOrbit<L, E> implements CircularOrbit<L, E> {
    protected List<E> physicalObjects = new ArrayList<>();
    protected List<Track> tracks = new ArrayList<>();
    protected Map<E, Position> positionOfPhysicalObjects = new HashMap<>();
    protected Map<E, Track> belongs = new HashMap<>();
    protected Map<Track, List<E>> trackContains=new HashMap<>();
    protected L centerObject = null;
    protected ApplicationType type;
    protected List<Pair<Position, Position>> straightLine=new ArrayList<>();
    protected Random random=new Random();

    @Override
    public double getShang(){
        double ans=0;
        int num=0;
        for(Track eachT:trackContains.keySet()){
            num++;
            ans+=num*trackContains.get(eachT).size();
        }
        ans/=physicalObjects.size();
        return ans;
    }

    @Override
    public ApplicationType getType(){
        return type;
    }

    @Override
    public List<Pair<Position, Position>> getStraight(){
        return straightLine;
    }

    @Override
    public boolean addTrack(Track T) {
        if (tracks.contains(T)) {
            return false;
        }else {
            tracks.add(T);
            trackContains.put(T,new ArrayList<>());
            return true;
        }
    }

    @Override
    public boolean removeTrack(Track T) {
        if(!tracks.contains(T)){
            return false;
        }else{
            for(E eachObject:trackContains.get(T)){
                positionOfPhysicalObjects.remove(eachObject);
                physicalObjects.remove(eachObject);
                belongs.remove(eachObject);
            }
            trackContains.remove(T);
            tracks.remove(T);
            return true;
        }
    }

    @Override
    public boolean addCenterObject(L CenterObject) {
        if(centerObject == null){
            this.centerObject = CenterObject;
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean addPhysicalObject(Track T, E object) {
        if(!tracks.contains(T)) return false;
        else{
            physicalObjects.add(object);
            belongs.put(object, T);
            trackContains.get(T).add(object);
            return true;
        }
    }

    @Override
    public boolean removePhysicalObject(E object){
        if(!physicalObjects.contains(object)){
            return false;
        }else{
            for(Track eachTrack:tracks){
                if(trackContains.get(eachTrack).contains(object))
                    trackContains.get(eachTrack).remove(object);
            }
            positionOfPhysicalObjects.remove(object);
            physicalObjects.remove(object);
            belongs.remove(object);
            return true;
        }
    }

    @Override
    public List<E> getPhysicalObjects() {
        List<E> reList=new ArrayList<>(physicalObjects);
        return reList;
    }

    @Override
    public List<E> getPhysicalObjects(Track T) {
        return trackContains.get(T);
    }

    @Override
    public Position getPhysicalObjectPosition(E object){
        if(positionOfPhysicalObjects.get(object)==null){
            throw new IllegalArgumentException();
        }
        else{
            return positionOfPhysicalObjects.get(object);
        }
    }

    @Override
    public void setPosition(E object){
        positionOfPhysicalObjects.put(object, new Position(((CircleTrack)belongs.get(object)).getRadius(), random.nextDouble()*2*Math.PI, 2));
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
    public List<Pair<String, List<String>>> processParameter(String fileName) {
        return Reader.getArgs(fileName);
    }

    @Override
    public Iterator<E> iterator(){
        return new Citr();
    }

    private class Citr implements Iterator<E>{
        int cur=0;
        @Override
        public boolean hasNext() {
            return cur!=physicalObjects.size();
        }

        @Override
        public E next() {
            if(cur!=physicalObjects.size()){
                cur++;
                return physicalObjects.get(cur);
            }else{
                return null;
            }
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }
    }

    @Override
    public double getPhysicalDistance(String a,String b){
        double ans=0;
        E e1=null;
        E e2=null;
        if(centerObject.equals(a)){
            for(E eachE:physicalObjects){
                if(eachE.equals(b)){
                    e2=eachE;
                }
            }
            Position p2=positionOfPhysicalObjects.get(e2);
            ans+=p2.getPolarCoordinatePosition().getKey();
        }
        else if(centerObject.equals(b)){
            for(E eachE:physicalObjects){
                if(eachE.equals(a)){
                    e1=eachE;
                }
            }
            Position p1=positionOfPhysicalObjects.get(e1);
            ans+=p1.getPolarCoordinatePosition().getKey();
        }else{
            for(E eachE:physicalObjects){
                if(eachE.equals(a)){
                    e1=eachE;
                }
                if(eachE.equals(b)){
                    e2=eachE;
                }
            }
            if(e1==null||e2==null){
                return -1;
            }
            Position p1=positionOfPhysicalObjects.get(e1);
            Position p2=positionOfPhysicalObjects.get(e2);
            ans+=(p1.getCartesianCoordinatePosition().getKey()-p2.getCartesianCoordinatePosition().getKey())*(p1.getCartesianCoordinatePosition().getKey()-p2.getCartesianCoordinatePosition().getKey());
            ans+=(p1.getCartesianCoordinatePosition().getValue()-p2.getCartesianCoordinatePosition().getValue())*(p1.getCartesianCoordinatePosition().getValue()-p2.getCartesianCoordinatePosition().getValue());
            ans=Math.sqrt(ans);
        }
        return ans;
    }
}
