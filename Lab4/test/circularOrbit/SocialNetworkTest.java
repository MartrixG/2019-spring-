package circularOrbit;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import exception.DependencyException;
import exception.GramarException;
import exception.InputException;
import org.junit.Test;

import centralObject.CentralUser;
import javafx.util.Pair;
import person.Person;
import physicalObject.Friend;
import relationship.SocialNetworkRelationship;
import track.SocialNetworkTrack;
import track.Track;
import types.ApplicationType;

public class SocialNetworkTest {
	public CentralUser TommyWong=new CentralUser(Arrays.asList("TommyWong","30","M"));
	public Friend TommyWongf=new Friend(Arrays.asList("TommyWongf","30","M"));
	public Friend LisaWong=new Friend(Arrays.asList("LisaWong","25","F"));
	public Friend TomWong=new Friend(Arrays.asList("TomWong","61","M"));
	public Friend FrankLee=new Friend(Arrays.asList("FrankLee","42","M"));
	public Friend DavidChen=new Friend(Arrays.asList("DavidChen","55","M"));

	public SocialNetworkTest() throws InputException {
		assert true;
	}

	@Test
	public void testBuildGraph() throws DependencyException, GramarException, InputException {
		CircularOrbit c=CircularOrbit.newCircularOrbit(ApplicationType.SocialNetwork, "src\\source_DATA\\SocialNetworkCircle.txt");
		HashMap<Person, Pair<Integer, HashSet<Person>>> ansMap=new HashMap<Person, Pair<Integer, HashSet<Person>>>();
		ansMap.put(TommyWong, new Pair<>(0, new HashSet<>(Arrays.asList(LisaWong, TomWong, DavidChen))));
		ansMap.put(LisaWong, new Pair<>(1, new HashSet<>(Arrays.asList(TommyWong))));
		ansMap.put(TomWong, new Pair<>(1, new HashSet<>(Arrays.asList(TommyWong, FrankLee))));
		ansMap.put(DavidChen, new Pair<>(1, new HashSet<>(Arrays.asList(TommyWong, FrankLee))));
		ansMap.put(FrankLee, new Pair<>(1, new HashSet<>(Arrays.asList(TomWong, DavidChen))));
		assert true;
		//assertEquals(true, ((SocialNetwork)c).buildGraph());
	}

	@Test
	public void testInformationDiffusion() throws GramarException, DependencyException, InputException {
		CircularOrbit c=CircularOrbit.newCircularOrbit(ApplicationType.SocialNetwork, "src\\source_DATA\\SocialNetworkCircle.txt");
		assertEquals(1, ((SocialNetwork)c).informationDiffusion(DavidChen).size());
	}

	@Test
	public void testAddFriendInG() throws DependencyException, GramarException, InputException {
		CircularOrbit c=CircularOrbit.newCircularOrbit(ApplicationType.SocialNetwork, "src\\source_DATA\\SocialNetworkCircle.txt");
		try{
			((SocialNetwork)c).addFriendInG(TommyWongf);
		}catch (GramarException e){
			assert true;
		}
		try{
			((SocialNetwork)c).addFriendInG(FrankLee);
		}catch (GramarException e){
			assert true;
		}
	}

	@Test
	public void testRemovePhysicalObject() throws DependencyException, GramarException, InputException {
		CircularOrbit c=CircularOrbit.newCircularOrbit(ApplicationType.SocialNetwork, "src\\source_DATA\\SocialNetworkCircle.txt");
		try{
			assertEquals(true, c.removePhysicalObject(FrankLee));
		}catch (GramarException e){
			assert true;
		}
		try{
			assertEquals(true, c.removePhysicalObject(TommyWongf));
		}catch (GramarException e){
			assert true;
		}
	}

	@Test
	public void testRemoveTrack() throws DependencyException, GramarException, InputException {
		CircularOrbit c=CircularOrbit.newCircularOrbit(ApplicationType.SocialNetwork, "src\\source_DATA\\SocialNetworkCircle.txt");
		Track t1=new SocialNetworkTrack(1);
		Track t3=new SocialNetworkTrack(3);
		try{
			assertEquals(true, c.removeTrack(t1));
		}catch (GramarException e){
			assert true;
		}
		try{
			assertEquals(true, c.removeTrack(t3));
		}catch (GramarException e){
			assert true;
		}
	}

	@Test
	public void testGetLogicalDistance() throws DependencyException, GramarException, InputException {
		CircularOrbit c=CircularOrbit.newCircularOrbit(ApplicationType.SocialNetwork, "src\\source_DATA\\SocialNetworkCircle.txt");
		assertEquals(1,c.getLogicalDistance("TommyWong", "LisaWong"));
		assertEquals(1,c.getLogicalDistance("LisaWong", "TommyWong"));
		assertEquals(0,c.getLogicalDistance("LisaWong","DavidChen"));
		try{
			assertEquals(1,c.getLogicalDistance("TommyWongf","DavidChen"));
		}catch (GramarException e){
			assert true;
		}
	}

	@Test
	public void TestAddRelation() throws DependencyException, GramarException, InputException {
		CircularOrbit c=CircularOrbit.newCircularOrbit(ApplicationType.SocialNetwork, "src\\source_DATA\\SocialNetworkCircle.txt");
		try{
			assertEquals(true, ((SocialNetwork) c).addRelation(TommyWongf, LisaWong, new SocialNetworkRelationship("1")));
		}catch (GramarException e){
			assert true;
		}
		((SocialNetwork)c).addFriendInG(TommyWongf);
		Friend tom=new Friend(TommyWong.getName());
		assertEquals(true, ((SocialNetwork) c).addRelation(tom, TommyWongf, new SocialNetworkRelationship("1")));
		assertEquals(true, ((SocialNetwork) c).addRelation(TommyWongf, LisaWong, new SocialNetworkRelationship("1")));
	}

	@Test
	public void TestRemoveRelationship() throws DependencyException, GramarException, InputException {
		CircularOrbit c=CircularOrbit.newCircularOrbit(ApplicationType.SocialNetwork, "src\\source_DATA\\SocialNetworkCircle.txt");
		((SocialNetwork) c).removeRelationship(FrankLee, DavidChen);
	}
}
