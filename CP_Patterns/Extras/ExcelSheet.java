package CP_Patterns.Extras;

public class ExcelSheet {
    // 168. Excel Sheet Column Title
    public String convertToTitle(int n) {
        StringBuilder s = new StringBuilder("");
        while(n>0) {
            n--;
            s.append((char)(n%26 + 'A'));
            n/=26;
        }
        return s.reverse().toString();
    }

    // 171. Excel Sheet Column Number
    public int titleToNumber(String columnTitle) {
        int result = 0;
        for (int i = 0; i < columnTitle.length(); i++) {
            char c = columnTitle.charAt(i);
            int value = c - 'A' + 1;  // Convert char to its corresponding number
            result = result * 26 + value;  // Accumulate the result in base-26
        }
        return result;
    }
}
