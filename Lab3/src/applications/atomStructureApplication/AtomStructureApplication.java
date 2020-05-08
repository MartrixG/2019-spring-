package applications.atomStructureApplication;

import applications.Interaction;
import circularOrbit.AtomStructure;
import physicalObject.Electronic;
import track.ElectronShells;
import track.Track;
import types.operations.AtomStructureOperation;

public class AtomStructureApplication {
    public static String[] Ops(){
        String[] reS=new String[AtomStructureOperation.values().length];
        for(int i=0;i<AtomStructureOperation.values().length;i++){
            reS[i]=AtomStructureOperation.values()[i].toString();
        }
        return reS;
    }
    public static void doOperation(AtomStructure c, int op){
        String[] args=null;
        Track t=null;
        switch (op){
            case 5:
                System.out.println("Input number of Track");
                args= Interaction.getArgs();
                t=new ElectronShells(Integer.valueOf(args[0]));
                if(!c.addTrack(t)){
                    System.out.println("Input track has existed");
                }
                break;
            case 6:
                System.out.println("Input number of Track");
                args= Interaction.getArgs();
                t=new ElectronShells(Integer.valueOf(args[0]));
                Electronic newE=new Electronic(c.getPhysicalObjects().size()+1);
                if(c.addPhysicalObject(t, newE)){
                    c.setPosition(newE);
                }else{
                    System.out.println("Input track does not exist.");
                }
                break;
            case 7:
                System.out.println("Input the electronic in which track:");
                args=Interaction.getArgs();
                t=new ElectronShells(Integer.valueOf(args[0]));
                if(!c.getTracks().contains(t)){
                    System.out.println("Input track does not exist.");
                }
                else if(c.getPhysicalObjects(t).size()==0){
                    System.out.println("The track does not have electronic.");
                }
                else{
                    c.removePhysicalObject(c.getPhysicalObjects(t).get(0));
                }
                break;
            case 8:
                System.out.println("Input the source track and the target track:");
                args=Interaction.getArgs();
                t=new ElectronShells(Integer.valueOf(args[0]));
                Track t2=new ElectronShells(Integer.valueOf(args[1]));
                if((!c.getTracks().contains(t))||(!c.getTracks().contains(t2))){
                    System.out.println("Input track does not exist.");
                }
                else if(c.getPhysicalObjects(t).size()==0){
                    System.out.println("The track does not have electronic.");
                }
                c.trans(c.getPhysicalObjects(t).get(0), t2);
                break;
            case 9:
                System.out.println("Input the target status of the rollback:");
                args=Interaction.getArgs();
                if(!c.UNDO(Integer.valueOf(args[0]))){
                    System.out.println("UNDO failed.");
                }
                break;
        }
    }
}
