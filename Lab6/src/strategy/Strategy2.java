package strategy;

import ladder.Ladder;
import monkey.Monkey;
import runs.CrossRiver;

public class Strategy2 {
    public static Ladder distribution(Monkey nowM) {
        Ladder reLadder = null;
        for (Ladder eachL : CrossRiver.ladders) {
            int x;
            if (nowM.getDirection() == 0) {
                x = 1;
            } else {
                x = eachL.getH();
            }
            if (CrossRiver.runningLadders.get(eachL).isMovable(x)) {
                if (CrossRiver.directionOfLadder.get(eachL).equals(2) || CrossRiver.directionOfLadder.get(eachL).equals(nowM.getDirection())) {
                    reLadder = eachL;
                    CrossRiver.directionOfLadder.put(eachL, nowM.getDirection());
                    CrossRiver.speedOfLadder.put(eachL, nowM.getV());
                    break;
                }
            }
        }
        return reLadder;
    }
}
