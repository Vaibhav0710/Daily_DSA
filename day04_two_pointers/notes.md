# Day 04: Two Pointers Pattern

## 💡 Core Pattern Insights
The **Two Pointers** pattern allows us to process data (usually Arrays or Strings) with two iterators moving in a coordinated way.

*   **Key Signal:** The array/string is **sorted** or we are evaluating symmetry (like palindromes/reversal).
*   **Time Complexity Win:** Often optimizes an `O(N^2)` nested loop approach down to `O(N)` linear time because both pointers usually traverse the structure at most once.
*   **Space Complexity Win:** Incredibly space-efficient. Instead of using a `HashMap` (`O(N)` space), Two Pointers operates strictly in `O(1)` constant space since it relies purely on mathematical indices.

---

## 💻 Problem 1: Valid Palindrome
*   **Approach:** Start `left` at 0 and `right` at `length - 1`. Move inward until they cross.
*   **Constraint Magic:** Don't clean the string and build a new one beforehand (that costs `O(N)` Memory). Instead, use inner `while` loops to dynamically skip non-alphanumeric characters.
*   **Useful Java Built-ins:**
    *   `Character.isLetterOrDigit(char c)` -> safely skips spaces/punctuation.
    *   `Character.toLowerCase(char c)` -> ensures case-insensitive matching.

---

## 💻 Problem 2: Two Sum II (Input Array Is Sorted)
*   **The HashMap Trap:** Standard Two Sum is `O(N)` time using a HashMap, but costs `O(N)` space. When the array is explicitly *sorted*, you can solve it in `O(1)` space using Two Pointers.
*   **Approach:** Pointer `start` at 0, pointer `end` at the end. Look at the `sum = numbers[start] + numbers[end]`.
    *   If `sum > target` 👉 The sum is too big. Since the array is sorted, we move the `end` pointer left (`end--`) to grab a smaller number.
    *   If `sum < target` 👉 The sum is too small. Move the `start` pointer right (`start++`) to grab a larger number.
    *   If `sum == target` 👉 Found it! Add `1` to indices if a 1-indexed return is required.
*   **Edge Cases:** Negative numbers do not break this logic because the order remains strictly sorted non-decreasingly.
