# Dynamic Programming
DP is efficient recursion (recursion + storage). So how to identify its recursion or dp problem.
- Choices
- Optimal
When we have choices like whether to put it or not or like do or not its recursion
On top of it if lets say while solving a particular branch u see its already solved in some branch so its overlapping so its dp.
Steps: 1. once identified as dp problem, first right recusive solution
       2. memoziation of it (DP)
       3. top down (DP)

Recursion: For finding the base case, always think of the smallest valid input
           Then make the choice tree diagram and according to that make the recursion call
Memoziaton: Consider only those variables which are changing in recursion for the dp array, fill it with -1
Why top down is considered better ? As here there is no chance of stack overflow unlike recursion calls filling the stack
Eg: 87. Scramble String here memo will have stack overflow

Base condition of recrusive function becomes the  initialisation of top down matrix (the first row and col as they are 0)
----------------------------------------------------------------------
## Types of Dynamic Programming Problems:
    1. 0-1 Knapsack (6)
    2. Unbouded Knapsack
    3. Fibonacci
    4. LCS
    5. LIS
    6. Kadane's Algo
    7. Matrix Chain Multiply
    8. DP on Trees
    9. DP on Grid
    10. Others
----------------------------------------------------------------------
## Types in 0/1 Knapsack :
- Subset sum
- Equal sum partition
- Count of subset sum
- Min of subset sum diff
- Target sum
- No of subset of given diff

### Knapsack:
    Goal is to fill the pouch(Knapsack) using the given weights an get the max value.
    Types:
        - Fractional Knapsack - Greedy
        - 0/1 Knapsack - Dp
        - Unbounded Knapsack - Dp
    
    1. Fractional Knapsack: lets say we have 10kg pouch to fill and already 9kg is filled and one obj of 2 kg is left so we can break it in half and put it.
        - Sort items by v[i] / w[i] in descending order. 
        - Pick full items until you can't fit any more.
        - For the last item that doesn't fully fit, take a fraction of it.

    2. 0/1 Knapsack: we can't put it either we put it whole(1) or no(0)

    3. Unbounded Knapsack: Here we have unlimited supply of a item unlike the other 2 variation i.e. multi occurence allowed.
