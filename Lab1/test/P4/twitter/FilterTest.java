package P4.twitter;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class FilterTest {
	private  Instant d1 = Instant.parse("2018-04-27T10:00:00Z");
    private  Instant d2 = Instant.parse("2018-02-17T11:00:00Z");
    private  Instant d3 = Instant.parse("2018-03-10T08:45:43Z");
    private  Instant d4 = Instant.parse("2018-03-09T08:44:12Z");
    private  Instant ds = Instant.parse("2018-02-10T08:44:12Z");
    private  Instant de = Instant.parse("2018-04-10T08:44:12Z");
    
    private  Tweet t1 = new Tweet(1, "fawn_Graham", "is it reasonable to talk about rivest so much?", d1);
    private  Tweet t2 = new Tweet(2, "bbitdiddle", "RT @SoSportsNation: This South Korean gentleman just won the entire Olympics. #PyeongChang2018 #Olympics https://t.co/ZW7ZOwIZ4G", d2);
    private  Tweet t3 = new Tweet(3, "SimmonsREteam", "RT @reallyhoffman @HealthFitnessIE Y'all are stuck in 2018 watching the Olympics in 2D while I'm in the future watching it in 3D https://t.co/CsKHpbZQki", d3);
    private  Tweet t4 = new Tweet(4, "NormanCharles88", "@flourish_pantry @HealthFitnessIE @NutraminoIRL @AlanQuinlan1 #TeamIreand in Abu Dhabi for Special Olympics World S\\u2026 https://t.co/iehoYVl8Ju", d4);
    private  Tweet t5 = new Tweet(5, "NormanCharles88", "@flourish_pantry @HealthFitnessIE @NutraminoIRL @AlanQuinlan1 #TeamIreand in Abu Dhabi for Special Olympics World S\\u2026 https://t.co/iehoYVl8Ju", d4);
    @Test
	void testWrittenBy() {
    	List<Tweet> tweets=new ArrayList<Tweet>();
    	tweets.add(t1);tweets.add(t2);tweets.add(t3);
    	tweets.add(t4);tweets.add(t5);
		List<Tweet> ansList=new ArrayList<Tweet>();
		ansList.add(t4);
		ansList.add(t5);
		String nameString="NormanCharles88";
		assertEquals(ansList, Filter.writtenBy(tweets,nameString));
		//fail("Not yet implemented");
	}

    @Test
    void testInTimespan() {
    	Timespan timespan=new Timespan(ds,de);
    	List<Tweet> tweets=new ArrayList<Tweet>();
    	tweets.add(t1);tweets.add(t2);tweets.add(t3);
    	tweets.add(t4);tweets.add(t5);
    	List<Tweet> ansList=new ArrayList<Tweet>();
    	ansList.add(t2);
    	ansList.add(t3);
    	ansList.add(t4);
		ansList.add(t5);
		assertEquals(ansList, Filter.inTimespan(tweets, timespan));
    }
    
    @Test
    void testContaining() {
    	List<Tweet> tweets=new ArrayList<Tweet>();
    	tweets.add(t1);tweets.add(t2);tweets.add(t3);
    	tweets.add(t4);tweets.add(t5);
    	ArrayList<String> words=new ArrayList<String>();
    	words.add("just");
    	words.add("Olympics");
    	List<Tweet> ansList=new ArrayList<Tweet>();
    	ansList.add(t2);ansList.add(t3);
    	ansList.add(t4);ansList.add(t5);
    	assertEquals(ansList, Filter.containing(tweets, words));
    }
}
