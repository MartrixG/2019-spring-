package monkey;

public class MonkeyGenerator {
    //L->R 0 R->L 1
    public static Monkey creatMonkey(int name, int v, int direction) {
        String[] args = new String[3];
        args[0] = String.valueOf(name);
        args[1] = String.valueOf(v);
        if (direction == 0) {
            args[2] = "L->R";
        } else {
            args[2] = "R->L";
        }
        return new Monkey(args);
    }
}
