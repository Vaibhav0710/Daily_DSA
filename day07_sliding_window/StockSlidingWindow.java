package day07_sliding_window;

/**
 * Day 07: Best Time to Buy/Sell Stock (Sliding Window Revisit)
 * Pattern: Sliding Window (Dynamic)
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */
public class StockSlidingWindow {
    public int maxProfit(int[] prices) {
        int buyPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < buyPrice) {
                buyPrice = prices[i];
            }
            maxProfit = Math.max(maxProfit, prices[i] - buyPrice);
        }
        return maxProfit;
    }

    public int maxProfitSlidingWindow(int[] prices) {
        int left = 0; // Buy Day
        int right = 1; // Sell Day
        int maxProfit = 0;

        while (right < prices.length) {
            // Is it a profitable window?
            if (prices[left] < prices[right]) {
                int profit = prices[right] - prices[left];
                maxProfit = Math.max(maxProfit, profit);
            } else {
                // If prices[right] is lower than prices[left], 
                // we found a new local minimum. Move the window start.
                left = right;
            }
            right++; // Expand the window
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        StockSlidingWindow solver = new StockSlidingWindow();

        int[] test1 = { 7, 1, 5, 3, 6, 4 }; // Expected: 5
        int[] test2 = { 7, 6, 4, 3, 1 }; // Expected: 0
        int[] test3 = { 1, 2 }; // Expected: 1

        System.out.println("--- Original Approach ---");
        System.out.println("Test 1 (7,1,5,3,6,4): " + solver.maxProfit(test1));
        System.out.println("Test 2 (7,6,4,3,1): " + solver.maxProfit(test2));
        System.out.println("Test 3 (1,2): " + solver.maxProfit(test3));

        System.out.println("\n--- Sliding Window Approach ---");
        System.out.println("Test 1 (7,1,5,3,6,4): " + solver.maxProfitSlidingWindow(test1));
        System.out.println("Test 2 (7,6,4,3,1): " + solver.maxProfitSlidingWindow(test2));
        System.out.println("Test 3 (1,2): " + solver.maxProfitSlidingWindow(test3));
    }
}
