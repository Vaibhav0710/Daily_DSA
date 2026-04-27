package day08_sliding_window;

public class CharacterReplacement {

    public int characterReplacement(String s, int k) {
        int start = 0;
        int[] freq = new int[26];
        int maxFreq = 0;
        int maxLen = 0;

        for (int end = 0; end < s.length(); end++) {
            char ch = s.charAt(end);

            freq[ch - 'A']++;
            maxFreq = Math.max(maxFreq, freq[ch - 'A']);

            while ((end - start + 1) - maxFreq > k) {
                freq[s.charAt(start) - 'A']--;
                start++;
            }
            maxLen = Math.max(maxLen, end - start + 1);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        CharacterReplacement solution = new CharacterReplacement();

        // Test Case 1: k=2. We can replace the two 'A's with 'B's or vice versa.
        System.out.println(solution.characterReplacement("ABAB", 2)); // Expected: 4

        // Test Case 2: k=1. Replace the one 'A' in the middle.
        System.out.println(solution.characterReplacement("AABABBA", 1)); // Expected: 4
    }
}
