# 🧠 Day 02: Arrays - State Tracking & Precomputation

## Problem 1: Best Time to Buy and Sell Stock
**Pattern:** Array State Tracking (Dynamic Sliding Window / 1-Pass)
**Target:** `O(N)` Time | `O(1)` Space

### 💡 Intuition
Instead of using a nested loop (`O(N^2)`) to check every buy/sell combination, we only care about the **best state so far**. As you traverse left-to-right:
1. **Track the historically lowest price:** `minPrice = Math.min(minPrice, prices[i])`
2. **Track the historically highest profit:** `maxProfit = Math.max(maxProfit, prices[i] - minPrice)`

### 🚦 Tricky Edge Cases
- Arrays with `< 2` elements cannot produce a profit (guard clause!).
- Prices that purely go down (e.g., `[7, 6, 4, 3, 1]`) will safely return `0` if `maxProfit` is initialized to `0`.

---

## Problem 2: Product of Array Except Self
**Pattern:** Precomputation (Prefix / Suffix Products)
**Target:** `O(N)` Time | `O(1)` Extra Space (output array doesn't count)
**Strict Constraint:** Cannot use division (`/`)

### 💡 Intuition
Given the restriction on division, the only way to find the product of everything *except* `nums[i]` is to use the formula:
`(Product of all left elements) × (Product of all right elements)`

### 🧠 The Evolution of the Solution
1. **O(N) Space (The Base Strategy):** Create a `prefix[]` array and a `suffix[]` array using two separate non-nested loops. Then combine them in a third loop `result[i] = prefix[i] * suffix[i]`.
2. **O(1) Extra Space (The FAANG Strategy):**
   - **Pass 1 (Left to Right):** Maintain a `prefix` variable, accumulating it and storing it *directly* into `result[i]`.
   - **Pass 2 (Right to Left):** Maintain a `postfix` variable, looping backward. Multiply `result[i]` by `postfix`, then update `postfix *= nums[i]`.

### 🚦 Tricky Edge Cases
- Without division, **Zeroes** won't throw divide-by-zero exceptions, and their mathematical properties (`anything * 0 = 0`) perfectly wipe out combinations where they belong! 
