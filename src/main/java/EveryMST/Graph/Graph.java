package EveryMST.Graph;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Graph {
    private final List<List<Edge>> incoming_edges;

    public Graph (int size){
        incoming_edges = Stream.generate(ArrayList<Edge>::new).limit(size).collect(Collectors.toList());
    }

    public Graph (Graph g){
        incoming_edges = g.incoming_edges.stream().map(ArrayList<Edge>::new).collect(Collectors.toList());
    }

    public boolean hasNoEdge(){
        return IntStream.range(0, size()).allMatch(i -> getIncomingEdges(i).isEmpty());
    }

    public int size(){
        return incoming_edges.size();
    }

    public Edge setDirectedEdge(int source, int target, double weight){
        List<Edge> edges = getIncomingEdges(target);
        Edge a = new Edge(source, target, weight);
        edges.add(a);
        return a;
    }

    public void setUndirectedEdge (int source, int target, double weight){
        setDirectedEdge(source, target, weight);
        setDirectedEdge(target, source, weight);
    }

    public List<Edge> getIncomingEdges(int target){
        return incoming_edges.get(target);
    }

    public List<Edge> getAllEdges(){
        return incoming_edges.stream().flatMap(List :: stream).collect(Collectors.toList());
    }

    public Deque<Integer> getVerticesWithNoIncomingEdges(){
        return IntStream.range(0, size()).filter(i -> getIncomingEdges(i).isEmpty()).boxed().collect(Collectors.toCollection(ArrayDeque::new));
    }

    public List<Deque<Edge>> getOutgoingEdges(){
        List<Deque<Edge>> outgoing_edges = Stream.generate(ArrayDeque<Edge>::new).limit(size()).collect(Collectors.toList());

        for (int target = 0; target < size(); target++){
            for ( Edge incomingEdge : incoming_edges.get(target)){
                outgoing_edges.get(incomingEdge.getSource()).add(incomingEdge);
            }
        }

        return outgoing_edges;
    }

    public boolean containsCycle(){
        Deque<Integer> notVisited = IntStream.range(0, size()).boxed().collect(Collectors.toCollection(ArrayDeque::new));

        while(!notVisited.isEmpty()){
            if (containsCycleAux(notVisited.poll(), notVisited, new HashSet<>())) return true;
        }
        return false;
    }

    public boolean containsCycleAux(int target, Deque<Integer> notVisited, Set<Integer> visited){

        //target is visited now
        notVisited.remove(target);
        visited.add(target);

        for (Edge edge : getIncomingEdges(target)){

            //check for cycle at this vertex
            if (visited.contains(edge.getSource())) return true;

            //recursively check for cycle at further vertices
            if (containsCycleAux(edge.getSource(), notVisited, new HashSet<>(visited))) return true;

        }

        return false;
    }


    public List<Integer> topological_sort(boolean depthFirst){

        Deque<Integer> global = getVerticesWithNoIncomingEdges();
        List<Deque<Edge>> outgoingEdgesAll = getOutgoingEdges();
        List<Integer> order  = new ArrayList<>();

        while (!global.isEmpty()){
            Deque<Integer> local = new ArrayDeque<>();

            int vertex = global.poll();
            order.add(vertex);
            Deque<Edge> outgoingEdges = outgoingEdgesAll.get(vertex);

            while (!outgoingEdges.isEmpty()){
                Edge edge = outgoingEdges.poll();

                List<Edge> incomingEdges = getIncomingEdges(edge.getTarget());
                incomingEdges.remove(edge);

                if(incomingEdges.isEmpty()){
                    local.add(edge.getTarget());
                }
            }

            while (!local.isEmpty()){
                if (depthFirst) global.addFirst(local.removeLast());
                else global.addLast(local.removeFirst());
            }
        }

        if (!hasNoEdge()) throw new IllegalArgumentException("The graph is cyclic!");

        return order;
    }
}
