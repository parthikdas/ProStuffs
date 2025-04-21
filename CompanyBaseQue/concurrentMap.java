package CompanyBaseQue;

import java.util.*;

// Asked in jpmorgan, I have a map and i am deleting a entry using iterator
// why it is throwing error
public class concurrentMap {
    public static void main(String[] args) {
        Map<String, String> cityCode = new HashMap<>();
        cityCode.put("Delhi", "India");
        cityCode.put("Moscow", "Russia");
        cityCode.put("New York", "USA");
        
        Iterator<String> iterator = cityCode.keySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(cityCode.get(iterator.next()));
            cityCode.remove("Delhi");
        }
        System.out.println(cityCode.size()); // Should be 2


        // Output coming:
        /*
         Exception in thread "main" java.util.ConcurrentModificationException
        at java.base/java.util.HashMap$HashIterator.nextNode(HashMap.java:1605)
        at java.base/java.util.HashMap$KeyIterator.next(HashMap.java:1628)
        at CompanyBaseQue.concurrentMap.main(concurrentMap.java:16)

        as hashmap is not thread safe so the iterator is fail fast iterator (If we try to change the structure of the DS using fail fast iterator it immediately throws Concurrent modi exception)
        Solution :         Map<String, String> cityCode = new ConcurrentHashMap<>(); // Now this concurrent hashmap is thread safe
                        and the iterator is also thread safe iterator so it does the work

         */
    }
}
