package day05_two_pointers;

public class ContainerWithMostWater {

    // Approach: Two Pointers from outside in, greedy move on shorter side
    // Time Complexity: O(N) - Each pointer moves at most N times total
    // Space Complexity: O(1) - Only using pointer variables and maxArea
    public int maxArea(int[] height) {
        // 1. Set left = 0, right = height.length - 1
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
            // 2. Calculate area = min(height[left], height[right]) * (right - left)
            int area = Math.min(height[left], height[right]) * (right - left);
            // 3. Update maxArea if current area is larger
            maxArea = Math.max(maxArea, area);
            // 4. Move the SHORTER pointer inward (if equal, move either — both are fine)
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        // 5. Return maxArea
        return maxArea;
    }

    public static void main(String[] args) {
        ContainerWithMostWater solution = new ContainerWithMostWater();

        // Test 1: Standard case
        System.out.println("Test 1: " + solution.maxArea(new int[] { 1, 8, 6, 2, 5, 4, 8, 3, 7 }));
        // Expected: 49 (between index 1 height=8 and index 8 height=7 → min(8,7)*7 =
        // 49)

        // Test 2: Two elements only
        System.out.println("Test 2: " + solution.maxArea(new int[] { 1, 1 }));
        // Expected: 1

        // Test 3: All same heights
        System.out.println("Test 3: " + solution.maxArea(new int[] { 5, 5, 5, 5 }));
        // Expected: 15 (5 * 3 = 15, using outermost pair)

        // Test 4: Descending
        System.out.println("Test 4: " + solution.maxArea(new int[] { 5, 4, 3, 2, 1 }));
        // Expected: 6 (min(3,5)*2=6 or min(2,5)*3=6 or min(4,5)*1... actually
        // min(2,5)*3=6)

        // Test 5: Ascending
        System.out.println("Test 5: " + solution.maxArea(new int[] { 1, 2, 3, 4, 5 }));
        // Expected: 6 (min(2,5)*3=6)
    }
}
