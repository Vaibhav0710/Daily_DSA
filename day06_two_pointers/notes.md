# Day 06: Trapping Rain Water
**Pattern:** Two Pointers / Pre-computation
**Time Complexity:** O(N)
**Space Complexity:** O(1)

## 💡 Intuition
The amount of water trapped above any single bar is determined by two things:
1. The **highest bar to its left**.
2. The **highest bar to its right**. 

The water level at index `i` is the minimum of those two highest bounds, minus the height of the bar at index `i` itself:
`Water[i] = Math.min(maxLeft, maxRight) - height[i]`

If we know that `maxLeft < maxRight`, the exact height of `maxRight` doesn't even matter! The water is guaranteed to be capped by `maxLeft`. This allows us to use a Two Pointer approach starting from both ends, pinching inwards.

## 🧩 Strategy
1. Initialize `left = 0`, `right = height.length - 1`.
2. Maintain running maximums: `maxLeft` and `maxRight`.
3. Loop while `left < right`:
   - If `height[left] < height[right]`:
     - Bottleneck is on the left.
     - Update `maxLeft`.
     - Add `maxLeft - height[left]` to total water.
     - `left++`
   - Else:
     - Bottleneck is on the right.
     - Update `maxRight`.
     - Add `maxRight - height[right]` to total water.
     - `right--`

## 🚦 Edge Cases
- **Empty or Small Arrays:** Arrays with length < 3 cannot trap any water.
- **Strictly Increasing / Decreasing:** Stairs trap 0 water.
- **Single Dip:** e.g., `[2, 0, 2]` efficiently calculates `2`.
