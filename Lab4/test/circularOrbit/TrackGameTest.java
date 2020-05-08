package circularOrbit;

import static org.junit.Assert.*;

import exception.DependencyException;
import exception.GramarException;
import exception.InputException;
import org.junit.Test;

import centralObject.TrackGameNull;
import physicalObject.Athlete;
import relationship.SocialNetworkRelationship;
import track.GameTrack;
import track.Track;
import types.ApplicationType;
import types.trackGameTypes.GameType;

public class TrackGameTest {
	@Test
	public void testChangeGroup() throws DependencyException, GramarException, InputException {
		@SuppressWarnings("unchecked")
		CircularOrbit<TrackGameNull, Athlete> c=CircularOrbit.newCircularOrbit(ApplicationType.TrackGame, "src\\source_DATA\\TrackGame.txt");
		((TrackGame)c).setAthlete(GameType.Records);
		Athlete a1=new Athlete("Obama");
		Athlete a2=new Athlete("Trump");
		assertEquals(true, ((TrackGame)c).changeGroup(a1, a2));
	}

	@Test
	public void testTrans() throws DependencyException, GramarException, InputException {
		@SuppressWarnings("unchecked")
		CircularOrbit<TrackGameNull, Athlete> c=CircularOrbit.newCircularOrbit(ApplicationType.TrackGame, "src\\source_DATA\\TrackGame.txt");
		((TrackGame)c).setAthlete(GameType.Records);
		Athlete a1=new Athlete("Obama");
		Track t1=new GameTrack(1);
		Track t4=new GameTrack(4);
		assertEquals(t4,((TrackGame)c).trans(a1, t1));
	}

	@Test
	public void testAddPhysicalObject() throws DependencyException, GramarException, InputException {
		CircularOrbit<TrackGameNull, Athlete> c=CircularOrbit.newCircularOrbit(ApplicationType.TrackGame, "src\\source_DATA\\TrackGame.txt");
		((TrackGame)c).setAthlete(GameType.Records);
		Track t1=new GameTrack(1);
		Athlete newA=new Athlete("aa");
		Track t2=new GameTrack(5);
		Athlete newB=new Athlete("bb");
		try{
			assertEquals(true, c.addPhysicalObject(t1,newA));
		}catch (GramarException e){
			assert true;
		}
		assertEquals(true, c.addPhysicalObject(t2,newB));
	}

	@Test
	public void testToString() throws DependencyException, GramarException, InputException {
		CircularOrbit<TrackGameNull, Athlete> c=CircularOrbit.newCircularOrbit(ApplicationType.TrackGame, "src\\source_DATA\\TrackGame.txt");
		((TrackGame)c).setAthlete(GameType.Records);
		String s="\t\tTrack1\t\tTrack2\t\tTrack3\t\tTrack4\t\tTrack5\n" +
				"Group0\tChistr\t\tTrump\t\tRonaldo\t\tObama\t\tCliton\t\n" +
				"Group1\tPark\t\tWeiii\t\tBolt\t\tLewis\t\tPeter\t\n" +
				"Group2\t\t\t\tTommy\t\tCoal\t\tChen\t\t\t\t\n";
		assertEquals(s,c.toString());
	}

	@Test
	public void testOther() throws DependencyException, GramarException, InputException {
		CircularOrbit<TrackGameNull, Athlete> c=CircularOrbit.newCircularOrbit(ApplicationType.TrackGame, "src\\source_DATA\\TrackGame.txt");
		((TrackGame)c).setAthlete(GameType.Records);
		assertEquals(10000, c.getLogicalDistance("a","b"));
		assertEquals(false, c.addRelationC2P(c.getCenterObject(), c.getPhysicalObjects().get(0), new SocialNetworkRelationship("1")));
		assertEquals(false, c.addRelationP2P(c.getPhysicalObjects().get(0),c.getPhysicalObjects().get(1),new SocialNetworkRelationship("1")));
	}
}
