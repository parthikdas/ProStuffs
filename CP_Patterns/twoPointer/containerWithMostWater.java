package CP_Patterns.twoPointer;
// 11. Container With Most Water
public class containerWithMostWater {
    public int maxArea(int[] height) {
        int l = 0, r = height.length-1;
        int max = 0;
        while(r>l) {
            // Calc area with current pointers
            max = Math.max(max, Math.min(height[l], height[r]) * (r - l));
            // Move the pointer pointing to the shorter line
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return max;
    }
}
