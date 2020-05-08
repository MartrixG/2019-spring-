package P4.twitter;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

class MySocialNetworkTest {
	private  Instant d1 = Instant.parse("2018-04-27T10:00:00Z");
    private  Instant d2 = Instant.parse("2018-02-17T11:00:00Z");
    private  Instant d3 = Instant.parse("2018-03-10T08:45:43Z");
    private  Instant d4 = Instant.parse("2018-03-09T08:44:12Z");
    private  Instant d5 = Instant.parse("2018-02-10T08:44:12Z");
    
    private  Tweet t1 = new Tweet(1, "a", "@b is it reasonable to talk about rivest so much?", d1);
    private  Tweet t2 = new Tweet(2, "b", "@a @c @e SoSportsNation: This South Korean gentleman just won the entire Olympics.", d2);
    private  Tweet t3 = new Tweet(3, "c", "@d @b reallyhoffman Y'all are stuck in 2018 watching the Olympics in 2D.", d3);
    private  Tweet t4 = new Tweet(4, "d", "@c @e AlanQuinlan1 #TeamIreand in Abu Dhabi for Special Olympics World.", d4);
    private  Tweet t5 = new Tweet(5, "e", "@c @b @d #TeamIreand in Abu Dhabi for Special Olympics World.", d5);
	
    String a="a";
	String b="b";
	String c="c";
	String d="d";
	String e="e";
	
    @Test
	void testTriadicClosure() {
    	List<Tweet> tweets = new ArrayList<Tweet>();
    	tweets.add(t1);tweets.add(t2);tweets.add(t3);
    	tweets.add(t4);tweets.add(t5);
    	
    	Map<String, Set<String>> ansMap=new HashMap<String, Set<String>>();
    	ansMap.put(a, new HashSet<String>(Arrays.asList(c,e)));
    	ansMap.put(b, new HashSet<String>(Arrays.asList(d)));
    	ansMap.put(c, new HashSet<String>(Arrays.asList(e,a)));
    	ansMap.put(d, new HashSet<String>(Arrays.asList(b)));
    	ansMap.put(e, new HashSet<String>(Arrays.asList(a,c)));
    	
    	assertEquals(ansMap, SocialNetwork.triadicClosure(SocialNetwork.guessFollowsGraph(tweets)));
	}

}
