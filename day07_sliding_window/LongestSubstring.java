package day07_sliding_window;

import java.util.HashSet;
import java.util.Set;

/**
 * Day 07: Longest Substring Without Repeating Characters
 * Pattern: Sliding Window (Dynamic)
 * Time Complexity: O(N)
 * Space Complexity: O(min(N, M)) where M is character set size
 */
public class LongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        // Your implementation here
        if (s == null || s.length() == 0) {
            return 0;
        }

        int left = 0;
        int right = 0;
        int maxLength = 0;
        Set<Character> charSet = new HashSet<>();

        while (right < s.length()) {
            char currentChar = s.charAt(right);

            // If the character is already in the set, shrink the window from the left until
            // the duplicate is removed
            while (charSet.contains(currentChar)) {
                charSet.remove(s.charAt(left));
                left++;
            }

            // Add the current character to the set (it's guaranteed to be unique now)
            charSet.add(currentChar);

            // Update the maximum length found so far
            maxLength = Math.max(maxLength, right - left + 1);

            // Expand the window to the right
            right++;
        }

        return maxLength;
    }

    public static void main(String[] args) {
        LongestSubstring solver = new LongestSubstring();

        String test1 = "abcabcbb"; // Expected: 3
        String test2 = "bbbbb"; // Expected: 1
        String test3 = "pwwkew"; // Expected: 3
        String test4 = ""; // Expected: 0
        String test5 = "dvdf"; // Expected: 3 (Tricky case for simple window)
        String test6 = " "; // Expected: 1

        System.out.println("Test 1 (abcabcbb): " + solver.lengthOfLongestSubstring(test1));
        System.out.println("Test 2 (bbbbb): " + solver.lengthOfLongestSubstring(test2));
        System.out.println("Test 3 (pwwkew): " + solver.lengthOfLongestSubstring(test3));
        System.out.println("Test 4 (empty): " + solver.lengthOfLongestSubstring(test4));
        System.out.println("Test 5 (dvdf): " + solver.lengthOfLongestSubstring(test5));
        System.out.println("Test 6 (space): " + solver.lengthOfLongestSubstring(test6));
    }
}
