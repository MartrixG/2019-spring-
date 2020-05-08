package applications.socialNetworkApplication;

import applications.Interaction;
import circularOrbit.SocialNetwork;
import exception.DependencyException;
import exception.GramarException;
import exception.InputException;
import physicalObject.Friend;
import relationship.RelationShip;
import relationship.SocialNetworkRelationship;
import sun.rmi.runtime.Log;
import types.operations.SocialNetworkOperation;

import java.util.logging.Logger;

public class SocialNetworkApplivation {
    public static String[] Ops(){
        String[] reS=new String[SocialNetworkOperation.values().length];
        for(int i=0;i<SocialNetworkOperation.values().length;i++){
            reS[i]=SocialNetworkOperation.values()[i].toString();
        }
        return reS;
    }
    public static void doOperation(SocialNetwork c, int op) throws GramarException, InputException, DependencyException {
        try{
            String[] args=null;
            Friend nf=null;
            switch (op){
                case 5:
                    Logger.getGlobal().info("Manual input the person's information to add.");
                    System.out.println("Input a new friend(name year sex):");
                    args= Interaction.getArgs();
                    nf=new Friend(args);
                    c.addFriendInG(nf);
                    break;
                case 6:
                    Logger.getGlobal().info("Manual input the person's information to remove");
                    System.out.println("Input the name of person to remove:");
                    args=Interaction.getArgs();
                    nf=new Friend(args[0]);
                    c.removePhysicalObject(nf);
                    break;
                case 7:
                    Logger.getGlobal().info("Manual input the person to calculate the information diffusion.");
                    System.out.println("Input the name of person to remove:");
                    args=Interaction.getArgs();
                    nf=new Friend(args[0]);
                    System.out.println(c.informationDiffusion(nf).size());
                    break;
                case 8:
                    Logger.getGlobal().info("Manual input two person to add relationship");
                    System.out.println("Input the two people's name and the relationship:");
                    args=Interaction.getArgs();
                    nf=new Friend(args[0]);
                    Friend nf2=new Friend(args[1]);
                    RelationShip r=new SocialNetworkRelationship(args[2]);
                    if(!c.addRelation(nf, nf2, r)){
                        System.out.println("The two person don not exist.");
                    }
                    break;
                case 9:
                    Logger.getGlobal().info("Manual input two person to remove relationship");
                    System.out.println("Input the two people's name to remove the relationship:");
                    args=Interaction.getArgs();
                    nf=new Friend(args[0]);
                    Friend nf1=new Friend(args[1]);
                    c.removeRelationship(nf,nf1);
                    break;
            }
        }catch(GramarException e){
            throw e;
        } catch (InputException e) {
            throw e;
        } catch (DependencyException e) {
            throw e;
        }
    }
}
