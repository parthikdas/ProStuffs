package CP_Patterns.Trie.LeetCodeQue;

import java.util.HashMap;

// 3045

// Z algo for this is making TLE

//////////////////////////////
// Fastest smartest: 34ms
class Solution4 {
    class TrieNode {
        TrieNode[] children;
        int count;
        String str;
        TrieNode () {
            children = new TrieNode[26];
            count = 0;
            str = null;
        }
    }
    public long countPrefixSuffixPairs(String[] words) {  // O(n*L^2), O(n*L)     
        long res = 0;
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }                
                node = node.children[index];
                if (node.str != null && word.endsWith(node.str)) res += node.count; // We found the prefix so far as str is not null now also check if the word ends with it then its prefix and suffix both              
            }
            node.count++;
            if (node.str == null) node.str = word;
        }
        return res;
    }

}

//////////////////////////////

/*
 Logic for below solution:
 We will have a counter on how many times we visited a node. Now for checking both suf and pre we will use
 word: abcd 
 Now break it when i = 0, n-i-1 = 3
 when i = 1, n-i-1 = 2 ... 
 So if u think its kind of 2 pointer and we are trying to put both forward and backward char
 */
class Node{ // 53ms
    Node[] child;
    int counter;
    Node() {
        this.child = new Node[26];
        this.counter = 0;
    }
}
class Solution {
    public long countPrefixSuffixPairs(String[] words) { // O(n*L) n-no of words, l-avg word length
        long count = 0;
        Node node = new Node();
        // Insertion in Trie
        for(String word: words) {
            Node temp = node;
            for(int i=0, n=word.length();i<n;i++) {
                char ch = word.charAt(i);
                if(temp.child[ch - 'a'] == null)
                    temp.child[ch - 'a'] = new Node();
                temp = temp.child[ch - 'a'];
                
                ch = word.charAt(n-i-1);
                if(temp.child[ch - 'a'] == null)
                    temp.child[ch - 'a'] = new Node();
                temp = temp.child[ch - 'a'];
                count+=temp.counter;
            }
            temp.counter++;
        }

        return count;
    }
}

//////////////////////////////
/*
 This is slower than approach 1 but the basic idea is to same to make a key which uses left and right side of elemetns both
 While inserting, if this path already exists in the Trie, that means a previous word followed the same prefix-suffix path up to this point
 x.count stores how many such words reached this node before.
 */
class Solution1 {
    class TrieNode {
        HashMap<Integer, TrieNode> children = new HashMap<>();
        int count = 0;
    }

    public long countPrefixSuffixPairs(String[] words) {
        TrieNode root = new TrieNode();
        long res = 0;
        for (String word : words) {
            TrieNode x = root;
            for (int i = 0, n = word.length(); i < n; ++i) {
                int key = word.charAt(i) * 128 + word.charAt(word.length() - i - 1);
                x = x.children.computeIfAbsent(key, _ -> new TrieNode());
                res += x.count;
            }
            x.count++;
        }
        return res;
    }
}
public class CountPrefixSuffixPair2 {
    
}
