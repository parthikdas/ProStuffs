package CP_Patterns.Backtrack;

import java.sql.Time;
import java.util.LinkedList;
import java.util.Queue;

public class island {
    //
//     ⏳ Time Complexity: O(m × n) (All cells visited once)
// 🛠 Space Complexity: O(m × n) (Recursive stack in worst case)
    public int numIslands(char[][] grid) { // DFS
        if (grid == null || grid.length == 0) return 0;
        
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {  // Found an island
                    dfs(grid, i, j);
                    count++;  // Increase island count
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';  // Mark as visited
        dfs(grid, i - 1, j); // Up
        dfs(grid, i + 1, j); // Down
        dfs(grid, i, j - 1); // Left
        dfs(grid, i, j + 1); // Right
    }


    public int shortestPath(int[][] grid) { // Time Complexity: O(m × n)  - BFS
        // 🛠 Space Complexity: O(m × n)
        int m = grid.length, n = grid[0].length;
        if (grid[0][0] == 1 || grid[m-1][n-1] == 1) return -1;  // Blocked start/end
        
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // Up, Down, Left, Right
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 0});  // (row, col, distance)
        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int row = curr[0], col = curr[1], steps = curr[2];
            
            if (row == m - 1 && col == n - 1) return steps;  // Reached the destination
            
            for (int[] dir : directions) {
                int newRow = row + dir[0], newCol = col + dir[1];
                if (newRow >= 0 && newCol >= 0 && newRow < m && newCol < n && !visited[newRow][newCol] && grid[newRow][newCol] == 0) {
                    queue.offer(new int[]{newRow, newCol, steps + 1});
                    visited[newRow][newCol] = true;
                }
            }
        }
        return -1; // No path found
    }
}
