package applications;

import APIs.CircularOrbitAPIs;
import APIs.CircularOrbitHelper;
import applications.atomStructureApplication.AtomStructureApplication;
import applications.socialNetworkApplication.SocialNetworkApplivation;
import applications.trackGameApplication.TrackGameApplication;
import circularOrbit.AtomStructure;
import circularOrbit.CircularOrbit;
import circularOrbit.SocialNetwork;
import circularOrbit.TrackGame;
import exception.DependencyException;
import exception.GramarException;
import exception.InputException;
import types.ApplicationType;
import types.operations.BaseOperationType;
import types.trackGameTypes.GameType;
import visual.Visual;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Applications {
    public static void doOperation(CircularOrbit c) throws InputException, GramarException {
        Visual v = CircularOrbitHelper.visualize(c);
        v.show();
        List<String> args=new ArrayList<String>();
        for(BaseOperationType eachOp:BaseOperationType.values()){
            args.add(eachOp.toString());
        }
        String[] ops=null;
        switch (c.getType()){
            case TrackGame:
                ops=TrackGameApplication.Ops();
                break;
            case SocialNetwork:
                ops= SocialNetworkApplivation.Ops();
                break;
            case AtomStructure:
                ops= AtomStructureApplication.Ops();
                break;
        }
        for(int i=0;i<ops.length;i++){
            args.add(ops[i]);
        }
        while(true){
            v.dispose();
            v.show();
            int op=Interaction.chooseOp(args);
            if(op==-1){
                v.dispose();
                break;
            }
            switch (op) {
                case 0:
                    System.out.println("Input Track number:");
                    break;
                case 1:
                    System.out.println(CircularOrbitAPIs.getObjectDistributionEntropy(c));
                    break;
                case 2:
                    System.out.println("Input two name of physical objects:");
                    break;
                case 3:
                    System.out.println("Input two name of physical objects:");
                    break;
                case 4:
                    CircularOrbit c2=Applications.newApplication();
                    System.out.print(CircularOrbitAPIs.getDifference(c,c2).getDif());
                    break;
            }
            String[] arg=null;
            if(op==0||op==2||op==3)
                arg=Interaction.getArgs();
            try{
                switch (op){
                    case 0:
                        CircularOrbitAPIs.deleteTrack(c, arg);
                        break;
                    case 2:
                        System.out.println(CircularOrbitAPIs.getPhysicalDistance(c, arg));
                        break;
                    case 3:
                        System.out.println(CircularOrbitAPIs.getLogicalDistance(c, arg)-1);
                        break;
                }
            }catch(GramarException e){
                Logger.getGlobal().warning(e.getLocalizedMessage());
            } catch (DependencyException e) {
                Logger.getGlobal().warning(e.getLocalizedMessage());
            }

            if(op>=5){
                try{
                    switch (c.getType()){
                        case TrackGame:
                            TrackGameApplication.doOperation((TrackGame)c, op);
                            break;
                        case SocialNetwork:
                            SocialNetworkApplivation.doOperation((SocialNetwork)c, op);
                            break;
                        case AtomStructure:
                            AtomStructureApplication.doOperation((AtomStructure) c, op);
                            break;
                    }
                }catch(GramarException e){
                    Logger.getGlobal().warning(e.getLocalizedMessage());
                }catch (DependencyException e) {
                    Logger.getGlobal().warning(e.getLocalizedMessage());
                }
            }
        }
    }
    public static CircularOrbit newApplication() {
        try{
            Logger.getGlobal().info("Choose file to build system.");
            String path="src/source_DATA";
            ApplicationType newType=Interaction.chooseApp();
            String fileName=path+"/"+Interaction.chooseFile(path);
            CircularOrbit c=CircularOrbit.newCircularOrbit(newType,fileName);
            if(newType.equals(ApplicationType.TrackGame)){
                GameType strategy=Interaction.chooseGameType();
                ((TrackGame)c).setAthlete(strategy);
            }
            return c;
        }catch(Exception e){
            Logger.getGlobal().warning(e.getLocalizedMessage());
            return Applications.newApplication();
        }
    }
}
