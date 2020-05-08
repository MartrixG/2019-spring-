package applications.atomStructureApplication;

import applications.Interaction;
import circularOrbit.AtomStructure;
import exception.DependencyException;
import exception.GramarException;
import physicalObject.Electronic;
import track.ElectronShells;
import track.Track;
import types.operations.AtomStructureOperation;

import java.util.List;
import java.util.logging.Logger;

public class AtomStructureApplication {
    private AtomStructureApplication() {
    }

    public static String[] ops() {
        String[] reS = new String[AtomStructureOperation.values().length];
        for (int i = 0; i < AtomStructureOperation.values().length; i++) {
            reS[i] = AtomStructureOperation.values()[i].toString();
        }
        return reS;
    }

    public static void doOperation(AtomStructure c, int op) throws GramarException {
        try {
            String[] args = null;
            Track t = null;
            switch (op) {
                case 6:
                    Logger.getGlobal().info("Manual input track to add.");
                    System.out.println("Input number of Track");
                    args = Interaction.getArgs();
                    t = new ElectronShells(Integer.valueOf(args[0]));
                    c.addTrack(t);
                    break;
                case 7:
                    Logger.getGlobal().info("Manual input track to add electronic.");
                    System.out.println("Input number of Track");
                    args = Interaction.getArgs();
                    t = new ElectronShells(Integer.valueOf(args[0]));
                    List<Electronic> electronicList = c.getPhysicalObjects();
                    Electronic newE;
                    int num = 1;
                    while (true) {
                        newE = new Electronic(num);
                        if (electronicList.contains(newE)) {
                            num++;
                        } else {
                            break;
                        }
                    }
                    c.addPhysicalObject(t, newE);
                    c.setPosition(newE);
                    break;
                case 8:
                    Logger.getGlobal().info("Manual input track to remove electronic.");
                    System.out.println("Input the electronic in which track:");
                    args = Interaction.getArgs();
                    t = new ElectronShells(Integer.valueOf(args[0]));
                    if (!c.getTracks().contains(t)) {
                        throw new GramarException("The track input does not exist.");
                    } else if (c.getPhysicalObjects(t).size() == 0) {
                        throw new GramarException("The track does not have electronic.");
                        //System.out.println("The track does not have electronic.");
                    } else {
                        c.removePhysicalObject(c.getPhysicalObjects(t).get(0));
                    }
                    break;
                case 9:
                    Logger.getGlobal().info("Manual input to trans.");
                    System.out.println("Input the source track and the target track:");
                    args = Interaction.getArgs();
                    t = new ElectronShells(Integer.valueOf(args[0]));
                    Track t2 = new ElectronShells(Integer.valueOf(args[1]));
                    if ((!c.getTracks().contains(t)) || (!c.getTracks().contains(t2))) {
                        throw new GramarException("Input track does not exit.");
                        //System.out.println("Input track does not exist.");
                    } else if (c.getPhysicalObjects(t).size() == 0) {
                        throw new GramarException("The track des not have elextronic.");
                        //System.out.println("The track does not have electronic.");
                    }
                    c.trans(c.getPhysicalObjects(t).get(0), t2);
                    break;
                case 10:
                    Logger.getGlobal().info("UNDO");
                    System.out.println("Input the target status of the rollback:");
                    args = Interaction.getArgs();
                    c.undo(Integer.valueOf(args[0]));
                    if (!c.undo(Integer.valueOf(args[0]))) {
                        System.out.println("UNDO failed.");
                    }
                    break;
                default:
                    break;
            }
        } catch (GramarException e) {
            throw e;
        } catch (DependencyException e) {
            e.printStackTrace();
        }
    }
}
