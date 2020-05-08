package memento;

import javafx.util.Pair;
import physicalObject.Electronic;
import track.Track;

public class Originator {
    private Pair<Electronic, Pair<Track, Track>> state;

    public Originator(){
        this.state=null;
    }

    public Memento createMemento(){
        return new Memento(state);
    }

    public void restoreMemento(MementoIF memento){
        this.state=((Memento)memento).getStates();
    }

    public void setState(Pair<Electronic, Pair<Track, Track>> S){
        this.state=S;
    }

    public Pair<Electronic, Pair<Track, Track>> getStates(){
        return state;
    }

    private class Memento implements MementoIF {
        private Pair<Electronic, Pair<Track, Track>> state;
        private Memento(Pair<Electronic, Pair<Track, Track>> state){
            this.state=state;
        }

        public Pair<Electronic, Pair<Track, Track>> getStates(){
            return state;
        }
    }
}
