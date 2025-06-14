package CP_Patterns.bitmask;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
    Subsequence :
        We dont need for loop inside recursion as we only have 2 choices to either take or not take it
        When we move towards multi choices like permutaions we need for loop
        In permutation we need a visisted arr to check if the current one is visited or not here we dont
        we use backtrack to make this
    Subset : 
        When elements of arr are unique subset and subsequence will have same res.
        But when they are not unique then res is diff. Eg: [aa] -> subs: "", "a", "a", "aa" <- 4
                                                                   subset: "", "a", "aa" <- 3
        We use powerset to make this

    Both are O(2^n) time and O(n) in space
 */
public class subSeq_Set_Study {
// Note: below are the codes of subsequence and subset in recursion and iterative both will generate the same output
    // Function to generate all subsequence using backtrack
    public static void generateSub(String s, int i, StringBuilder path) {
        if( i >= s.length() ) {
            System.out.println(path.toString());
            return;
        }
        // include
        path.append(s.charAt(i));
        generateSub(s, i+1, path);
        // exclude
        path.setLength(path.length()-1);
        generateSub(s, i+1, path);
    }
    // Function to generate all subset using powerset
    public static void generatesubset(String s) {
        List<String> result = new ArrayList<>();
        int n = s.length(), totalSubsets = 1 << n; // 2^n
        for (int i = 0; i < totalSubsets; i++) {
            StringBuilder subset = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) { // Check if jth bit in i is set
                    subset.append(s.charAt(j));
                }
            }
            result.add(subset.toString());
        }
        result.forEach(System.out::println);
    }


// -----------------------------------------------------------------------


// Note: Below are code of subset where subset and subsequence has distinct values
    // Recursive set version of subsequence so no dup will also work for subset
    public static void generateSubUnique(String s, int i, StringBuilder path, Set<String> uniqueSubs) {
        if( i >= s.length() ) {
            if(!path.toString().equals("")) uniqueSubs.add(path.toString()); // just dont wanna print ""
            return;
        }
        // include
        path.append(s.charAt(i));
        generateSubUnique(s, i+1, path, uniqueSubs);
        // exclude
        path.setLength(path.length()-1);
        generateSubUnique(s, i+1, path, uniqueSubs);
    }
    // Function to generate all subset using powerset
    public static void generatesubsetUnique(String s) {
        Set<String> result = new HashSet<>();
        int n = s.length(), totalSubsets = 1 << n; // 2^n
        for (int i = 0; i < totalSubsets; i++) {
            StringBuilder subset = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) { // Check if jth bit in i is set
                    subset.append(s.charAt(j));
                }
            }
            result.add(subset.toString());
        }
        result.forEach(System.out::println);
    }

    public static void main(String[] args) {
        // With dup
        String s = "aca";
        generateSub(s, 0, new StringBuilder("")); // Subsequence
        generatesubset(s); // Subset

        // Without dup
        System.out.println();
        Set<String> uniqueSubs = new HashSet<>();
        generateSubUnique(s, 0, new StringBuilder(""), uniqueSubs); // Subsequence
        uniqueSubs.forEach(System.out::println);
        generatesubsetUnique(s);

    }
}
