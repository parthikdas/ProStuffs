package CP_Patterns.Arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class intersectOf2Arr {
    // 349. Intersection of Two Arrays
    public int[] intersection(int[] nums1, int[] nums2) { // O(n1+n2+k), O(n1,k)
        HashSet<Integer> set = new HashSet<>();
        for(int n:nums1) set.add(n);
        List<Integer> l = new ArrayList<>();
        for(int n:nums2) {
            if(set.contains(n)) {
                l.add(n);
                set.remove(n);
            }
        }
        int[] intArray = new int[l.size()];
        for (int i = 0; i < l.size(); i++) {
            intArray[i] = l.get(i);
        }
        return intArray;
    }

    //  350. Intersection of Two Arrays II
    public int[] intersect(int[] nums1, int[] nums2) {
        //Store the elements of arr 1 in map, while iterating over second check if the freq is more than 1 then only add to ans. Happy Codinggg...:)
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int n : nums1) map.put(n, map.getOrDefault(n, 0) + 1);
        List<Integer> l = new ArrayList<>();
        for(int n : nums2) {
            if(map.containsKey(n) && map.get(n) > 0) {
                l.add(n);
                map.put(n, map.get(n) - 1);
            } 
        }
        int[] intArray = new int[l.size()];
        for (int i = 0; i < l.size(); i++) {
            intArray[i] = l.get(i);
        }
        return intArray;
    }
}
