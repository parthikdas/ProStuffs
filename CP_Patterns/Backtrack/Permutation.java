package CP_Patterns.Backtrack;

import java.util.ArrayList;
import java.util.List;
// 46 leetcode
public class Permutation {
    // We are not sending temp as we only need it when start = length so why to waste
    public void backtrack(int[] nums, int start, List<List<Integer>> ans) {
        if(start == nums.length) {
            List<Integer> temp = new ArrayList<>();
            for(int n: nums) temp.add(n);
            ans.add(temp);
            return;
        }
        for(int i = start;i <nums.length;i++) {
            swap(nums, start, i);
            backtrack(nums, start+1, ans); // Here unlike subset we are not going for all possibilites so start+1
            // basically the no u deal with u send, here its start
            swap(nums, start, i);
        }
    }
    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(nums, 0, ans);
        return ans;
    }
}
