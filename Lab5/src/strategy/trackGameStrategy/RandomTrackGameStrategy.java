package strategy.trackGameStrategy;

import javafx.util.Pair;
import physicalObject.Athlete;

import java.util.*;

public class RandomTrackGameStrategy implements TrackGameStrategy {
    @Override
    public Map<Athlete, Pair<Integer, Integer>> doOperation(List<Athlete> athletes, int numOfTrack) {
        Map<Athlete, Pair<Integer, Integer>> reMap = new HashMap<>();
        Random random = new Random();
        Set<Integer> makeRange = new HashSet<>();
        for (int i = 0; i < athletes.size(); i++) {
            makeRange.add(i);
        }
        Integer[] range = new Integer[athletes.size()];

        while (!makeRange.isEmpty()) {
            int tmp = random.nextInt(athletes.size());
            if (makeRange.contains(tmp)) {
                range[athletes.size() - makeRange.size()] = tmp;
                makeRange.remove(tmp);
            }
        }

        int numOfGroup = athletes.size() / numOfTrack;
        if (athletes.size() % numOfTrack != 0) {
            numOfGroup++;
        }

        int cur = 0;

        for (int i = 0; i < numOfGroup; i++) {
            for (int j = 0; j < numOfTrack; j++) {
                reMap.put(athletes.get(range[cur]), new Pair<>(i, j));
                cur++;
                if (cur == athletes.size()) {
                    return reMap;
                }
            }
        }
        return reMap;
    }
}
