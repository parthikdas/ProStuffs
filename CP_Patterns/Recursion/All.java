package CP_Patterns.Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class All {
    // Reverse Array 
        // 2 pointer
    public static void revArray(int[] a, int i, int j) { // Recursion
        if(i>=j) return;
        a[i] = a[i] ^ a[j];
        a[j] = a[i] ^ a[j];
        a[i] = a[i] ^ a[j];
        revArray(a, i+1, j-1);
    }
        // 1 pointer
    public static void revArray1(int[] a, int i) { // Recursion space optimised
        if(i>=a.length/2) return; // as we need to go half of array
        a[i] = a[i] ^ a[a.length - i -1];
        a[a.length - i -1] = a[i] ^ a[a.length - i -1];
        a[i] = a[i] ^ a[a.length - i -1];
        revArray1(a, i+1);
    }
    // -------------------------------------------------------
    // String palindrome
    public static boolean checkPalindrome(String s, int i) {
        if(i>=s.length()/2) return true;
        return s.charAt(i) == s.charAt(s.length()-i-1);
    }
    // -------------------------------------------------------
    // Fibonacci
        // Using function
    public static int fibonacci(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;
        return fibonacci(n-1) + fibonacci(n-2);
    } 
        // memoised 
    public static int fibonacci(int n, int[] dp) {
        if(n == 0 || n == 1 || dp[n] != 0) return dp[n]; // define dp[0] = 0; dp[1] = 1;
        return dp[n] = fibonacci(n-1, dp) + fibonacci(n-2, dp);
    }
        // space optimised 
    public static int fibonacci1(int n) { // O(n), O(1)
        if(n == 0) return 0;
        if(n == 1) return 1;
        int a = 0, b = 1;
        for(int i = 2; i <= n; i++) {
            int c = a+b;
            a = b;
            b = c;
        }
        return b;
    }
    // -------------------------------------------------------

    public static void main(String[] args) {
        
    }
    
}
