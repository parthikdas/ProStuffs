package CP_Patterns.Arrays;

public class zeroAtEnd {
    public static void main(String[] args) {
        int a[] = {1,0,20,3,0,4,0};
        // int b[] = new int[a.length];
        // int i = 0;
        // for(int j = 0; j < a.length; j++) if(a[j]!=0) b[i++] = a[j];
        // for(int j = 0; j < a.length; j++) System.out.println(b[j]);
        int i=0,j=0;
        for(; j < a.length; j++) {
            if(a[j] != 0) a[i++] = a[j];
        }
        for(;i<a.length;i++) a[i] = 0;
        for(j = 0; j < a.length; j++) System.out.println(a[j]);
    }
}
