
package EveryMST;

import EveryMST.BasicMST.MSTKruskal;
import EveryMST.BasicMST.SpanningTree;
import EveryMST.Graph.Edge;
import EveryMST.Graph.Graph;

import java.util.*;
import java.util.stream.Collectors;

public class EveryMST  {

    public List<SpanningTree> getMinimumSpanningTrees(Graph graph) {

        List result = new ArrayList();

        MSTKruskal kruskal = new MSTKruskal();
        SpanningTree firstSP = kruskal.getMinimumSpanningTree(graph);
        double min = firstSP.getTotalWeight();

        Deque<Edge> initialEdges = graph.getIncomingEdges(0).stream().collect(Collectors.toCollection(ArrayDeque::new));//I get an error when I use a list to traverse

        Graph graphcopy = new Graph(graph);//I pass a copy so the original graph isn't modified

        while (!initialEdges.isEmpty()){
            Edge edge = initialEdges.poll();
            Set<Integer> visited = new HashSet<>();
            visited.add(edge.getTarget());

            generateTrees(edge, new SpanningTree(), result, graphcopy, new HashSet<>(visited),  min);

        }

        return result;

    }

    private void generateTrees( Edge edge, SpanningTree tree, List result, Graph graph, Set<Integer> visited, double min){
        List<Edge> traversal = new ArrayList<>();


        tree.addEdge(edge);
        visited.add(edge.getSource());
        if (tree.size() + 1 == graph.size()) {
            result.add(tree);
            return;
        }

        for (int vertex : visited){
            traversal.addAll(graph.getIncomingEdges(vertex));
        }

        Graph newGraph = new Graph(graph);

        for (Edge addingEdge : traversal){
            if (visited.contains(addingEdge.getSource())) continue;

            if (tree.size() + 2 == graph.size() && tree.getTotalWeight() + addingEdge.getWeight() > min ) continue;

            generateTrees( addingEdge, new SpanningTree(tree), result, newGraph, new HashSet<>(visited), min);

        }

        graph.getIncomingEdges(edge.getTarget()).remove(edge);

    }

}