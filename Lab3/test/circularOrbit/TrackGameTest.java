package circularOrbit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import centralObject.CentralObject;
import centralObject.TrackGameNull;
import physicalObject.Athlete;
import physicalObject.Electronic;
import track.GameTrack;
import track.Track;
import types.ApplicationType;
import types.trackGameTypes.GameType;

class TrackGameTest {
	@Test
	void testChangeGroup() {
		@SuppressWarnings("unchecked")
		CircularOrbit<TrackGameNull, Athlete> c=CircularOrbit.newCircularOrbit(ApplicationType.TrackGame, "src\\source_DATA\\TrackGame.txt");
		((TrackGame)c).setAthlete(GameType.Records);
		Athlete a1=new Athlete("Obama");
		Athlete a2=new Athlete("Trump");
		assertEquals(true, ((TrackGame)c).changeGroup(a1, a2));
	}

	@Test
	void testTrans() {
		@SuppressWarnings("unchecked")
		CircularOrbit<TrackGameNull, Athlete> c=CircularOrbit.newCircularOrbit(ApplicationType.TrackGame, "src\\source_DATA\\TrackGame.txt");
		((TrackGame)c).setAthlete(GameType.Records);
		Athlete a1=new Athlete("Obama");
		Track t1=new GameTrack(1);
		Track t4=new GameTrack(4);
		assertEquals(t4,((TrackGame)c).trans(a1, t1));
	}
}
