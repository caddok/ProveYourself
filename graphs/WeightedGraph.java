package graphs;

import java.util.*;

public class WeightedGraph<TVertex extends Comparable<TVertex>> {
    private Map<TVertex, List<Edge>> vertices;
    private Set<TVertex> visited;

    public WeightedGraph() {
        vertices = new HashMap<>();
        visited = new HashSet<>();
    }

    public void addEdge(TVertex from, TVertex to, int weight) {
        addUndirectedEdge(from, to, weight);
        addUndirectedEdge(to, from, weight);
    }

    public void dfs(TVertex beginning) {
        visited = new HashSet<>();
        visited.add(beginning);
        dfsRecursive(beginning);
    }

    private void dfsRecursive(TVertex beginning) {
        System.out.println(beginning);
        for (Edge next : vertices.get(beginning)) {
            if (visited.contains(next)) {
                continue;
            }
            visited.add((TVertex) next);
            dfsRecursive((TVertex) next);
        }
    }

    public void bfs(TVertex vertex) {
        Queue<TVertex> queue = new ArrayDeque<>();
        visited = new HashSet<>();

        queue.offer(vertex);
        while (!queue.isEmpty()) {
            TVertex current = queue.poll();
            visited.add(current);

            for (Edge tVertex : vertices.get(current)) {
                if (visited.contains(current)) {
                    continue;
                }
                queue.offer(tVertex.to);
            }
            System.out.println(current);
        }
    }




    private void addUndirectedEdge(TVertex to, TVertex from, int weight) {
        vertices.put(from, (List<Edge>) new Edge(to, weight));
    }

    class Edge {
        private TVertex to;
        private int weight;

        Edge(TVertex to, int weight) {
            setTo(to);
            setWeight(weight);
        }

        public TVertex getTo() {
            return to;
        }

        public void setTo(TVertex to) {
            this.to = to;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }
}
