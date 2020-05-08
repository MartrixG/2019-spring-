package memento;

import java.util.ArrayList;
import java.util.List;

public class Caretaker {
    private Originator originator;
    private List<MementoIF> mementos=new ArrayList<>();
    private int index=0;

    public Caretaker(Originator originator){
        this.originator=originator;

    }

    public int createMemento() {
        MementoIF memento = this.originator.createMemento();
        this.mementos.add(memento);
        return this.index++;
    }

    public void restoreMemento(int index) {
        MementoIF memento = mementos.get(index);
        originator.restoreMemento(memento);
    }

    public void removeMemento(int index) {
        mementos.remove(index);
        this.index--;
    }

    public int getIndex(){
        return index;
    }
}
