package day04_two_pointers;

public class ValidPalindrome {

    // Approach: Two Pointers from outside in
    // Time Complexity: O(N) - We traverse the string at most once
    // Space Complexity: O(1) - No extra strings or arrays created, strictly done
    // in-place
    public boolean isPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            // If both are valid alphanumeric characters, compare them
            if (Character.isLetterOrDigit(s.charAt(start)) && Character.isLetterOrDigit(s.charAt(end))) {
                if (Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))) {
                    return false;
                }
                start++;
                end--;
            }
            // If left is not alphanumeric, skip it
            else if (!Character.isLetterOrDigit(s.charAt(start))) {
                start++;
            }
            // If right is not alphanumeric, skip it
            else if (!Character.isLetterOrDigit(s.charAt(end))) {
                end--;
            }
        }
        return true;
    }

    // Approach: Clean string via Regex and reverse it using StringBuilder
    // Time Complexity: O(N) - Traversing string to filter and reverse
    // Space Complexity: O(N) - We create entirely new Strings and StringBuilders
    // Note: This is an elegant, readable solution for production, but in an
    // interview,
    // the string allocations mean it fails the strict O(1) space optimization!
    public boolean isPalindromeUsingBuiltInMethods(String s) {
        String cleaned = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String reversed = new StringBuilder(cleaned).reverse().toString();
        return cleaned.equals(reversed);
    }

    public static void main(String[] args) {
        ValidPalindrome solution = new ValidPalindrome();

        // Test Cases
        System.out.println("Test 1: " + solution.isPalindrome("A man, a plan, a canal: Panama")); // Expected: true
        System.out.println("Test 2: " + solution.isPalindrome("race a car")); // Expected: false
        System.out.println("Test 3: " + solution.isPalindrome(" ")); // Expected: true
        System.out.println("Test 4: " + solution.isPalindrome(".,")); // Expected: true
        System.out.println("Test 5: " + solution.isPalindrome("0P")); // Expected: false
        System.out.println("Test 1: " + solution.isPalindromeUsingBuiltInMethods("A man, a plan, a canal: Panama")); // Expected:true
        System.out.println("Test 2: " + solution.isPalindromeUsingBuiltInMethods("race a car")); // Expected: false
        System.out.println("Test 3: " + solution.isPalindromeUsingBuiltInMethods(" ")); // Expected: true
        System.out.println("Test 4: " + solution.isPalindromeUsingBuiltInMethods(".,")); // Expected: true
        System.out.println("Test 5: " + solution.isPalindromeUsingBuiltInMethods("0P")); // Expected: false
    }
}
