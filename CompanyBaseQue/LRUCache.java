package CompanyBaseQue;

import java.util.LinkedHashMap;
import java.util.Map;

// We can also do private Map<String, Integer> map = new LinkedHashMap<>(3,0.75f,true);
// As linkedHashMap uses Double LL we can add or dlt from last or first

// Uses : Cache recently visited pages or DNS lookups
//        Store results of recent expensive queries
//        Keep recently accessed files in memory

public class LRUCache<K, V> extends LinkedHashMap<K, V>{
    private int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        /*
         * LinkedHashMap normally stores ele based on insertion but if want we ele based on access like who was accessed recent then we do access order true
         * the middle one is load factor its basically after how much full should the map size expand like after 75% full the size inc
         */
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) { // if any size or anycondition we wanna remove
        // return true; // {} // all will be removed its normal false 
        return size() > capacity; // remove when size exceeds capacity
    }

    public static void main(String[] args) {
        LRUCache<String, Integer> cache = new LRUCache<>(3);
        cache.put("a", 1);
        cache.put("b", 2);
        cache.put("c", 3);
        cache.get("a");
        cache.put("d", 4); // This will evict "b", which is the least recently used

        System.out.println(cache.lastEntry()); // gets the last entry, gives null if not found
        System.out.println(cache.entrySet().iterator().next()); // gets the first ele, give null pointer exception
    }
}
/* // one line
 * Map<String, Integer> LRUCache = new LinkedHashMap<>(3,0.75f,true) {
        @Override
        protected boolean removeEldestEntry(Map<String, Integer> eldest) {
            // return true; // {} // all will be removed its normal false 
            return size() > 3; // remove when size exceeds capacity
        }
    };
 */

//  ---------------------------- ---------------------------- ----------------------------
// Design a LRU cache