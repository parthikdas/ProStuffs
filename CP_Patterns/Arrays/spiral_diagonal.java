package CP_Patterns.Arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class spiral_diagonal {
    // 54. Spiral Matrix
    /*
     First, we will check whether the matrix contains a second column. If yes, then we can start from right, otherwise, we have to start from down. Now loop endlessly and check if the current notice is visited, that is our base condition, then break out or else check we can go in the same path or not if we hit the last element of the row or column change direction 
     */
    public List<Integer> spiralOrder(int[][] m) {
        List<Integer> ans = new ArrayList<>();
        int n = m.length, c = m[0].length; // row, col and no of ele
        int up = 0, down = (c == 1 ? 1 : 0), left = 0, right = (c == 1 ? 0 : 1); // check if there are more than 1 column we can go right or else start wih down
        boolean[][] visited = new boolean[n][c]; // to check which are visited
        int i = 0, j = 0; // traversal var
        while(true) { // we will loop out when all the cells around us is visited
            if(right == 1) {
                if(visited[i][j] == true) break; // if the current cell is already visited nowhere to go this is base condition so break out
                ans.add(m[i][j]);
                visited[i][j] = true; // mark it done
                if(j < c-1) j++; // handle edge cases go only if possible
                if(j == c-1 || visited[i][j+1] == true) { // last cell or visited cell, change direction
                    right = 0;
                    down = 1;
                }
            }
            if(down == 1) {
                if(visited[i][j] == true) break;
                ans.add(m[i][j]);
                visited[i][j] = true; // mark it done
                if(i < n-1) i++; // if within range then only incement
                if(i == n-1 || visited[i+1][j] == true) { // last cell or visited cell
                    down = 0;
                    left = 1;
                }
            }
            if(left == 1) {
                if(visited[i][j] == true) break;
                ans.add(m[i][j]);
                visited[i][j] = true; // mark it done
                if(j > 0 ) j--;
                if(j == 0 || visited[i][j-1] == true) { // last cell or visited cell
                    left = 0;
                    up = 1;
                }
            }
            if(up == 1) {
                if(visited[i][j] == true) break;
                ans.add(m[i][j]);
                visited[i][j] = true; // mark it done
                if(i > 0) i--;
                if(i == 0 || visited[i-1][j] == true) { // last cell or visited cell
                    up = 0;
                    right = 1;
                }
            }
        }
        
        return ans;
    }

    // 498. Diagonal Traverse
    // If u hit the wall change the direction or else go the next diagonal value.
    public int[] findDiagonalOrder(int[][] mat) {
        int direction = 0; // 0 for up, 1 for down
        int m = mat.length, n = mat[0].length; // row, col
        int i = 0, j = 0, ind = 0; // for traversing
        int[] a = new int[m * n];
        m--;n--;
        while(true) {
            a[ind++] = mat[i][j];
            if(i == m && j == n) break; // base condition
            if(direction == 0) { // up
                if(i == 0 && j < n) { // hit the top wall but not at the first column
                    j = j + 1;
                    direction = 1; // change direction to down
                } else if(j == n) { // hit the right corner wall
                    i = i + 1;
                    direction = 1; // change direction to down
                } else {
                    i = i - 1;
                    j = j + 1;
                }
            } else if(direction == 1) { // down
                if(j == 0 && i < m) { // hit the left wall but not at the last row
                    i = i + 1;
                    direction = 0; // change direction to up
                } else if(i == m && j < n) { // hit the bottom wall but not at the last column
                    j = j + 1;
                    direction = 0; // change direction to up
                } else {
                    i = i + 1;
                    j = j - 1;
                }
            }
        }
        return a;
    }
}