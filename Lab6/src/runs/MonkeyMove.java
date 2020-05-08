package runs;

import ladder.Ladder;
import monkey.Monkey;

import java.util.logging.Logger;

public class MonkeyMove implements Runnable {
    private Monkey monkey;
    private Ladder ladder;
    private int[] copyOfRunningLadder;
    private int nowPos;

    MonkeyMove(Ladder r, Monkey m) {
        this.nowPos = 0;
        this.monkey = m;
        this.ladder = r;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (monkey.getDirection() == 0) {
            nowPos = 1;
            synchronized (CrossRiver.runningLadders.get(ladder)) {
                SynchronizeRunningLadder();
            }
            while (nowPos < ladder.getH()) {
                int step = 0;
                while (step < monkey.getV() && copyOfRunningLadder[nowPos + step + 1] != 1 && nowPos + step < ladder.getH()) {
                    step++;
                    //System.out.println(step);
                }
                Logger.getGlobal().info("Monkey " + monkey.getName() + " in " + "ladder " + ladder.getName() + " move " + step + " to " + (nowPos + step) + ".");
                copyOfRunningLadder[nowPos] = 0;
                copyOfRunningLadder[nowPos + step] = 1;
                synchronized (CrossRiver.runningLadders.get(ladder)) {
                    CrossRiver.runningLadders.get(ladder).setPos(nowPos, nowPos + step);
                }
                nowPos = nowPos + step;

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (CrossRiver.runningLadders.get(ladder)) {
                    SynchronizeRunningLadder();
                }
            }
        } else {
            nowPos = ladder.getH();
            synchronized (CrossRiver.runningLadders.get(ladder)) {
                SynchronizeRunningLadder();
            }
            while (nowPos > 1) {
                int step = 0;
                while (step < monkey.getV() && copyOfRunningLadder[nowPos - step - 1] != 1 && nowPos - step > 1) {
                    step++;
                }
                Logger.getGlobal().info("Monkey " + monkey.getName() + " in " + "ladder " + ladder.getName() + " move " + step + " to " + (nowPos + step) + ".");
                copyOfRunningLadder[nowPos] = 0;
                copyOfRunningLadder[nowPos - step] = 1;
                synchronized (CrossRiver.runningLadders.get(ladder)) {
                    CrossRiver.runningLadders.get(ladder).setPos(nowPos, nowPos - step);
                }
                nowPos -= step;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (CrossRiver.runningLadders.get(ladder)) {
                    SynchronizeRunningLadder();
                }
            }
        }
        synchronized (CrossRiver.runningLadders.get(ladder)) {
            CrossRiver.runningLadders.get(ladder).setZero(nowPos);
        }
        this.monkey.setFinaishTime(CrossRiver.runTime);
        CrossRiver.latch.countDown();
    }

    private void SynchronizeRunningLadder() {
        this.copyOfRunningLadder = CrossRiver.runningLadders.get(ladder).getPos();
    }
}
