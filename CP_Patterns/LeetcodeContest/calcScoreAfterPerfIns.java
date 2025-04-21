package CP_Patterns.LeetcodeContest;

import java.util.HashSet;
import java.util.Set;

public class calcScoreAfterPerfIns {
    // 3522. Calculate Score After Performing Instructions
    public long calculateScore(String[] instructions, int[] values) {
        int n = instructions.length;
        Set<Integer> visited = new HashSet<>();
        long score = 0;
        int i = 0;

        while (i >= 0 && i < n && !visited.contains(i)) { // we are storing the visited nodes in some there -ve so we cant go back
            visited.add(i);

            if (instructions[i].equals("add")) {
                score += values[i];
                i++;
            } else if (instructions[i].equals("jump")) {
                i += values[i];
            }
        }

        return score;
    }

// using array not set
    public long calculateScore1(String[] instructions, int[] values) {
        int n = instructions.length;
        int[] visited = new int[n];
        long score = 0;
        int i = 0;

        while (i >= 0 && i < n && visited[i]!=1) {
            visited[i] = 1;

            if (instructions[i].equals("add")) {
                score += values[i];
                i++;
            } else if (instructions[i].equals("jump")) {
                i += values[i];
            }
        }

        return score;
    }
}
