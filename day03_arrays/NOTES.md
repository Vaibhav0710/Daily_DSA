# 🧠 Day 03: Arrays — HashSet for Uniqueness & Membership

## Problem 1: Valid Sudoku
**Pattern:** HashSet Uniqueness Tracking (Multi-Dimensional)
**Target:** `O(81)` = `O(1)` Time | `O(81)` = `O(1)` Space

### 💡 Intuition
We need to check three constraints simultaneously: no duplicates in any row, column, or 3×3 box. Instead of scanning each constraint separately (which would require 3 separate passes), we use **27 HashSets** — 9 for rows, 9 for columns, 9 for boxes — and validate everything in a **single pass**.

### 🧠 Key Trick: Box Index Formula
Given cell `(i, j)`:
- `i / 3` → box row (0, 1, or 2)
- `j / 3` → box column (0, 1, or 2)
- **Box index = `(i / 3) * 3 + (j / 3)`** → flattens to 0–8

This is just the standard 2D-to-1D index conversion formula applied to a 3×3 grid of boxes.

### 🚦 Edge Cases
- **Skip `'.'` cells** — they represent empty spots. If you don't skip, you'll get false duplicates.
- **Char-to-Int conversion** — `board[i][j] - '0'` converts the character to its numeric value.
- Board is always 9×9 so time/space is effectively constant.

### 🔄 Alternative Approach: Boolean Arrays (Faster, Less Memory)
Instead of `HashSet` objects, use raw `boolean[][]` arrays:
```java
boolean[][] rows  = new boolean[9][9];  // rows[i][num-1] = seen
boolean[][] cols  = new boolean[9][9];
boolean[][] boxes = new boolean[9][9];
// Check: if (rows[i][num]) return false; else rows[i][num] = true;
```
- **Pros:** No object overhead, no hashing, cache-friendly.
- **Cons:** Only works when value domain is small and fixed (1–9 in this case).

---

## Problem 2: Longest Consecutive Sequence
**Pattern:** HashSet Membership Check (Sequence Detection)
**Target:** `O(N)` Time | `O(N)` Space

### 💡 Intuition
Sorting would make this trivial (`O(N log N)`) but we need `O(N)`. The trick: if we dump everything into a HashSet, we get `O(1)` lookups. Then we only need to identify **sequence start points** and extend forward.

**A number is a sequence START if `(num - 1)` does NOT exist in the set.**

If `num - 1` exists, then `num` is in the middle of some sequence — skip it and let the earlier start point handle the counting.

### 🧠 Why O(N) and not O(N²)?
The `while` loop inside the `for` loop looks like `O(N²)`, but it's actually amortized `O(N)`:
- Each element is visited **at most twice** total: once in the outer `for` loop, and once when being extended from a sequence start.
- Middle-of-sequence elements are **skipped** by the `if (!set.contains(num - 1))` guard.
- Total work across ALL iterations = `O(N)`.

### 🚦 Edge Cases
- **Empty array → 0** (no elements to form a sequence).
- **Duplicates:** `[1, 1, 2, 3]` → HashSet deduplicates automatically → answer is `3`, not `4`.
- **All same numbers:** `[7, 7, 7]` → sequence length is `1`.
- **Negative numbers:** `[-3, -2, -1, 0]` → works perfectly, answer is `4`.

### 🔄 Alternative Approach: Sorting — O(N log N) Time, O(1) Space
```java
Arrays.sort(nums);
int maxLen = 1, currentLen = 1;
for (int i = 1; i < nums.length; i++) {
    if (nums[i] == nums[i-1]) continue;       // skip duplicates
    if (nums[i] == nums[i-1] + 1) currentLen++;
    else currentLen = 1;
    maxLen = Math.max(maxLen, currentLen);
}
```
- **Pros:** No extra space, simpler logic.
- **Cons:** Doesn't meet the `O(N)` constraint. Use only if interviewer allows `O(N log N)`.

---

## 📌 Day 03 Summary
| Concept | Signal | Data Structure |
|---|---|---|
| Uniqueness across multiple dimensions | "no duplicates in row/col/box" | HashSet per dimension |
| Sequence detection without sorting | "O(N) consecutive sequence" | HashSet + start-point detection |

**Meta-Pattern:** When you need `O(1)` "have I seen this?" checks → **HashSet**.
