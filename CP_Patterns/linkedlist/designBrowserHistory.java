package CP_Patterns.linkedlist;

import java.util.LinkedList;

public class designBrowserHistory {
    // 1472. Design Browser History
    class BrowserHistory {
    LinkedList<String> l;
    int i = -1;
    int size = 0;
    public BrowserHistory(String homepage) {
        l = new LinkedList<>();
        l.add(homepage);
        i = 0;
        size = 1;
    }
    
    public void visit(String url) {
        // If we are not at the last valid history, remove all forward history.
        while (i+1 < l.size()) {
            l.removeLast();
        }
        l.add(url);
        i++;
        size++;
    }
    
    public String back(int steps) {
        i = Math.max(0, i - steps);  // Ensure i doesn't go below 0
        return l.get(i);
    }
    
    public String forward(int steps) {
        i = Math.min(size - 1, i + steps);  // Ensure i doesn't go beyond the last index
        return l.get(i);
    }
}

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */
}
