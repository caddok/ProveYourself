package exam;

import java.util.*;

public class Packages {
    public static Set<Integer> visited;
    public static Map<Integer, List<Vertex>> graph;
    public static Stack<Integer> sorted;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int numberOfDependencies = in.nextInt();
        in.nextLine();
        graph = new HashMap<>();

        for (int i = 0; i < numberOfDependencies; i++) {
            String[] dependency = in.nextLine().split(" ");
            int p = Integer.parseInt(dependency[0]);
            int d = Integer.parseInt(dependency[1]);
            Vertex vertex = new Vertex(d);
            graph.putIfAbsent(p, new ArrayList<>());
            graph.computeIfPresent(p,(v,k)-> {
                k.add(vertex);
                return k;
            });
        }

        int numberOfQueries = in.nextInt();
        in.nextLine();
        for (int i = 0; i < numberOfQueries; i++) {
            int packageToInstall = in.nextInt();
            in.nextLine();
            resetCollections();
            topSort(packageToInstall);
            printResult();
        }
    }

    private static void topSort(int packageToInstall) {
        List<Vertex> currentVertex = graph.get(packageToInstall);
        visited.add(packageToInstall);

        if (currentVertex == null) {
            sorted.push(packageToInstall);
            return;
        }

        for (Vertex vertex : currentVertex) {
            if (visited.contains(vertex.getTo())) {
                continue;
            }
            topSort(vertex.getTo());
        }
        sorted.push(packageToInstall);
    }

    private static void resetCollections() {
        visited = new HashSet<>();
        sorted = new Stack<>();
    }

    private static void printResult() {
        List<Integer> result = new ArrayList<>(sorted);
        Collections.sort(result);
        for (int j = 0; j < result.size(); j++) {
            if (j == result.size() - 1) {
                System.out.print(result.get(j));
            } else {
                System.out.print(result.get(j) + " ");
            }
        }
        System.out.println();
    }
    static class Vertex {
        private Integer dependantOn;

        public Vertex(Integer to) {
            this.dependantOn = to;
        }

        public Integer getTo() {
            return dependantOn;
        }
    }

}
