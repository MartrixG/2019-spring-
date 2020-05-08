/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javafx.util.Pair;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    String a="a";  String d="d";
    String b="b";  String e="e";
    String c="c";  String f="f";
    Vertex<String> ver1=new Vertex<String>(a);Vertex<String> ver4=new Vertex<String>(d);
    Vertex<String> ver2=new Vertex<String>(b);Vertex<String> ver5=new Vertex<String>(e);
    Vertex<String> ver3=new Vertex<String>(c);Vertex<String> ver6=new Vertex<String>(f);    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph<>();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */
    
    // Testing strategy for ConcreteVerticesGraph.toString()
    //   TODO
    
    // TODO tests for ConcreteVerticesGraph.toString()
    
    /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex
    //   TODO
    @Test
    public void testGetArrivalVertices() {
    	ver2.addEdge(d, 1);
    	ver2.addEdge(c, 1);
    	Set<String> ansSet=new HashSet<String>();
    	ansSet.add(c);
    	ansSet.add(d);
    	assertEquals(ansSet, ver2.getArrivalVertices());
    }
    @Test
    public void testGetToVertextList() {
    	List<Pair<String, Integer>> ansList=new ArrayList<Pair<String, Integer>>();
    	ver2.addEdge(d, 1);
    	ver2.addEdge(c, 1);
    	ansList.add(new Pair<>(d, 1));
    	ansList.add(new Pair<>(c, 1));
    	assertEquals(ansList, ver2.getToVertextList());
    }
    @Test
    public void testGetName() {
    	assertEquals(a, ver1.getName());
    	assertEquals(b, ver2.getName());
    }
    @Test
    public void testAddEdge() {
    	assertEquals(true, ver1.addEdge(b, 1));
    }
    // TODO tests for operations of Vertex
    @Test
    public void testToString() {
    	String ansString;
    	ver2.addEdge(d, 1);
    	ver2.addEdge(c, 1);
    	ansString="b->d:1\nb->c:1\n";
    	assertEquals(ansString, ver2.toString());
    }
    @Test
    public void testAdd() {
    	Graph<String> G=emptyInstance();
    	assertEquals(true, G.add(a));
    	assertEquals(true, G.add(b));
    	assertEquals(false, G.add(a));
    }
    @Test
    public void testSet() {
    	Graph<String> G=emptyInstance();
    	G.add(a);
    	G.add(b);
    	G.add(c);
    	assertEquals(0, G.set(a, b, 4));
    	assertEquals(0, G.set(a, c, 1));
    	assertEquals(4, G.set(a, b, 2));
    	assertEquals(1, G.set(a, c, 0));
    	assertEquals(0, G.set(a, c, 3));
    }
    @Test
    public void testRemove() {
    	Graph<String> G=emptyInstance();
    	G.add(a);
    	assertEquals(false, G.remove(c));
    	assertEquals(true, G.remove(a));
    }
    @Test
    public void testVertices() {
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
    public void testSources() {
    	Graph<String> G=emptyInstance();
    	G.add(a);
    	G.add(b);
    	G.add(c);
    	G.set(a, b, 1);G.set(a, c, 1);G.set(b, c, 1);
    	Map<String, Integer> ansMap=new HashMap<String, Integer>();
    	ansMap.put(a,1);ansMap.put(b, 1);
    	assertEquals(ansMap, G.sources(c));
    }
    @Test
    public void testTargets() {
    	Graph<String> G=emptyInstance();
    	G.add(a);G.add(b);G.add(c);
    	G.set(a, b, 1);G.set(a, c, 1);G.set(b, c, 1);
    	Map<String, Integer> ansMap=new HashMap<String, Integer>();
    	ansMap.put(c,1);ansMap.put(b, 1);
    	assertEquals(ansMap, G.targets(a));
    }
    @Test
    public void testGToString() {
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
