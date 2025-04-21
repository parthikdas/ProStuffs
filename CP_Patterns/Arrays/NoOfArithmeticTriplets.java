package CP_Patterns.Arrays;

import java.util.HashSet;
import java.util.Set;

public class NoOfArithmeticTriplets {
    // 2367. Number of Arithmetic Triplets
    // O(n), O(n)
    public int arithmeticTriplets(int[] nums, int diff) {
        int count = 0;
        Set<Integer> set = new HashSet<>();
        // Traverse through the array and check valid triplets
        for (int num : nums) {
            // a+b = diff => b = a - diff, c = b - diff
            if (set.contains(num - diff) && set.contains(num - 2 * diff)) { // num , num+1 is second, num+2 is third
                count++;
            }
            set.add(num);  // Add current number to the set for future triplets
        }
        return count;
    }
    // O(n^2)
    public int arithmeticTriplets1(int[] nums, int diff) {
        int count = 0;
        
        // Outer loop to iterate through each element nums[i]
        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1;
            int k = nums.length - 1;

            // Two pointers to find the valid j and k such that the triplet is arithmetic
            while (j < k) {
                if (nums[j] - nums[i] == diff && nums[k] - nums[j] == diff) {
                    count++;
                    j++;
                    k--;
                } else if (nums[j] - nums[i] < diff) {
                    j++;  // Increment j to increase the difference between nums[i] and nums[j]
                } else {
                    k--;  // Decrement k to decrease the difference between nums[k] and nums[j]
                }
            }
        }
        
        return count;
    }
}
