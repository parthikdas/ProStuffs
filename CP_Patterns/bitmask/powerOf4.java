package CP_Patterns.bitmask;

public class powerOf4 {
    // 342. Power of Four
    public boolean isPowerOfFour(int n) {
        // Must be > 0 and a power of two (only one bit set), and that bit must be at even position
        return n > 0 && (n & (n - 1)) == 0 && (n % 3 == 1);
    }
}
