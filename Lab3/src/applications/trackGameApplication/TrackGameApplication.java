package applications.trackGameApplication;

import applications.Interaction;
import circularOrbit.TrackGame;
import physicalObject.Athlete;
import track.GameTrack;
import track.Track;
import types.operations.TrackGameOperation;

public class TrackGameApplication {
    public static String[] Ops(){
        String[] reS=new String[TrackGameOperation.values().length];
        for(int i=0;i<TrackGameOperation.values().length;i++){
            reS[i]=TrackGameOperation.values()[i].toString();
        }
        return reS;
    }
    public static boolean deleteTrack(String trackName){
        return false;
    }
    public static void doOperation(TrackGame c, int op){
        String[] args=null;
        Track t=null;
        Athlete newA=null;
        switch (op){
                //add track
            case 5:
                System.out.println("Input number of Track");
                args= Interaction.getArgs();
                t=new GameTrack(Integer.valueOf(args[0]));
                if(!c.addTrack(t)){
                    System.out.println("Input track has existed");
                }
                break;
                //add ob
            case 6:
                System.out.println("Input the param of the athlete(Name Number Country Year bestRecord) and the track:");
                args=Interaction.getArgs();
                newA =new Athlete(args);
                t=new GameTrack(Integer.valueOf(args[5]));
                if(c.addPhysicalObject(t, newA)){
                    c.setPosition(newA);
                }else{
                    System.out.println("Input track does not exist.");
                }
                break;
                //dele ob
            case 7:
                System.out.println("Input the name of the athlete");
                args=Interaction.getArgs();
                newA=new Athlete(args[0]);
                if(!c.removePhysicalObject(newA)){
                    System.out.println("Input athlete does not exist.");
                }
                break;
                //ex track
            case 8:
                //ex group
            case 9:
                System.out.println("Input the name of the athletes");
                args=Interaction.getArgs();
                newA=new Athlete(args[0]);
                Athlete newB=new Athlete(args[1]);
                if(!c.changeGroup(newA,newB)){
                    System.out.println("Input athletes do not exist.");
                }
                break;
            //to string
            case 10:
                System.out.println(c.toString());
                break;
        }
    }
}
