package CP_Patterns.Arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
// 169. Majority Element
public class majorityEle {
    public int majorityElement(int[] nums) { // O(nlogn), O(1)
        // Sort the array
        Arrays.sort(nums);
        
        // The majority element will always be at the middle index after sorting
        return nums[nums.length / 2];
    }


    public int majorityElement1(int[] nums) {// O(n), O(n)
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int n : nums) map.put(n, map.getOrDefault(n,0)+1);
        int max = -1, val = -1;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue() > (nums.length/2)) {
                if(max< entry.getValue()) {
                    max = entry.getValue();
                    val = entry.getKey();
                }
            }
        }
        return val;
    }
}
