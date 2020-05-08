package P4.twitter;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class SocialNetworkTest {
	private  Instant d1 = Instant.parse("2018-04-27T10:00:00Z");
    private  Instant d2 = Instant.parse("2018-02-17T11:00:00Z");
    private  Instant d3 = Instant.parse("2018-03-10T08:45:43Z");
    private  Instant d4 = Instant.parse("2018-03-10T08:44:12Z");
    
    private  Tweet t1 = new Tweet(1, "a", "@b @c is it reasonable to talk about rivest so much?", d1);
    private  Tweet t2 = new Tweet(2, "b", "@c @d This South Korean gentleman just won the entire Olympics.", d2);
    private  Tweet t3 = new Tweet(3, "c", "@b Y'all are stuck in 2018 watching the Olympics in 2", d3);
    private  Tweet t4 = new Tweet(4, "d", "@c TeamIreand in Abu Dhabi for Special Olympics World", d4);
	String n1="a";
	String n2="b";
	String n3="c";
	String n4="d";
	@Test
	void testGuessFollowsGraph() {
		List<Tweet> tweets = new ArrayList<Tweet>();
		tweets.add(t1);tweets.add(t2);
		tweets.add(t3);tweets.add(t4);
		Map<String, Set<String>> ansMap=new HashMap<String, Set<String>>();
		Map<String, Set<String>> testMap=new HashMap<String, Set<String>>();
		Set<String> set1=new HashSet<String>();
		Set<String> set2=new HashSet<String>();
		Set<String> set3=new HashSet<String>();
		set1.add(n1);set1.add(n3);
		ansMap.put(n2, set1);		
		set2.add(n1);set2.add(n2);set2.add(n4);
		ansMap.put(n3, set2);
		set3.add(n2);
		ansMap.put(n4, set3);
		testMap=SocialNetwork.guessFollowsGraph(tweets);
		assertEquals(ansMap, testMap);
		//fail("Not yet implemented");
	}

	@Test
	void testInfluencers() {
		List<String> ansName=new ArrayList<String>();
		ansName.add(n3);
		ansName.add(n2);
		ansName.add(n4);
		List<Tweet> tweets = new ArrayList<Tweet>();
		tweets.add(t1);tweets.add(t2);
		tweets.add(t3);tweets.add(t4);
		assertEquals(ansName, SocialNetwork.influencers(SocialNetwork.guessFollowsGraph(tweets)));
		//fail("Not yet implemented");
	}

}
