package circularOrbit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.sun.javafx.collections.MappingChange.Map;

import centralObject.CentralUser;
import javafx.util.Pair;
import person.Person;
import physicalObject.Friend;
import relationship.RelationShip;
import relationship.SocialNetworkRelationship;
import types.ApplicationType;

class SocialNetworkTest {
	@SuppressWarnings("unchecked")
	public CircularOrbit<CentralUser, Friend> c=CircularOrbit.newCircularOrbit(ApplicationType.SocialNetwork, "\\src\\source_DATA\\SocialNetworkCircle.txt");
	public CentralUser TommyWong=new CentralUser(Arrays.asList("TommyWong","30","M"));
	public Friend TommyWongf=new Friend(Arrays.asList("TommyWong","30","M"));
	public Friend LisaWong=new Friend(Arrays.asList("LisaWong","25","F"));
	public Friend TomWong=new Friend(Arrays.asList("TomWong","61","M"));
	public Friend FrankLee=new Friend(Arrays.asList("FrankLee","42","M"));
	public Friend DavidChen=new Friend(Arrays.asList("DavidChen","55","M"));
	@Test
	void testBuildGraph() {
		HashMap<Person, Pair<Integer, HashSet<Person>>> ansMap=new HashMap<Person, Pair<Integer, HashSet<Person>>>();
		ansMap.put((Person)TommyWong, new Pair<Integer, HashSet<Person>>(0, new HashSet<Person>(Arrays.asList(LisaWong, TomWong, DavidChen))));
		ansMap.put((Person)LisaWong, new Pair<Integer, HashSet<Person>>(1, new HashSet<Person>(Arrays.asList(TommyWong))));
		ansMap.put((Person)TomWong, new Pair<Integer, HashSet<Person>>(1, new HashSet<Person>(Arrays.asList(TommyWong,FrankLee))));
		ansMap.put((Person)DavidChen, new Pair<Integer, HashSet<Person>>(1, new HashSet<Person>(Arrays.asList(TommyWong,FrankLee))));
		ansMap.put((Person)FrankLee, new Pair<Integer, HashSet<Person>>(1, new HashSet<Person>(Arrays.asList(TomWong,DavidChen))));
		assertEquals(true, ((SocialNetwork)c).buildGraph());
	}

	@Test
	void testInformationDiffusion() {
		assertEquals(0, ((SocialNetwork)c).informationDiffusion(DavidChen).size());
	}
}
