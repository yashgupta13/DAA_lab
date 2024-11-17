// Yash Gupta
// 500125397 
import java.util.*;

class Item {
    int weight, value;
    Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }
}

class GreedyKnapsack {
    public static double greedyKnapsack(Item[] items, int capacity) {
        Arrays.sort(items, (a, b) -> (b.value * a.weight - a.value * b.weight));

        int currentWeight = 0;
        double totalValue = 0.0;

        for (Item item : items) {
            if (currentWeight + item.weight <= capacity) {
                currentWeight += item.weight;
                totalValue += item.value;
            } else {
                break;
            }
        }

        return totalValue;
    }
}
class DynamicKnapsack {
    public static int dynamicKnapsack(Item[] items, int n, int capacity) {
        int[][] dp = new int[n + 1][capacity + 1];

        // Build table dp[][] in bottom-up manner
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= capacity; w++) {
                if (items[i - 1].weight <= w) {
                    dp[i][w] = Math.max(items[i - 1].value + dp[i - 1][w - items[i - 1].weight], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Maximum value at dp[n][capacity]
        return dp[n][capacity];
    }
}
public class Knapsack_Problem {
    public static void main(String[] args) {
        int capacity = 50;
        int n = 10;
        Random rand = new Random();

        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            int value = rand.nextInt(100) + 1;
            int weight = rand.nextInt(20) + 1;
            items[i] = new Item(value, weight);
        }

        // Measure time for Greedy Approach
        long startGreedy = System.nanoTime();
        double greedyValue = GreedyKnapsack.greedyKnapsack(items, capacity);
        long endGreedy = System.nanoTime();

        // Measure time for Dynamic Programming Approach
        long startDP = System.nanoTime();
        int dpValue = DynamicKnapsack.dynamicKnapsack(items, n, capacity);
        long endDP = System.nanoTime();

        System.out.println("Greedy Approach Value: " + greedyValue);
        System.out.println("Greedy Approach Time: " + (endGreedy - startGreedy) / 1e6 + " ms");

        System.out.println("Dynamic Programming Value: " + dpValue);
        System.out.println("Dynamic Programming Time: " + (endDP - startDP) / 1e6 + " ms");
    }
}
