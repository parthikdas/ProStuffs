import java.util.*;

/*
 1 2
 2 3
 2 4
 4 5
 4 6
 5 6
 */
class Node {
    int val;
    Node next;
    Node() {}
    Node(int val) { this.val = val; }
}
public class Graph {
    public Map<Integer, List<Integer>> createAdjList(int mat[][]) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for(int i = 0; i < mat.length; i++) {
            adjList.computeIfAbsent(mat[i][0], _ -> new ArrayList<>()).add(mat[i][1]);
        }
        return adjList;
    }
    public void printAllPaths_recur(Map<Integer, List<Integer>> adjList, int start, List<Integer> curPath, Set<Integer> visited, List<List<Integer>> allPaths) { // Dfs to print all the paths
        // If the current node is already visited, return
        if(visited.contains(start)) {
            return;
        }
        // Add current node to the path and mark it as visited
        curPath.add(start);
        visited.add(start);
        
        // If there are no more neighbors, or the node is a leaf, save the current path
        if (!adjList.containsKey(start) || adjList.get(start).isEmpty()) {
            allPaths.add(new ArrayList<>(curPath));  // Add the current path to allPaths
        } else {
            // For each adjacent node (neighbor), perform DFS recursively
            for (int neighbor : adjList.get(start)) {
                printAllPaths_recur(adjList, neighbor, curPath, visited, allPaths);
            }
        }
        // Backtrack: remove current node from the path and unmark it as visited
        curPath.remove(curPath.size() - 1);
        visited.remove(start);
    }
    public void printAllPaths_iter(Map<Integer, List<Integer>> adjList, int start) {
        List<List<Integer>> allPaths = new ArrayList<>();
        Stack<Pair> stack = new Stack<>();
    stack.push(new Pair(start, new ArrayList<>(List.of(start)))); // Pair of node and path taken to reach it

    while (!stack.isEmpty()) {
        Pair current = stack.pop();
        int node = current.node;
        List<Integer> path = current.path;

        if (!adjList.containsKey(node) || adjList.get(node).isEmpty()) {
            // No further neighbors, print or add this path
            allPaths.add(new ArrayList<>(path));
        } else {
            // Explore all neighbors
            for (int neighbor : adjList.get(node)) {
                // Create a new path for the neighbor
                List<Integer> newPath = new ArrayList<>(path);
                newPath.add(neighbor);
                stack.push(new Pair(neighbor, newPath));
            }
        }
    }
        System.out.println(allPaths);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Graph graph = new Graph();

        // Take input
        List<String> input = new ArrayList<>();
        while(sc.hasNextLine()) {
            input.add(sc.nextLine().trim());
        }
        int n = input.size();
        int mat[][] = new int[n][2];
        for(int i = 0; i < n; i++) {
            mat[i][0] = input.get(i).charAt(0) - '0';
            mat[i][1] = input.get(i).charAt(2) - '0';
        }

        // Create adj list
        Map<Integer, List<Integer>> adjList = graph.createAdjList(mat);
        System.out.println(adjList);

        // Print all paths
        List<List<Integer>> allPaths = new ArrayList<>();
        graph.printAllPaths_recur(adjList, 1, new ArrayList<>(), new HashSet<>(), allPaths);
        System.out.println(allPaths);
        graph.printAllPaths_iter(adjList, 1);

        sc.close();
    }
}
