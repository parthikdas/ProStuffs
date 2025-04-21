package CP_Patterns.bitmask;

public class hammingDist {
    // 461. Hamming Distance
    public int hammingDistance(int x, int y) {
        int c = 0;
        while(x>0 || y>0){
            int sum = 0;
            if(x>0) { sum += x&1; x = x>>1; }
            if(y>0) { sum += y&1; y = y>>1; }
            if(sum == 1) c++;
        }
        return c;
    }
}
