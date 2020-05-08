/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {
    
    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // TODO constructor
    public ConcreteEdgesGraph() {
    	checkRep();
    }
    // TODO checkRep
    private void checkRep() {
    	for(Edge<L> eachEdge:edges) {
    		if((!vertices.contains(eachEdge.getSource()))||(!vertices.contains(eachEdge.getTarget()))) {
    			throw new NullPointerException();
    		}
    		if(eachEdge.getWeight()<0) {
    			throw new IllegalArgumentException();
    		}
    		assert eachEdge.getSource()!=null;
    		assert eachEdge.getTarget()!=null;
    	}
    }
    @Override public boolean add(L vertex) {
    	if(vertex==null) {
    		throw new NullPointerException();
    	}
    	if(vertices.contains(vertex)) {
    		System.out.println("The vertex has exist.");
    		return false;
    	}
    	else{
    		vertices.add(vertex);
    		checkRep();
    		return true;
    	}
        //throw new RuntimeException("not implemented");
    }
    
    @Override public int set(L source, L target, int weight) {
    	if(source==null||target==null) {
    		throw new NullPointerException();
    	}
    	if(weight<0) {
    		throw new IllegalArgumentException();
    	}
    	for(int i=0;i<edges.size();i++) {
    		if(edges.get(i).getSource().equals(source)&&edges.get(i).getTarget().equals(target)) {
    			int w=edges.get(i).getWeight();
    			if(weight==0) {
    				edges.remove(i);
    				return w;
    			}else {
    				edges.remove(i);
    				edges.add(new Edge<L>(source, target, weight));
    				return w;
    			}
    		}
    	}
    	if((!vertices.contains(source))&&(!vertices.contains(target))&&weight==0){
    		throw new IllegalArgumentException();
    	}
    	if(!vertices.contains(source)) {
    		add(source);
    	}
    	if(!vertices.contains(target)) {
    		add(target);
    	}
    	edges.add(new Edge<L>(source, target, weight));
    	checkRep();
    	return weight;
    	//throw new RuntimeException("not implemented");
    }
    
    @Override public boolean remove(L vertex) {
        if(vertex==null) {
        	throw new NullPointerException();
        }
        if(!vertices.contains(vertex)) {
        	return false;
        }
        else {
        	for(int i=0;i<edges.size();i++) {
        		if(edges.get(i).getSource().equals(vertex)||edges.get(i).getTarget().equals(vertex)) {
        			edges.remove(i);
        		}
        	}
        }
        vertices.remove(vertex);
        checkRep();
        return true;
    	//throw new RuntimeException("not implemented");
    }
    
    @Override public Set<L> vertices() {
    	return vertices;
        //throw new RuntimeException("not implemented");
    }
    
    @Override public Map<L, Integer> sources(L target) {
    	Map<L, Integer> sourcesMap=new HashMap<L, Integer>();
    	for(Edge<L> eachEdge:edges) {
    		if(eachEdge.getTarget().equals(target)) {
    			sourcesMap.put(eachEdge.getSource(),eachEdge.getWeight());
    		}
    	}
    	checkRep();
    	return sourcesMap;
        //throw new RuntimeException("not implemented");
    }
    
    @Override public Map<L, Integer> targets(L source) {
    	Map<L, Integer> targetsMap=new HashMap<L, Integer>();
    	for(Edge<L> eachEdge:edges) {
    		if(eachEdge.getSource().equals(source)) {
    			targetsMap.put(eachEdge.getTarget(),eachEdge.getWeight());
    		}
    	}
    	checkRep();
    	return targetsMap;
    	//throw new RuntimeException("not implemented");
    }
    
    // TODO toL()
    public String toString() {
    	String G=new String();
    	for(Edge<L> eachEdge:edges) {
    		G+=eachEdge.toString();
    		G+="\n";
    	}
    	return G;
    }
}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge<L> {
    // fields
    private final L source;
    private final L target;
    private final Integer v;
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // constructor
    public Edge(L source, L target, int v) {
    	this.target=target;
    	this.source=source;
    	this.v=v;
    	checkRep();
    }
    // checkRep
    private void checkRep() {
    	assert this.source!=null;
    	assert this.target!=null;
    	assert this.v>0;
    }
    // methods
    public L getSource() {
    	return this.source;
    }
    public L getTarget() {
    	return this.target;
    }
    public Integer getWeight() {
    	return this.v;
    }
    
    // toL()
    public String toString() {
    	return this.source+"->"+this.target+":"+this.v.toString();
    }
}
