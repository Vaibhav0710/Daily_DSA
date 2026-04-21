package day05_two_pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    // Approach: Sort + Fix one number + Two Sum II on the rest
    // Time Complexity: O(N^2) - Outer loop O(N) * Inner Two Pointer O(N)
    // Space Complexity: O(1) - Sorting in-place, only using pointers
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        // Step 1: Sort the array — enables Two Pointer approach and duplicate skipping
        Arrays.sort(nums);

        // Step 2: Fix nums[i], then run Two Sum II on [i+1 ... n-1] with target = -nums[i]
        for (int i = 0; i < nums.length - 2; i++) {
            // Early exit: if nums[i] > 0, no three positive numbers can sum to 0
            if (nums[i] > 0) break;

            // Skip duplicate values of i to avoid repeated triplets in result
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;
            int target = -nums[i];

            // Inner Two Sum II — find pairs that sum to target in sorted subarray
            while (left < right) {
                int currentSum = nums[left] + nums[right];
                if (currentSum == target) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    // Skip duplicate left values after finding a valid triplet
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    // Skip duplicate right values after finding a valid triplet
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else if (currentSum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        ThreeSum solution = new ThreeSum();

        // Test 1: Standard case with duplicates
        System.out.println("Test 1: " + solution.threeSum(new int[] { -1, 0, 1, 2, -1, -4 }));
        // Expected: [[-1, -1, 2], [-1, 0, 1]]

        // Test 2: All zeros
        System.out.println("Test 2: " + solution.threeSum(new int[] { 0, 0, 0, 0 }));
        // Expected: [[0, 0, 0]]

        // Test 3: No valid triplet
        System.out.println("Test 3: " + solution.threeSum(new int[] { 1, 2, 3 }));
        // Expected: []

        // Test 4: Less than 3 elements
        System.out.println("Test 4: " + solution.threeSum(new int[] { 0, 1 }));
        // Expected: []

        // Test 5: Negative numbers
        System.out.println("Test 5: " + solution.threeSum(new int[] { -2, -1, 0, 1, 2, 3 }));
        // Expected: [[-2, -1, 3], [-2, 0, 2], [-1, 0, 1]]
    }
}
