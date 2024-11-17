// Yash Gupta
// 500125397 
import java.util.*;

public class Sum_of_Subset {
    // Function to find subsets that sum up to the target sum
    public static void findSubsets(int[] nums, int index, List<Integer> currentSubset, int currentSum, int targetSum) {
        // If current sum equals target sum, print the current subset
        if (currentSum == targetSum) {
            System.out.println(currentSubset);
            return;
        }

        // Explore further elements
        for (int i = index; i < nums.length; i++) {
            // If current sum + nums[i] exceeds target, skip it
            if (currentSum + nums[i] <= targetSum) {
                currentSubset.add(nums[i]);
                findSubsets(nums, i + 1, currentSubset, currentSum + nums[i], targetSum);
                // Backtrack: remove the last element to try another subset
                currentSubset.remove(currentSubset.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {10, 7, 5, 18, 12, 20, 15};
        int targetSum = 35;

        Arrays.sort(nums); // Optional: Sorting helps in pruning branches earlier
        System.out.println("Subsets with sum equal to " + targetSum + " are:");
        findSubsets(nums, 0, new ArrayList<>(), 0, targetSum);
    }
}
