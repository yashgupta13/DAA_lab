// Yash Gupta
// 500125397 
import java.util.*;
class BacktrackingKnapsack {
    static int maxProfit = 0;

    public static void knapsack(int[] weights, int[] values, int n, int capacity, int index, int currentWeight, int currentProfit) {
        // Base case
        if (index == n) {
            if (currentProfit > maxProfit) {
                maxProfit = currentProfit;
            }
            return;
        }

        // Prune the branch if adding the item exceeds capacity
        if (currentWeight + weights[index] <= capacity) {
            knapsack(weights, values, n, capacity, index + 1, currentWeight + weights[index], currentProfit + values[index]);
        }

        // Exclude the current item
        knapsack(weights, values, n, capacity, index + 1, currentWeight, currentProfit);
    }

    public static int solveKnapsack(int[] weights, int[] values, int n, int capacity) {
        maxProfit = 0;
        knapsack(weights, values, n, capacity, 0, 0, 0);
        return maxProfit;
    }
}
class Node {
    int level, profit, bound, weight;
    Node(int level, int profit, int bound, int weight) {
        this.level = level;
        this.profit = profit;
        this.bound = bound;
        this.weight = weight;
    }
}

class BranchBoundKnapsack {
    public static int bound(Node u, int n, int capacity, int[] weights, int[] values) {
        if (u.weight >= capacity) return 0;

        int profitBound = u.profit;
        int j = u.level + 1;
        int totalWeight = u.weight;

        while (j < n && totalWeight + weights[j] <= capacity) {
            totalWeight += weights[j];
            profitBound += values[j];
            j++;
        }

        if (j < n) {
            profitBound += (capacity - totalWeight) * values[j] / weights[j];
        }

        return profitBound;
    }

    public static int knapsack(int[] weights, int[] values, int n, int capacity) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> b.bound - a.bound);
        pq.add(new Node(-1, 0, 0, 0));

        int maxProfit = 0;
        while (!pq.isEmpty()) {
            Node u = pq.poll();
            if (u.level == n - 1) continue;

            Node v = new Node(u.level + 1, u.profit + values[u.level + 1], u.bound, u.weight + weights[u.level + 1]);

            if (v.weight <= capacity && v.profit > maxProfit) {
                maxProfit = v.profit;
            }

            v.bound = bound(v, n, capacity, weights, values);

            if (v.bound > maxProfit) {
                pq.add(v);
            }

            v = new Node(u.level + 1, u.profit, 0, u.weight);
            v.bound = bound(v, n, capacity, weights, values);

            if (v.bound > maxProfit) {
                pq.add(v);
            }
        }

        return maxProfit;
    }
}
class DynamicKnapsack {
    public static int knapsack(int[] weights, int[] values, int n, int capacity) {
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], values[i - 1] + dp[i - 1][w - weights[i - 1]]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][capacity];
    }
}
public class Knapsack01 {
    public static void main(String[] args) {
        int[] weights = {10, 20, 30};
        int[] values = {60, 100, 120};
        int capacity = 50;
        int n = weights.length;

        long startBacktracking = System.nanoTime();
        int backtrackingResult = BacktrackingKnapsack.solveKnapsack(weights, values, n, capacity);
        long endBacktracking = System.nanoTime();

        long startBranchBound = System.nanoTime();
        int branchBoundResult = BranchBoundKnapsack.knapsack(weights, values, n, capacity);
        long endBranchBound = System.nanoTime();

        long startDP = System.nanoTime();
        int dpResult = DynamicKnapsack.knapsack(weights, values, n, capacity);
        long endDP = System.nanoTime();

        System.out.println("Backtracking Result: " + backtrackingResult);
        System.out.println("Backtracking Time: " + (endBacktracking - startBacktracking) / 1e6 + " ms");

        System.out.println("Branch and Bound Result: " + branchBoundResult);
        System.out.println("Branch and Bound Time: " + (endBranchBound - startBranchBound) / 1e6 + " ms");

        System.out.println("Dynamic Programming Result: " + dpResult);
        System.out.println("Dynamic Programming Time: " + (endDP - startDP) / 1e6 + " ms");
    }
}
