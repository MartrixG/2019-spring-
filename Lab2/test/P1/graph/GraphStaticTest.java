/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import javafx.util.Pair;

/**
 * Tests for static methods of Graph.
 * 
 * To facilitate testing multiple implementations of Graph, instance methods are
 * tested in GraphInstanceTest.
 */
public class GraphStaticTest {
    
    // Testing strategy
    //   empty()
    //     no inputs, only output is empty graph
    //     observe with vertices()

    @Test
    public void testEmptyVerticesEmpty1() {
    	Graph<String> sGraph=new ConcreteEdgesGraph<String>();
        assertEquals(Collections.emptySet(), sGraph.vertices());
    }
    
    // TODO test other vertex label types in Problem 3.2
    @Test
    public void testEmptyVerticesEmpty2() {
    	Graph<Integer> iGraph=new ConcreteEdgesGraph<Integer>();
        assertEquals(Collections.emptySet(), iGraph.vertices());
    }
    @Test
    public void testEmptyVerticesEmpty3() {
    	Graph<Pair<String, String>> pGraph=new ConcreteEdgesGraph<Pair<String, String>>();
        assertEquals(Collections.emptySet(), pGraph.vertices());
    }
    @Test
    public void testEmptyVerticesEmpty4() {
    	Graph<ArrayList<String>> lGraph=new ConcreteEdgesGraph<ArrayList<String>>();
        assertEquals(Collections.emptySet(), lGraph.vertices());
    }
}
