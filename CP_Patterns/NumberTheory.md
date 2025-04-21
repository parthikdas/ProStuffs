# Binary Operator:
## Reason for int number size
- We have signed (we store the sign) and unsigned (we dont store the sign)
- As we have 32 bits and we use 1st bit to store the sign so the no can be 2^31-1 
And as the 1st bit is sign, the no ranges from ( - 2^31 to 2^31-1 ) Reason: 2^31 is signed bit as sign is there so removing that we use 2^31-1 for +ve ones and -2^31 for -ve ones
- So in unsigned int where we dont care about the sign we do 2^32 which is greater than signed int

## Bitwise in c++ & java
    - we have signed int and unsigned in c++:
        unsigned int a = -8; cout<<(a>>2); // -2 , kept the -ve sign
        signed int a = -8; cout<<(a>>2); // 1073741822 , not kept the -ve sign
    - but not in java use long if u want to store big and for very very big use BIG INTEGER
        We can use >> for signed stuffs and >>> for unsigned stuffs
        >>> shifts the bit not preserving the sign and fills with 0 but <<< does not exist
    - Usage:
        >> used to divide by power of 2:
            - divide by 2 : 22 >> 1 = 11
            - divide by 4 : 44 >> 2 = 11
        << used to multiply by power of 2:
            - multiply by 2 : 22 << 1 = 44
            - multiply by 4 : 44 << 2 = 176

    - odd or even:
        (n&1) as last bit will be 1 for odd
        
    - power of 2 ways:
        n > 0 and (n & (n - 1)) == 0 as powers of 2 have exactly one bit set in binary representation.
        another way but slower ceil(log2(n)) == floor(log2(n))
    








Mastering bitwise operations is crucial for competitive programming (CP) because they provide powerful, efficient ways to solve problems that involve numbers at the binary level. Understanding bitwise operations can help you handle tasks like number manipulation, optimization, and solving complex algorithmic challenges efficiently. Here’s a breakdown of key bitwise concepts you should focus on to level up your competitive programming skills:

1. Basic Bitwise Operators:
These are the foundational operations you’ll use most frequently.

AND (&): The result is 1 only if both corresponding bits are 1.
Example: 5 & 3 = 101 & 011 = 001 (which is 1).
OR (|): The result is 1 if at least one corresponding bit is 1.
Example: 5 | 3 = 101 | 011 = 111 (which is 7).
XOR (^): The result is 1 if the corresponding bits are different.
Example: 5 ^ 3 = 101 ^ 011 = 110 (which is 6).
NOT (~): Inverts all the bits (i.e., flips 1 to 0 and vice versa). It’s a unary operator.
Example: ~5 = ...11111111111111111111111111111010 (in two's complement representation for negative numbers).
Left Shift (<<): Shifts bits to the left, filling with zeros from the right.
Example: 5 << 1 = 10 (which is 5 * 2).
Right Shift (>>): Shifts bits to the right. For signed integers, the leftmost bits are filled with the sign bit (arithmetic shift).
Example: 5 >> 1 = 2 (which is 5 / 2 rounded down).
Unsigned Right Shift (>>>): Similar to the right shift, but fills with 0 instead of the sign bit, even for negative numbers.
Example: -8 >>> 1 = 1073741822.
2. Important Bitwise Patterns & Tricks:
Check if a number is even or odd:
num & 1 returns 0 if even, 1 if odd.
Example: 6 & 1 = 0, 7 & 1 = 1.
Setting the nth bit to 1:
num | (1 << n) sets the nth bit of num to 1.
Example: 5 | (1 << 1) = 7.
Clearing the nth bit (setting it to 0):
num & ~(1 << n) clears the nth bit of num.
Example: 5 & ~(1 << 1) = 5 & 11111101 = 4.
Toggling the nth bit:
num ^ (1 << n) toggles (flips) the nth bit of num.
Example: 5 ^ (1 << 1) = 7 (flips the second bit of 5).
Checking if the nth bit is set (1):
(num & (1 << n)) != 0 checks if the nth bit is 1.
Example: (5 & (1 << 2)) != 0 returns true because the 3rd bit of 5 is set.
Counting the number of 1's (Hamming Weight):
A common trick to count the number of 1s in a number is to use the following method:
while (num > 0) { num &= (num - 1); count++; }
This works by continuously clearing the rightmost set bit.
Finding the position of the rightmost set bit:
num & -num gives the value of the rightmost set bit of num.
Example: For 12 (1100), 12 & -12 = 4 (0100).
Checking if a number is a power of 2:
num > 0 && (num & (num - 1)) == 0 checks if a number is a power of 2.
Example: 8 & 7 == 0 (true for 8).
Clearing all bits after (and including) the rightmost set bit:
num & (num - 1) clears the rightmost set bit of num.
Example: For 12 (1100), 12 & 11 = 8 (1000).
Finding the largest power of 2 less than or equal to a number:
num & (num - 1) can help find the largest power of 2 less than or equal to num.
Isolate the rightmost set bit:
num & -num isolates the rightmost 1 bit of num.
3. Applications of Bitwise Operators in Competitive Programming:
Set Representation: Use integers to represent sets. For example, you can use a number’s bits to represent a set of flags, and use bitwise operators to manipulate the set (set, unset, toggle, etc.).
Finding Subsets: Bitwise operations can be used to generate all subsets of a set by iterating over the numbers from 0 to 2^n - 1, where each number represents a subset in binary form.
Efficient Multiplication and Division by Powers of 2: As mentioned earlier, << can be used to multiply by powers of 2, and >> to divide by powers of 2, which are faster than regular multiplication and division.
Bit Manipulation for String Matching: Bitwise operations can be used in certain string matching algorithms like the Knuth-Morris-Pratt (KMP) algorithm or Rabin-Karp for fast pattern searching.
Bitmasking: A technique used for problems like generating combinations, solving subsets problems, or representing sets of objects (like in DP or graph problems). Bitmasks allow efficient manipulation of sets using integer variables.
Fast Calculations: Bitwise tricks are often used to optimize algorithms, such as counting set bits, finding minimum/maximum of numbers, checking divisibility, etc.
4. Advanced Bitwise Concepts:
Binary Indexed Tree (Fenwick Tree): A data structure that uses bitwise operations to efficiently perform cumulative frequency table queries and updates.
Segment Tree with Lazy Propagation: Segment trees often use bitwise operations for efficient range updates and queries.
Bitwise DP: Solving dynamic programming problems using bitmasking (representing subsets with integers and doing state transitions via bitwise operations).
5. Tips and Common Mistakes to Avoid:
Be Careful with Signed and Unsigned Integers: In some languages, >> is arithmetic (sign-extending), while >>> is logical (filling with zeroes). This is particularly important when working with negative numbers.
Watch for Overflow: When using bit shifts, ensure you don't accidentally overflow the range of integers (e.g., shifting by too many bits).
Avoid Complex Bit Manipulation When Not Necessary: While bitwise operations are powerful, don't overuse them if simpler algorithms are easier to implement and understand.
6. Practice Problems:
Hamming Distance: Given two integers, find the number of bits that are different.
Counting Set Bits: Write a function to count the number of set bits (1s) in an integer.
Power of 2: Check if a given number is a power of 2 using bitwise operations.
Subset Problems: Use bitwise operations to find all subsets of a set.
Sum of Two Numbers: Without using arithmetic operators, calculate the sum of two integers using bitwise operators.
Resources for Practice:
LeetCode: Many problems like "Single Number," "Power of Two," and "Counting Bits" can help you practice bitwise manipulation.
Codeforces: Look for problems related to "Bitmasking," "XOR," and "Bitwise AND/OR."
GeeksforGeeks: This website has excellent tutorials and problems on bitwise manipulation.