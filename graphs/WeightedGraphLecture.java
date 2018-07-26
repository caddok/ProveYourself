package graphs;

import java.util.*;

public class WeightedGraphLecture<TVertex> {
    Map<TVertex, List<Vertex<TVertex>>> vertices;

    public WeightedGraphLecture() {
        this.vertices = new HashMap<>();
    }

    public void addEdge(TVertex x, TVertex y, Integer weight) {
        addDirectedEdge(x, y, weight);
        addDirectedEdge(y, x, weight);
    }

    private void addDirectedEdge(TVertex from, TVertex to, int weight) {
        if (!vertices.containsKey(from)) {
            vertices.put(from, new ArrayList<>());
        }
        vertices.get(from).add(new Vertex<>(to, weight));
    }

    public Map<TVertex, Integer> dijkstra(TVertex vertex) {
        Set<TVertex> used = new HashSet<>();

        Map<TVertex, Integer> distances = new HashMap<>();
        distances.put(vertex,0);
        PriorityQueue<Vertex<TVertex>> queue = new PriorityQueue<>(
                Comparator.comparingInt(Vertex::getWeight)
        );
        queue.add(new Vertex<>(vertex, 0));
        while (!queue.isEmpty()) {
            Vertex<TVertex> best = queue.poll();
            if (used.contains(best.getName())) {
                continue;
            }

            used.add(best.getName());
            for (Vertex<TVertex> next : vertices.get(best.getName())) {
                if (used.contains(next.getName())) {
                    continue;
                }

                int newDistanceToNext = distances.get(best.getName()) + distances.get(next.getName());

                if (!distances.containsKey(next.getName())) {
                    distances.put(next.getName(), newDistanceToNext);
                    queue.offer(new Vertex<>(next.getName(), newDistanceToNext));
                } else {
                    int currentDistanceToNext = distances.get(next.getName());
                    if (newDistanceToNext < currentDistanceToNext) {
                        distances.put(next.getName(), newDistanceToNext);
                        queue.offer(new Vertex<>(next.getName(), newDistanceToNext));
                    }
                }
            }
        }
        return distances;
    }


    class Vertex<TVertex> {
        private TVertex name;
        private Integer weight;

        public Vertex(TVertex name, Integer weight) {
            setName(name);
            setWeight(weight);
        }

        public TVertex getName() {
            return name;
        }

        public void setName(TVertex name) {
            this.name = name;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }
    }
}
