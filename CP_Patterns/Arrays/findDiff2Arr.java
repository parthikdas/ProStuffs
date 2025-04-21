package CP_Patterns.Arrays;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
// 2215. Find the Difference of Two Arrays
public class findDiff2Arr {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
       List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());  // List for distinct elements in nums1 that are not in nums2
        ans.add(new ArrayList<>());  // List for distinct elements in nums2 that are not in nums1
        
        // Create HashSets to track elements in both arrays
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        
        // Add elements to respective HashSets
        for (int n : nums1) set1.add(n);
        for (int n : nums2) set2.add(n);
        
        // Add elements from nums1 that are not in nums2
        for (int n : set1) {
            if (!set2.contains(n)) {
                ans.get(0).add(n);
            }
        }
        
        // Add elements from nums2 that are not in nums1
        for (int n : set2) {
            if (!set1.contains(n)) {
                ans.get(1).add(n);
            }
        }
        
        return ans;
    }
}
