package CP_Patterns.BinarySearch;
// 1095 Find in mountain array
interface MountainArray {
    public int get(int index);
    public int length();
}
public class findInMountainArr {
    // Step 1: Find the peak index
    public int findPeak(MountainArray a) {
        int left = 0, right = a.length() - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (a.get(mid) < a.get(mid + 1)) {
                left = mid + 1; // Peak is to the right
            } else {
                right = mid; // Peak is to the left or at mid
            }
        }
        
        return left; // left and right will meet at the peak
    }

    // Step 2: Binary search in the increasing part of the array
    public int binarySearchIncreasing(MountainArray a, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int value = a.get(mid);
            
            if (value == target) return mid;
            else if (value < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    // Step 3: Binary search in the decreasing part of the array
    public int binarySearchDecreasing(MountainArray a, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int value = a.get(mid);
            
            if (value == target) return mid;
            else if (value > target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    public int findInMountainArray(int target, MountainArray a) {
        int n = a.length();
        if (n < 3) return -1;  // If the array length is less than 3, it's not a mountain array
        
        // Step 1: Find the peak element
        int peak = findPeak(a);
        
        // Step 2: Search in the increasing part
        int leftResult = binarySearchIncreasing(a, 0, peak, target);
        if (leftResult != -1) return leftResult;
        
        // Step 3: Search in the decreasing part
        return binarySearchDecreasing(a, peak + 1, n - 1, target);
    }
}
