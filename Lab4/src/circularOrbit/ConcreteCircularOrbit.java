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

import java.util.*;

public abstract class ConcreteCircularOrbit<L, E> implements CircularOrbit<L, E> {
    List<E> physicalObjects = new ArrayList<>();
    List<Track> tracks = new ArrayList<>();
    Map<E, Position> positionOfPhysicalObjects = new HashMap<>();
    Map<E, Track> belongs = new HashMap<>();
    Map<Track, List<E>> trackContains=new HashMap<>();
    L centerObject = null;
    protected ApplicationType type;
    List<Pair<Position, Position>> straightLine=new ArrayList<>();
    private Random random=new Random();

    public void checkRep(){
        for(Track eachT:tracks){
            assert (eachT!=null);
        }
        for(E eachE:physicalObjects){
            assert (eachE!=null);
            //assert (belongs.get(eachE)!=null);
        }
    }

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
    public boolean addTrack(Track T) throws GramarException {
        if (tracks.contains(T)){
            throw new GramarException("The Track to add has existed.");
        }else {
            tracks.add(T);
            trackContains.put(T,new ArrayList<>());
            checkRep();
            return true;
        }
    }

    @Override
    public boolean removeTrack(Track T) throws GramarException, DependencyException {
        if(!tracks.contains(T)){
            throw new GramarException("The track input does not exist.");
        }else{
            for(E eachObject:trackContains.get(T)){
                positionOfPhysicalObjects.remove(eachObject);
                physicalObjects.remove(eachObject);
                belongs.remove(eachObject);
            }
            trackContains.remove(T);
            tracks.remove(T);
            checkRep();
            return true;
        }
    }

    @Override
    public boolean addCenterObject(L CenterObject) throws InputException {
        if(centerObject == null){
            this.centerObject = CenterObject;
            checkRep();
            return true;
        }else{
            throw new InputException("Too many center object");
        }
    }

    @Override
    public boolean addPhysicalObject(Track T, E object) throws GramarException, DependencyException {
        if(!tracks.contains(T)) {
            throw new GramarException("Input track does not exist.");
        }
        else{
            physicalObjects.add(object);
            belongs.put(object, T);
            trackContains.get(T).add(object);
            checkRep();
            return true;
        }
    }

    @Override
    public boolean removePhysicalObject(E object) throws GramarException, DependencyException {
        if(!physicalObjects.contains(object)){
            throw new GramarException("No such element.");
        }else{
            for(Track eachTrack:tracks){
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
    public List<E> getPhysicalObjects(Track T) {
        return trackContains.get(T);
    }

    @Override
    public Position getPhysicalObjectPosition(E object) throws GramarException {
        if(positionOfPhysicalObjects.get(object)==null){
            throw new GramarException("No such element.");
        }
        else{
            return positionOfPhysicalObjects.get(object);
        }
    }

    @Override
    public void setPosition(E object){
        positionOfPhysicalObjects.put(object, new Position(((CircleTrack)belongs.get(object)).getRadius(), random.nextDouble()*2*Math.PI, 2));
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
            return cur<physicalObjects.size();
        }

        @Override
        public E next() {
            if(cur<physicalObjects.size()){
                cur++;
                return physicalObjects.get(cur-1);
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
    public double getPhysicalDistance(String a,String b) {
        return -1;
    }
}
