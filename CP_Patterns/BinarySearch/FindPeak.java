package CP_Patterns.BinarySearch;

public class FindPeak {
    // 162. Leetcode
    public int findPeakElement(int[] a) { // normal linear search
        if(a==null) return -1;
        if(a.length == 0) return -1;
        int i = 0;
        if(i >= a.length-1) return i;
        while(i+1<a.length && a[i+1] >=a[i]) i++;
        return i;
    }
    public int findPeakElement1(int[] nums) { // Using binarySearch in logn time
        int left = 0, right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            // Check if mid is a peak
            if (nums[mid] > nums[mid + 1]) {
                right = mid;  // Peak is on the left or mid itself
            } else {
                left = mid + 1;  // Peak is on the right
            }
        }
        
        return left;  // left will be the peak element index
    }
}
