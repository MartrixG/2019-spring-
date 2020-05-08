package monkey;

import java.util.List;

public class Monkey {
    private Integer name;
    private Integer v;
    private String direction;
    public int bornTime;
    public int finaishTime;

    Monkey(String[] args) {
        name = Integer.valueOf(args[0]);
        v = Integer.valueOf(args[1]);
        direction = args[2];
    }

    public int calcF(Monkey y) {
        if ((y.bornTime - this.bornTime) * (y.finaishTime - this.finaishTime) >= 0) {
            return 1;
        } else {
            return -1;
        }
    }

    public Monkey(List<String> args) {
        name = Integer.valueOf(args.get(0));
        v = Integer.valueOf(args.get(1));
        direction = args.get(2);
    }

    public void setBornTime(int t) {
        this.bornTime = t;
    }

    public void setFinaishTime(int t) {
        this.finaishTime = t;
    }

    public int getName() {
        return name;
    }

    public int getV() {
        return v;
    }

    //L->R 0 R->L 1
    public int getDirection() {
        if (direction.charAt(0) == 'L') {
            return 0;
        } else {
            return 1;
        }
    }
}
