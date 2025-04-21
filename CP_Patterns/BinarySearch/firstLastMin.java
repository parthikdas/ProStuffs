package CP_Patterns.BinarySearch;

public class firstLastMin {
    public int findFirstOccurrence(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                result = mid;  // Store the result and continue searching in the left half
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;  // Search right half
            } else {
                right = mid - 1;  // Search left half
            }
        }
        
        return result;  // If the target is not found, return -1
    }

    public int findLastOccurrence(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                result = mid;  // Store the result and continue searching in the right half
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;  // Search right half
            } else {
                right = mid - 1;  // Search left half
            }
        }
        
        return result;  // If the target is not found, return -1
    }

    public int findMinimum(int[] nums) {
        int left = 0, right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;  // Move to the right side
            } else {
                right = mid;  // Move to the left side
            }
        }
        
        return left;
    }
}
