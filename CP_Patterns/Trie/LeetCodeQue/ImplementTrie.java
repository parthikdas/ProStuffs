package CP_Patterns.Trie.LeetCodeQue;
// 208
class Trie {
    private Trie[] trie;
    boolean isEndOfWord;

    public Trie() {
        this.trie = new Trie[26];
        this.isEndOfWord = false;
    }

    public void insert(String word) { // O(L), O(L) -> L is length of word
        Trie temp = this; // start with root
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (temp.trie[ch - 'a'] == null)
                temp.trie[ch - 'a'] = new Trie();
            temp = temp.trie[ch - 'a'];
            if(i == word.length() - 1)
                temp.isEndOfWord = true;
        }
    }

    // For search we have to ensure all chars exists and also its end of the word
    public boolean search(String word) { // O(L), O(1)
        Trie temp = this;
        for (char ch : word.toCharArray()) {
            if (temp.trie[ch - 'a'] == null) return false;
            else temp = temp.trie[ch - 'a'];
        }
        return temp.isEndOfWord;
    }

    // For prefix search we dont need it to end
    public boolean startsWith(String prefix) { // O(P), O(1) -> P is length of prefix
        Trie temp = this;
        for (char ch : prefix.toCharArray()) {
            if (temp.trie[ch - 'a'] == null) return false;
            else temp = temp.trie[ch - 'a'];
        }
        return true;
    }
}
public class ImplementTrie {
    
}
