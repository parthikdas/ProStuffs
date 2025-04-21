package CP_Patterns.DP;
import java.util.*;
public class pascal {
    public List<Integer> getRow(int n) { // If u want to just print the row
        List<Integer> first = new ArrayList<>();
        for(int i = 0;i<=n;i++){
            List<Integer> second = new ArrayList<>();
            for(int j = 0;j<=i;j++) {
                if(j == 0 || j == i) second.add(1);
                else second.add(first.get(j-1) + first.get(j));
            }
            first = second;
        }
        return first;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<List<Integer>> tri = new ArrayList<>();
        for(int i = 0; i<n; i++) {
            List<Integer> row = new ArrayList<>();
            for(int j = 0;j<=i;j++) { // j<=i to ensure correct number of elements
                if(j == 0 || j == i) {
                    row.add(1); // First and last element of each row is always 1
                } else {
                    row.add(tri.get(i-1).get(j-1) + tri.get(i-1).get(j)); // Get the previous parents
                }
            }
            tri.add(row);
        }
        tri.stream().forEach((row) -> {
            row.stream().forEach(System.out::print);
            System.out.println();
        });
    }
}
