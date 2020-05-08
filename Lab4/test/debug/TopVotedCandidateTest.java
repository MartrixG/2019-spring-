package debug;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TopVotedCandidateTest {

    @Test
    public void testTopVotedCandidateTest() {
        int[] person = {0,1,1,0,0,1,0};
        int[] time = {0,5,10,15,20,25,30};
        TopVotedCandidate topVotedCandidate = new TopVotedCandidate(person, time);
        assertEquals(topVotedCandidate.q(3), 0);
        assertEquals(topVotedCandidate.q(12), 1);
        assertEquals(topVotedCandidate.q(25), 1);
        assertEquals(topVotedCandidate.q(15), 0);
        assertEquals(topVotedCandidate.q(24), 0);
        assertEquals(topVotedCandidate.q(8), 1);
    }
}