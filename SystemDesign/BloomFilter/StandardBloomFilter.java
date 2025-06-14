package SystemDesign.BloomFilter;

import java.util.BitSet;
import java.util.Random;
/*
 * - No deletion
 * - False +ve possible
 * - Fast and mem efficient
 */
public class StandardBloomFilter {
    private BitSet bitSet;
    private int[] seed;
    private int bitSetSize;
    private int noOfHashFunctions;

    StandardBloomFilter(int bitSetSize, int noOfHashFunctions) {
        this.bitSetSize = bitSetSize;
        this.noOfHashFunctions = noOfHashFunctions;
        bitSet = new BitSet(bitSetSize);
        seed = new int[noOfHashFunctions];
        // Different seeds for hash function
        for(int i = 0; i < noOfHashFunctions; i++) {
            seed[i] = new Random().nextInt();
        }
    }

    public int hash(String item, int seed) {
        int index = 0;
        for(char ch: item.toCharArray()) {
            index = seed * index + ch;
        }
        return (index % bitSetSize); // to keep in range
    }

    public void add(String item) {
        for(int seed: seed) {
            int hashIndex = hash(item, seed);
            bitSet.set(hashIndex);
        }
    }

    public boolean mightContain(String item) {
        for (int seed : seed) {
            int hashIndex = hash(item, seed);
            if (!bitSet.get(hashIndex)) {
                return false; // definitely not present
            }
        }
        return true; // might be present
    }

    // For demo
    public static void main(String[] args) {
        StandardBloomFilter bf = new StandardBloomFilter(1000, 3);
        bf.add("apple");
        bf.add("banana");

        System.out.println(bf.mightContain("apple"));   // true
        System.out.println(bf.mightContain("banana"));  // true
        System.out.println(bf.mightContain("grape"));   // maybe false (but can be true rarely)
    }
}
