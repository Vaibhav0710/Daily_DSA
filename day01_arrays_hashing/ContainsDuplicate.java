import java.util.HashSet;

public class ContainsDuplicate {

    // Problem: Given an integer array nums, return true if any value appears
    // at least twice in the array, and return false if every element is distinct.
    //
    // Example: nums = [1,2,3,1]
    // Output: true

    public boolean containsDuplicate(int[] nums) {
        // TODO: Implement your HashSet solution here
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }

    public static void main(String[] args) {
        ContainsDuplicate solution = new ContainsDuplicate();

        int[] nums1 = { 1, 2, 3, 1 };
        System.out.println("Test 1 (Expected true): " + solution.containsDuplicate(nums1));

        int[] nums2 = { 1, 2, 3, 4 };
        System.out.println("Test 2 (Expected false): " + solution.containsDuplicate(nums2));
    }
}
