package strategy;

import javafx.util.Pair;
import physicalObject.Athlete;

import java.util.List;
import java.util.Map;

public interface Strategy {
    public Map<Athlete, Pair<Integer, Integer>> doOperation(List<Athlete> athletes, int numOfTrack);
}
