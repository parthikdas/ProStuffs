package CP_Patterns.DP.Knapsack01;

import java.util.Arrays;

class Item {
    int value, weight;
    
    public Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }
}
public class fractionalKnap {
    // Function to get the maximum value
    public static double getMaxValue(Item[] items, int capacity) {
        // Sort by value/weight ratio in descending order
        Arrays.sort(items, (a, b) -> Double.compare(
            (double)b.value / b.weight,
            (double)a.value / a.weight
        ));
        
        double totalValue = 0.0;

        for (Item item : items) {
            if (capacity >= item.weight) {
                // Take the whole item
                capacity -= item.weight;
                totalValue += item.value;
            } else {
                // Take fraction of item
                totalValue += item.value * ((double)capacity / item.weight);
                break;
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        Item[] items = {
            new Item(60, 10),
            new Item(100, 20),
            new Item(120, 30)
        };
        int capacity = 50;

        double maxValue = getMaxValue(items, capacity);
        System.out.println("Maximum value in knapsack = " + maxValue); // Output: 240.0
    }
}
/*
 💡 Why value / weight?
Imagine two items:

Item A: value = 100, weight = 20 → ratio = 5
Item B: value = 60, weight = 10 → ratio = 6
Now, say you only have space for 10 units of weight.

If you pick Item A, you get: 100 × (10/20) = 50
If you pick Item B, you get: 60
🔎 Conclusion: Even though Item A is "worth more" in total, Item B gives you more value per kg of weight.

✅ Analogy:
If you're shopping with limited money, you'd buy products with the most benefit per dollar — same logic here: we want most value per weight.

🧠 So:
By sorting items on value / weight:

We prioritize items that give us the most value for each unit of weight
This greedy strategy guarantees optimal solution for the fractional version of the problem.
 */