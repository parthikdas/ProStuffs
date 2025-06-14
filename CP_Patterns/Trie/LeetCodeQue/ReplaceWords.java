package CP_Patterns.Trie.LeetCodeQue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// 648

// Note : using string to store the word at final char to indicate last char and also get the final string

// Array and without stream - Fastest
class Solution {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null; // Store root word when it's the end of a word
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        TrieNode root = new TrieNode();

        // 1. Insert all roots into Trie
        for (String rootWord : dictionary) {
            TrieNode node = root;
            for (char ch : rootWord.toCharArray()) {
                int index = ch - 'a';
                if (node.children[index] == null)
                    node.children[index] = new TrieNode();
                node = node.children[index];
            }
            node.word = rootWord; // Mark end of root word
        }

        // 2. Process sentence
        StringBuilder sb = new StringBuilder();
        for (String word : sentence.split(" ")) {
            TrieNode node = root;
            String replacement = word;
            for (char ch : word.toCharArray()) {
                int index = ch - 'a';
                if (node.children[index] == null || node.word != null) break;
                node = node.children[index];
            }
            if (node.word != null) replacement = node.word;
            sb.append(replacement).append(" ");
        }

        // 3. Remove trailing space
        return sb.toString().trim();
    }
}

        // Array Faster
class Node {
    String s; // Root word
    Node[] children;

    Node() {
        s = null;
        children = new Node[26];
    }
}
class Solution1 {
    public String replaceWords(List<String> dictionary, String sentence) {
        Node root = new Node();

        // Build Trie
        for (String d : dictionary) {
            Node temp = root;
            for (char ch : d.toCharArray()) {
                int idx = ch - 'a';
                if (temp.children[idx] == null)
                    temp.children[idx] = new Node();
                temp = temp.children[idx];
            }
            temp.s = d; // only at the end put the root word
        }

        // Replace words using Trie
        return Arrays.stream(sentence.split(" "))
                .map(word -> { // return if the last char found or else original word
                    Node temp = root;
                    for (char ch : word.toCharArray()) {
                        int idx = ch - 'a';
                        if (temp.children[idx] == null) break;
                        temp = temp.children[idx];
                        if (temp.s != null) return temp.s;
                    }
                    return word;
                })
                .collect(Collectors.joining(" "));
    }
}
     // Map 
class Node1{
    String s;
    Map<Character, Node1> map;
    Node1() {
        s = null;
        map = new HashMap<>();
    }
}
class Solution2 {
    public String replaceWords(List<String> dictionary, String sentence) {
        Node1 root = new Node1();
        for(String d : dictionary) {
            Node1 temp = root;
            for(char ch: d.toCharArray()) {
                temp.map.computeIfAbsent(ch, x -> new Node1());
                temp = temp.map.get(ch);
            }
            temp.s = d; // only at the end put the word
        }
        return Arrays.stream(sentence.split(" ")).map(word -> {
            Node1 temp = root;
            for (char ch : word.toCharArray()) {
                if (!temp.map.containsKey(ch)) break;
                temp = temp.map.get(ch);
                if (temp.s != null) return temp.s; // Found root wor
            }
            return word; // No match found, keep original word
        }).collect(Collectors.joining(" "));
    }
}
//////////////////////////////////////////////////////

public class ReplaceWords {
    
}
