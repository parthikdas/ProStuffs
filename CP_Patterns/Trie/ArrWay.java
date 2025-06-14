package CP_Patterns.Trie;
import java.util.*;
// Note: Below we dont need String builder as at last we can store the String full string and it will signify as end of word as well as rest of the strings will be null

/*class Node {
    StringBuilder s;
    boolean isEndOfWord;
    Node[] list = new Node[26]; // make a set maybe
    Node() {s = new StringBuilder();}
    Node(StringBuilder s) { this.s = new StringBuilder(s); } // To prevent mutation of parent

    @Override
    public String toString() {
        StringBuilder print = new StringBuilder("[");
        for(Node l : list) if(l!=null) print.append(l).append(",");
        print.append("]");
        return "Node [s=" + s + ", list=" + print.toString() + "]";
    }
}
public class ArrWay {
    public static void main(String[] args) {
        Node node = new Node();
        String[] words = {"kittu", "abc", "dfd", "kity"};

        for(String word: words) {
            Node temp = node;
            for(Character ch : word.toCharArray()) {
                if(temp.list[ch - 'a'] == null ) // if its new char, else just jump
                    temp.list[ch - 'a'] = new Node(new StringBuilder(temp.s).append(ch)); // Make a new node along with assign value
                temp = temp.list[ch - 'a']; // Jump there
            }
        }

        System.out.println(node.toString());
    }
} */

class Node {
    String s;
    Node[] list;
    Node() {
        this.s = null;
        this.list =  new Node[26];
    }

    @Override
    public String toString() {
        StringBuilder print = new StringBuilder("[");
        for(Node l : list) if(l!=null) print.append(l).append(",");
        print.append("]");
        return "Node [s=" + s + ", list=" + print.toString() + "]";
    }
}
public class ArrWay {
    public static void main(String[] args) {
        Node node = new Node();
        String[] words = {"kittu", "abc", "dfd", "kity"};

        for(String word: words) {
            Node temp = node;
            for(Character ch : word.toCharArray()) {
                if(temp.list[ch - 'a'] == null ) // if its new char, else just jump
                    temp.list[ch - 'a'] = new Node(); // Make a new node along with assign value
                temp = temp.list[ch - 'a']; // Jump there
            }
            temp.s = word; // put at last and also signifies end of word
        }

        System.out.println(node.toString());
    }
}
