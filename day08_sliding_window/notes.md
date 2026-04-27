# Day 08: Sliding Window - Longest Repeating Character Replacement

## 📝 Problem Statement
You are given a string `s` and an integer `k`. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most `k` times.

**Goal:** Return the length of the longest substring containing the same letter you can get after performing the above operations.

---

## 🚫 Brute Force Approach
The naive approach would be to check every single possible substring. 
1. Use two nested loops to generate all substrings.
2. For each substring, find the most frequent character.
3. Calculate how many characters are *not* the most frequent character.
4. If that number is `<= k`, update the maximum length.

**Why it's bad:** Generating all substrings takes $O(N^2)$ time. Finding frequencies inside takes $O(N)$ time. This gives an overall $O(N^3)$ or $O(N^2)$ time complexity, which will result in Time Limit Exceeded (TLE) on FAANG interviews.

---

## 💡 Optimal Approach: Sliding Window + Frequency Map
Instead of recalculating from scratch, we use a **Sliding Window** to maintain a valid substring and an array to keep track of character frequencies.

### The Core Invariant
For any substring (window) to be valid, the number of replacements we need to make must not exceed our budget `k`.

*   **Window Size** = `end - start + 1`
*   **Most Frequent Character Count** = `maxFreq`
*   **Characters to Replace** = `Window Size - maxFreq`

**Validity Condition:** `(end - start + 1) - maxFreq <= k`

### Step-by-Step Logic
1. Expand the window by moving the `end` pointer to the right.
2. Add the current character to our frequency map and update the historical `maxFreq`.
3. If the window becomes invalid (i.e., we need more than `k` replacements), we shrink the window by moving the `start` pointer to the right and decrementing the frequency of the character left behind.
4. Keep track of the `maxLen` of any valid window we've seen.

---

## 🔍 Dry Run
Let's trace `s = "AABABBA", k = 1`

*Initialize:* `start = 0`, `maxFreq = 0`, `maxLen = 0`, `freq = [0, ..., 0]`

1.  **end = 0, char = 'A'**
    *   `freq['A'] = 1`, `maxFreq = 1`
    *   Window size: 1. `1 - 1 <= 1` (Valid).
    *   `maxLen = 1`
2.  **end = 1, char = 'A'**
    *   `freq['A'] = 2`, `maxFreq = 2`
    *   Window size: 2. `2 - 2 <= 1` (Valid).
    *   `maxLen = 2`
3.  **end = 2, char = 'B'**
    *   `freq['B'] = 1`, `maxFreq = 2`
    *   Window size: 3. `3 - 2 <= 1` (Valid). We replace 'B' with 'A'.
    *   `maxLen = 3`
4.  **end = 3, char = 'A'**
    *   `freq['A'] = 3`, `maxFreq = 3`
    *   Window size: 4. `4 - 3 <= 1` (Valid). We replace 'B' with 'A'.
    *   `maxLen = 4` *(Current Longest is "AABA")*
5.  **end = 4, char = 'B'**
    *   `freq['B'] = 2`, `maxFreq = 3`
    *   Window size: 5. `5 - 3 > 1` (**Invalid!**).
    *   *Shrink:* remove `s[start]` ('A'). `freq['A'] = 2`. `start = 1`.
6.  **end = 5, char = 'B'**
    *   `freq['B'] = 3`, `maxFreq = 3`
    *   Window size: 5 (from index 1 to 5: "ABABB"). `5 - 3 > 1` (**Invalid!**).
    *   *Shrink:* remove `s[start]` ('A'). `freq['A'] = 1`. `start = 2`.
7.  **end = 6, char = 'A'**
    *   `freq['A'] = 2`, `maxFreq = 3`
    *   Window size: 5 (from index 2 to 6: "BABBA"). `5 - 3 > 1` (**Invalid!**).
    *   *Shrink:* remove `s[start]` ('B'). `freq['B'] = 2`. `start = 3`.

**Final Result:** `maxLen = 4`.

---

## 🚦 Complexity Analysis
- **Time Complexity: $O(N)$** 
  Both the `start` and `end` pointers only move forward. In the worst case, each character is processed twice (once when added by `end`, once when removed by `start`). Therefore, time complexity is linear relative to the string length.
- **Space Complexity: $O(1)$** 
  We use an integer array `freq` of size 26 to store character frequencies. Since 26 is a constant, space complexity is strictly $O(1)$.

---

## ⚠️ Edge Cases
- **`k = 0`:** The formula becomes `window_size - maxFreq <= 0`, meaning we just look for the longest sequence of identical characters without replacements.
- **`k >= s.length()`:** We can replace every character in the string, so the answer is immediately the length of the string.
- **All Unique Characters:** Works seamlessly; `maxFreq` will be 1, and the window will stay size `k + 1`.

---

## 💎 Bonus FAANG Optimization Insight
Notice how in our code we use a `while` loop to shrink the window:
```java
while ((end - start + 1) - maxFreq > k) {
    freq[s.charAt(start) - 'A']--;
    start++;
}
```
**We can optimize this to just an `if` statement!** 
Why? Because `maxFreq` is the *historical* maximum frequency. We don't actually need to shrink the window until it's perfectly valid again. We only care about finding a window that is *larger* than our current `maxLen`. A larger window requires an even higher `maxFreq`. 

By changing it to `if`, the window size remains constant (`end` and `start` both increment by 1) until we encounter a character that boosts `maxFreq`, allowing the window to expand again. This avoids nested loops and impresses interviewers.
