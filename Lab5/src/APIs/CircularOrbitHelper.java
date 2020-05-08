package APIs;

import circularOrbit.CircularOrbit;
import visual.Visual;

public class CircularOrbitHelper {
    private CircularOrbitHelper() {
    }

    public static Visual visualize(CircularOrbit c) {
        Visual v = new Visual(c);
        return v;
    }
}
