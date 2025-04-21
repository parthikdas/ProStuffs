package CP_Patterns.Arrays;

public class relativeRanks {
        public String[] findRelativeRanks(int[] score) { // O(n), O(n)
            int max = -1, c = 1, n = score.length;
            // Find the maximum score
            for (int no : score) {
                if (no > max) {
                    max = no;
                }
            }
            // Initialize an array to store the rank of each score
            int[] rankArray = new int[max + 1];
            // Fill the rankArray with the index+1 (the rank order)
            for (int i = 0; i < n; i++) {
                rankArray[score[i]] = i+1; // as the score having 0 will be put as 0 so to distinguish we put it as +1
            }
            String ans[] = new String[n];
            for(int i = max ;i>=0;i--) { // traverse from last to get descending
                if(rankArray[i] > 0) {
                    if(c == 1) ans[rankArray[i]-1] = "Gold Medal";
                    else if(c == 2) ans[rankArray[i]-1] = "Silver Medal";
                    else if(c == 3) ans[rankArray[i]-1] = "Bronze Medal";
                    else ans[rankArray[i]-1] = "" + c;
                    c++;
                }
            }
            return ans;
        }
    
}
