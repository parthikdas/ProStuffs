package CP_Patterns.bitmask;
/*
 To flip the bits properly:

Create a mask that has 1s only for the length of the number's binary representation.
XOR n with that mask.
 */
public class complimentBaseInt {
    // 1009. Complement of Base 10 Integer
    public int bitwiseComplement(int n) {
        if (n == 0) return 1; // special case: binary of 0 is 0 → complement is 1

        int mask = 0;
        int temp = n;

        // Build the mask (e.g., if n = 5 (101), mask becomes 111)
        while (temp > 0) {
            mask = (mask << 1) | 1; // shift till the mask becomes the length
            temp >>= 1;
        }

        // XOR with mask to flip the bits
        return n ^ mask;
    }

    // 476. Number Complement
    public int findComplement(int num) {
        int temp = num, mask = 0;
        while(num > 0) {
            mask = (mask<<1) | 1;
            num = num>>1;
        }
        return temp^mask;
    }
}
