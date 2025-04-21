package CP_Patterns.sumProblems;

import java.util.ArrayList;
import java.util.List;

// Idea : If we find all the indices of the keys in a given array in increasing order, then we can iterate k elements on the right and left sides of that key index to add them to answer in increasing order. For the next key index we can start iterating from the maximum(last key index + k , current key index - k) so that we will not process the already iterated array index.
//Once we process all the indices with keys, we have a answer list in increasing order and we return it without the need of sorting.
public class kindicesinarr {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        List<Integer> idx = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        for(int i = 0 ; i < nums.length; i++){
            if(nums[i] == key){
                idx.add(i);
            }
        }
        int last = 0;
        for(int ind : idx){
            int i = Math.max(last,ind-k);
            for(; i <= ind+k && i < nums.length; i++){
                ans.add(i);
            }
            last = i;
        }
        return ans;
    }
}
