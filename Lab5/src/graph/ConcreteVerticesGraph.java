package graph;

import javafx.util.Pair;

import java.util.*;

public class ConcreteVerticesGraph<L> {
    public final static Double INF = 1e10;
    private final List<Vertex<L>> vertices = new ArrayList<>();

    public ConcreteVerticesGraph() {
        checkRep();
    }

    void checkRep() {
        for (Vertex<L> eachVertex : vertices) {
            assert eachVertex != null;
        }
        for (Vertex<L> eachVertex : vertices) {
            for (Pair<L, Double> eachPair : eachVertex.getToVertextList()) {
                assert eachPair.getKey() != null;
                assert eachPair.getValue() > 0;
                assert vertices.contains(eachVertex);
            }
        }
    }

    public boolean add(L vertex) {
        if (vertex == null) {
            throw new NullPointerException();
        }
        if (vertices.contains(new Vertex<>(vertex))) {
            return false;
        }
        vertices.add(new Vertex<L>(vertex));
        checkRep();
        return true;
    }

    public Double set(L source, L target, Double weight) {
        if (source == null || target == null) {
            throw new NullPointerException();
        }
        if (weight < 0) {
            throw new IllegalArgumentException();
        }
        for (Vertex<L> eachVertex : vertices) {
            if (eachVertex.getName().equals(source)) {
                List<Pair<L, Double>> toVertex;
                toVertex = eachVertex.getToVertextList();
                for (int i = 0; i < toVertex.size(); i++) {
                    if (toVertex.get(i).getKey().equals(target)) {
                        Double w = toVertex.get(i).getValue();
                        toVertex.remove(i);
                        if (weight == 0) {
                            return w;
                        } else {
                            toVertex.add(new Pair<>(target, weight));
                            return w;
                        }
                    }
                }
            }
        }
        if ((!vertices.contains(new Vertex<>(source))) && (!vertices.contains(new Vertex<>(target))) && weight == 0) {
            throw new IllegalArgumentException();
        }
        if (!(vertices.contains(new Vertex<>(source)))) {
            vertices.add(new Vertex<L>(source));
        }
        if (!(vertices.contains(new Vertex<>(target)))) {
            vertices.add(new Vertex<L>(target));
        }
        for (Vertex<L> eachVertex : vertices) {
            if (eachVertex.getName().equals(source)) {
                eachVertex.addEdge(target, weight);
            }
        }
        checkRep();
        return 0.0;
    }

    public boolean remove(L vertex) {
        if (vertex == null) {
            throw new NullPointerException();
        }
        if (!vertices.contains(new Vertex<>(vertex))) {
            return false;
        }
        for (int i = 0; i < vertices.size(); i++) {
            List<Pair<L, Double>> toVertex;
            toVertex = vertices.get(i).getToVertextList();
            for (int j = 0; j < toVertex.size(); j++) {
                if (toVertex.get(j).getKey().equals(vertex)) {
                    toVertex.remove(j);
                }
            }
        }
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).getName().equals(vertex)) {
                vertices.remove(i);
            }
        }
        checkRep();
        return true;
    }

    //一个人对应着一个深度以及他可以到达的人的集合
    public Map<L, Pair<Integer, Set<L>>> bfs(L start) {
        Map<L, Pair<Integer, Set<L>>> reM = new HashMap<>();
        Map<L, Integer> vis = new HashMap<>();
        for (Vertex eachVer : vertices) {
            vis.put((L) eachVer.getName(), (Integer) 0);
        }
        Queue<Pair<L, Integer>> q = new LinkedList<>();
        q.add(new Pair<>(start, 0));
        vis.put(start, 1);
        while (!q.isEmpty()) {
            Pair<L, Integer> head = q.poll();
            Set<L> s = this.targets(head.getKey()).keySet();
            reM.put(head.getKey(), new Pair<>(head.getValue(), s));
            for (L eachL : s) {
                if (vis.get(eachL) == 0) {
                    q.add(new Pair<>(eachL, head.getValue() + 1));
                    vis.put(eachL, 1);
                }
            }
        }
        return reM;
    }

    public Set<L> informationDiffusion(L source) {
        Set<L> reS = new HashSet<>();
        Map<L, Integer> vis = new HashMap<>();
        for (Vertex eachVer : vertices) {
            vis.put((L) eachVer.getName(), 0);
        }
        Queue<Pair<L, Double>> q = new LinkedList<>();
        q.add(new Pair<>(source, 2.0));
        vis.put(source, 1);
        while (!q.isEmpty()) {
            Pair<L, Double> head = q.poll();
            Map<L, Double> m = this.targets(head.getKey());
            for (L eachL : m.keySet()) {
                if (vis.get(eachL) == 0 && head.getValue() - m.get(eachL) >= 0) {
                    reS.add(eachL);
                    vis.put(eachL, 1);
                }
            }
        }
        return reS;
    }

    public Set<L> vertices() {
        Set<L> reSet = new HashSet<L>();
        for (Vertex<L> eachVertex : vertices) {
            reSet.add(eachVertex.getName());
        }
        checkRep();
        return reSet;
    }

    public Map<L, Double> sources(L target) {
        Map<L, Double> reMap = new HashMap<L, Double>();
        for (Vertex<L> eachVertex : vertices) {
            List<Pair<L, Double>> toVertex;
            toVertex = eachVertex.getToVertextList();
            for (Pair<L, Double> eachPair : toVertex) {
                if (eachPair.getKey().equals(target)) {
                    reMap.put(eachVertex.getName(), eachPair.getValue());
                }
            }
        }
        checkRep();
        return reMap;
    }

    public Map<L, Double> targets(L source) {
        Map<L, Double> reMap = new HashMap<L, Double>();
        for (Vertex<L> eachVertex : vertices) {
            if (eachVertex.getName().equals(source)) {
                for (Pair<L, Double> eachPair : eachVertex.getToVertextList()) {
                    reMap.put(eachPair.getKey(), eachPair.getValue());
                }
                break;
            }
        }
        checkRep();
        return reMap;
    }
}

class Vertex<L> {
    private L name;
    private List<Pair<L, Double>> toVertex;

    Vertex(L name) {
        this.name = name;
        this.toVertex = new ArrayList<Pair<L, Double>>();
        checkRep();
    }

    void checkRep() {
        for (Pair<L, Double> eachPair : toVertex) {
            assert eachPair.getKey() != null;
            assert eachPair.getValue() > 0;
        }
    }

    Boolean addEdge(L target, Double weight) {
        this.toVertex.add(new Pair<>(target, weight));
        checkRep();
        return true;
    }

    public Set<L> getArrivalVertices() {
        Set<L> ansSet = new HashSet<L>();
        for (Pair<L, Double> eachPair : toVertex) {
            ansSet.add(eachPair.getKey());
        }
        return ansSet;
    }

    List<Pair<L, Double>> getToVertextList() {
        return this.toVertex;
    }

    public L getName() {
        return this.name;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return o.hashCode() == this.hashCode();
    }
}
