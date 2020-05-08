package runs;

import javafx.util.Pair;
import ladder.Ladder;
import ladder.RunningLadder;
import monkey.Monkey;
import monkey.MonkeyGenerator;
import strategy.Strategy3;
import visual.Visual;

import java.util.*;
import java.util.concurrent.CountDownLatch;

public class CrossRiver extends Thread {
    private int n;//梯子数
    private int h;//梯子长度
    private int t;//刷新时间
    private int N;//总共猴子数
    private int k;//每次生成的猴子数
    private int MV;//猴子最大速度
    private Map<Integer, Set<Monkey>> allMonkeys = new HashMap<>();
    public static CountDownLatch latch;
    public static Set<Ladder> ladders = new HashSet<>();
    public static final Set<Monkey> leftMonkey = new HashSet<>();
    public static Map<Ladder, RunningLadder> runningLadders = new HashMap<>();
    public static Map<Ladder, Integer> directionOfLadder = new HashMap<>();
    public static Map<Ladder, Integer> speedOfLadder = new HashMap<>();
    public static boolean go = true;
    static int runTime = 0;

    @Override
    public void run() {
        while (go) {
            Thread view = new Visual();
            view.start();
            if (allMonkeys.get(runTime) != null) {
                synchronized (leftMonkey) {
                    leftMonkey.addAll(allMonkeys.get(runTime));
                }
            }
            boolean flag = true;
            for (Ladder eachL : ladders) {
                flag &= runningLadders.get(eachL).ifEmpty();
            }
            if (flag && leftMonkey.size() == 0) {
                go = false;
            }
            for (Ladder eachE : ladders) {
                if (runningLadders.get(eachE).ifEmpty()) {
                    directionOfLadder.put(eachE, 2);
                }
            }
            Random random = new Random();
            Set<Monkey> toRemove = new HashSet<>();
            for (Monkey eachM : leftMonkey) {
                int chose;
                chose = random.nextInt(3);
                Ladder targetLadder;
                Thread newT;
                switch (chose) {
                    case 0:
                        targetLadder = Strategy3.distribution(eachM);
                        break;
                    case 1:
                        targetLadder = Strategy3.distribution(eachM);
                        break;
                    case 2:
                        targetLadder = Strategy3.distribution(eachM);
                        break;
                    default:
                        targetLadder = null;
                }
                if (targetLadder != null) {
                    MonkeyMove tmpM = new MonkeyMove(targetLadder, eachM);
                    newT = new Thread(tmpM);
                    if (eachM.getDirection() == 0) {
                        runningLadders.get(targetLadder).setPos(0, 1);
                    } else {
                        runningLadders.get(targetLadder).setPos(0, targetLadder.getH());
                    }
                    toRemove.add(eachM);
                    newT.start();
                }
            }
            synchronized (leftMonkey) {
                for (Monkey eachM : toRemove) {
                    leftMonkey.remove(eachM);
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            runTime++;
        }
        Visual.frame.dispose();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        calcEfficiencyFairness();
    }

    public void calcEfficiencyFairness() {
        List<Monkey> monkeys = new ArrayList<>();
        for (Integer eachI : allMonkeys.keySet()) {
            monkeys.addAll(allMonkeys.get(eachI));
        }
        System.out.print("Throughput rate:");
        System.out.println((double) monkeys.size() / (double) runTime);
        double F = 0;
        for (int i = 0; i < monkeys.size(); i++) {
            for (int j = 0; j < monkeys.size(); j++) {
                if (i != j) {
                    F += monkeys.get(i).calcF(monkeys.get(j));
                }
            }
        }
        F /= (double) (monkeys.size() * (monkeys.size() - 1));
        System.out.println("Fairness:" + F);
    }

    CrossRiver(List<Pair<String, List<String>>> paramList) {
        if (paramList.get(2).getKey().equals("monkey")) {
            monkeyInit(paramList);
        } else {
            paramInit(paramList);
        }
    }

    private void monkeyInit(List<Pair<String, List<String>>> paramList) {
        for (Pair<String, List<String>> eachP : paramList) {
            switch (eachP.getKey()) {
                case "n":
                    this.n = Integer.valueOf(eachP.getValue().get(0));
                    break;
                case "h":
                    this.h = Integer.valueOf(eachP.getValue().get(0));
                    break;
                case "monkey":
                    Monkey newM = new Monkey(Arrays.asList(eachP.getValue().get(1), eachP.getValue().get(4), eachP.getValue().get(2)));
                    int runTime = Integer.valueOf(eachP.getValue().get(0));
                    newM.setBornTime(runTime);
                    allMonkeys.computeIfAbsent(runTime, k1 -> new HashSet<>());
                    allMonkeys.get(runTime).add(newM);
                    break;
            }
        }
        String[] args = new String[2];
        for (int i = 1; i <= n; i++) {
            args[0] = String.valueOf(i);
            args[1] = String.valueOf(h);
            Ladder newL = new Ladder(args);
            ladders.add(newL);
            directionOfLadder.put(newL, 2);
            runningLadders.put(newL, new RunningLadder(newL));
            speedOfLadder.put(newL, 10);
        }
        int numOfMonkeys = 0;
        for (Integer eachI : allMonkeys.keySet()) {
            numOfMonkeys += allMonkeys.get(eachI).size();
        }
        latch = new CountDownLatch(numOfMonkeys);
    }

    private void paramInit(List<Pair<String, List<String>>> paramList) {
        for (Pair<String, List<String>> eachP : paramList) {
            switch (eachP.getKey()) {
                case "n"://梯子数
                    this.n = Integer.valueOf(eachP.getValue().get(0));
                    break;
                case "h"://梯子长度
                    this.h = Integer.valueOf(eachP.getValue().get(0));
                    break;
                case "t"://刷新时间
                    this.t = Integer.valueOf(eachP.getValue().get(0));
                    break;
                case "N"://总共猴子数
                    this.N = Integer.valueOf(eachP.getValue().get(0));
                    break;
                case "k"://每次生成的猴子数
                    this.k = Integer.valueOf(eachP.getValue().get(0));
                    break;
                case "MV"://猴子最大速度
                    this.MV = Integer.valueOf(eachP.getValue().get(0));
                    break;
            }
        }
        latch = new CountDownLatch(N);
        String[] args = new String[2];
        for (int i = 1; i <= n; i++) {
            args[0] = String.valueOf(i);
            args[1] = String.valueOf(h);
            Ladder newL = new Ladder(args);
            ladders.add(newL);
            directionOfLadder.put(newL, 2);
            speedOfLadder.put(newL, 20);
            runningLadders.put(newL, new RunningLadder(newL));
        }
        int runTime = 0;
        for (int numOfMonkeys = 0; numOfMonkeys <= N; numOfMonkeys += k) {
            int numOfCreatMonkey = 0;
            if (numOfMonkeys + k > N) {
                numOfCreatMonkey = N - numOfMonkeys;
            } else {
                numOfCreatMonkey = k;
            }
            Random random = new Random();
            Set<Monkey> tempSet = new HashSet<>();
            for (int i = 1; i <= numOfCreatMonkey; i++) {
                int d = random.nextInt(2);
                Monkey newM = MonkeyGenerator.creatMonkey(numOfMonkeys + i, (random.nextInt(MV + 1) + 1), d);
                tempSet.add(newM);
                newM.setBornTime(runTime);
            }
            allMonkeys.put(runTime, tempSet);
            runTime += t;
        }
    }
}
