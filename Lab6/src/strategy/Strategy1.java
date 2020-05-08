package strategy;

import ladder.Ladder;
import monkey.Monkey;
import runs.CrossRiver;

public class Strategy1 {
    public static Ladder distribution(Monkey nowM) {
        for (Ladder eachL : CrossRiver.ladders) {
            int x;
            if (nowM.getDirection() == 0) {
                x = 1;
            } else {
                x = eachL.getH();
            }
            if (CrossRiver.runningLadders.get(eachL).isMovable(x)) {
                if (CrossRiver.directionOfLadder.get(eachL).equals(2)) {
                    CrossRiver.speedOfLadder.put(eachL, nowM.getV());
                    CrossRiver.directionOfLadder.put(eachL, nowM.getDirection());
                    return eachL;
                }
            }
        }
        return null;
    }
}
