package track;

import static org.junit.Assert.*;

import org.junit.Test;

import types.TrackType;

public class TrackTest {

	@Test
	public void testGetTrackType() {
		ElectronShells shell=new ElectronShells(1);
		assertEquals((int)1, (int)shell.noNumber);
	}

	@Test
	public void testGetType() {
		ElectronShells shell=new ElectronShells(1);
		assertEquals(TrackType.CIRCLE.toString(), shell.getTrackType());
	}

	@Test
	public void testGetNoNumber(){
		Track track=new GameTrack(1);
		assertEquals((Integer) 1,track.getNoNumber());
	}

	@Test
	public void testElectronShells(){
		Track shell=new ElectronShells(1);
		Track shell1=new ElectronShells(1);
		int hash=shell.hashCode();
		assertEquals(hash, shell.hashCode());
		assertEquals(true,shell.equals(shell1));
	}

	@Test
	public void testGameTrack(){
		Track t=new GameTrack(1);
		Track t1=new GameTrack(1);
		int hash=t.hashCode();
		assertEquals(hash, t.hashCode());
		assertEquals(true, t.equals(t1));
	}

	@Test
	public void testSocialNetworkTrack(){
		Track t=new SocialNetworkTrack(1);
		Track t1=new SocialNetworkTrack(1);
		int hash=t.hashCode();
		assertEquals(hash,t.hashCode());
		assertEquals(true,t.equals(t1));
	}

}
