package CP_Patterns.Graph;
import java.util.*;
/*
    Directed graph is 1 -> 2
    Undirected graph is 1 -> 2, 2 -> 1

    Operations ( For unweighted graph ):
        - graph ( adjacency list ) creation
        - Traversal
            - bfs
            - dfs
        - All paths (dfs)
            - recursive
            - iterative
            - count all paths
        - Shortest path (bfs)
            - Distance of shortest path
            - Path of shortest path

            - All shortest paths from src to dest (bfs + dfs)
            - Shortest Path with constraints ( bfs + dfs)
            - Multi source shortest path ( bfs + dfs)
            - Dijkstra's algo for shortest path - weighted graph ( +ve weights )
            - All shortest paths weighted (Dijkstra + bfs)
            - Bellman-Ford algo for shortest path - weighted graph ( -ve weights )
            - Bidirectional search for shortest path - weighted graph (+ve weights) (for large graphs)
        - Cycle detection - dfs
            - undirected graph (dfs)
            - directed graph ( bfs + dfs)
        - Graph Traversal and component identification
        - Topological sorting of DAG
        - Shortest Path in graph with multi obs
        - Union-Find / Disjoint Set Union (DSU) for handling dynamic connectivity.
          Prim’s Algorithm or Kruskal’s Algorithm for Minimum Spanning Trees (MST).
          Graph Coloring, especially for problems like bipartite graph checking.
          DFS and bfs of weighted graphs
          cycle detection for weighted graphs
          all paths weidhted

    6
    1 2
    3 2
    2 4
    4 6
    4 5
    5 6

    java Normal.java < input.txt
 */
class Normal {
    public Map<Integer, List<Integer>> createAdjacencyList(int[][] edge) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i = 0; i < edge.length; i++) {
            graph.computeIfAbsent(edge[i][0], _ -> new ArrayList<>());
            graph.computeIfAbsent(edge[i][1], _ -> new ArrayList<>());
            graph.get(edge[i][0]).add(edge[i][1]);
        }
        return graph;
    }
    public void printMap(Map<Integer, List<Integer>> graph) {
        graph.forEach((key, val) -> System.out.println(key + " : " + val.toString()));
    }
    // ---------------------------Traversal------------------------------------
    public void bfs(Map<Integer, List<Integer>> graph, int start) {
        if(graph.isEmpty()) {
            System.out.println("Graph is empty");
            return;
        }
        if(!graph.containsKey(start)) {
            System.out.println("Start does not exist");
            return;
        }
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(start);
        visited.add(start);

        while(!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");
            for(int neighbour : graph.get(node)) {
                if(!visited.contains(neighbour)) {
                    queue.offer(neighbour);
                    visited.add(neighbour);
                }
            }
        }
        System.out.println();
    }
    public void dfs(Map<Integer, List<Integer>> graph, int start) {
        if(graph.isEmpty()) {
            System.out.println("Graph is empty");
            return;
        }
        if(!graph.containsKey(start)) {
            System.out.println("Start does not exist");
            return;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        stack.offer(start);
        visited.add(start);

        while(!stack.isEmpty()) {
            int node = stack.poll();
            System.out.print(node + " ");
            for(int neighbour : graph.get(node)) {
                if(!visited.contains(neighbour)) {
                    stack.offer(neighbour);
                    visited.add(neighbour);
                }
            }
        }
        System.out.println();
    }
    // ---------------------------All Paths------------------------------------
    public void findAllPathsRecur(int src, int dest, Map<Integer, List<Integer>> adj, List<Integer> path, List<List<Integer>> result) {
        path.add(src);
        if (src == dest) {
            result.add(new ArrayList<>(path));
        } else {
            for (int nei : adj.getOrDefault(src, new ArrayList<>())) {
                findAllPathsRecur(nei, dest, adj, path, result);
            }
        }
        path.remove(path.size() - 1); // backtrack
    }
    public List<List<Integer>> findAllPathsIter(int src, int dest, Map<Integer, List<Integer>> graph) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<List<Integer>> stack = new ArrayDeque<>();
        stack.add(Arrays.asList(src));
        while(!stack.isEmpty()) {
            List<Integer> path = stack.poll();
            int lastNode = path.get(path.size()-1);

            if(lastNode == dest) {
                result.add(path);
                continue;
            }

            for (int nei : graph.getOrDefault(lastNode, new ArrayList<>())) {
                if (!path.contains(nei)) { // avoid cycles
                    List<Integer> newPath = new ArrayList<>(path);
                    newPath.add(nei);
                    stack.push(newPath);
                }
            }
        }
        return result;
    }
    public int countAllPaths(int src, int dest, Map<Integer, List<Integer>> graph, Map<Integer, Integer> memo) {
        if(src == dest) return 1;
        if(memo.containsKey(src)) return memo.get(src);

        int total = 0;
        for (int nei : graph.getOrDefault(src, new ArrayList<>())) {
            total += countAllPaths(nei, dest, graph, memo);
        }
        memo.put(src, total);
        return total;
    }
    // ---------------------------Shortest Path------------------------------------
    public int shortestPathDist(int src, int dest, Map<Integer, List<Integer>> graph) {
        Deque<Integer> q = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> distance = new HashMap<>();
        q.add(src);
        visited.add(src);
        distance.put(src, 0);

        while(!q.isEmpty()) {
            int node = q.poll();
            if(node == dest) return distance.get(node);
            for(int nei : graph.getOrDefault(node, new ArrayList<>())) {
                if(!visited.contains(nei)) {
                    q.add(nei);
                    visited.add(nei);
                    distance.put(nei, distance.get(node) + 1);
                }
            }
        }
        return -1;
    }
    public List<Integer> shortestPath(int src, int dest, Map<Integer, List<Integer>> graph) { // O(V+E), O(V)
        Deque<Integer> q = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> parent = new HashMap<>();
        q.add(src);
        visited.add(src);
        parent.put(src, -1);

        while(!q.isEmpty()) {
            int node = q.poll();
            if(node == dest) break;
            for(int nei : graph.getOrDefault(node, new ArrayList<>())) {
                if(!visited.contains(nei)) {
                    q.add(nei);
                    visited.add(nei);
                    parent.put(nei, node);
                }
            }
        }
        // Now reconstruct the path
        if(!parent.containsKey(dest)) return new ArrayList<>(); // or null -> no path found
        List<Integer> path = new ArrayList<>();
        for(int at = dest; at != -1; at = parent.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }
    public List<List<Integer>> allShortestPath(int src, int dest, Map<Integer, List<Integer>> graph) {
        
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Normal normal = new Normal();
        int n = sc.nextInt();
        int[][] edge = new int[n][2];
        for(int i = 0; i < n; i++) {
            edge[i][0] = sc.nextInt();
            edge[i][1] = sc.nextInt();
        }
        Map<Integer, List<Integer>> graph = normal.createAdjacencyList(edge);
        normal.printMap(graph);
        
        // List<List<Integer>> result = new ArrayList<>();
        // List<Integer> path = new ArrayList<>();
        // normal.findAllPathsRecur(1, 6, graph, path, result);
        // for (List<Integer> p : result) {
        //     System.out.println(p);
        // }
        // result = normal.findAllPathsIter(1, 6, graph);
        // for (List<Integer> p : result) {
        //     System.out.println(p);
        // }
        // System.out.println(normal.countAllPaths(1, 6, graph, new HashMap<>()));

        System.out.println(normal.shortestPathDist(1, 6, graph));
        System.out.println(normal.shortestPath(1, 6, graph));
        System.out.println(normal.allShortestPath(1, 6, graph));
    }
}
