package CP_Patterns.Arrays;

public class divisiblityArrayOfString {
    // 2575. Find the Divisibility Array of a String
    public int[] divisibilityArray(String word, int m) {
        int res[] = new int[word.length()];
        long sum = 0;int k = 0;
        for(char n : word.toCharArray()) {
            int no = n-'0';
            sum = (sum*10 + no) % m;
            if(sum == 0) res[k] = 1;
            k++;
        }
        return res;
    }
    // The number can be very very big so the hack is to use m as modulo, so the sum will never exceed m and the sum needs to be 0 to be divisble by m as we do n%m==0
}
