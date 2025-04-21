package CP_Patterns.Arrays;

import java.util.HashMap;

// 219. Contains Duplicate II
public class containsDup {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // HashMap to store the last index of each element
        HashMap<Integer, Integer> map = new HashMap<>();
        
        // Iterate over the array
        for (int i = 0; i < nums.length; i++) {
            // Check if the element is already in the map
            if (map.containsKey(nums[i])) {
                // Check if the difference in indices is <= k
                if (i - map.get(nums[i]) <= k) {
                    return true;
                }
            }
            
            // Update the last index of the element in the map
            map.put(nums[i], i);
        }
        
        return false;
    }
}
