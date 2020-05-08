package applications.trackGameApplication;

import applications.Interaction;
import circularOrbit.TrackGame;
import exception.DependencyException;
import exception.GramarException;
import exception.InputException;
import physicalObject.Athlete;
import track.GameTrack;
import track.Track;
import types.operations.TrackGameOperation;

import java.util.logging.Logger;

public class TrackGameApplication {
    private TrackGameApplication() {
    }

    public static String[] ops() {
        String[] reS = new String[TrackGameOperation.values().length];
        for (int i = 0; i < TrackGameOperation.values().length; i++) {
            reS[i] = TrackGameOperation.values()[i].toString();
        }
        return reS;
    }

    public static void doOperation(TrackGame c, int op) throws GramarException {
        try {
            String[] args;
            Track t;
            Athlete newA;
            switch (op) {
                //add track
                case 6:
                    Logger.getGlobal().info("Manual input track to add.");
                    System.out.println("Input number of Track");
                    args = Interaction.getArgs();
                    t = new GameTrack(Integer.valueOf(args[0]));
                    c.addTrack(t);
                    break;
                //add ob
                case 7:
                    Logger.getGlobal().info("Manual input to add athlete.");
                    System.out.println("Input the param of the athlete(Name Number Country Year bestRecord) and the track:");
                    args = Interaction.getArgs();
                    newA = new Athlete(args);
                    t = new GameTrack(Integer.valueOf(args[5]));
                    c.addPhysicalObject(t, newA);
                    c.setPosition(newA);
                    break;
                //dele ob
                case 8:
                    Logger.getGlobal().info("Manual input to remove athlete.");
                    System.out.println("Input the name of the athlete");
                    args = Interaction.getArgs();
                    newA = new Athlete(args[0]);
                    c.removePhysicalObject(newA);
                    break;
                //ex track
                case 9:
                    //ex group
                case 10:
                    Logger.getGlobal().info("Manual input to switch two athletes.");
                    System.out.println("Input the name of the athletes");
                    args = Interaction.getArgs();
                    newA = new Athlete(args[0]);
                    Athlete newB = new Athlete(args[1]);
                    c.changeGroup(newA, newB);
                    break;
                //to string
                case 11:
                    Logger.getGlobal().info("Show the strategy of the game.");
                    System.out.println(c.buildTable());
                    break;
                default:
                    break;
            }
        } catch (DependencyException | InputException e) {
            e.printStackTrace();
        }
    }
}
