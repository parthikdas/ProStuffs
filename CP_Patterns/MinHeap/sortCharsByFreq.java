package CP_Patterns.MinHeap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class sortCharsByFreq {
    public String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for(char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> q = new PriorityQueue<>((a,b) -> b.getValue() - a.getValue());
        for(Map.Entry<Character, Integer> m : map.entrySet()) {
            q.offer(m);
        }
        StringBuilder ss = new StringBuilder("");
        while(!q.isEmpty()) {
            int no = q.peek().getValue();
            Character temp = q.poll().getKey();
            while(no-->0)
                ss.append(temp);
        }
        return ss.toString();
    }
}
