package CP_Patterns.String;

import java.util.ArrayList;
import java.util.List;
// 1023. Leetcode
public class camelCaseMatch {
    public List<Boolean> camelMatch(String[] queries, String p) {
        List<Boolean> ans = new ArrayList<>();
        for(String s: queries) {
            int i = 0, j = 0, flag = 0;
            while(i < s.length()) {
                char ch = s.charAt(i);
                if(ch >= 'A' && ch <='Z') {
                    if(p.indexOf(Character.toString(ch)) == -1) { // Caps exist or not in pattern
                        flag = 1;
                        ans.add(false); // Caps not found in Pattern 
                        break;
                    }
                    else { // Caps Found in pattern
                        // Need to check if current one is same
                        if(ch != p.charAt(j)) {
                            flag = 1;
                            ans.add(false);
                            break;
                        }
                    }
                }
                if(j<p.length() && ch == p.charAt(j)) j++;
                i++;
            }
            if(flag == 0 ) {
                ans.add(j>p.length()-1); // if pattern is not completed
            }
        }
        return ans;
    }
}
