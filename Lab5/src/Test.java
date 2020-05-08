import applications.socialNetworkApplication.SocialNetworkApplication;
import circularOrbit.CircularOrbit;
import circularOrbit.SocialNetwork;
import exception.DependencyException;
import exception.GramarException;
import exception.InputException;
import types.ApplicationType;
import types.ioType.IOType;

public class Test {
    public static void main(String[] args) throws DependencyException, GramarException, InputException {
        CircularOrbit c = CircularOrbit.newCircularOrbit(ApplicationType.SocialNetwork, "src/source_DATA/BIG_SocialNetworkCircle.txt", IOType.BufferReader);
        SocialNetworkApplication.doOperation((SocialNetwork) c, 6);
        //SocialNetworkApplication.doOperation((SocialNetwork) c, 7);
        //SocialNetworkApplication.doOperation((SocialNetwork) c, 8);
        //SocialNetworkApplication.doOperation((SocialNetwork) c, 9);
        //SocialNetworkApplication.doOperation((SocialNetwork) c, 10);
    }
}
