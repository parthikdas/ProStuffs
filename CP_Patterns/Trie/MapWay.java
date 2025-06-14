package CP_Patterns.Trie;
import java.util.*;
class TrieNode {
    boolean isEndOfWord;
    Map<Character, TrieNode> children;
    TrieNode() { isEndOfWord = false; children = new HashMap<>();}
    @Override
    public String toString() {
        return "TrieNode [isEndOfWord=" + isEndOfWord + ", children=" + children + "]";
    }
    
}
public class MapWay {
    public static void main(String[] args) {
        TrieNode node = new TrieNode();
        String[] words = {"kittu", "abc", "dfd", "kity"};

        for(String word: words) {
            TrieNode temp = node;
            for(int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                temp.children.computeIfAbsent(ch, _ -> new TrieNode());
                temp = temp.children.get(ch);
            }
            temp.isEndOfWord = true;
        }

        System.out.println(node);
    }
}
