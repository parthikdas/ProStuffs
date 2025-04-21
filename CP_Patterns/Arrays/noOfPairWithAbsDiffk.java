package CP_Patterns.Arrays;

import java.util.HashMap;
// 2006. Count Number of Pairs With Absolute Difference K
public class noOfPairWithAbsDiffk {
    public int countKDifference(int[] a, int k) {// O(n),O(n)
        int c = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int n : a) {
            // Check for a pair where nums[i] - k is already in the map
            if (map.containsKey(n - k)) {
                c += map.get(n - k);
            }

            // Check for a pair where nums[i] + k is already in the map
            if (map.containsKey(n + k)) {
                c += map.get(n + k);
            }

            // Update the frequency of the current number in the map
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        return c;
    }

    public int countKDifference1(int[] a, int k) { // O(n2)
        int c = 0;
        for(int i = 0;i<a.length-1;i++) {
            for(int j=i+1;j<a.length;j++) {
                if(Math.abs(a[i] - a[j]) == k) c++;
            }
        }
        return c;
    }
}
