package CompanyBaseQue;
import java.util.*;
// Same sort code can be used for JS aslo
// or sort((a, b) => a[0] - b[0] || a[1] - b[1] || a[2] - b[2]) in js it works
    
class T {
    int n;
    String s;
    int val;
    T() {}
    T(int n, String s, int val) {
        this.n = n;
        this.s = s;
        this.val = val;
    }
    @Override
    public String toString() {
        return "T{n=" + n + ", s='" + s + "', val=" + val + "}";
    }
}
// note in java, comparator of arrays expects integer not boolean
class Main {
    public static void main(String[] args) {
        T node = new T();
        List<T> l = new ArrayList<>(Arrays.asList(
            new T(1, "Prathik", 23),
            new T(2, "P", 2),
            new T(1, "Parthik", 23),
            new T(1, "Parthik", 21)
        ));
        l.forEach(System.out::println);
        System.out.println("----------------------");
        Collections.sort(l, (a,b) -> {
            if(a.n!=b.n) return a.n-b.n;
            int cmp = a.s.compareTo(b.s);
            if (cmp != 0) return cmp;
            return a.val - b.val;
        });
        // OR
        Collections.sort(l, (a,b) -> a.n == b.n ? a.s.compareTo(b.s) == 0 ? a.val - b.val : a.s.compareTo(b.s) : a.n- b.n);
        // OR
        l.sort(Comparator.comparingInt((T t) -> t.n)
            .thenComparing(t -> t.s)
            .thenComparingInt(t -> t.val)
        );
        l.forEach(System.out::println);
    }
}

public class sorringBasedOnComparator {
    // Another eg
    public static void main(String[] args) {
        int[][] bb = {
            {2,3,1},
            {1,2,3},
            {1,1,2}
        };
        Arrays.sort(bb, (c,b) -> c[0]==b[0] ? c[1]==b[1] ? c[2] - b[2] : c[1]-b[1] : c[0]-b[0]);
        //   OR
        Arrays.sort(bb, (c, b) -> {
        if (c[0] != b[0]) return c[0] - b[0];
        if (c[1] != b[1]) return c[1] - b[1];
        return c[2] - b[2];
        });
        // OR
        Arrays.sort(bb, Comparator.comparingInt((int[] a) -> a[0])
                                  .thenComparingInt(a -> a[1])
                                  .thenComparing(a -> a[2]));
        for(int m[] : bb) {
            for(int n: m) System.out.print(n + " ");
            System.out.println();
        }

    }
}

