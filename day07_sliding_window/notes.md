# Day 07: Sliding Window (Dynamic)

## 📋 General Pattern Intuition
Sliding Window is used for **contiguous** sequence problems. We use two pointers (`L` and `R`) to represent the window boundaries and "slide" the window across the data.

---

## 1️⃣ Longest Substring Without Repeating Characters

### 🔴 Brute Force
- **Approach:** Generate all possible substrings, check each one for duplicates using a Set.
- **Time Complexity:** $O(N^3)$ (Nested loops for all substrings $O(N^2) \times$ checking each $O(N)$).
- **Space Complexity:** $O(N)$ for the Set.

### 🟢 Optimal (Sliding Window)
- **Approach:** Expand `R` to add characters. If `s[R]` is a duplicate, shrink `L` until the window is valid again.
- **Time Complexity:** $O(N)$ (Each pointer moves at most $N$ times).
- **Space Complexity:** $O(min(N, M))$ where $M$ is the character set size.

### 🔍 Dry Run: `s = "abcabcbb"`
| Step | R | Char | HashSet Content | Action | Window [L, R] | MaxLen |
| :--- | :- | :--- | :--- | :--- | :--- | :--- |
| 1 | 0 | 'a' | {a} | Add | [0, 0] | 1 |
| 2 | 1 | 'b' | {a, b} | Add | [0, 1] | 2 |
| 3 | 2 | 'c' | {a, b, c} | Add | [0, 2] | 3 |
| 4 | 3 | 'a' | {b, c, a} | Duplicate 'a'! L moves to 1 | [1, 3] | 3 |
| 5 | 4 | 'b' | {c, a, b} | Duplicate 'b'! L moves to 2 | [2, 4] | 3 |
| 6 | 5 | 'c' | {a, b, c} | Duplicate 'c'! L moves to 3 | [3, 5] | 3 |
| 7 | 6 | 'b' | {c, b} | Duplicate 'b'! L moves to 5 | [5, 6] | 3 |
| 8 | 7 | 'b' | {b} | Duplicate 'b'! L moves to 7 | [7, 7] | 3 |

---

## 2️⃣ Best Time to Buy/Sell Stock

### 🔴 Brute Force
- **Approach:** Use two nested loops to calculate every possible profit pair (buy day $i$, sell day $j$ where $j > i$).
- **Time Complexity:** $O(N^2)$.
- **Space Complexity:** $O(1)$.

### 🟢 Optimal (Sliding Window)
- **Approach:** `L` is Buy day, `R` is Sell day. If `prices[R] < prices[L]`, we found a new bottom, so move `L = R`. Otherwise, calculate profit and update max.
- **Time Complexity:** $O(N)$.
- **Space Complexity:** $O(1)$.

### 🔍 Dry Run: `prices = [7, 1, 5, 3, 6, 4]`
| Step | L (Buy) | R (Sell) | Price[L] | Price[R] | Logic | MaxProfit |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| 1 | 0 | 1 | 7 | 1 | $1 < 7 \rightarrow$ **Jump!** L = R = 1 | 0 |
| 2 | 1 | 2 | 1 | 5 | $5 > 1 \rightarrow$ Profit = 4 | 4 |
| 3 | 1 | 3 | 1 | 3 | $3 > 1 \rightarrow$ Profit = 2 | 4 |
| 4 | 1 | 4 | 1 | 6 | $6 > 1 \rightarrow$ Profit = 5 | 5 |
| 5 | 1 | 5 | 1 | 4 | $4 > 1 \rightarrow$ Profit = 3 | 5 |

**Conclusion:** Buying at index 1 ($1) and selling at index 4 ($6) gives the best profit of **5**.

---

## 🚦 Common Edge Cases
1.  **Empty Inputs:** `""` or `[]` $\rightarrow$ Return 0.
2.  **Single Elements:** `"a"` or `[5]` $\rightarrow$ Handled by loop conditions.
3.  **No Profit/All Duplicates:** Handled by `max` initialization to 0.
