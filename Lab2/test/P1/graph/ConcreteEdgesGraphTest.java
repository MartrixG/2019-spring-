/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    String a="a";  String d="d";
    String b="b";  String e="e";
    String c="c";  String f="f";
    Edge<String> edge1=new Edge<String>(a,b,1);	Edge<String> edge2=new Edge<String>(f,c,1);
    Edge<String> edge3=new Edge<String>(b,d,1);	Edge<String> edge4=new Edge<String>(c,e,3);
    Edge<String> edge5=new Edge<String>(b,c,1);	Edge<String> edge6=new Edge<String>(a,c,2);    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph<String>();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    
    // Testing strategy for ConcreteEdgesGraph.toString()
    //   TODO
    @Test
    void testToString() {
    	Graph<String> G=emptyInstance();
    	G.add(a);
    	G.add(b);
    	G.add(c);
    	G.set(a, b, 1);
    	G.set(b, c, 1);
    	assertEquals("a->b:1\nb->c:1\n", G.toString());
    }
    
    // TODO tests for ConcreteEdgesGraph.toString()
    
    /*
     * Testing Edge...
     */
    
    // Testing strategy for Edge
    //   TODO
    @Test
    void testEdgeStrategy() {
    	assertEquals(a, edge1.getSource());
    	assertEquals(e, edge4.getTarget());
    	assertEquals((Integer)3, edge4.getWeight());
    }
    // TODO tests for operations of Edge
    @Test
    void testEdgeOperations() {
    	assertEquals("a->b:1", edge1.toString());
    }
    @Test
    void testAdd() {
    	Graph<String> G=emptyInstance();
    	assertEquals(true, G.add(a));
    	assertEquals(true, G.add(b));
    	assertEquals(false, G.add(a));
    }
    @Test
    void testSet() {
    	Graph<String> G=emptyInstance();
    	G.add(a);
    	G.add(b);
    	G.add(c);
    	assertEquals(4, G.set(a, b, 4));
    	assertEquals(1, G.set(a, c, 1));
    	assertEquals(4, G.set(a, b, 2));
    	assertEquals(1, G.set(a, c, 0));
    	assertEquals(3, G.set(a, c, 3));
    }
    @Test
    void testRemove() {
    	Graph<String> G=emptyInstance();
    	G.add(a);
    	assertEquals(false, G.remove(c));
    	assertEquals(true, G.remove(a));
    }
    @Test
    void testVertices() {
    	Graph<String> G=emptyInstance();
    	Set<String> ansSet=new HashSet<String>();
    	ansSet.add(a);
    	ansSet.add(b);
    	ansSet.add(c);
    	G.add(a);
    	G.add(b);
    	G.add(c);
    	assertEquals(ansSet, G.vertices());
    }
    @Test
    void testSources() {
    	Graph<String> G=emptyInstance();
    	G.add(a);G.add(b);G.add(c);
    	G.set(a, b, 1);G.set(a, c, 1);G.set(b, c, 1);
    	Map<String, Integer> ansMap=new HashMap<String, Integer>();
    	ansMap.put(a,1);ansMap.put(b, 1);
    	assertEquals(ansMap, G.sources(c));
    }
    @Test
    void testTargets() {
    	Graph<String> G=emptyInstance();
    	G.add(a);G.add(b);G.add(c);
    	G.set(a, b, 1);G.set(a, c, 1);G.set(b, c, 1);
    	Map<String, Integer> ansMap=new HashMap<String, Integer>();
    	ansMap.put(c,1);ansMap.put(b, 1);
    	assertEquals(ansMap, G.targets(a));
    }
    @Test
    void testGToString() {
    	Graph<String> G=emptyInstance();
    	G.add(a);G.add(b);G.add(c);
    	G.set(a, b, 1);G.set(a, c, 1);G.set(b, c, 1);
    	String anString=new String();
    	anString+="a->b:1\n";
    	anString+="a->c:1\n";
    	anString+="b->c:1\n";
    	assertEquals(anString, G.toString());
    }
}
