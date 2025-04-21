package CP_Patterns;

public class nextGreater {
    // 2 array
    public int next(int[] nums1, int nums2[], int k) {
        int i = 0;
        while(i<nums2.length) {
            i++;
            if(nums2[i] > nums1[k]) return nums2[i];
            if(i == k) return -1;
        }
        return -1;
    }
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length];
        int ind = 0;
        for(int i=0;i<nums1.length;i++){
            ans[ind++] = next(nums1, nums2, i);
        }
        return ans;
    }
    // 1array
    public int next(int[] nums, int k) {
        int i = k;
        while(i<nums.length) {
            if(i == nums.length-1) i = 0;
            else i++;
            if(nums[i] > nums[k]) return nums[i];
            if(i == k) return -1;
        }
        return -1;
    }
    public int[] nextGreaterElements(int[] nums) {
        int[] ans = new int[nums.length];
        int ind = 0;
        for(int i=0;i<nums.length;i++){
            ans[ind++] = next(nums, i);
        }
        return ans;
    }
}
