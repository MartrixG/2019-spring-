package circularOrbit;

import centralObject.CentralUser;
import exception.DependencyException;
import exception.GramarException;
import exception.InputException;
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
import types.ioType.IOType;
import types.socialNetworkTypes.SocialNetworkParamType;

import java.util.*;
import java.util.logging.Logger;

public class SocialNetwork extends ConcreteCircularOrbit<CentralUser, Friend> {
    private ConcreteVerticesGraph<Person> G = new ConcreteVerticesGraph<>();
    private int numOfNewFile = 0;

    @Override
    public void checkRep() {
        super.checkRep();
    }

    private void init(List<Pair<String, List<String>>> paramList) throws GramarException, InputException, DependencyException {
        type = ApplicationType.SocialNetwork;
        List<List<String>> tilePair = new ArrayList<>();

        int size = paramList.size();
        int cnt = 0;

        for (Pair<String, List<String>> eachPair : paramList) {

            cnt++;
            if (cnt % 10000 == 0) {
                System.out.print(cnt);
                System.out.print("/");
                System.out.println(size);
                System.out.println(new Double((double) cnt / (double) size));
            }

            if (eachPair.getKey().equals(SocialNetworkParamType.CentralUser.toString())) {
                Logger.getGlobal().info("Add a center user.");
                this.addCenterObject(new CentralUser(eachPair.getValue()));
                G.add(centerObject);
            } else if (eachPair.getKey().equals(SocialNetworkParamType.Friend.toString())) {
                //Logger.getGlobal().info("Add friends.");
                Friend newFriend = new Friend(eachPair.getValue());
                physicalObjects.add(newFriend);
                G.add(newFriend);
            } else if (eachPair.getKey().equals(SocialNetworkParamType.SocialTie.toString())) {
                tilePair.add(eachPair.getValue());
            } else {
                throw new InputException("Wrong file opened.");
            }
        }

        System.out.println("Input finished.");

        for (List<String> eachList : tilePair) {
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
                    p1 = eachFriend;
                }
                if (eachList.get(1).equals(eachFriend.getName())) {
                    p2 = eachFriend;
                }
            }
            if (p1 == null || p2 == null) {
                throw new DependencyException("Social relationship uses person does not exist.");
            }
            G.set(p1, p2, Double.valueOf(eachList.get(2)));
            G.set(p2, p1, Double.valueOf(eachList.get(2)));
        }
        physicalObjects.clear();
        this.buildGraph();

        System.out.println("build graph");

        checkRep();
    }

    public void addFriendInG(Friend f) throws GramarException {
        if (!physicalObjects.contains(f)) {
            G.add(f);
            checkRep();
        } else {
            throw new GramarException("Input person exists in this network.");
        }
    }

    private void buildGraph() throws GramarException, DependencyException {
        Map<Person, Pair<Integer, Set<Person>>> buildMap = G.bfs(centerObject);
        Map<Integer, Track> toTrack = new HashMap<>();
        for (Person eachP : buildMap.keySet()) {
            if (eachP != centerObject) {
                Pair<Integer, Set<Person>> tmpP = buildMap.get(eachP);
                if (toTrack.containsKey(tmpP.getKey())) {
                    this.addPhysicalObject(toTrack.get(tmpP.getKey()), (Friend) eachP);
                } else {
                    Track newTrack = new SocialNetworkTrack(tmpP.getKey());
                    toTrack.put(tmpP.getKey(), newTrack);
                    this.addTrack(newTrack);
                    this.addPhysicalObject(toTrack.get(tmpP.getKey()), (Friend) eachP);
                }
            }
        }
        for (Friend eachP : physicalObjects) {
            setPosition(eachP);
        }
        for (Person eachP : buildMap.keySet()) {
            for (Person toP : buildMap.get(eachP).getValue()) {
                if (positionOfPhysicalObjects.get(eachP) != null && positionOfPhysicalObjects.get(toP) != null) {
                    straightLine.add(new Pair<>(positionOfPhysicalObjects.get(eachP), positionOfPhysicalObjects.get(toP)));
                } else if (positionOfPhysicalObjects.get(eachP) == null && positionOfPhysicalObjects.get(toP) != null) {
                    straightLine.add(new Pair<>(new Position((double) 0, (double) 0, 1), positionOfPhysicalObjects.get(toP)));
                } else if (positionOfPhysicalObjects.get(eachP) == null && positionOfPhysicalObjects.get(toP) != null) {
                    straightLine.add(new Pair<>(positionOfPhysicalObjects.get(eachP), new Position((double) 0, (double) 0, 1)));
                }
            }
        }
        checkRep();
    }

    @Override
    public boolean removePhysicalObject(Friend object) throws GramarException, DependencyException {
        if (!physicalObjects.contains(object)) {
            throw new GramarException("Input person does not exist in friends.");
        } else {
            physicalObjects.clear();
            tracks.clear();
            positionOfPhysicalObjects.clear();
            belongs.clear();
            trackContains.clear();
            straightLine.clear();
            G.remove(object);
            this.buildGraph();
            checkRep();
            return true;
        }
    }

    @Override
    public boolean removeTrack(Track t) throws GramarException, DependencyException {
        if (!tracks.contains(t)) {
            throw new GramarException("Can not remove this track.");
        } else {
            for (Person eachP : trackContains.get(t)) {
                G.remove(eachP);
            }
            physicalObjects.clear();
            tracks.clear();
            positionOfPhysicalObjects.clear();
            belongs.clear();
            trackContains.clear();
            straightLine.clear();
            this.buildGraph();
            checkRep();
            return true;
        }
    }

    static SocialNetwork creat(String fileName, IOType type) throws GramarException, InputException, DependencyException {
        SocialNetwork reNetwork = new SocialNetwork();
        reNetwork.init(reNetwork.processParameter(fileName, type));
        return reNetwork;
    }

    public Set<Person> informationDiffusion(Friend source) throws GramarException {
        if (!physicalObjects.contains(source)) {
            throw new GramarException("Input person does not exist in friends.");
        }
        Set<Person> res = G.informationDiffusion(source);
        res.remove(centerObject);
        checkRep();
        return res;
    }

    @Override
    public int getLogicalDistance(String a, String b) throws GramarException {
        if (centerObject.equals(a)) {
            Friend bf = new Friend(b);
            checkRep();
            return belongs.get(bf).getNoNumber();
        } else if (centerObject.equals(b)) {
            Friend af = new Friend(a);
            checkRep();
            return belongs.get(af).getNoNumber();
        } else {
            Friend af = new Friend(a);
            Friend bf = new Friend(b);
            if (physicalObjects.contains(af) && physicalObjects.contains(bf)) {
                checkRep();
                return Math.abs(belongs.get(af).getNoNumber() - belongs.get(bf).getNoNumber());
            } else {
                throw new GramarException("Input person does not exit.");
            }
        }
    }

    public boolean addRelation(Friend f1, Friend f2, RelationShip r) throws GramarException, DependencyException {
        if (f1.getName().equals(centerObject.getName())) {
            checkRep();
            return addRelationC2P(centerObject, f2, r);
        } else if (f2.getName().equals(centerObject.getName())) {
            checkRep();
            return addRelationC2P(centerObject, f1, r);
        } else {
            checkRep();
            return addRelationP2P(f1, f2, r);
        }
    }

    @Override
    public boolean addRelationC2P(CentralUser centerObject, Friend object, RelationShip r) throws GramarException, DependencyException {
        if (!G.vertices().contains(object)) {
            throw new GramarException("Input person does not exit.");
        }
        G.add(object);
        G.set(this.centerObject, object, ((SocialNetworkRelationship) r).getIntimacy());
        G.set(object, this.centerObject, ((SocialNetworkRelationship) r).getIntimacy());
        physicalObjects.clear();
        tracks.clear();
        belongs.clear();
        trackContains.clear();
        positionOfPhysicalObjects.clear();
        straightLine.clear();
        buildGraph();
        checkRep();
        return true;
    }

    @Override
    public boolean addRelationP2P(Friend object1, Friend object2, RelationShip r) throws GramarException, DependencyException {
        if (!G.vertices().contains(object1) || (!G.vertices().contains(object2))) {
            throw new GramarException("Input person does not exit.");
        }
        G.set(object1, object2, ((SocialNetworkRelationship) r).getIntimacy());
        G.set(object2, object1, ((SocialNetworkRelationship) r).getIntimacy());
        physicalObjects.clear();
        tracks.clear();
        belongs.clear();
        trackContains.clear();
        positionOfPhysicalObjects.clear();
        straightLine.clear();
        buildGraph();
        checkRep();
        return true;
    }

    public void removeRelationship(Friend f1, Friend f2) throws GramarException, DependencyException {
        if (!G.vertices().contains(f1) || (!G.vertices().contains(f2))) {
            throw new GramarException("Input person does not exit.");
        }
        G.set(f1, f2, (double) 0);
        G.set(f2, f1, (double) 0);
        physicalObjects.clear();
        tracks.clear();
        belongs.clear();
        trackContains.clear();
        positionOfPhysicalObjects.clear();
        straightLine.clear();
        buildGraph();
        checkRep();
    }

    @Override
    public String fileName() {
        String s = "savedSocialNetworkCircle";
        numOfNewFile++;
        s += numOfNewFile;
        return s;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("CentralUser ::= ");
        s.append(centerObject.toString());
        s.append("\n");
        for (Friend eachF : physicalObjects) {
            s.append("Friend ::= ");
            s.append(eachF.toString());
            s.append("\n");
        }
        List<Pair<Person, Person>> addedRela = new ArrayList<>();
        for (Person eachF : G.vertices()) {
            Map<Person, Double> friendRela = G.targets(eachF);
            for (Map.Entry<Person, Double> eachE : friendRela.entrySet()) {
                if (!addedRela.contains(new Pair<>(eachE.getKey(), (Person) eachF))) {
                    s.append("SocialTie ::= <");
                    try {
                        s.append(((Friend) eachF).getName());
                    } catch (Exception e) {
                        s.append(centerObject.getName());
                    }
                    s.append(", ");
                    try {
                        s.append(((Friend) eachE.getKey()).getName());
                    } catch (Exception e) {
                        s.append(centerObject.getName());
                    }
                    s.append(", ");
                    s.append(eachE.getValue());
                    s.append(">");
                    s.append("\n");
                    addedRela.add(new Pair<>(eachF, eachE.getKey()));
                }
            }
        }
        return s.toString();
    }

    @Override
    public Track trans(Friend object, Track t) {
        return null;
    }
}
