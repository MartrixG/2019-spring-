package P4.twitter;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class ExtractTest {
	private  Instant d1 = Instant.parse("2018-04-27T10:00:00Z");
    private  Instant d2 = Instant.parse("2018-02-17T11:00:00Z");
    private  Instant d3 = Instant.parse("2018-03-10T08:45:43Z");
    private  Instant d4 = Instant.parse("2018-03-10T08:44:12Z");
    
    private  Tweet t1 = new Tweet(1, "fawn_Graham", "is it reasonable to talk about rivest so much?", d1);
    private  Tweet t2 = new Tweet(2, "bbitdiddle", "RT @SoSportsNation : This South Korean gentleman just won the entire Olympics. #PyeongChang2018 #Olympics https://t.co/ZW7ZOwIZ4G", d2);
    private  Tweet t3 = new Tweet(3, "SimmonsREteam", "RT @reallyhoffman @HealthFitnessIE Y'all are stuck in 2018 watching the Olympics in 2D while I'm in the future watching it in 3D https://t.co/CsKHpbZQki", d3);
    private  Tweet t4 = new Tweet(4, "NormanCharles88", "@flourish_pantry @HealthFitnessIE @NutraminoIRL @AlanQuinlan1 #TeamIreand in Abu Dhabi for Special Olympics World S\\u2026 https://t.co/iehoYVl8Ju", d4);
    
    @Test
    public void testGetTimespan() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(t1, t2, t3, t4));
        assertEquals(d2, timespan.getStart());
        assertEquals(d1, timespan.getEnd());
    }
    
    @Test
    public void testGetMentionedUsers() {
        Set<String> goalMentionedUsers = new HashSet<String>();
        goalMentionedUsers.add("healthfitnessie");
        goalMentionedUsers.add("reallyhoffman");
        assertEquals(Extract.getMentionedUsers(Arrays.asList(t3)), goalMentionedUsers);
    }
}
