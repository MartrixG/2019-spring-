package applications.socialNetworkApplication;

import applications.Interaction;
import circularOrbit.SocialNetwork;
import physicalObject.Friend;
import relationship.RelationShip;
import relationship.SocialNetworkRelationship;
import types.operations.SocialNetworkOperation;

public class SocialNetworkApplivation {
    public static String[] Ops(){
        String[] reS=new String[SocialNetworkOperation.values().length];
        for(int i=0;i<SocialNetworkOperation.values().length;i++){
            reS[i]=SocialNetworkOperation.values()[i].toString();
        }
        return reS;
    }
    public static void doOperation(SocialNetwork c, int op){
        String[] args=null;
        Friend nf=null;
        switch (op){
            case 5:
                System.out.println("Input a new friend(name year sex):");
                args= Interaction.getArgs();
                nf=new Friend(args);
                c.addFriendInG(nf);
                break;
            case 6:
                System.out.println("Input the name of person to remove:");
                args=Interaction.getArgs();
                nf=new Friend(args[0]);
                if(!c.removePhysicalObject(nf)){
                    System.out.println("The person does not exist.");
                }
                break;
            case 7:
                System.out.println("Input the name of person to remove:");
                args=Interaction.getArgs();
                nf=new Friend(args[0]);
                System.out.println(c.informationDiffusion(nf).size());
                break;
            case 8:
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
                System.out.println("Input the two people's name to remove the relationship:");
                args=Interaction.getArgs();
                nf=new Friend(args[0]);
                Friend nf1=new Friend(args[1]);
                if(!c.removeRelationship(nf,nf1)){
                    System.out.println("The to person do not exist.");
                }
                break;
        }
    }
}
