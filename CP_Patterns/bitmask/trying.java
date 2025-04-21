package CP_Patterns.bitmask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
    Subarray solve can be done by 2 ways : 1. Sliding Window 2. Prefix Sum + Hashmap
    When to use what ?? 
        -> Sliding Window : When we are looking for length, max/min, legit the subarray. Maybe most +ve nos is there.
            -> Can make a array and fill it and slide
            -> Can use 2 var : left and right and use it saves memory
            *** We need constraint or a condition to make this as slding window is fixed size of window

        -> Prefix Sum : When we are counting subarrays with a certain sum, difference, or condition. Handles -ve nos.


    Arr : [1,2,1]
    Goal - > 1. Print subarray : [1], [2], [1,2] : iterate and recursion
             2. Count subarray : 3 - use math
             3. Subarray all even : sliding window
             4. Subarray evenSum : print (O(n^2)) and count (prefix)
             5. Count - Subarray sum = k
             6. Count - Subarray divisible by k
             7. Count - Subarray where a[i]==a[j]
             9. Max Sum subarray - kadane
             10. Min sum subarray - modified kadane
             11. Max length subarray with sum k
             12. Max length subarray with equal 0s, 1s
             
 */
public class trying {
    // Printing subarray using loop
    public static void printSubArrayUsingLoop(int[] a) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            StringBuilder s = new StringBuilder();
            for (int j = i; j < a.length; j++) {
                s.append(a[j]).append(" ");
                list.add(s.toString().trim());
            }
        }
        System.out.println("Subarrays:");
        list.forEach(System.out::println);
        System.out.println("Count: " + list.size());
    }
    // Printing subArray using recursion
    public static void helper(int[] a, int i, String s, List<String> list) {
        if(i >= a.length) return;
        s += a[i];
        list.add(s);
        helper(a, i+1, s, list);
    }
    public static void printSubArrayUsingRecur(int[] a) {
        List<String> list = new ArrayList<>();
        for(int i = 0; i < a.length; i++) 
            helper(a, i, "", list);
        System.out.println("Subarrays:");
        list.forEach(System.out::println);
        System.out.println("Count: " + list.size());
    }
    // -----------------------------------------------------------------
    // Count the number of subarray
    public static void countTotalSubarray(int[] a) {
        int n = a.length;
        System.out.println(" Total number of subarrays : " + (n * (n+1)) / 2);
    }
    // -----------------------------------------------------------------
    // Print subarray with only even nos
    public static void printSubarrayOnlyEvenNos(int[] a) {
        System.out.println("Subarray with only even nos : ");
        int left = -1;
        for(int right = 0; right < a.length; right++) {
            if((a[right]&1) == 1) {
                if(left != -1) {
                    // Before changing print it
                    // for (int i = left; i < right; i++) { // if u dont want string
                    //     for (int j = i; j < right; j++) {
                    //         for (int k = i; k <= j; k++) {
                    //             System.out.print(a[k] + " ");
                    //         }
                    //         System.out.println();
                    //     }
                    // }
                    for (int i = left; i < right; i++) { // String
                        StringBuilder s = new StringBuilder();
                        for (int j = i; j < right; j++) {
                            s.append(a[j]).append(" ");
                            System.out.println(s.toString());
                        }
                    }
                }
                left = -1; // reset window - odd
            }
            else {
                if (left == -1) left = right; // new even no
            } 
        }
    }
    // -----------------------------------------------------------------
    // Print subarray with even sum
    public static void printSubarrayWithEvenSum(int[] a) {
        System.out.println("Subarray with even sum : ");
        for(int i = 0; i < a.length; i++) {
            int sum = 0;
            for(int j = i; j < a.length; j++) {
                sum += a[j];
                if((sum&1) == 0) {
                    for(int k = i; k <= j; k++) System.out.print(a[k] + " ");
                    System.out.println();
                }
            }
        }
    }
    // count subarray with even sum
    public static void countsubarrayWithEvenSum(int []a) { // For odd just do this and at last (n*(n+1))/2 - evenSum u will get it
        /*
         Use a running prefix sum.
            Even + Even = Even
            Odd + Odd = Even
            So, two prefix sums with same parity form an even-sum subarray.
        */
        Map<Integer, Integer> freq = new HashMap<>();
        freq.put(0, 1); // Initial prefix sum is 0 (even)
        
        int prefix = 0, count = 0;
        for (int num : a) {
            prefix += num;
            int parity = prefix % 2;
            
            // Make sure parity is positive (in case of negatives)
            if (parity < 0) parity += 2;
            
            count += freq.getOrDefault(parity, 0);
            freq.put(parity, freq.getOrDefault(parity, 0) + 1);
        }
        System.out.println("The total number of subArray: " + count);
    }
    // -----------------------------------------------------------------
    // Print the count of subarray with sum k
    public static void countsubarrayWithSumK(int []a, int t) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0, count = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
            int prefix = sum - t;
            count += map.getOrDefault(prefix, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1); // we will store what we are concerned with, here its sum
        }
        System.out.println(count);
    }
    // -----------------------------------------------------------------
    // Print count subarray divisible by k
    public static void countsubarrayDivisibleByK(int[] a, int t) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0, count = 0;
        for(int i = 0; i < a.length; i++) {
            sum += a[i];
            int mod = ((sum % t) + t) % t; // normalize negative mods
            count += map.getOrDefault(mod, 0);
            map.put(mod, map.getOrDefault(mod, 0) + 1); // we will store what we are concerned with, here its mod
        }
        System.out.println(count);
    }
    // -----------------------------------------------------------------
    // Print count subarray where a[i]==a[j]
    public static void countSubarraysEqualEnds(int[] a) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        long count = 0;
        for (int i = 0; i < a.length; i++) {
            int val = a[i];
            count += freq.getOrDefault(val, 0);
            freq.put(val, freq.getOrDefault(val, 0) + 1);
        }
        System.out.println(count);
    }
    // -----------------------------------------------------------------
    // Max Sum subarray
    public static void maxSumSubarray(int []a) {
        int maxSoFar = a[0], curMax = a[0];
        for(int i = 1; i < a.length; i++) {
            curMax = Math.max(a[i], curMax + a[i]);
            maxSoFar = Math.max(maxSoFar, curMax);
        }
        System.out.println("max sum : " + maxSoFar);
    }
    // Min Sum subarray
    public static void minSumSubarray(int []a) {
        int minSoFar = a[0], curMin = a[0];
        for(int i = 1; i < a.length; i++) {
            curMin = Math.min(a[i], curMin + a[i]);
            minSoFar = Math.min(minSoFar, curMin);
        }
        System.out.println("min sum : " + minSoFar);
    }
    // -----------------------------------------------------------------
    // Max Length subarray with sum k
        public static void maxLengthSubarraySumK(int a[], int k) {
            HashMap<Integer, Integer> map = new HashMap<>(); // Here we will store the sum and index as we dont care about the count
            map.put(0, -1); // Initialize with sum 0 at index -1 (helps handle subarrays starting from index 0)
            int max = -1, sum = 0;
            for(int i = 0; i < a.length; i++) {
                sum += a[i];
                if(map.containsKey(sum - k)) {
                    max = Math.max(max, i - map.get(sum - k));
                }
                if(!map.containsKey(sum)) 
                    map.put(sum, i);
            }
            System.out.println(max);
        }
    // -----------------------------------------------------------------
    // Max length subarray with equal 0s, 1s
    public static void maxLength01(int[] a) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int max = Integer.MIN_VALUE, sum = 0;
        for(int i = 0; i < a.length; i++) {
            sum += (a[i] == 0 ? -1 : 1); // If a[i] is 0, treat it as -1, else keep it as 1
            if (map.containsKey(sum)) {
                max = Math.max(max, i - map.get(sum));  // Update the max length
            } else {
                map.put(sum, i);
            }
        }
        System.out.println(max);
    }
    public static void main(String[] args) {
        int a[] = {2, -8, 3, -2, 4, -10};
        // printSubArrayUsingLoop(a);
        // printSubArrayUsingRecur(a);
        // countTotalSubarray(a);

        // printSubarrayOnlyEvenNos(a);
        // printSubarrayWithEvenSum(a);
        // countsubarrayWithEvenSum(a);
        // countsubarrayWithSumK(a, 5);
        // countsubarrayDivisibleByK(a, 5);
        // countSubarraysEqualEnds(a);

        // maxSumSubarray(a);
        // minSumSubarray(a);

        // maxLengthSubarraySumK(a, 5);

        maxLength01(new int[] {1, 0, 1, 1, 0, 0});
    }
}
