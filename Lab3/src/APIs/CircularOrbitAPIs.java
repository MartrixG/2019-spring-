package APIs;

import circularOrbit.CircularOrbit;
import difference.Difference;
import track.Track;
import types.ApplicationType;


/**
 * This API is built for clients to get informations from the given CircularOrbits and Objects.
 * @author Martrix

 */
public class CircularOrbitAPIs {
	public static void deleteTrack(CircularOrbit c, String[] arg){
		int number=Integer.valueOf(arg[0]);
		for(Object eachT:c.getTracks()){
			if(((Track)eachT).getNoNumber().equals(number)){
				c.removeTrack((Track)eachT);
			}
		}
	}

	/**
	 * This method is to calculate the total Entropy of a CircularOrbit.
	 * @param c label of CircularOrbit to calculate
	 * @return a (double) type of variable
	 */
	public static double getObjectDistributionEntropy(CircularOrbit c) {
		return c.getShang();
	}
	
	/**
	 * Calculate the physical distance of two objects in CircularOrbit c
	 * @param c label of the CircularOrbit
	 * @param arg label of the args
	 * @return a (double) type of variable
	 */
	public static double getPhysicalDistance (CircularOrbit c, String[] arg) {
		if(c.getType().equals(ApplicationType.AtomStructure)){
			return 100000;
		}else if(c.getType().equals(ApplicationType.TrackGame)){
			return 100000;
		}else{
			return c.getPhysicalDistance(arg[0], arg[1]);
		}
	}

	/**
	 * Calculate the logical distance of two objects in CircularOrbit c
	 * logical distance means the number of relations between e1 and e2 
	 * @param c label of the CircularOrbit
	 * @param arg label of the args
	 * @return a (int) type of variable if there is a existing relation chain between e1 and e2
	 * 			else return NaN
	 */
	public static int getLogicalDistance (CircularOrbit c,String[] arg) {
		return c.getLogicalDistance(arg[0],arg[1]);
	}
	
	/**
	 * Calculate the difference between two CircularOrbits.
	 * It needs to be expressed as follows: the difference of the number of orbits,
	 * the difference of the number of objects in the same track and the difference
	 * of objects (if the objects do not need to be distinguished, the difference of
	 * objects need not be given, only the difference of numbers).
	 * @param c1 
	 * @param c2
	 * @return
	 */
	public static Difference getDifference (CircularOrbit c1, CircularOrbit c2) {
		String s=new String();
		int di=Math.abs(c1.getTracks().size()-c2.getTracks().size());
		s+="轨道数差异：";
		s+=di;
		s+='\n';
		for(int i=0;i<Math.min(c1.getTracks().size(), c2.getTracks().size());i++){
			s+="轨道";
			s+=(i+1);
			s+="的物体数量差异：";
			s+=Math.abs(c1.getPhysicalObjects((Track)c1.getTracks().get(i)).size()-c2.getPhysicalObjects((Track)c1.getTracks().get(i)).size());
			s+='\n';
		}
		Difference f=new Difference(s);
		return f;
	}
}
