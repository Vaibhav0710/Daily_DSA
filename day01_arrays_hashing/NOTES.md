# Day 1: Arrays & Hashing

## Core Pattern: Hashing (HashMaps & HashSets)
- **Use Case:** Frequent lookups, finding "matches" (like a target sum), or finding duplicates.
- **Why?** Finding an element in an array with a `for` loop takes **O(N)** time. Looking up an element in a Hash structure takes **O(1)** time.
- **Trade-off:** We use extra space — **O(N)** space — to store the array elements in memory in exchange for lightning-fast search speed.

---

## Problem 1: Two Sum (LeetCode 1)

### Approach 1: Two Pointers (The Trap)
We often want to use the Two Pointers technique on arrays (one at the start, one at the end).
- **The catch:** Two Pointers only works if the array is **sorted**!
- In Two Sum, the array is not sorted. Furthermore, if we sort it first, we lose the original indices that the problem asks us to return. 

### Approach 2: One-Pass HashMap (Optimal)
As we iterate through the array, we can use a `HashMap<Integer, Integer>` where:
- **Key:** The number itself
- **Value:** The index of that number

**Algorithm:**
1. For each number, calculate its complement: `complement = target - current_number`.
2. Check if the `complement` is already in the HashMap.
3. If it is, we found our answer! Return `[map.get(complement), current_index]`.
4. If it isn't, add the `current_number` and `current_index` into the HashMap so future numbers can find it.

- **Time Complexity:** `O(N)` - We traverse the list containing $N$ elements exactly once. Each look up in the table costs only `O(1)` time.
- **Space Complexity:** `O(N)` - The extra space required depends on the number of items stored in the hash table, which stores at most $N$ elements.

---

## Problem 2: Contains Duplicate (LeetCode 217)

### Approach: HashSet (Optimal)
Unlike a `HashMap` that stores Key-Value pairs, a `HashSet` only stores **unique values**. It is the perfect data structure for finding duplicates.

**Algorithm:**
1. Create an empty `HashSet<Integer>`.
2. Iterate through the array.
3. Check if the current element is already in the `HashSet`.
   - If it is, we found a duplicate! Return `true`.
   - If not, add the element to the `HashSet`.
4. If the loop finishes without returning `true`, the array has no duplicates. Return `false`.

- **Time Complexity:** `O(N)` - We iterate over the array once. Adding/Checking a HashSet takes `O(1)` time.
- **Space Complexity:** `O(N)` - We store up to $N$ elements in the HashSet if all elements are unique.

**Pro-Tip (Java):** Since we only care about the values and not the indices, we can use an enhanced for-loop `for (int num : nums)` to iterate through the array cleanly without worrying about length bounds.
