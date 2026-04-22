# 📚 Algorithm Pattern Deep Dive: Arrays & Two Pointers

This document provides a highly detailed, interview-ready breakdown for 7 essential patterns. It mimics the FAANG-prep format by providing step-by-step algorithms, deep dive intuitions, and complete dry runs.

---

## 💻 1. Best Time to Buy and Sell Stock
*(Pattern: Sliding Window / One-Pass Min Tracking)*  
▶️ **Video Reference:** [NeetCode - Best Time to Buy and Sell Stock](https://www.youtube.com/watch?v=1pkOgXD63yU)

### 📝 Problem Statement
You are given an array `prices` where `prices[i]` is the price of a given stock on the `i`th day. You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock. Return the maximum profit you can achieve (or 0 if you cannot achieve any profit).

### 🔑 Key Signal
- **"Maximize the difference"** + **"Buy before sell"**: Whenever chronological order matters for subtraction, try to maintain a running minimum or maximum.

### 🧠 Deep Dive & Intuition
The naive approach is to check every pair of (buy, sell), which takes `O(N²)`. But look closer: for any given day you decide to *sell*, what is the absolute best day you could have *bought* prior to it? It's simply the **lowest price seen so far**. 
So, as we sweep from left to right, we simultaneously track two things:
1. The lowest price we have seen up to today.
2. The maximum profit we could make if we sold today.

### 📋 Step-by-Step Algorithm
1. Initialize `min_price` to `Integer.MAX_VALUE`.
2. Initialize `max_profit` to `0`.
3. Loop `price` through each element in `prices`:
   - **Step 3a:** Check if `price < min_price`. If yes, update `min_price = price`.
   - **Step 3b:** If no (or even if yes), calculate the potential profit: `profit = price - min_price`.
   - **Step 3c:** Update `max_profit = Math.max(max_profit, profit)`.
4. Return `max_profit`.

### 🔄 Dry Run
**Input:** `[7, 1, 5, 3, 6, 4]`
- **Day 0 (7):** `min_price` becomes **7**. `profit` = 7 - 7 = 0. `max_profit` = **0**.
- **Day 1 (1):** `price < min_price`, so `min_price` becomes **1**. `profit` = 1 - 1 = 0. `max_profit` = **0**.
- **Day 2 (5):** `min_price` remains 1. `profit` = 5 - 1 = 4. `max_profit` becomes **4**.
- **Day 3 (3):** `min_price` remains 1. `profit` = 3 - 1 = 2. `max_profit` remains **4**.
- **Day 4 (6):** `min_price` remains 1. `profit` = 6 - 1 = 5. `max_profit` becomes **5**.
- **Day 5 (4):** `min_price` remains 1. `profit` = 4 - 1 = 3. `max_profit` remains **5**.
**Result:** 5

### 🚦 Edge Cases & Complexity Targets
- **Time Complexity:** `O(N)` — Single pass.
- **Space Complexity:** `O(1)` — Only tracking two primitive variables.
- **Edge cases:** Array is strictly decreasing `[7, 6, 4, 3, 1]` (Code will naturally limit profit to 0). Array size is 1 or empty.

---

## 💻 2. Product of Array Except Self
*(Pattern: Prefix & Suffix Arrays / Two Passes)*  
▶️ **Video Reference:** [NeetCode - Product of Array Except Self](https://www.youtube.com/watch?v=bNvIQI2wAjk)

### 📝 Problem Statement
Given an integer array `nums`, return an array `answer` such that `answer[i]` is equal to the product of all the elements of `nums` except `nums[i]`. The product of any prefix or suffix is guaranteed to fit in a 32-bit integer. **You must write an algorithm that runs in `O(N)` time and without using the division operation.**

### 🔑 Key Signal
- **"Product of all EXCEPT self"** AND **"Without division"**: This explicitly screams "Prefix & Suffix computation".

### 🧠 Deep Dive & Intuition
If you can't use division, the product except `nums[i]` is exactly:
`(product of everything to its LEFT) × (product of everything to its RIGHT)`.
Instead of calculating left and right products from scratch for every index (which takes `O(N²)`), we can do one full pass to accumulate all the left products, and one full pass backwards to accumulate the right products. To achieve `O(1)` space (ignoring the output array), we compute the left products directly into our answer array, and calculate the right products on the fly during our backward pass.

### 📋 Step-by-Step Algorithm
1. Create a `result` array of size `n` initialized with `1`.
2. **Left-to-Right Pass:**
   - Initialize a variable `leftProduct = 1`.
   - For `i` from `0` to `n-1`:
     - Set `result[i] = leftProduct`.
     - Multiply `leftProduct` by `nums[i]`.
3. **Right-to-Left Pass:**
   - Initialize a variable `rightProduct = 1`.
   - For `i` from `n-1` down to `0`:
     - Multiply `result[i]` by `rightProduct` (so it now has both left and right).
     - Multiply `rightProduct` by `nums[i]`.
4. Return `result`.

### 🔄 Dry Run
**Input:** `[1, 2, 3, 4]`

**Left Pass:** (`leftProduct` running total)
- `i=0`: `result[0] = 1`, `leftProduct` = 1 * 1 = 1
- `i=1`: `result[1] = 1`, `leftProduct` = 1 * 2 = 2
- `i=2`: `result[2] = 2`, `leftProduct` = 2 * 3 = 6
- `i=3`: `result[3] = 6`, `leftProduct` = 6 * 4 = 24
- `result` array currently: `[1, 1, 2, 6]`

**Right Pass:** (`rightProduct` running total)
- `i=3`: `result[3] = 6 * 1` = 6. `rightProduct` = 1 * 4 = 4
- `i=2`: `result[2] = 2 * 4` = 8. `rightProduct` = 4 * 3 = 12
- `i=1`: `result[1] = 1 * 12` = 12. `rightProduct` = 12 * 2 = 24
- `i=0`: `result[0] = 1 * 24` = 24. `rightProduct` = 24 * 1 = 24
- Final `result`: `[24, 12, 8, 6]`

### 🚦 Edge Cases & Complexity Targets
- **Time Complexity:** `O(N)` — Two sequential passes.
- **Space Complexity:** `O(1)` — Outside of the returned result array.
- **Edge cases:** Array containing zeros `[1, 2, 0, 4]` (Result has zeros everywhere except at index 2). Array with multiple zeros (Result is all zeros).

---

## 💻 3. 3Sum
*(Pattern: Sorting + Two Pointers)*  
▶️ **Video Reference:** [NeetCode - 3Sum](https://www.youtube.com/watch?v=jzZsGME8Ifk)

### 📝 Problem Statement
Given an integer array `nums`, return all the triplets `[nums[i], nums[j], nums[k]]` such that `i != j != k`, and `nums[i] + nums[j] + nums[k] == 0`. Notice that the solution set must not contain duplicate triplets.

### 🔑 Key Signal
- **"Find triplets that sum to X"** + **"Without duplicates"**: Sorting is mandatory to avoid duplicates effectively, and sorting immediately enables the Two Pointers approach on the remaining subarray.

### 🧠 Deep Dive & Intuition
Finding 3 elements that sum to `0` means finding elements where `nums[i] + nums[j] + nums[k] = 0`.
If we fix `nums[i]`, the problem reduces to a 2Sum problem: `nums[j] + nums[k] = -nums[i]`. By sorting the array first, we can iterate through the array to lock in `nums[i]`, and then use Two Pointers (`left` and `right`) to find the remaining two numbers.

### 📋 Step-by-Step Algorithm
1. **Sort** the input array `nums`. Important to skip duplicates.
2. Iterate an outer pointer `i` from `0` up to `n - 3`.
3. **Crucial Optimization:** If `nums[i] > 0`, break. Since the array is sorted, all subsequent numbers are also positive and can never sum to zero.
4. **Duplicate Skip (Outer):** If `i > 0` and `nums[i] == nums[i-1]`, `continue`.
5. For each `i`, set `left = i + 1` and `right = n - 1`.
6. Enter a `while (left < right)` loop:
   - Calculate `sum = nums[i] + nums[left] + nums[right]`.
   - If `sum == 0`, awesome! Add the triplet to results. 
   - Move both pointers: `left++`, `right--`.
   - **Duplicate Skip (Inner):** Advance `left` while `left < right` AND `nums[left] == nums[left - 1]` to avoid logging identical triplets.
   - If `sum < 0`, the sum is too small, so move `left++` to get a bigger number.
   - If `sum > 0`, the sum is too big, so move `right--` to get a smaller number.

### 🔄 Dry Run
**Input:** `[-1, 0, 1, 2, -1, -4]`
**Sorted:** `[-4, -1, -1, 0, 1, 2]`

- `i=0` (nums[i] = -4): `left`=1(-1), `right`=5(2). Sum = -4 - 1 + 2 = -3 < 0. `left++`. Eventually no pairs sum to 4.
- `i=1` (nums[i] = -1): `left`=2(-1), `right`=5(2). Sum = -1 - 1 + 2 = 0. **Match! Found `[-1, -1, 2]`**.
  - Move `left++`, `right--`. Now `left`=3(0), `right`=4(1).
  - Check duplicates: `nums[left](0) != nums[left-1](-1)`. Safe.
  - Sum = -1 + 0 + 1 = 0. **Match! Found `[-1, 0, 1]`**.
- `i=2` (nums[i] = -1): Loop sees `nums[2] == nums[1]`. **SKIP**.
- `i=3` (nums[i] = 0): `left`=4(1), `right`=5(2). Sum = 3. `right--`. Fails out.
**Result:** `[[-1, -1, 2], [-1, 0, 1]]`

### 🚦 Edge Cases & Complexity Targets
- **Time Complexity:** `O(N²)` — `O(N log N)` for sorting + `O(N)` loop with `O(N)` inner two-pointers.
- **Space Complexity:** `O(1)` or `O(N)` depending on sorting implementation.
- **Edge cases:** Input of all zeroes `[0, 0, 0, 0]`. Heavy duplicates `[-2, -2, -2, 1, 1, 1]`. Missing elements (array length < 3).

---

## 💻 4. Container With Most Water
*(Pattern: Two Pointers / Greedy)*  
▶️ **Video Reference:** [NeetCode - Container With Most Water](https://www.youtube.com/watch?v=UuiTKBwzMA4)

### 📝 Problem Statement
You are given an integer array `height` of length `n`. There are `n` vertical lines drawn such that the two endpoints of the `i`th line are `(i, 0)` and `(i, height[i])`. Find two lines that together with the x-axis form a container, such that the container contains the most water.

### 🔑 Key Signal
- **"Maximize bounding area defined by two ends"**: This instantly suggests Two Pointers starting from the absolute widest bounds.

### 🧠 Deep Dive & Intuition
The formula for area is: `Area = width × min(height_left, height_right)`.
If you start with maximum width (pointers at indices 0 and n-1), the only way to possibly get a *larger* area is to find a *taller* line that makes up for the loss of width. 
Moving the pointer that points to the taller line is pointless—the area is bottlenecked by the shortest line, so moving the tall line will only decrease width without improving the height limit. Therefore, greedily **always move the smaller pointer inwards**.

### 📋 Step-by-Step Algorithm
1. Initialize `left = 0`, `right = height.length - 1`, and `max_area = 0`.
2. While `left < right`:
   - Calculate `width = right - left`.
   - Calculate `current_area = width * Math.min(height[left], height[right])`.
   - Update `max_area = Math.max(max_area, current_area)`.
   - **Greedy decision:** Move the pointer with the smaller height.
     - IF `height[left] < height[right]`, then `left++`.
     - ELSE `right--`.
3. Return `max_area`.

### 🔄 Dry Run
**Input:** `[1, 8, 6, 2, 5, 4, 8, 3, 7]`

- **Step 1:** L=0(H=1), R=8(H=7). Width=8. Area = 8 * min(1, 7) = **8**. `max_area=8`. L is shorter, `L++`
- **Step 2:** L=1(H=8), R=8(H=7). Width=7. Area = 7 * min(8, 7) = **49**. `max_area=49`. R is shorter, `R--`
- **Step 3:** L=1(H=8), R=7(H=3). Width=6. Area = 6 * min(8, 3) = **18**. `max_area=49`. R is shorter, `R--`
- (continues narrowing until L=R, but max_area peaked at 49)

### 🚦 Edge Cases & Complexity Targets
- **Time Complexity:** `O(N)` — Single pass bringing pointers together.
- **Space Complexity:** `O(1)` — Only constant pointers used.
- **Edge cases:** Heights are all identical `[5, 5, 5, 5]` (Width will win). Only two elements.

---

## 💻 5. Valid Sudoku
*(Pattern: Arrays & Hash Sets)*  
▶️ **Video Reference:** [NeetCode - Valid Sudoku](https://www.youtube.com/watch?v=TlQC0NcV-M4)

### 📝 Problem Statement
Determine if a `9 x 9` Sudoku board is valid. Only the filled cells need to be validated according to:
1. Each row must contain digits `1-9` without repetition.
2. Each column must contain digits `1-9` without repetition.
3. Each of the nine `3 x 3` sub-boxes must contain digits `1-9` without repetition.

### 🔑 Key Signal
- **"Without repetition in rows/cols/boxes"**: Tracking repetitions maps flawlessly to `HashSets` or `boolean arrays`.

### 🧠 Deep Dive & Intuition
Rows and columns are easy—we just need a HashSet for each index `0..8`.
The magic of the algorithm lies in managing the 3x3 grids. We can map any `(r, c)` grid location to an index `0..8` using the formula:
`box_index = (r / 3) * 3 + (c / 3)`.
For example, row 4, col 5 (the middle of the board) maps to `(4/3)*3 + 5/3 = 1*3 + 1 = 4` (box 4, the exact middle). Once mapped, iterate through the whole 2D grid once, inserting values into the appropriate row, col, and box HashSets.

### 📋 Step-by-Step Algorithm
1. Initialize an array of `HashSet` (or boolean `[9][9]` matrix) for `rows`, `cols`, and `boxes`.
2. Loop over `r` from 0 to 8:
   - Loop over `c` from 0 to 8:
     - If `board[r][c] == '.'`, skip.
     - Get `val = board[r][c]`.
     - Calculate `boxIndex = (r / 3) * 3 + (c / 3)`.
     - If `val` is already contained in `rows[r]`, `cols[c]`, or `boxes[boxIndex]`, the board is invalid. Return `false`.
     - Otherwise, `.add(val)` to `rows[r]`, `cols[c]`, and `boxes[boxIndex]`.
3. If loops complete successfully, return `true`.

### 🔄 Dry Run (Mental Exercise)
- Check `board[0][0] = '5'`. Add '5' to `rows[0]`, `cols[0]`, and `boxes[0]`.
- Later, say we look at `board[2][2] = '5'`.
- First, the row is 2, col is 2. `rows[2]` and `cols[2]` don't have '5'.
- Calculate box index: `(2/3)*3 + (2/3) = 0*3 + 0 = 0`.
- Attempt to check `boxes[0]`. It contains '5'!
- Board is invalid. Return `false`.

### 🚦 Edge Cases & Complexity Targets
- **Time Complexity:** `O(1)` (or `O(81)` -> Constant).
- **Space Complexity:** `O(1)` (Bounded at maximum 81 constants).
- **Edge cases:** Sparse boards, multiple empty cells. Remember, it doesn't have to be *solvable*, just valid in its current state.

---

## 💻 6. Longest Consecutive Sequence
*(Pattern: Hashing / Sequence Building)*  
▶️ **Video Reference:** [NeetCode - Longest Consecutive Sequence](https://www.youtube.com/watch?v=P6RZZMu_maU)

### 📝 Problem Statement
Given an unsorted array of integers `nums`, return the length of the longest consecutive elements sequence. You must write an algorithm that runs in `O(N)` time.

### 🔑 Key Signal
- **"Unsorted array"** + **"Longest sequence"** + **"O(N) runtime limit"**: Because `O(N log N)` sorting is forbidden, you MUST rely on hashing `O(1)` lookups.

### 🧠 Deep Dive & Intuition
Throw all the numbers into a HashSet so checking constraints becomes `O(1)`.
The problem asks for consecutive sequences. Instead of checking every number and counting up, which could overlap and become `O(N²)`, recognize a simple truth: **A sequence *only* truly begins if the number right below it doesn't exist**.
If we see `100` and `99` isn't in the set, we know `100` is the start. We only run our `while` loop logic on starting numbers. This prevents overlapping labor.

### 📋 Step-by-Step Algorithm
1. Initialize a `HashSet` and add all `nums` to it.
2. Initialize `longestStreak = 0`.
3. Loop through `num` in the set:
   - **Start of sequence check:** If `!set.contains(num - 1)`:
     - We found a root. Set `currentNum = num` and `currentStreak = 1`.
     - **Inner Loop:** While `set.contains(currentNum + 1)`:
       - Increment `currentNum++` and `currentStreak++`.
     - Update `longestStreak = Math.max(longestStreak, currentStreak)`.
4. Return `longestStreak`.

### 🔄 Dry Run
**Input:** `[100, 4, 200, 1, 3, 2]`
- Push all into `HashSet`.
- Inspect `100`. Is `99` in set? No. Start tracking. Is `101` in set? No. Streak = 1.
- Inspect `4`. Is `3` in set? Yes. **Skip**. (It's part of a chain, let's start at the true root).
- Inspect `200`. Is `199` in set? No. Start tracking. Streak = 1.
- Inspect `1`. Is `0` in set? No. Start tracking root `1`.
  - Check `2`. Present. (`currentStreak` = 2)
  - Check `3`. Present. (`currentStreak` = 3)
  - Check `4`. Present. (`currentStreak` = 4)
  - Check `5`. Not Present.
  - Final streak for this branch = 4. `longestStreak=4`.
- Inspect `3`. `2` is present, Skip.
- Inspect `2`. `1` is present, Skip.
**Result:** 4

### 🚦 Edge Cases & Complexity Targets
- **Time Complexity:** `O(N)` — The outer loop explores N elements. The inner loop *only runs* for true sequence starts, meaning throughout the entire algorithm, the inner loop processes elements at most `N` times total. Thus `O(2N) = O(N)`.
- **Space Complexity:** `O(N)` — Elements tracked in the HashSet.
- **Edge cases:** Array with duplicates `[1, 2, 0, 1]`. Negative values. Empty array.

---

## 💻 7. Trapping Rain Water
*(Pattern: Two Pointers / Pre-computation)*  
▶️ **Video Reference:** [NeetCode - Trapping Rain Water](https://www.youtube.com/watch?v=ZI2z5pq0TqA)

### 📝 Problem Statement
Given `n` non-negative integers representing an elevation map where the width of each bar is `1`, compute how much water it can trap after raining.

### 🔑 Key Signal
- **"Computing trapped capacity"**: For any random index `i`, trapped water depends on the highest boundary to its left and highest boundary to its right.

### 🧠 Deep Dive & Intuition
The raw equation for water at column `i` is:
`Water = Math.min(highest_bar_left, highest_bar_right) - height[i]`.
If calculating `maxLeft` and `maxRight` for every spot is slow `O(N²)`, we can use Two Pointers starting from edges. Because water level is capped by the *minimum* of the two highest bars mapping the edges, if we know `maxLeft < maxRight`, the exact height of `maxRight` is irrelevant! The `maxLeft` acts as the bottleneck ceiling.
We can safely calculate trapped water from the left pointer side and inch left forward.

### 📋 Step-by-Step Algorithm
1. Initialize edge cases: If `height.length == 0` return 0.
2. Initialize pointers: `left = 0`, `right = n - 1`.
3. Track maxes: `maxLeft = height[left]`, `maxRight = height[right]`.
4. Initialize `totalWater = 0`.
5. Enter a `while` loop until `left >= right`:
   - Compare boundaries: If `maxLeft < maxRight`:
     - Calculate bottleneck at left. `left++`.
     - Update new maximum: `maxLeft = Math.max(maxLeft, height[left])`.
     - Water trapped here = `maxLeft - height[left]`. Add to `totalWater`.
   - Else (if `maxRight <= maxLeft`):
     - Calculate bottleneck at right. `right--`.
     - Update new maximum: `maxRight = Math.max(maxRight, height[right])`.
     - Water trapped here = `maxRight - height[right]`. Add to `totalWater`.
6. Return `totalWater`.

### 🔄 Dry Run
**Input:** `[0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]`

- Start: L=0, R=11. `maxLeft`=0, `maxRight`=1.
- `maxLeft < maxRight` (0 < 1). `L++` (L=1). `maxLeft` = max(0, 1) = 1. Water = 1 - 1 = 0.
- `maxLeft == maxRight` (1 == 1). `R--` (R=10). `maxRight` = max(1, 2) = 2. Water = 2 - 2 = 0.
- `maxLeft < maxRight` (1 < 2). `L++` (L=2). `maxLeft` = max(1, 0) = 1. Water = 1 - 0 = **1**. (First puddle!)
- `maxLeft < maxRight` (1 < 2). `L++` (L=3). `maxLeft` = max(1, 2) = 2. Water = 2 - 2 = 0.
- `maxLeft == maxRight` (2 == 2). `R--` (R=9). `maxRight` = max(2, 1) = 2. Water = 2 - 1 = **1**.
- ... continues, pinching towards peak of height 3.

### 🚦 Edge Cases & Complexity Targets
- **Time Complexity:** `O(N)` — Single pass converging to the middle.
- **Space Complexity:** `O(1)` — Only pointer tracking variables used.
- **Edge cases:** Continually ascending/descending stairs (0 water). Single dip `[2, 0, 2]` (Traps 2).

---

> **Coach's Note:** Memorizing solutions is fragile. Memorizing the **Key Signals** and the **Step-by-Step Dry Run** is how you consistently crush these during technical screening rounds. Review these daily.
