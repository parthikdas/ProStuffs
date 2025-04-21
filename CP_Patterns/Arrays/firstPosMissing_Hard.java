package CP_Patterns.Arrays;

import java.util.HashSet;

public class firstPosMissing_Hard {
 // 41. First Missing Positive
 public int firstMissingPositive(int[] nums) { // O(n), O(1)
    int a[] = new int[nums.length + 1];  // Create an array to store counts of elements in the range [1, n]
    int n = nums.length;
    // Loop through the original array and populate the count array
    for(int i = 0; i < n; i++) {
        if(nums[i] > 0 && nums[i] <= n) {  // We only care about positive numbers within the range [1, n]
            a[nums[i]]++;  // Increment the corresponding index in the count array
        }
    }
    // Check the count array for the first missing positive number
    for(int i = 1; i <= n; i++) {
        if(a[i] == 0) return i;  // If the count is 0, it means the number 'i' is missing
    }
    // If all numbers from 1 to n are present, then return n + 1
    if(n == 1 && (nums[0] == 1 || nums[0] == 0)) {
        return nums[0] + 1;
    }
    return n + 1;  // Otherwise, return the next number after n, which is n + 1
}
public int firstMissingPositive1(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int n : nums) {
            if(n > 0 ) set.add(n);
        }
        for(int i = 1; i < Integer.MAX_VALUE;i++) if(!set.contains(i)) return i;
        return 1; 
    }   
}
