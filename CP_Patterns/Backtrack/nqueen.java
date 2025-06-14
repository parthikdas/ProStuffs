package CP_Patterns.Backtrack;

import java.util.*;
public class nqueen {
class Main {
    public static void printMat(int mat[][], int n) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) System.out.print(mat[i][j] + " ");
            System.out.println();
        }
    }
    public static boolean isSafe(int mat[][], int r, int c, int n) {
        // Checking for the col
        for(int i = 0; i < r; i++) {
            if(mat[i][c] == 1) return false;
        }
        
        // Checking for left upper diag
        for(int i = r, j = c; i >= 0 && j >= 0; i--, j--) {
            if(mat[i][j] == 1) return false;
        }
        
        // Checking for right upper diag
        for(int i = r, j = c; i >= 0 && j < n; i--, j++) {
            if(mat[i][j] == 1) return false;
        }
        
        return true;
    }
    public static boolean solveNqueens(int mat[][], int r, int n) {
        if(r >= n) return true;
        for(int c = 0; c < n; c++) {
            if(isSafe(mat, r, c, n)) {
                mat[r][c] = 1;
                if(solveNqueens(mat, r+1, n)) return true;
                mat[r][c] = 0;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        int mat[][] = new int[n][n];
        if(solveNqueens(mat, 0, n)) // start with first row
            printMat(mat, n);
        else 
            System.out.println("No solution found");
    }
}
}
