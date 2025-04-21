package CP_Patterns.slidingWindow;

public class ApplyOperationArrEle0 {
    // 2772. leetcode
        public boolean checkArray(int[] nums, int k) {
            int n = nums.length; //size of array
            int c = 0;
            for (int i = 0; i <= n-k; i++) { //iterating through array
                if(nums[i] == 0) continue; //no operation performed
                if(nums[i] < 0) return false; //negative value
                if(nums[i] > 0) c = nums[i]; //save the value for operation
                for(int j = i;j <i+k;j++) nums[j]-= c; //perfoming the operation
             }
            for (int i = n-k; i < n; i++) { //checking for values
                if (nums[i] != 0) {
                    return false; //elements not zero after perfoming max operations
                }
            }
            return true;
        }
}
