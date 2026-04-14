import java.util.Arrays;

public class ProductOfArrayExceptSelf {

    /*
     * Solution 1: Using Extra Space
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int[] productExceptSelfUsingExtraSpace(int[] nums) {
        // TODO: Implement your logic here
        int len = nums.length;
        int[] prefix = new int[len];
        int[] suffix = new int[len];
        int[] result = new int[len];

        prefix[0] = 1;
        for (int i = 1; i < len; i++) {
            prefix[i] = prefix[i - 1] * nums[i - 1];
        }
        suffix[len - 1] = 1;
        for (int i = len - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] * nums[i + 1];
        }
        for (int i = 0; i < len; i++) {
            result[i] = prefix[i] * suffix[i];
        }
        return result;
    }

    /*
     * Solution 2: Using Division
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int[] productExceptSelfUsingDivision(int[] nums) {
        int zeros = 0, index = 0, product = 1;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] == 0) {
                zeros++;
                index = i;
            } else {
                product *= nums[i];
            }
        }

        int[] response = new int[len];
        Arrays.fill(response, 0);
        if (zeros == 0) {
            for (int i = 0; i < len; i++) {
                response[i] = product / nums[i];
            }
        } else if (zeros == 1) {
            response[index] = product;
        }
        return response;
    }

    /*
     * Solution 3: Optimal Solution
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] result = new int[len];
        int prefix = 1;
        for (int i = 0; i < len; i++) {
            result[i] = prefix;
            prefix *= nums[i];
        }
        int postfix = 1;
        for (int i = len - 1; i >= 0; i--) {
            result[i] *= postfix;
            postfix *= nums[i];
        }
        return result;
    }

    public static void main(String[] args) {
        ProductOfArrayExceptSelf solution = new ProductOfArrayExceptSelf();

        // Test Case 1: Normal array
        int[] nums1 = { 1, 2, 3, 4 };
        System.out.print("Test 1 Expected: [24, 12, 8, 6] | Actual: ");
        printArray(solution.productExceptSelf(nums1));

        // Test Case 2: Array with a zero
        int[] nums2 = { -1, 1, 0, -3, 3 };
        System.out.print("Test 2 Expected: [0, 0, 9, 0, 0] | Actual: ");
        printArray(solution.productExceptSelf(nums2));

        // Test Case 3: Array with multiple zeros
        int[] nums3 = { 0, 4, 0 };
        System.out.print("Test 3 Expected: [0, 0, 0] | Actual: ");
        printArray(solution.productExceptSelf(nums3));
    }

    // Helper to print arrays cleanly
    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + (i == arr.length - 1 ? "" : ", "));
        }
        System.out.println("]");
    }
}
