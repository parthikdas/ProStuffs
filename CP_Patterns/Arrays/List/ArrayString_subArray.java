package CP_Patterns.Arrays.List;

import java.util.*;

// Here are all the questions of the list
public class ArrayString_subArray {
// Subarray
    // Max Subarray Kadane
    public static int maxSub(int[] a) {
        int maxTillNow = a[0], maxSoFar = a[0];
        for(int i = 1; i < a.length; i++) {
            maxTillNow = Math.max(a[i], maxTillNow + a[i]); // Tracks the maximum sum of the subarray that ends at the current index.
            maxSoFar = Math.max(maxSoFar, maxTillNow); // Keeps track of the overall maximum sum encountered during the iteration.
        }
        return maxSoFar;
    }
    // Count Subarray sum equals K
    public static int subSumK(int[] a, int k) {
        HashMap<Integer, Integer> map = new HashMap<>(); // storing prefix and how many times occured
        int sum = 0, c=0;
        for(int n: a) {
            sum += n;
            c += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return c;
    }
    // 718. Max Length of repeated subarray and Longest Common Subarray in 2 arrays
        // Using 2d dp
    public static int subArrRepeat2d(int[] a, int[] b) { // O(m*n), O(m*n)
        int m = a.length, n = b.length, maxLength = 0;
        int[][] dp = new int[m+1][n+1]; //  We will not touch 0 row and 0 column
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(a[i-1] == b[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1; // Take old data and increment
                    maxLength = Math.max(maxLength, dp[i][j]);
                } else {
                    dp[i][j] = 0; // we cant continue the subarray anymore, dp[i][j] = 0 on mismatch in substring/subarray because continuity is broken.
                }
            }
        }
        return maxLength;
    }
        // Using 1d dp
    public static int subArrRepeat1d(int[] a, int[] b) { // O(m*n), O(n)
        int m = a.length, n = b.length, maxLength = 0;
        int[] dp = new int[m+1]; //  Idea here is we maintain only 2 rows one here and one inside the loop
        for(int i = 1; i <= m; i++) {
            // Temporary array to store previous dp values
            int[] newDp = new int[n + 1];
            for(int j = 1; j <= n; j++) {
                if(a[i-1] == b[j-1]) {
                    newDp[j] = dp[j-1] + 1; // Take old data and increment
                    maxLength = Math.max(maxLength, newDp[j]);
                } else {
                    newDp[j] = 0; // As we are resuing the same array we need to reset it
                }
            }
            dp = newDp; // set the prev row
        }
        return maxLength;
    }
    // 152. Maximum Product Subarray
    public int maxProduct(int[] nums) {
        int maxSoFar = nums[0];
        int maxEndingHere = nums[0];
        int minEndingHere = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int temp = maxEndingHere;
            maxEndingHere = Math.max(nums[i], Math.max(maxEndingHere * nums[i], minEndingHere * nums[i]));
            minEndingHere = Math.min(nums[i], Math.min(temp * nums[i], minEndingHere * nums[i]));
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }
    // 1248. Count Number of Nice Subarrays
        // Map version
    public int numberOfSubarraysMap(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // to handle exact k odds at start
        int count = 0, prefixSum = 0;
        for (int num : nums) {
            if (num % 2 != 0) prefixSum++; // count of odds so far, Convert the array into 0 (even) and 1 (odd)
            // Check how many subarrays ended here with exactly k odds
            count += map.getOrDefault(prefixSum - k, 0);
            // Store count of prefixSum so far
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }
        return count;
    }
        // Faster array version
    public int numberOfSubarraysArr(int[] nums, int k) {
       int n = nums.length;
       int[] map = new int[2 * n + 1];  // handles negative indices safely
       int offset = n; // shifts index range to [0, 2n]
       map[offset] = 1;  // prefixSum = 0 mapped to offset
       int count = 0, prefixSum = 0;
       for (int num : nums) {
           if (num % 2 != 0) prefixSum++;
           count += map[prefixSum - k + offset];
           map[prefixSum + offset]++;
       }
       return count;
    }
    // 209. Minimum Size Subarray Sum
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, n = nums.length, sum = 0, minLength = Integer.MAX_VALUE;
        for(int right = 0; right < n; right++) {
            sum+=nums[right];
            while(sum>=target) {
                minLength = Math.min(minLength, right - left + 1);
                sum -= nums[left];
                left++;
            }   
        }
        // If minLength was updated, return it; otherwise, return 0 (no valid subarray)
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
    // Longest subarray having sum of elements atmost K
    public static int longestSubArraySumAtmostk(int[] a, int k) {
        int left = 0, sum = 0, maxLength = Integer.MIN_VALUE;
        for(int right = 0; right < a.length; right++) {
            sum += a[right];
            while(sum > k) {
                maxLength = Math.max(maxLength, right - left + 1);
                sum -= a[left];
                left++;
            }
        }
        return maxLength == Integer.MIN_VALUE ? 0 : maxLength;
    }
    // Count Complete Subarrays in an Array
        // Slower map
        public int countCompleteSubarraysMap(int[] nums) {
            int left = 0, res = 0;
            int k = (int) Arrays.stream(nums).distinct().count();
            Map<Integer, Integer> mpp = new HashMap<>();

            for (int i = 0; i < nums.length; ++i) {
                mpp.put(nums[i], mpp.getOrDefault(nums[i], 0) + 1);
                while (mpp.size() == k) {
                    res += nums.length - i;
                    mpp.put(nums[left], mpp.get(nums[left]) - 1);
                    if (mpp.get(nums[left]) == 0) mpp.remove(nums[left]);
                    left++;
                }
            }
            return res;
        }
        // Faster Arr
        public static int countCompleteSubarraysArr(int[] nums) {
            int distinct = 0, a[] = new int[2001]; // distinct will be the window size (stores all the distincts)
            for(int n : nums) if(a[n]++ == 0) distinct++; // put all the distincts in the array, clever way to count the distincts without another loop
            int count = 0, l = 0, localD = 0;
            int b[] = new int[2001];
            for(int r = 0; r < nums.length; r++) {
                if(b[nums[r]]++ == 0) {
                    localD++; // Found a distinct
                }
                while(localD == distinct) { // reached window size
                    // The goal is to count l to r and also r' -> r+1,r+2..n-1 all of them
                    count+=nums.length - r; // So when we have local distinct same as global distincts count that mean from l to r but also l to r+1/r+2/..n-1 the count gonna be same, so we cleverly count all subarrays that can start at l and end at any index from r to the end of the array.
                    if(b[nums[l++]]-- == 1) // if 1 so means -- will make it 0 so one distinct gone
                        localD--;
                }
            }
            return count;
        }
    // 1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit
    public int longestSubarray(int[] nums, int limit) { // Deque makes this O(n), O(n)
        Deque<Integer> maxDeque = new ArrayDeque<>(); // We using Double Queue to store max ele indexes
        Deque<Integer> minDeque = new ArrayDeque<>();
        int left = 0, maxLen = 0;
        for (int right = 0; right < nums.length; right++) {
            int num = nums[right];
            while (!maxDeque.isEmpty() && num > maxDeque.peekLast()) maxDeque.pollLast(); // Maintain maxDeque: Keep it in descending order (max at the front)
            while (!minDeque.isEmpty() && num < minDeque.peekLast()) minDeque.pollLast(); // Maintain minDeque: Keep it in ascending order (min at the front)
            maxDeque.addLast(num);
            minDeque.addLast(num);
            while (maxDeque.peekFirst() - minDeque.peekFirst() > limit) { // Check if the window is valid
                // If the max - min exceeds the limit, move the left pointer to shrink the window
                if (nums[left] == maxDeque.peekFirst()) maxDeque.pollFirst(); // Remove indices that are out of the window range
                if (nums[left] == minDeque.peekFirst()) minDeque.pollFirst(); // Remove indices that are out of the window range
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
    // Sliding Window Max
        // brute force is to go through each window and check the max. O(k*n-k), O(1)
        // Priority Queue - O(nlogn), O(n)
        // Best Deque -  O(n), O(k)
        public int[] maxSlidingWindowPQ(int[] nums, int k) {
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]); // max-heap
            int n = nums.length, res[] = new int[n - k + 1], resIndex = 0;
            for (int i = 0; i < n; i++) {
                pq.offer(new int[]{nums[i], i}); // Storing the index as well will help in maintaining the window
                // Start filling results when window is full
                if (i >= k - 1) {
                    // Remove all elements from heap that are out of window
                    while (pq.peek()[1] <= i - k) pq.poll();
                    res[resIndex++] = pq.peek()[0];
                }
            }
            return res;
        }
        public static int[] slideWindowMaxDeque(int[] nums, int k) {
            Deque<Integer> dq = new ArrayDeque<>();
            int n = nums.length, res[] = new int[n-k+1], ind = 0;
            for(int i = 0; i < n; i++) {
                //remove item out of window
                // Check whether the Current big daddy is within range
                if(!dq.isEmpty() && dq.peekFirst() <= i - k)
                    dq.pollFirst();
                //remove item that is smaller then nums[i]
                // Remove all the smol ele to insert the big daddy
                while(!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) // if existing nos at last os smaller then remove 
                    dq.pollLast();
                dq.offerLast(i);
                //store max to result
                if(i >= k - 1){ // if we hit the mark then only 
                    res[ind++] = nums[dq.peekFirst()]; // as left contains the big ele
                }
            }
            return res;
        }
    // 2845. Count of Interesting Subarrays
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        long count = 0;
        long prefix = 0;
        HashMap<Long, Integer> freq = new HashMap<>();
        // Base case: prefix sum 0 has occurred once
        freq.put(0L, 1);
        for (int num : nums) {
            // If nums[i] % modulo == k, contribute 1 to prefix sum
            if (num % modulo == k) prefix++;
            // Current prefix sum modulo
            long mod = prefix % modulo;
            // What prefix sum mod are we looking for?
            long target = (mod - k + modulo) % modulo;
            // Add the number of times the target prefix sum has occurred
            count += freq.getOrDefault(target, 0);
            // Update the current prefix modulo count
            freq.put(mod, freq.getOrDefault(mod, 0) + 1);
        }
        return count;
    }
    // 2958. Length of Longest Subarray With at Most K Frequency
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> a = new HashMap<>();
        int res = 0, l = 0;
        for(int r = 0; r < nums.length; r++) {
            a.put(nums[r], a.getOrDefault(nums[r], 0) + 1);
            while (a.get(nums[r]) > k) {
                a.put(nums[l], a.get(nums[l]) - 1);
                l++;
            }
            res = Math.max(res, r - l + 1); // After above step its ensured we got a valid one so updating length
        }
        return res;
    }
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
        int a[] = {1, -2, 3, 4, -1, 2, 1, -5, 4};
        // System.out.println(maxSub(a)); // 9
        // System.out.println(subSumK(a, 5)); // 3
        // System.out.println(subArrRepeat2d(new int[] {1,2,3,2,1}, new int[] {3,2,1,4,7}));
        // System.out.println(subArrRepeat1d(new int[] {1,2,3,2,1}, new int[] {3,2,1,4,7}));
        // System.out.println(longestSubArraySumAtmostk(new int[] {1, 2, 1, 0, 1, 1, 0}, 4));
        System.out.println(countCompleteSubarraysArr(new int[] {2,3,1,2,3,1}));
    }
}
