package strategy;

import ladder.Ladder;
import monkey.Monkey;
import runs.CrossRiver;

public class Strategy3 {
    public static Ladder distribution(Monkey nowM) {
        Ladder reLadder = null;
        Ladder targetLadder = null;
        int minOfDelta = 20;
        int x;
        if (nowM.getDirection() == 0) {
            x = 1;
        } else {
            x = CrossRiver.ladders.iterator().next().getH();
        }
        for (Ladder eachL : CrossRiver.ladders) {
            if (CrossRiver.runningLadders.get(eachL).isMovable(x)) {
                if (nowM.getDirection() == CrossRiver.directionOfLadder.get(eachL)) {
                    if (nowM.getV() <= CrossRiver.speedOfLadder.get(eachL)) {
                        if (minOfDelta > CrossRiver.speedOfLadder.get(eachL) - nowM.getV()) {
                            minOfDelta = CrossRiver.speedOfLadder.get(eachL) - nowM.getV();
                            targetLadder = eachL;
                        }
                    }
                }
            }
        }

        if (targetLadder == null) {
            for (Ladder eachL : CrossRiver.ladders) {
                if (CrossRiver.directionOfLadder.get(eachL) == 2) {
                    CrossRiver.speedOfLadder.put(eachL, nowM.getV());
                    CrossRiver.directionOfLadder.put(eachL, nowM.getDirection());
                    reLadder = eachL;
                    break;
                }
            }
        } else {
            CrossRiver.speedOfLadder.put(targetLadder, nowM.getV());
            reLadder = targetLadder;
        }

        if (reLadder == null) {
            int numOfMonkeys = 1000;
            targetLadder = null;
            for (Ladder eachL : CrossRiver.ladders) {
                if (CrossRiver.directionOfLadder.get(eachL).equals(nowM.getDirection()) && CrossRiver.runningLadders.get(eachL).isMovable(x)) {
                    if (numOfMonkeys > CrossRiver.runningLadders.get(eachL).numOfMonkey()) {
                        numOfMonkeys = CrossRiver.runningLadders.get(eachL).numOfMonkey();
                        targetLadder = eachL;
                    }
                }
            }
            if (targetLadder != null) {
                reLadder = targetLadder;
            }
        }
        return reLadder;
    }
}
