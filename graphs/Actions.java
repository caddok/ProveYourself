package graphs;

import java.util.*;

public class Actions {
    public static Map<Integer, Set<Integer>> graph;
    public static Stack<Integer> result;
    public static Set<Integer> visited;
    public static Map<Integer, Integer> parentNodeCount;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int allActions = in.nextInt();
        int mandatoryActions = in.nextInt();
        in.nextLine();

        graph = new TreeMap<>();
        parentNodeCount = new TreeMap<>();
        for (int i = 0; i < mandatoryActions; i++) {
            String[] actions = in.nextLine().split(" ");
            Integer potentialParent = Integer.parseInt(actions[0]);
            Integer potentialChild = Integer.parseInt(actions[1]);
            graph.putIfAbsent(potentialParent,new TreeSet<>());
            graph.computeIfPresent(potentialParent,(v, k) ->
            {
                k.add(potentialChild);
                return k;
            });
            parentNodeCount.putIfAbsent(potentialParent,0);
            parentNodeCount.putIfAbsent(potentialChild,0);
            parentNodeCount.computeIfPresent(potentialChild,(k,v)-> v + 1);
        }
        result = new Stack<>();
        visited = new HashSet<>();
        for (int i = 0; i < allActions; i++) {
            graph.putIfAbsent(i,new TreeSet<>());
            parentNodeCount.putIfAbsent(i,0);
        }
        genocideSort();
        ArrayList<Integer> printResult = new ArrayList<>(result);
        for (Integer action : printResult) {
            System.out.println(action);
        }
    }

    private static void genocideSort() {
        while (!parentNodeCount.isEmpty()) {
            int parentKey = 0;
            for (Map.Entry<Integer, Integer> node : parentNodeCount.entrySet()) {
                if (node.getValue() == 0) {
                    result.add(node.getKey());
                    parentKey = node.getKey();
                    for (Integer child : graph.get(node.getKey())) {
                        parentNodeCount.computeIfPresent(child,(k,v)-> {
                            if(v == 0) {
                                return 0;
                            } else {
                                return v - 1;
                            }
                        });
                    }
                    break;
                }
            }
            parentNodeCount.remove(parentKey);
        }
    }
}
