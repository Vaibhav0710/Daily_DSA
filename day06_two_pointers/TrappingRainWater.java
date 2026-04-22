public class TrappingRainWater {

    public int trap(int[] height) {
        // TODO: Implement the Two Pointers approach here.
        // Remember your tracking variables:
        // left, right, maxLeft, maxRight, and totalWater
        int maxLeft = 0;
        int maxRight = 0;
        int left = 0;
        int right = height.length - 1;
        int totalWater = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] > maxLeft) {
                    maxLeft = height[left];
                }
                totalWater += maxLeft - height[left];
                left++;
            } else {
                if (height[right] > maxRight) {
                    maxRight = height[right];
                }
                totalWater += maxRight - height[right];
                right--;
            }
        }

        return totalWater;
    }

    public static void main(String[] args) {
        TrappingRainWater solution = new TrappingRainWater();

        int[] test1 = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 }; // Expected: 6
        int[] test2 = { 4, 2, 0, 3, 2, 5 }; // Expected: 9
        int[] testEdge = { 2, 0, 2 }; // Expected: 2
        int[] testAscending = { 1, 2, 3, 4, 5 }; // Expected: 0

        System.out.println("Test 1 (Expected 6): " + solution.trap(test1));
        System.out.println("Test 2 (Expected 9): " + solution.trap(test2));
        System.out.println("Test Edge (Expected 2): " + solution.trap(testEdge));
        System.out.println("Test Ascending (Expected 0): " + solution.trap(testAscending));
    }
}
