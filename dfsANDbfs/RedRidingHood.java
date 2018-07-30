package dfsANDbfs;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RedRidingHood {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<Vertex> graph = new ArrayList<>();
        readGraph(in, n, graph);

        //selecting leaf
        int leaf = getLeaf(graph);
        //setting distances to 0
        List<Integer> distances = Stream.generate(()-> 0)
                .limit(graph.size())
                .collect(Collectors.toList());
        //pick up a leaf to start dfs
        distances.set(leaf,graph.get(leaf).getCoins());
        //finding the longest path
        dfs(leaf, graph, distances, -1);

        //writing the longest path and getting the start index
        int max = Collections.max(distances);
        int start = distances.indexOf(max);

        //resetting the distances
        distances = Stream.generate(()-> 0)
                .limit(graph.size())
                .collect(Collectors.toList());
        //setting the last leaf of the longest path as start
        distances.set(start,graph.get(start).getCoins());
        //check if there is a greater value along the longest path
        dfs(start, graph, distances, -1);
        System.out.println(Collections.max(distances));
    }

    private static void readGraph(Scanner in, int n, List<Vertex> graph) {
        for (int i = 0; i < n; i++) {
            int coins = in.nextInt();
            graph.add(new Vertex(coins));
        }

        for (int i = 0; i < n - 1; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            graph.get(x)
                    .addNextVertex(y);
            graph.get(y)
                    .addNextVertex(x);
        }
    }

    private static int getLeaf(List<Vertex> graph) {
        for (int i = 0; i < graph.size(); i++) {
            if (graph.get(i).getNextVertex().size() == 1) {
                return i;
            }
        }
        return -1;
    }

    private static void dfs(int vertex, List<Vertex> graph, List<Integer> distances, int parent) {
        for (int next : graph.get(vertex).getNextVertex()){
            if (next == parent) {
                continue;
            }
            int newDistance = distances.get(vertex) + graph.get(next).getCoins();
            distances.set(next,newDistance);
            dfs(next,graph,distances,vertex);
        }
    }

    static class Vertex {
        int coins;
        List<Integer> nextVertex;

        public Vertex(int coins) {
            this.coins = coins;
            this.nextVertex = new ArrayList<>();
        }

        void addNextVertex(int nextVertex) {
            this.nextVertex.add(nextVertex);
        }

        List<Integer> getNextVertex(){
            return this.nextVertex;
        }

        int getCoins() {
            return this.coins;
        }
    }
}
