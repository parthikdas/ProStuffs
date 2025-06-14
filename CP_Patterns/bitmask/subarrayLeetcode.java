package CP_Patterns.bitmask;

import java.util.*;
/*
   
 Use a loop inside recursion when you're exploring multiple branching choices at each level, like:
        Subset generation
        Combinations (like nCr)
        Permutations
        Partitioning an array
        Backtracking problems (like N-Queens, Sudoku)

    How to Do: 
        Print Subarray : Best 2 loops
        Subarray sum problems : Prefix Sum / HashMap

    Types of Prefix Sum/ hashmap subarray
    1. Subarrays with Sum Equal to K :560. Subarray Sum Equals K
    2. Subarrays Divisible by K : 974. Subarray Sums Divisible by K
    3. Contiguous Array (Equal 0s and 1s) : 525. Contiguous Array
    4. Binary Subarrays With Sum = S : 930. Binary Subarrays With Sum
    5. Number of Submatrices That Sum to Target : 1074. Number of Submatrices That Sum to Target
    6. Longest Subarray With Sum K : 325. Maximum Size Subarray Sum Equals k
    7. Find Total Sum of All Subarrays With Odd/Even Length : 1588. Sum of All Odd Length Subarrays
    8. 2364. Count Number of Bad Pairs
    2845. Count of Interesting Subarrays
    3381. Maximum Subarray Sum With Length Divisible by K
    2537. Count the Number of Good Subarrays
    2588. Count the Number of Beautiful Subarrays

    For prefix sum/ hashmap u need to decide 2 things:
    1. What u gonna store in hashmap
    2. how to make the key

    Prefix is the one we remove from the sum so that we get subarray sum. Eg: from 4,5,0 we have prefix in map we can remove the idea to count so we count this way not geenrating the subarrays in O(n^2) way
    And we store itd freq
 */

public class subarrayLeetcode {
    // Below is brute force and its slow
    public static void getAllSubarrays(int[] a, List<String> l) { // O(n^2) - brute force, good for small input
        for(int i = 0; i < a.length; i++) {
            StringBuilder sub = new StringBuilder();
            int c = 0;
            for(int j = i; j < a.length; j++) {
                c+=a[j];
                sub.append(a[j]);
                l.add(sub.toString() + "->" + c );
            }
        }
    }
    // --------------------------------------------
    // Template
    // Basic idea is to store the rem and point to note as we have to check for target, the size will never increase from more thn target
    // try to do hashmap first as it is easier to implement if succes then array
    public int solve(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // Base case: prefixSum == target at the start
        int count = 0;
        int prefixSum = 0;
        for (int num : nums) {
            prefixSum += num;
            // Check how many times (prefixSum - target) has appeared before
            count += map.getOrDefault(prefixSum - target, 0);
            // Record current prefix sum
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }
        return count;
    }
    // --------------------------------------------
    // 974. Subarray Sums Divisible by K
    // Below code can also be used a template for solving these questions using array instead of hashmap is way faster
    public static int subarraysDivByK(int[] nums, int k) { // O(n), O(k)
        int[] map = new int[k]; // store frequency of remainders
        map[0] = 1;             // base case for prefix sum % k == 0
        int sum = 0, count = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int rem = sum % k;
            if (rem < 0) rem += k; // handle negative remainders

            count += map[rem]; // count how many times this remainder has occurred
            map[rem]++;        // update frequency
        }
        return count;
    }
    public int subarraysDivByK1(int[] nums, int k) { // hashmap version, same time space but a lot slower
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0, count = 0;
// the modulus operation for your problem is intended to track the remainders of the prefix sum in the range [0, k-1], because we're trying to use the remainder as the key in a HashMap to count occurrences.
// If the remainder is negative, it will cause issues since HashMap keys must be positive integers, and you'll encounter an invalid key.
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int rem = sum % k;
            if (rem < 0) rem += k; // we need to do this whether array or map, ensure the key is +ve
            if(map.containsKey(rem)) count += map.get(rem); // count how many times this remainder has occurred
            map.put(rem, map.getOrDefault(rem, 0) + 1);        // update frequency
        }
        return count;
    }
    // --------------------------------------------
    // 560. Subarray Sum Equals K
    // As this is sum question so the resultant might be very big so its better to use hashmap
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        int sum = 0, count = 0;
        for(int n : nums) {
            sum += n;
            if(map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
    // --------------------------------------------
    // 2364. Count Number of Bad Pairs
    // Logic : j - i != nums[j] - nums[i] => nums[i] - i == nums[j] - j (good pair ==)
    // First get the no of good then subtract from total
    public long countBadPairs(int[] nums) {
        long c = 0;
        HashMap<Long, Long> map = new HashMap<>(); // Use HashMap to store frequencies of differences
        for(int i = 0 ; i < nums.length ; i++) {
            long diff = (long) nums[i] - i; // Calculate the difference
            if(map.containsKey(diff)) c += map.get(diff); // Add the number of times this diff has been seen
            map.put(diff, map.getOrDefault(diff, 0L) + 1);  // Update the frequency of this diff
        }
        // Calculate total number of pairs and subtract the number of good pairs (c)
        // Return the number of bad pairs, total no of subarray n * (n-1) / 2
        return (long) nums.length * (nums.length - 1) / 2 - c;
    }
    public long countBadPairs1(int[] nums) {
        long c = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0 ; i < nums.length ; i++) {
            int diff = nums[i] - i;
            if(map.containsKey(diff)) c += map.get(diff);
            map.put(diff, map.getOrDefault(diff, 0) + 1);
        }
        return 1L * nums.length * (nums.length - 1) / 2 - c; // OR else at last multiply with 1L to make it long
    }
    // --------------------------------------------
    // 1590. Make Sum Divisible by P
    // logic behind this was the whole array sum % p = removal sum subarray both are coming equal. Eg: 3,1,4,2 -> sum : 10%6-> 4 and when u remove 4 from array it becomes 3,1,2->6%6=0 so 4 is the smallest 
    public int minSubarray(int[] nums, int p) {
        // step1 : total sum % p
        int total = 0;
        for(int n : nums) {
            total = (total + n) % p;
        }
        // Step 2: If already divisible, no need to remove anything
        if (total == 0) return 0;
        // Step 3: Map to store prefix sum mod values and their latest index
        HashMap<Integer, Integer> map = new HashMap<>(); // Storing the prefix and their index
        map.put(0, -1);  // to handle subarray starting from index 0
        int prefix = 0, minLength = nums.length;
        for(int i = 0 ; i < nums.length ; i++) {
            prefix = (prefix + nums[i]) % p;
            // Step 5: Target is the mod value we want to match with earlier prefix
            int target = (prefix - total + p) % p;
            // Step 6: If target found, we found a valid subarray
            if (map.containsKey(target)) {
                minLength = Math.min(minLength, i - map.get(target));
            }
            // Step 7: Update map with current prefix
            map.put(prefix, i);
        }
        return minLength == nums.length ? -1 : minLength; // can't be full length
    }
    public static void main(String[] args) {
        int[] a = {4,5,0,-2,-3,1};
        // List<String> list = new ArrayList<>();
        // getAllSubarrays(a,list);
        // list.forEach(System.out::println);
        // subarraysDivByK(a, 5);
    }
}



