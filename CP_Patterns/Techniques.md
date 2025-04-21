# Sliding Window :
- When its the first iteration i.e. the window is not created, then loop and create it, but for the rest of the elements, start++ and add the new ele so we are sliding not creating a new window. Eg: 567 leetcode

- Using hashtable instead of hashSet is faster
- using for loop to traverse a string is faster than s.toCharArray()
- 2 pointers is mostly done from extreme ends

- lets say u have a array and u need to sort ele as well as maintain the original indexes:
    int max = -1;
    for(int n : score) if(n > max) max = n;
    int a[] = new int[max+1];
    for(int i = 0; i<n; i++) {
        a[score[i]] = i+1; now a[] will store all sorted as well as indexes, traverse from max to >= 0 to get all sorted
    }
    best eg : 506. Relative Ranks

- lets say u are running 2 loops to enter in lists do this :
    while(temp!=null || temp2!=null ) {
        if(temp!=null) {
            a.append(temp.val);
            temp = temp.next;
        }
        if(temp2!=null) {
            b.append(temp2.val);
            temp2 = temp2.next;
        }
    }
# Merging sorted things
- Merge k things just put in pq or do merge sort

# Anagram stuffs:
- Number of words that can be generated from a word = ! no of distinct chars in it.
- Anagram: n! / !freqOfEachChar   Eg: aabc -> 4! / 2!1!1! -> 12     abc -> 3! / 1!1!1! -> 6

# New 3 nested loops style O(n^3)
    for (int i = 0; i < nums.length; i++) {
        for (int k = nums.length - 1; k > i; k--) {
            int j = i + 1;
            while (j < k) {
                // Do whatever u want
                j++;
            }
        }
    }
        
# see merge k sorted lists for infinite input

# to check if a char is digit we can use Character.isDigit or ch>=0&&ch<=9 but best "0123456789".indexOf(ch)!=-1 same goes for special char


# IMP - Leetcode - Static variables are not refreshed during testcases so if u use it first testcase value of that var will be carried to next testcases causing incorrect output