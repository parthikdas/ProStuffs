package CP_Patterns.Arrays;
// 705. Design HashSet
public class designHashSet {
    private boolean[] mp;

    public MyHashSet() {
        mp = new boolean[1000001];
        // Arrays.fill(mp, false);
    }

    public void add(int key) {
        mp[key] = true;
    }

    public void remove(int key) {
        mp[key] = false;
    }

    public boolean contains(int key) {
        return mp[key];
    }

    /* 706 design hashmap
     private boolean[] mp;
    private int[] val;

    public MyHashMap() {
        mp = new boolean[1000001];
        val = new int[1000001];
        // Arrays.fill(mp, -1);
    }
    
    public void put(int key, int value) {
        mp[key] = true;
        val[key] = value;
    }
    
    public int get(int key) {
        return mp[key] ? val[key] : -1;
    }
    
    public void remove(int key) {
        mp[key] = false;
    }
     */
}
