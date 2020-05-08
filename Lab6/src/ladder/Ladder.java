package ladder;

import java.util.List;

public class Ladder {
    private int name;
    private int h;

    public Ladder(String[] args) {
        name = Integer.valueOf(args[0]);
        h = Integer.valueOf(args[1]);
    }

    public Ladder(List<String> args) {
        name = Integer.valueOf(args.get(0));
        h = Integer.valueOf(args.get(1));
    }

    public int getName() {
        return name;
    }

    public int getH() {
        return h;
    }

    @Override
    public int hashCode() {
        return name;
    }
}
