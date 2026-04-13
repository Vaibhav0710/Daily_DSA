import java.util.HashMap;

public class TwoSum {

    // Problem: Given an array of integers nums and an integer target,
    // return indices of the two numbers such that they add up to target.
    //
    // Example: nums = [2,7,11,15], target = 9
    // Output: [0,1]

    public int[] twoSumTwoPointers(int[] nums, int target) {
        // TODO: Implement your solution here
        if (nums.length < 2) {
            return new int[] {};
        }
        int sum = 0;
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            sum = nums[i] + nums[j];
            if (sum == target) {
                return new int[] { i, j };
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }
        }
        return new int[] {};
    }

    public int[] twoSum(int[] nums, int target) {
        // Your new HashMap code here
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        return new int[] {};
    }

    public static void main(String[] args) {
        TwoSum solution = new TwoSum();
        int[] nums = { 2, 7, 11, 15 };
        int target = 9;

        int[] result = solution.twoSum(nums, target);

        if (result.length == 2) {
            System.out.println("Indices: [" + result[0] + ", " + result[1] + "]");
        } else {
            System.out.println("No solution found.");
        }
    }
}
