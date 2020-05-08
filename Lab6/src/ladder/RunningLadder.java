package ladder;

public class RunningLadder {
    private Ladder ladder;
    private int[] pos;

    public RunningLadder(Ladder l) {
        this.ladder = l;
        pos = new int[l.getH() + 2];
    }

    public Ladder getLadder() {
        return ladder;
    }

    public int[] getPos() {
        return pos.clone();
    }

    public void setPos(int from, int to) {
        pos[from] = 0;
        pos[to] = 1;
    }

    public void setZero(int x) {
        pos[x] = 0;
    }

    public boolean isMovable(int x) {
        return pos[x] == 0;
    }

    public boolean ifEmpty() {
        int tot = 0;
        for (int i = 1; i <= ladder.getH(); i++) {
            tot += pos[i];
        }
        return tot == 0;
    }

    public int numOfMonkey() {
        int tot = 0;
        for (int i = 1; i <= ladder.getH(); i++) {
            tot += pos[i];
        }
        return tot;
    }

    @Override
    public String toString() {
        StringBuilder reS = new StringBuilder();
        for (int i = 1; i <= ladder.getH(); i++) {
            reS.append(pos[i]);
            reS.append(' ');
        }
        return reS.toString();
    }
}
