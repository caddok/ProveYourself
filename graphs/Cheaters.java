package graphs;

import java.util.*;

public class Cheaters {
    //Collection for visited Vertexes
    public static Set<String> visited;
    //Collection for the graph
    public static Map<String, List<Vertex>> graph;
    //Collection for the founded answers
    public static Stack<String> sorted;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //read the input and build the graph
        int numberOfDependencies = in.nextInt();
        in.nextLine();
        graph = new HashMap<>();
        for (int i = 0; i < numberOfDependencies; i++) {
            String[] dependencies = in.nextLine().split(" ");
            String firstPerson = dependencies[0];
            String secondPerson = dependencies[1];
            String subject = dependencies[2];
            Vertex vertex = new Vertex(secondPerson, subject);
            graph.putIfAbsent(firstPerson, new ArrayList<>());
            graph.computeIfPresent(firstPerson, (v, k) -> {
                k.add(vertex);
                return k;
            });
            //Completed reading and building the graph
        }
        //Queries for the graph
        int numberOfCommands = in.nextInt();
        in.nextLine();
        for (int i = 0; i < numberOfCommands; i++) {
            //Reading the query
            String[] commandLine = in.nextLine().split(" ");
            String searchedSubject = commandLine[1];
            String person = commandLine[0];

            //Don't forget to reset the collections for every query
            resetCollections();
            topSort(person, searchedSubject);
            printResult();
        }
    }

    private static void resetCollections() {
        visited = new HashSet<>();
        sorted = new Stack<>();
    }

    private static void printResult() {
        List<String> result = new ArrayList<>(sorted);
        for (int j = 0; j < result.size(); j++) {
            if (j == result.size() - 1) {
                System.out.print(result.get(j));
            } else {
                System.out.print(result.get(j) + ", ");
            }
        }
        // Don't forget the new line or you will get WA
        System.out.println();
    }

    private static void topSort(String person, String searchedSubject) {
        //Connections(vertices) from the current person to the others
        List<Vertex> currentVertex = graph.get(person);

        // like in regular dfs
        visited.add(person);

        // If you enter this if statement you are on a node
        // with no dependencies(leaf) and
        // must add it to the result stack
        if (currentVertex == null) {
            sorted.push(person);
            return;
        }

        for (Vertex vertex : currentVertex) {
            //We don't need people we've already used
            if (visited.contains(vertex.getTo())) {
                continue;
            }
            //and we need only the people with knowledge on the subject
            if (searchedSubject.equals(vertex.getSubject())) {
                // resolve the person's dependencies recursively
                topSort(vertex.getTo(), searchedSubject);
            }

        }
        // after recursively solving all dependencies we add
        // the current person to the result stack.
        sorted.push(person);
    }
    static class Vertex {
        private String to;
        private String subject;

        Vertex(String to, String subject) {
            this.to = to;
            this.subject = subject;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }
    }
}
