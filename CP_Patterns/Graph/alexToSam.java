package CP_Patterns.Graph;

import java.util.*;
import java.util.Map.Entry;

import javax.management.Query;
/*
    Testcase 1 : 
    5
    4
    1 2
    2 3
    3 4
    4 5
    1
    5

    Testcase 2 :
    7
    5
    1 3
    1 4
    2 5
    2 6
    5 7
    3 
    7 
 */
public class alexToSam {
    public static int bfs(int noEmp, int n, int[][] rel, int alex, int sam) {
        if (alex == sam) return 0; // alex is same at sam
        if (rel.length == 0) return alex == sam ? 0 : -1; // if no relationship

        // Step 1: Build the graph
        HashMap<Integer, List<Integer>> graph = new HashMap<>(); // Adjacent list
        for(int[] r : rel) {
            graph.computeIfAbsent(r[0], x -> new ArrayList<>()).add(r[1]);
            graph.computeIfAbsent(r[1], x -> new ArrayList<>()).add(r[0]);
        }
        System.out.println("Graph Structure:");
        graph.forEach((key, value) -> System.out.println(key + " -> " + value));
        
        // Step 2: BFS
        Queue<int[]> q = new LinkedList<>(); // // pair of (node, level)
        Set<Integer> visited = new HashSet<>(); // to check for visited nodes
        q.offer(new int[]{alex, 0});
        visited.add(alex);
        while(!q.isEmpty()) {
            int[] current = q.poll();
            int person = current[0], level = current[1];
            if(person == sam ) {
                // return level - 1 >= 0 ? level - 1 : 0; // if alex and sam is both same nos
                return level-1; // sam==alex handled at top
            }
            for(int neighbor : graph.getOrDefault(person, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    q.offer(new int[]{neighbor, level + 1});
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int noEmp = sc.nextInt();
        int n = sc.nextInt();
        int[][] rel = new int[n][2];
        for(int i = 0; i < n; i++) {
            rel[i][0] = sc.nextInt();
            rel[i][1] = sc.nextInt();
        }
        int alexPos = sc.nextInt();
        int samPos = sc.nextInt();
        System.out.println("Min No of People involved : " + bfs(noEmp, n, rel, alexPos, samPos) );
        sc.close();
    }
}