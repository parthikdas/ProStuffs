package CP_Patterns.LeetcodeContest;

import java.util.HashMap;
import java.util.Map;
/*
 General Algorithm
Use a map to count how many rabbits gave each answer.
For each unique answer x, suppose count rabbits said that.
Each group needs to be of size x + 1
Number of such groups: Math.ceil(count / (x + 1))
Total rabbits in these groups: groups * (x + 1)
Sum this up for all x.
 */
public class RabbitsForest_Game {
    // 781. Rabbits in Forest
    public int numRabbits(int[] answers) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int a : answers) {
            countMap.put(a, countMap.getOrDefault(a, 0) + 1);
        }

        int total = 0;
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            int x = entry.getKey();     // the answer: "how many other rabbits like me"
            int count = entry.getValue(); // how many rabbits gave this answer

            int groupSize = x + 1;
            int numGroups = (count + x) / groupSize;  // same as ceil(count / groupSize)

            total += numGroups * groupSize;
        }

        return total;
    }
}
