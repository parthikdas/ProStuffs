package CP_Patterns.sumProblems;

import java.util.HashMap;

/*
 * Given an array of digit string nums and a digit string target, return the number of paires of indices
 * such that i!=j, concat of num[i], num[j] == target
 * 
 * nums=["777","7","77","77"] target="7777"
 * Output:4
 * Explain: Valid pairs are (0,1), (1,0), (2,3), (3,2)
 * 
 * nums=["123","4","12","34"] target="1234"
 * Output:2
 * Explain: Valid pairs are (0,1), (2,3)
 */
public class PairStringConcatToTarget {
    public int numOfPairs(String []nums, String target) {
        int c = 0;
        HashMap<String, Integer> map = new HashMap<>();
        // Step 1: Build the frequency map
        for(String n : nums) {
            map.put(n, map.getOrDefault(n, 0)+1);
        }
        // Step 2: Iterate over all possible spilits of target
        for(int i=0;i<target.length();i++) {
            // Split the target int 2 parts
            String prefix = target.substring(0, i);
            String suffix = target.substring(i);

            // Check if both are there in map
            if(map.containsKey(prefix) && map.containsKey(suffix)) {
                // Count the pairs
                c += map.get(prefix) * map.get(suffix);

                // handle the case where the prefix == suffix ( avoid i == j )
                if(prefix.equals(suffix)) {
                    c -= map.get(prefix); // Subtract the case i == j
                }
            }
        } 
        return c;
    }
}
