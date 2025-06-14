package CP_Patterns.Trie.LeetCodeQue;
import java.util.*;
// Approach 1 - Smart use of map
    class Encrypter1 {
        private String[] map1 = new String[26];
        private Map<String, Integer> map2 = new HashMap<>();

        public Encrypter1(char[] keys, String[] values, String[] dictionary) {
            for( int i = 0; i < keys.length; i++){
                map1[keys[i] - 'a'] = values[i];
            }
            for(String word : dictionary){
                String encrypted = encrypt(word);
                if(!map2.containsKey(encrypted)){
                    map2.put(encrypted, 1);
                } else {
                    map2.put(encrypted, map2.get(encrypted) + 1);
                }
            }
        }
        
        public String encrypt(String word1) {
            StringBuilder sb = new StringBuilder();
            for(char c : word1.toCharArray()){
                if(map1[c - 'a'] == null){
                    return "";
                }
                sb.append(map1[c - 'a']);
            }
            return sb.toString();
        }
        
        public int decrypt(String word2) {
            if(map2.containsKey(word2)){
                return map2.get(word2);
            }
            return 0;
        }
    }

    // Approach 2 - Trie + DFS

class Encrypter2 {
    private Map<Character, String> encryptMap;
    private Map<String, List<Character>> decryptMap;
    private TrieNode root;

    public Encrypter2(char[] keys, String[] values, String[] dictionary) {
        encryptMap = new HashMap<>();
        decryptMap = new HashMap<>();
        root = new TrieNode();

        // Build encryption map
        for (int i = 0; i < keys.length; i++) {
            encryptMap.put(keys[i], values[i]);
            decryptMap.computeIfAbsent(values[i], k -> new ArrayList<>()).add(keys[i]);
        }

        // Insert dictionary words into trie
        for (String word : dictionary) {
            insert(word);
        }
    }

    public String encrypt(String word) {
        StringBuilder sb = new StringBuilder();
        for (char c : word.toCharArray()) {
            if (!encryptMap.containsKey(c)) return ""; // invalid char
            sb.append(encryptMap.get(c));
        }
        return sb.toString();
    }

    public int decrypt(String encryptedWord) {
        return dfs(encryptedWord, 0, root);
    }

    // ------------------ Trie Helpers ------------------

    private void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
        }
        node.isEnd = true;
    }

    private int dfs(String encrypted, int index, TrieNode node) {
        if (index == encrypted.length()) {
            return node.isEnd ? 1 : 0;
        }

        String pair = encrypted.substring(index, index + 2);
        if (!decryptMap.containsKey(pair)) return 0;

        int count = 0;
        for (char c : decryptMap.get(pair)) {
            if (node.children.containsKey(c)) {
                count += dfs(encrypted, index + 2, node.children.get(c));
            }
        }
        return count;
    }

    // ------------------ Trie Node ------------------

    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isEnd = false;
    }
}
