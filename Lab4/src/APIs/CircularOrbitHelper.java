package APIs;

import circularOrbit.CircularOrbit;
import visual.Visual;

public class  CircularOrbitHelper {
    public static Visual visualize(CircularOrbit c){
        Visual v=new Visual(c);
        return v;
    }
}
