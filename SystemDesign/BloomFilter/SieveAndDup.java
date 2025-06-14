package SystemDesign.BloomFilter;

import java.util.BitSet;

public class SieveAndDup {
    public class Sieve { // Time complexity if O(nloglogn) same, Space usage will be less not complexity
    public static void main(String[] args) {
        int N = 1_000_000;
        BitSet isPrime = new BitSet(N + 1);
        isPrime.set(2, N + 1); // Assume all numbers 2..N are prime

        for (int p = 2; p * p <= N; p++) {
            if (isPrime.get(p)) {
                for (int multiple = p * p; multiple <= N; multiple += p) {
                    isPrime.clear(multiple);
                }
            }
        }

        // Print first 10 primes
        for (int i = 2, count = 0; count < 10 && i <= N; i++) {
            if (isPrime.get(i)) {
                System.out.println(i);
                count++;
            }
        }
    }
}

public class FastDuplicateCheck {
    public static void main(String[] args) {
        int[] nums = {4, 10, 100000, 4, 5}; // 4 is repeated
        BitSet seen = new BitSet(10_000_001);

        for (int num : nums) {
            if (seen.get(num)) {
                System.out.println("Duplicate: " + num);
            } else {
                seen.set(num);
            }
        }
    }
}
}
