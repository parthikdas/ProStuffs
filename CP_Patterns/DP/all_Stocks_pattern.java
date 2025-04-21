/*
 You are given an array prices[], where prices[i] represents the price of a stock on the i-th day. You need to find the maximum profit by buying and selling under different constraints.

Common Variations
Single Transaction → Buy once and sell once to maximize profit.
Multiple Transactions → Buy and sell multiple times (no overlapping transactions).
At Most K Transactions → You can make at most K transactions.
With Cooldown → After selling, you must wait for one day before buying again.
With Transaction Fee → Every transaction has a fixed fee deducted.

--------------------------
1. Best Time to Buy and Sell Stock (Single Transaction)

Problem Statement:
Find the maximum profit by buying and selling once.

Thinking Process
The key observation is that we need to buy at the lowest price before selling.
Maintain a minimum price seen so far and update max profit if selling at prices[i] gives a higher profit.
Optimized DP Approach (Kadane’s Algorithm)
Track the minimum price while iterating.
Calculate profit if sold on the current day.
Update max profit.
Code
public class StockProfit {
    public static int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            minPrice = Math.min(minPrice, price); // Keep track of the minimum price
            maxProfit = Math.max(maxProfit, price - minPrice); // Calculate max profit
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices)); // Output: 5
    }
}
Time Complexity: O(n)
Space Complexity: O(1)
--------------------------
2. Best Time to Buy and Sell Stock II (Multiple Transactions)

Problem Statement:
You can buy and sell multiple times, but you must sell before buying again.

Thinking Process
Instead of trying to find the lowest point, we can just profit from every price increase.
If prices[i] > prices[i-1], we buy at prices[i-1] and sell at prices[i].
Code
public class StockProfitII {
    public static int maxProfit(int[] prices) {
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1]; // Add profit of each increase
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices)); // Output: 7
    }
}
Time Complexity: O(n)
Space Complexity: O(1)
--------------------------
3. Best Time to Buy and Sell Stock III (At Most Two Transactions)

Problem Statement:
You can complete at most two transactions.

Thinking Process
We need to track two transactions:
First Buy → First Sell
Second Buy → Second Sell
Maintain four variables:
firstBuy (min cost of first purchase)
firstSell (profit after first sale)
secondBuy (effective cost after using first sale profit)
secondSell (max profit after second sale)
Code
public class StockProfitIII {
    public static int maxProfit(int[] prices) {
        int firstBuy = Integer.MIN_VALUE, firstSell = 0;
        int secondBuy = Integer.MIN_VALUE, secondSell = 0;

        for (int price : prices) {
            firstBuy = Math.max(firstBuy, -price);
            firstSell = Math.max(firstSell, firstBuy + price);
            secondBuy = Math.max(secondBuy, firstSell - price);
            secondSell = Math.max(secondSell, secondBuy + price);
        }

        return secondSell;
    }

    public static void main(String[] args) {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println(maxProfit(prices)); // Output: 6
    }
}
Time Complexity: O(n)
Space Complexity: O(1)
--------------------------
4. Best Time to Buy and Sell Stock with Cooldown

Problem Statement:
After selling a stock, you must wait 1 day before buying again.

Thinking Process
Use three states:
Holding (own stock) → hold
Not Holding (no stock, can buy) → notHold
Cooldown (rest after selling) → cooldown
Transition:
hold[i] = max(hold[i-1], cooldown[i-1] - prices[i])
notHold[i] = max(notHold[i-1], hold[i-1] + prices[i])
cooldown[i] = notHold[i-1]
Code
public class StockProfitCooldown {
    public static int maxProfit(int[] prices) {
        int hold = Integer.MIN_VALUE, notHold = 0, cooldown = 0;

        for (int price : prices) {
            int prevHold = hold;
            hold = Math.max(hold, cooldown - price);
            cooldown = notHold;
            notHold = Math.max(notHold, prevHold + price);
        }

        return Math.max(notHold, cooldown);
    }

    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 0, 2};
        System.out.println(maxProfit(prices)); // Output: 3
    }
}
Time Complexity: O(n)
Space Complexity: O(1)
--------------------------
5. Best Time to Buy and Sell Stock with Transaction Fee

Problem Statement:
Each transaction incurs a fee, and we must maximize profit.

Thinking Process
Similar to unlimited transactions, but we subtract the fee after selling.
Update hold and notHold states:
hold[i] = max(hold[i-1], notHold[i-1] - prices[i])
notHold[i] = max(notHold[i-1], hold[i-1] + prices[i] - fee)
Code
public class StockProfitFee {
    public static int maxProfit(int[] prices, int fee) {
        int hold = Integer.MIN_VALUE, notHold = 0;

        for (int price : prices) {
            int prevHold = hold;
            hold = Math.max(hold, notHold - price);
            notHold = Math.max(notHold, prevHold + price - fee);
        }

        return notHold;
    }

    public static void main(String[] args) {
        int[] prices = {1, 3, 2, 8, 4, 9};
        int fee = 2;
        System.out.println(maxProfit(prices, fee)); // Output: 8
    }
}
Time Complexity: O(n)
Space Complexity: O(1)
Final Thoughts

Single transaction: Track min price.
Multiple transactions: Profit from every increase.
At most K transactions: Use DP table.
Cooldown: Track states.
Transaction fee: Deduct fee after selling.
 */

package CP_Patterns.DP;

public class all_Stocks_pattern {
    
}
