package CP_Patterns.stack;
import java.util.*;
public class stackQue {
    public static void dltMiddle(String[] args) { // O(n), O(n)
        Stack<Integer> s = new Stack<>();
        for(int i = 0; i < 5; i++) s.add(i);
        Stack<Integer> t = new Stack<>();
        while(!s.isEmpty() && s.size()-1 != t.size()) {
            t.add(s.pop());
        }
        s.pop();
        while(!t.isEmpty()) {
            s.add(t.pop());
        }
        while(!s.isEmpty()) {
            System.out.println(s.pop());
        }
    }
    public static void sort(Stack<Integer> s) {
        Stack<Integer> t = new Stack<>();
        while(!s.isEmpty()) {
            int n = s.pop();
            while(!t.isEmpty() && t.peek() > n) s.add(t.pop());
            t.add(n);
        }
        // Move sorted elements back to original stack (optional)
        while (!t.isEmpty()) {
            s.push(t.pop());
        }
    }
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        for(int i = 0; i < 5; i++) s.add(i); 
        sort(s);
        while(!s.isEmpty()) System.out.println(s.pop());
        // s.forEach(System.out::println);
    }
}
