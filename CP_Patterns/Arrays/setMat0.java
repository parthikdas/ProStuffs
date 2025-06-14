package CP_Patterns.Arrays;
// 73. Set Matrix Zeroes
public class setMat0 {
    public void setZeroes(int[][] m) { // O(m*n), O(m+n)
        // Store the location, change it later
        boolean[] r = new boolean[m.length];
        boolean[] c = new boolean[m[0].length];
        // 1st pass
        for(int i=0; i<m.length; i++) {
            for(int j=0; j<m[0].length; j++) {
                if(m[i][j] == 0) {
                    r[i] = true;
                    c[j] = true;
                }
            }
        }
        // 2nd pass
        for(int i=0; i<m.length; i++) {
            for(int j=0; j<m[0].length; j++) {
                if(r[i] || c[j]) {
                    m[i][j] = 0;
                }
            }
        }
    }
}
