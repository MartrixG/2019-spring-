/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;



/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
    
    // Testing strategy
    //   TODO
    // TODO tests
    @Test
    public void testGraph() throws IOException {
    	GraphPoet nimoy = new GraphPoet(new File("src//P1//poet//poet.txt"));
    	String anString=new String();
    	anString+="To->explore:1\n";anString+="To->seek:1\n";anString+="explore->strange:1\n";
    	anString+="strange->new:1\n";anString+="new->worlds:1\n";anString+="new->life:1\n";
    	anString+="new->civilizations:1\n";anString+="worlds->To:1\n";anString+="seek->out:1\n";
    	anString+="out->new:1\n";anString+="life->and:1\n";anString+="and->new:1\n";
    	assertEquals(anString, nimoy.toString());
    }
}
