# Day 05: Two Pointers (Advanced) — 3Sum & Container With Most Water

## 💡 Pattern Evolution: From Day 4 → Day 5

Day 4 introduced **basic Two Pointers** — find one pair in a sorted array (Two Sum II) or check symmetry (Valid Palindrome). Both were `O(N)` with a single pass.

Day 5 **levels up** the pattern in two ways:
1. **3Sum** — Stacks a loop on top of Two Sum II. Introduces **duplicate handling**.
2. **Container With Most Water** — Uses Two Pointers with a **greedy** decision instead of a sum comparison.

---

## 💻 Problem 1: 3Sum

### Problem Statement
Given an integer array `nums`, return all **unique** triplets `[nums[i], nums[j], nums[k]]` such that `i != j != k` and `nums[i] + nums[j] + nums[k] == 0`.

### 🔑 Key Signal in the Problem
> "Find **all unique** triplets that **sum** to a target"

- **"sum to a target"** → Two Pointers (just like Two Sum II from Day 4).
- **"all unique"** → We need to **sort first** and **skip duplicates**.
- **"triplets" (3 numbers)** → Fix one number, reduce to a **two-number problem**.

### 🧠 The Reduction Trick: kSum → (k-1)Sum
This is the most important insight:
```
3Sum = for each nums[i]:
           TwoSum(remaining array, target = -nums[i])
```

**Why `-nums[i]`?**  
If `nums[i] + nums[j] + nums[k] = 0`, then `nums[j] + nums[k] = 0 - nums[i] = -nums[i]`.

> ⚠️ **Watch the signs!** `0 - (-1) = +1`, NOT `-1`. Interviewers love catching sign errors.

This pattern generalizes: **4Sum** would fix one number and call 3Sum. **5Sum** would fix one and call 4Sum. And so on.

### 📋 Step-by-Step Algorithm
1. **Sort** the array → enables Two Pointer approach and duplicate skipping.
2. **Outer loop:** iterate `i` from `0` to `nums.length - 3`.
   - **Early exit:** if `nums[i] > 0`, `break` — three positive numbers can never sum to 0.
   - **Skip duplicates:** if `i > 0 && nums[i] == nums[i-1]`, `continue`.
3. **Inner Two Sum II:** set `left = i + 1`, `right = nums.length - 1`, `target = -nums[i]`.
   - If `currentSum == target` → add triplet, move both pointers, skip duplicates on both.
   - If `currentSum < target` → `left++` (need bigger number).
   - If `currentSum > target` → `right--` (need smaller number).

### 🔄 Dry Run: `[-1, 0, 1, 2, -1, -4]`

**After sorting:** `[-4, -1, -1, 0, 1, 2]`

```
i=0: nums[i]=-4, target=4
  left=1(-1), right=5(2): sum=1 < 4 → left++
  left=2(-1), right=5(2): sum=1 < 4 → left++
  left=3(0),  right=5(2): sum=2 < 4 → left++
  left=4(1),  right=5(2): sum=3 < 4 → left++
  left=5, right=5: STOP (left not < right)

i=1: nums[i]=-1, target=1
  left=2(-1), right=5(2): sum=1 == 1 ✅ → add [-1, -1, 2]
    left=3, right=4. Skip dup left? nums[3]=0 ≠ nums[2]=-1 → no skip
  left=3(0), right=4(1): sum=1 == 1 ✅ → add [-1, 0, 1]
    left=4, right=3: STOP

i=2: nums[i]=-1, SAME as nums[1]=-1 → SKIP (duplicate i)

i=3: nums[i]=0, target=0
  left=4(1), right=5(2): sum=3 > 0 → right--
  left=4, right=4: STOP

Result: [[-1, -1, 2], [-1, 0, 1]] ✅
```

### ⚠️ Duplicate Skipping — The Tricky Part

There are **three places** duplicates must be skipped:

| Where | Code | Why |
|---|---|---|
| Outer `i` | `if (i > 0 && nums[i] == nums[i-1]) continue;` | Same fixed number → same triplets |
| Inner `left` | `while (left < right && nums[left] == nums[left-1]) left++;` | After finding a match, skip same left value |
| Inner `right` | `while (left < right && nums[right] == nums[right+1]) right--;` | After finding a match, skip same right value |

> **Critical:** The inner skips happen **only after finding a valid triplet** (inside the `== target` branch), not on every iteration.

### 🚦 Complexity
- **Time:** `O(N²)` — outer loop `O(N)` × inner Two Pointer `O(N)`. This is **optimal** for 3Sum.
- **Space:** `O(1)` extra — sorting is in-place, only pointer variables used.

### 💎 Interview Tips
- An interviewer might ask: *"Can you do better than O(N²)?"* — No, this is proven optimal for 3Sum.
- Always mention the **early exit** optimization (`nums[i] > 0 → break`).
- If asked about `nums.length < 3`, your loop guard `i < nums.length - 2` handles it (loop doesn't execute), but stating it explicitly shows awareness.

---

## 💻 Problem 2: Container With Most Water

### Problem Statement
Given an array `height[]` where each element represents a vertical line's height at that index, find two lines that together with the x-axis form a container holding the **most water**.

### 🔑 Key Signal in the Problem
> "Two boundaries defining a range" + "maximize a value"

- **Two boundaries** → Two Pointers from the outside in.
- **Maximize** → Track a running `maxArea`, update with `Math.max()`.

### 🧠 The Greedy Insight: Why Move the Shorter Pointer?

The area formula is:
```
Area = min(height[left], height[right]) × (right - left)
         ↑ bottleneck height                ↑ width
```

**Key observations:**
1. **Width always shrinks** — no matter which pointer you move, `right - left` decreases by 1.
2. **Area is capped by the shorter side** — `min(left, right)` is the bottleneck.

**Why moving the TALLER pointer is useless:**
- Suppose `height[left] = 1` and `height[right] = 7`.
- The bottleneck is `left` (height 1). Area = `1 × width`.
- If you move `right` inward: width shrinks, and the min is **still** capped at 1 (or less if new right < 1). Area can only **stay same or decrease**. Guaranteed waste.

**Why moving the SHORTER pointer might help:**
- If you move `left` inward: width shrinks by 1, BUT the new `height[left]` might be much taller (e.g., 8). New min could jump from 1 to 7. Area **might increase**.
- It's a **gamble that can pay off**. Moving the tall side is a **gamble that can never pay off**.

> **One-liner to remember:** *"Moving the tall pointer can never increase the min height — the short side still caps the area. Only moving the short pointer has a chance of improvement."*

### 📋 Step-by-Step Algorithm
1. Set `left = 0`, `right = height.length - 1`, `maxArea = 0`.
2. While `left < right`:
   - Calculate `area = min(height[left], height[right]) × (right - left)`.
   - Update `maxArea = Math.max(maxArea, area)`.
   - Move the **shorter** pointer inward. If equal, move either (both are fine).
3. Return `maxArea`.

### 🔄 Dry Run: `[1, 8, 6, 2, 5, 4, 8, 3, 7]`

```
L=0(1), R=8(7): area = min(1,7)×8 = 8.   maxArea=8.   Move L (1 < 7)
L=1(8), R=8(7): area = min(8,7)×7 = 49.  maxArea=49.  Move R (7 < 8)
L=1(8), R=7(3): area = min(8,3)×6 = 18.  maxArea=49.  Move R (3 < 8)
L=1(8), R=6(8): area = min(8,8)×5 = 40.  maxArea=49.  Move R (equal)
L=1(8), R=5(4): area = min(8,4)×4 = 16.  maxArea=49.  Move R (4 < 8)
L=1(8), R=4(5): area = min(8,5)×3 = 15.  maxArea=49.  Move R (5 < 8)
L=1(8), R=3(2): area = min(8,2)×2 = 4.   maxArea=49.  Move R (2 < 8)
L=1(8), R=2(6): area = min(8,6)×1 = 6.   maxArea=49.  Move R (6 < 8)
L=1, R=1: STOP

Answer: 49 ✅ (lines at index 1 and 8, heights 8 and 7)
```

### 🚦 Complexity
- **Time:** `O(N)` — each pointer moves at most `N` times, single pass.
- **Space:** `O(1)` — only three variables (`left`, `right`, `maxArea`).

### 💎 Interview Tips
- **Don't confuse with Trapping Rain Water** (Day 6). Container asks for max area between TWO lines. Trapping asks for total water between ALL bars.
- Use `Math.max(maxArea, area)` instead of `if (area > maxArea)` — cleaner, more idiomatic Java.
- If heights are equal, moving either pointer is correct — explain why (the proof works symmetrically if `height[left] == height[right]`).

### ⚠️ Edge Cases
| Case | Input | Result | Notes |
|---|---|---|---|
| Minimum valid | `[1, 1]` | `1` | Only one pair possible |
| All same height | `[5, 5, 5, 5]` | `15` | Outermost pair wins (max width) |
| Descending | `[5, 4, 3, 2, 1]` | `6` | `min(2,5)×3 = 6` |
| Ascending | `[1, 2, 3, 4, 5]` | `6` | `min(2,5)×3 = 6` |

---

## 🧠 Day 5 Summary: Two Pointers Pattern Map

| Variant | Example Problem | Decision Logic | Complexity |
|---|---|---|---|
| **Sum-based** | Two Sum II | `sum > target → right--`, `sum < target → left++` | O(N) |
| **Symmetry** | Valid Palindrome | Compare `left` and `right`, move inward | O(N) |
| **Layered (kSum)** | 3Sum | Fix one, run Two Sum II inside | O(N²) |
| **Greedy (maximize)** | Container With Most Water | Move the shorter pointer (bottleneck removal) | O(N) |

### 🔮 Tomorrow: Day 6 — Trapping Rain Water
The hardest Two Pointers problem. Instead of finding max area between two lines, you compute **total trapped water across all bars**. Builds directly on today's Container insight — know the greedy pointer movement cold!
