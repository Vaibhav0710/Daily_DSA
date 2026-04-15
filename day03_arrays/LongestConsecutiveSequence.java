import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {
        // Pattern: HashSet for O(1) membership checks
        // Strategy: Find sequence STARTS, then extend forward
        
        // Step 1: Add all elements to a HashSet (also deduplicates automatically)
        Set<Integer> st = new HashSet<>();
        for (int num : nums) {
            st.add(num);
        }
        
        int maxLen = 0;
        
        // Step 2: Iterate over the set (not the array — avoids processing duplicates)
        for (int num : st) {
            // KEY INSIGHT: Only start counting if 'num' is the START of a sequence.
            // A number is a sequence start if (num - 1) does NOT exist in the set.
            // This prevents redundant work — we never re-count the middle of a sequence.
            if (!st.contains(num - 1)) {
                int currentNum = num;
                int currentLen = 1;
                
                // Step 3: Extend the sequence forward as far as possible
                while (st.contains(currentNum + 1)) {
                    currentNum++;
                    currentLen++;
                }
                
                // Step 4: Update the global maximum
                maxLen = Math.max(maxLen, currentLen);
            }
        }
        return maxLen;
        
        /*
         * WHY IS THIS O(N) AND NOT O(N^2)?
         * The while loop looks scary inside a for loop, but each element is visited
         * at most TWICE total: once in the for loop, and once when extended from a
         * sequence start. Elements in the middle of a sequence are skipped by the
         * if-check, so the while loop only runs for sequence-start elements.
         * Total work across all iterations = O(N).
         * 
         * ALTERNATIVE: Sorting approach — O(N log N) Time, O(1) Space
         * Sort the array, then walk through it tracking consecutive runs.
         * Simpler to implement but doesn't meet the O(N) requirement.
         */
    }

    public static void main(String[] args) {
        LongestConsecutiveSequence solution = new LongestConsecutiveSequence();

        int[] nums1 = { 100, 4, 200, 1, 3, 2 };
        System.out.println("Test 1 (expect 4): " + solution.longestConsecutive(nums1));

        int[] nums2 = { 0, 3, 7, 2, 5, 8, 4, 6, 0, 1 };
        System.out.println("Test 2 (expect 9): " + solution.longestConsecutive(nums2));

        int[] nums3 = {}; // Edge case: Empty array
        System.out.println("Test 3 (expect 0): " + solution.longestConsecutive(nums3));
    }
}
