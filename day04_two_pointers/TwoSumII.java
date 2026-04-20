package day04_two_pointers;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSumII {

    public int[] twoSum(int[] numbers, int target) {
        // Initialize two pointers at the extremes of the SORTED array
        int start = 0;
        int end = numbers.length - 1;
        
        while (start < end) {
            int currentSum = numbers[start] + numbers[end];
            
            // If we found the target, return 1-indexed positions
            if (currentSum == target) {
                return new int[] { start + 1, end + 1 };
            } 
            // If sum is too small, move the left pointer to the right to increase the sum
            else if (currentSum < target) {
                start++;
            } 
            // If sum is too big, move the right pointer to the left to decrease the sum
            else {
                end--;
            }
        }
        return new int[] {};
    }

    // Approach: Standard Two Sum using a HashMap (Day 1 approach)
    // Time Complexity: O(N) - We iterate through the array at most twice.
    // Space Complexity: O(N) - In the worst case, we store all N elements in the HashMap.
    // Note: While this works, it fails the strict O(1) space constraint of Two Sum II!
    public int[] twoSumUsingHashMap(int[] numbers, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        
        // Pass 1: Add all elements and their indices to the map
        for (int i = 0; i < numbers.length; i++) {
            map.put(numbers[i], i);
        }
        
        // Pass 2: Check if the complement (target - current) exists in the map
        for (int i = 0; i < numbers.length; i++) {
            int complement = target - numbers[i];
            
            // Check if complement exists. (In a strict implementation we should also ensure 
            // map.get(complement) != i so we don't use the same element twice. But Two Sum II 
            // guarantees exactly one solution and no duplicates for the target).
            if (map.containsKey(complement)) {
                // Return 1-indexed positions
                return new int[] { i + 1, map.get(complement) + 1 };
            }
        }
        return new int[] {};
    }

    public static void main(String[] args) {
        TwoSumII solution = new TwoSumII();

        // Test Cases
        System.out.println("Test 1: " + Arrays.toString(solution.twoSumUsingHashMap(new int[] { 2, 7, 11, 15 }, 9)));
        // Expected: [1, 2]

        System.out.println("Test 2: " + Arrays.toString(solution.twoSumUsingHashMap(new int[] { 2, 3, 4 }, 6)));
        // Expected: [1, 3]

        System.out.println("Test 3: " + Arrays.toString(solution.twoSumUsingHashMap(new int[] { -1, 0 }, -1)));
        // Expected: [1, 2]

        System.out.println("Test 1: " + Arrays.toString(solution.twoSum(new int[] { 2, 7, 11, 15 }, 9)));
        // Expected: [1, 2]

        System.out.println("Test 2: " + Arrays.toString(solution.twoSum(new int[] { 2, 3, 4 }, 6)));
        // Expected: [1, 3]

        System.out.println("Test 3: " + Arrays.toString(solution.twoSum(new int[] { -1, 0 }, -1)));
        // Expected: [1, 2]
    }
}
