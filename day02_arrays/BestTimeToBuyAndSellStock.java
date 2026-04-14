public class BestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {
        // TODO: Implement your O(N) Time and O(1) Space logic here
        // Remember to handle the tricky edge cases we discussed!
        if (prices.length < 2) {
            return 0;
        }
        int minPrice = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        BestTimeToBuyAndSellStock solution = new BestTimeToBuyAndSellStock();

        // Test Case 1: Normal variation where profit is possible
        int[] prices1 = { 7, 1, 5, 3, 6, 4 };
        System.out.println("Test 1 Expected: 5 | Actual: " + solution.maxProfit(prices1));

        // Test Case 2: Array where price only drops
        int[] prices2 = { 7, 6, 4, 3, 1 };
        System.out.println("Test 2 Expected: 0 | Actual: " + solution.maxProfit(prices2));

        // Test Case 3: Edge case - empty array
        int[] prices3 = {};
        System.out.println("Test 3 Expected: 0 | Actual: " + solution.maxProfit(prices3));
    }
}
