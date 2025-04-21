package CP_Patterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class longSubstrwithoutRepeat_kadane {
    // Brute Force approach of creating all possible combinations
    public void bruteString() {
        String s = "Helolk";
        for(int i =0;i<s.length();i++) {
            String l = "";
            for(int j = i; j <s.length();j++) {
                l += s.charAt(j);
                System.out.println(l);
            }
        }
    }
    public static void bruteArray() {
        int a[] = {1,2,3};
        for(int i = 0 ; i<a.length;i++) {
            for(int j=i;j<a.length;j++) {
                System.out.println(Arrays.toString(Arrays.copyOfRange(a, i,j+1)));
            }
        }

    }
    // Medium questions
    // 1. Longest Substring without reapeating chars - HashSet and sliding
    public static int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet<>();
        int left = 0, maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(s.charAt(right));
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
    // 2. Max Sum Subarray - Kadane
    public static int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int currentSum = 0;

        for (int num : nums) {
            currentSum += num;
            maxSum = Math.max(maxSum, currentSum);
            if (currentSum < 0) currentSum = 0;
        }
        return maxSum;
    }
    // Hard Questions

    public static int sol(int[]a) { // Array is inc and then dec, Find the peak
        if(a==null) return -1;
        if(a.length == 0) return -1;
        int i = 0;
        if(i+1 >= a.length-1) return a[i+1];
        while(i+1<a.length && a[i+1] >=a[i]) i++;
        return a[i];
    }
    public static void main(String[] args) {
        // int a[] = {1,3,7,6,6,6,6,4,3,2,1}; // Dup
        // int b[] = {1,3,6,6,6,6,7,4,3,2,1}; // Dup
        // int c[] = {}; // Empty
        // int d[] = {1,2,3,4,3,2,1}; // Normal
        // int e[] = {1,2,3,4,5}; // inc
        // int f[] = {5,4,3,2,1}; // dec
        // int g[] = null; // null
        // System.out.println(sol(e));
       String s = "aaabbbsbccc";
       HashMap<Character, Integer> map = new HashMap<>();
       StringBuilder ss = new StringBuilder("");
       for(int i = 0;i<s.length();i++) {
        char ch = s.charAt(i);
        if(!map.containsKey(ch) && i>0) {
            ss.append(""+map.get(s.charAt(i-1)) + s.charAt(i-1));
            map.remove(s.charAt(i-1));
        } else map.put(ch, map.getOrDefault(ch, 0)+1);
       }
       System.out.println(ss);
    }
}
