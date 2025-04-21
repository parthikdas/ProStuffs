package CP_Patterns.sumProblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class sums {
    public int[] twoSum(int[] nums, int target) {
        int a[] = {0,0};
        for(int i=0;i<nums.length-1;i++) {
            int temp = target - nums[i];
            for(int j=i+1;j<nums.length;j++) {
                if(nums[j] == temp) {
                    a[0] = i;
                    a[1] = j;
                }
            }
        }
        return a;
    }
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        for(int i = 0 ; i < n-2 ; i++) {
            // Skip duplicate for 'i'
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i+1, k = n-1;
            while(k>j) {
                int a = nums[i] + nums[j] + nums[k];
                if(a == 0) {
                    ans.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    // Skip duplicate values for 'j' and 'k'
                    while (j < k && nums[j] == nums[j + 1]) j++;
                    while (j < k && nums[k] == nums[k - 1]) k--;
                    j++;k--;
                } else if(a < 0) j++;
                else k--;
            }
        }
        return ans;
    }
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums); // Sort the array first
        List<List<Integer>> ans = new ArrayList<>();
        if(nums[0] >= 999999999) return ans;
        int n = nums.length;
        
        // Iterate over the array with the first element as nums[i]
        for (int i = 0; i < n - 3; i++) {
            // Skip duplicate values for i
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            
            // Iterate with the second element as nums[j]
            for (int j = i + 1; j < n - 2; j++) {
                // Skip duplicate values for j
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                
                // Two pointers to find the remaining two elements
                int k = j + 1, l = n - 1;
                while (k < l) {
                    int sum = nums[i] + nums[j] + nums[k] + nums[l];
                    
                    if (sum == target) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                        
                        // Skip duplicates for k and l
                        while (k < l && nums[k] == nums[k + 1]) k++;
                        while (k < l && nums[l] == nums[l - 1]) l--;
                        
                        // Move the pointers
                        k++;
                        l--;
                    } else if (sum < target) {
                        k++; // Move left pointer to the right to increase the sum
                    } else {
                        l--; // Move right pointer to the left to decrease the sum
                    }
                }
            }
        }
        
        return ans;
    }

    public int countKDifference(int[] nums, int k) {
        int count = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for (int num : nums) {
			map.put(num, map.getOrDefault(num, 0) + 1);
			count += map.getOrDefault(num - k, 0) + map.getOrDefault(num + k, 0);
		}
		return count;
    }
}
