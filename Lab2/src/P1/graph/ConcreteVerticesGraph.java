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

import com.sun.org.apache.bcel.internal.generic.NEW;

import javafx.util.Pair;

/**
 * An implementation of Graph.
 * 	
 * <p>PS2 instructions: you MUST use the provided rep.
 * @param <L>
 */
public class ConcreteVerticesGraph<L> implements Graph<L> {
    
    private final List<Vertex<L>> vertices = new ArrayList<>();
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:	
    //   TODO
    
    // TODO constructor
    public ConcreteVerticesGraph() {
    	checkRep();
    }
    // TODO checkRep
    void checkRep() {
    	for(Vertex<L> eachVertex:vertices) {
    		assert eachVertex!=null;
    	}
    	for(Vertex<L> eachVertex:vertices) {
    		for(Pair<L, Integer> eachPair:eachVertex.getToVertextList()) {
    			assert eachPair.getKey()!=null;
    			assert eachPair.getValue()>0;
    			assert vertices.contains(eachVertex)==true;
    		}
    	}
    }

	@Override public boolean add(L vertex) {
       if(vertex==null) {
    	   throw new NullPointerException();
       }
       if(vertices.contains(new Vertex<>(vertex))) {
    	   return false;
       }
       vertices.add(new Vertex<L>(vertex));
       checkRep();
       return true;
    	// throw new RuntimeException("not implemented");
    }

	@Override public int set(L source, L target, int weight) {
        if(source==null||target==null) {
        	throw new NullPointerException();
        }
        if(weight<0) {
        	throw new IllegalArgumentException();
        }
        for(Vertex<L> eachVertex:vertices) {
        	if(eachVertex.getName().equals(source)) {
        		List<Pair<L, Integer>> toVertex;
        		toVertex=eachVertex.getToVertextList();        		
        		for(int i=0;i<toVertex.size();i++) {
        			if(toVertex.get(i).getKey().equals(target)) {
        				int w=toVertex.get(i).getValue();
        				toVertex.remove(i);
        				if(weight==0) {
        					return w;
        				}else {
        					toVertex.add(new Pair<L, Integer>(target, weight));
        					return w;
        				}
        			}
        		}
        	}
        }
    	if((!vertices.contains(new Vertex<>(source)))&&(!vertices.contains(new Vertex<>(target)))&&weight==0){
    		throw new IllegalArgumentException();
    	}
        if(!(vertices.contains(new Vertex<>(source)))) {
        	vertices.add(new Vertex<L>(source));
        }
        if(!(vertices.contains(new Vertex<>(target)))) {
        	vertices.add(new Vertex<L>(target));
        }
        for(Vertex<L> eachVertex:vertices) {
        	if(eachVertex.getName().equals(source)) {
        		eachVertex.addEdge(target, weight);
        	}
        }
        checkRep();
        return 0;
    	//throw new RuntimeException("not implemented");
    }
    
	@Override public boolean remove(L vertex) {
    	if(vertex==null) {
    		throw new NullPointerException();
    	}
        if(!vertices.contains(new Vertex<>(vertex))) {
        	return false;
        }
    	for(int i=0;i<vertices.size();i++) {
    		List<Pair<L, Integer>> toVertex;
    		toVertex=vertices.get(i).getToVertextList();
    		for(int j=0;j<toVertex.size();i++) {
    			if(toVertex.get(j).getKey().equals(vertex));
    			toVertex.remove(j);
    		}
    	}
        for(int i=0;i<vertices.size();i++) {
        	if(vertices.get(i).getName().equals(vertex)) {
        		vertices.remove(i);
        	}
        }
        checkRep();
        return true;
    	//throw new RuntimeException("not implemented");
    }
    
    @Override public Set<L> vertices() {
    	Set<L> reSet=new HashSet<L>();
    	for(Vertex<L> eachVertex:vertices) {
    		reSet.add(eachVertex.getName());
    	}
    	checkRep();
    	return reSet;
        //throw new RuntimeException("not implemented");
    }
    
    @Override public Map<L, Integer> sources(L target) {
    	Map<L, Integer> reMap=new HashMap<L, Integer>();
    	for(Vertex<L> eachVertex:vertices) {
    		List<Pair<L, Integer>> toVertex;
    		toVertex=eachVertex.getToVertextList();
    		for(Pair<L, Integer> eachPair:toVertex) {
    			if(eachPair.getKey().equals(target)) {
    				reMap.put(eachVertex.getName(), eachPair.getValue());
    			}
    		}
    	}
    	checkRep();
    	return reMap;
        //throw new RuntimeException("not implemented");
    }
    
    @Override public Map<L, Integer> targets(L source) {
    	Map<L, Integer> reMap=new HashMap<L, Integer>();
    	for(Vertex<L> eachVertex:vertices) {
    		if(eachVertex.getName().equals(source)) {
    			for(Pair<L, Integer> eachPair:eachVertex.getToVertextList()) {
    				reMap.put(eachPair.getKey(), eachPair.getValue());
    			}
    		}
    	}
    	checkRep();
    	return reMap;
        //throw new RuntimeException("not implemented");
    }
    
    // TODO toString()
    @Override public String toString() {
    	String reString=new String();
    	for(Vertex<L> eachVertex:vertices) {
    		reString+=eachVertex.toString();
    	}
    	return reString;
    }
}

/**
 * TODO specification
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 * @param <L>
 */
class Vertex<L> {
    // TODO fields
    private L name;
	private List<Pair<L, Integer>> toVertex;
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // TODO constructor
    public Vertex(L name) {
    	this.name=name;
    	this.toVertex=new ArrayList<Pair<L, Integer>>();
    	checkRep();
    }
    // TODO checkRep
    public void checkRep() {
    	for(Pair<L, Integer> eachPair:toVertex) {
    		assert eachPair.getKey()!=null;
    		assert eachPair.getValue()>0;
    	}
    }
    // TODO methods
    public Boolean addEdge(L target,Integer weight) {
    	this.toVertex.add(new Pair<L, Integer>(target, weight));
    	checkRep();
    	return true;
    }
    public Set<L> getArrivalVertices(){
    	Set<L> ansSet=new HashSet<L>();
    	for(Pair<L, Integer> eachPair:toVertex) {
    		ansSet.add(eachPair.getKey());
    	}
    	return ansSet;
    }
    public List<Pair<L, Integer>> getToVertextList(){
    	return this.toVertex;
    }
    public L getName() {
    	return this.name;
    }
    @Override public int hashCode() {
    	return this.name.hashCode();
    }
    @Override public boolean equals(Object o) {
    	if(o==this.name) return true;
    	else if(o.hashCode()==this.hashCode()) return true;
    	else return false;
    }
    // TODO toString()
    @Override public String toString() {
    	String reString = new String();
    	for(Pair<L, Integer> eachPair:toVertex) {
    		reString+=this.name.toString()+"->"+eachPair.getKey().toString()+":"+eachPair.getValue().toString();
    		reString+="\n";
    	}
    	return reString;
    }
}
