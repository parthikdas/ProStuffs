package CP_Patterns.Backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// 78 leetcode
public class subset {
    // Dup allowed
    public void backtrack(int[] nums, int start, List<Integer> temp, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(temp));
        for(int i = start;i <nums.length;i++) {
            temp.add(nums[i]);
            backtrack(nums, i+1, temp, ans);
            temp.remove(temp.size()-1);
        }
    }
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), ans);
        return ans;
    }

    // Dup not allowed - 90 Subset2
     public void subset(int[] nums, int start, List<Integer> temp, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(temp));  // Add current subset
        
        for(int i = start; i < nums.length; i++) {
            if(i > start && nums[i] == nums[i - 1]) continue;  // Skip duplicates
            temp.add(nums[i]);
            subset(nums, i + 1, temp, ans);
            temp.remove(temp.size() - 1);  // Backtrack
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);  // Sorting ensures duplicate elements are adjacent
        subset(nums, 0, new ArrayList<>(), ans);
        return ans;
    }
}
