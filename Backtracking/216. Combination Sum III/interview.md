# 216. Combination Sum III - Interview Guide

## Problem Analysis

**Problem**: Find all valid combinations of k numbers that sum up to n such that:
- Only numbers 1 through 9 are used
- Each number is used at most once
- Return a list of all possible valid combinations

**Example**: k = 3, n = 7
- Output: [[1,2,4]]
- Explanation: 1 + 2 + 4 = 7, and we used exactly 3 numbers

**Key Points**:
- Numbers are from 1 to 9 only
- Each number can be used at most once
- We need exactly k numbers
- Sum must equal n
- Order doesn't matter (combinations, not permutations)

## Solution Approach

This is a **backtracking** problem with additional constraints. Let me explain why:

1. **Why backtracking?**
   - We need to explore all possible combinations of k numbers
   - We can't use simple loops because k could be large
   - We need to try different combinations and backtrack when they don't work

2. **Key Differences from Basic Combinations**:
   - We have a target sum constraint
   - We need to track the current sum
   - We can prune early if sum exceeds target

3. **Tree Structure**:
   - Each level represents choosing one number from 1-9
   - We stop when we have k numbers or sum exceeds target
   - We only explore paths that could potentially reach the target

## Backtracking Three Steps

### Step 1: Determine function parameters and return value

```java
private void backtracking(int targetSum, int k, int startIndex, int sum)
```

- `targetSum`: the target sum we're trying to reach (n)
- `k`: how many numbers we need
- `startIndex`: where to start choosing numbers (prevents duplicates)
- `sum`: current sum of numbers in our path
- Return type: void (we use global variables)

### Step 2: Determine termination conditions

```java
if (sum > targetSum) return;
if (path.size() == k) {
    if (sum == targetSum) res.add(new ArrayList<>(path));
    return;
}
```

- **Early termination**: if current sum exceeds target, stop exploring
- **Valid combination**: when we have k numbers and sum equals target
- **Invalid combination**: when we have k numbers but wrong sum

### Step 3: Determine search traversal process

```java
for (int i = startIndex; i <= 9 - (k - path.size()) + 1; i++) {
    path.add(i);
    sum += i;
    backtracking(targetSum, k, i + 1, sum);
    path.removeLast();
    sum -= i;
}
```

- Loop through numbers starting from `startIndex`
- Add current number to path and sum
- Recursively try next numbers
- Remove current number and subtract from sum (backtrack)

## Code Walkthrough

Let me walk through the code line by line:

```java
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
```
- `res`: stores all valid combinations that sum to target
- `path`: current combination being built

```java
public List<List<Integer>> combinationSum3(int k, int n) {
    backtracking(n, k, 1, 0);
    return res;
}
```
- Main function that starts the backtracking process
- Start with sum = 0 and startIndex = 1
- Return all valid combinations

```java
private void backtracking(int targetSum, int k, int startIndex, int sum) {
    if (sum > targetSum) return;
```
- **Early pruning**: if we've already exceeded the target, no point continuing
- This saves a lot of unnecessary recursive calls

```java
if (path.size() == k) {
    if (sum == targetSum) res.add(new ArrayList<>(path));
    return;
}
```
- **Termination condition**: when we have exactly k numbers
- **Check sum**: only add to results if sum equals target
- **Important**: create a copy of path to avoid reference issues

```java
for (int i = startIndex; i <= 9 - (k - path.size()) + 1; i++) {
```
- **Pruning optimization**: similar to basic combinations problem
- We only need `k - path.size()` more numbers
- Since numbers are 1-9, we can stop at `9 - (k - path.size()) + 1`
- **Example**: k=3, path.size()=1, we need 2 more numbers
- We can stop at 9-(3-1)+1 = 8, because from 9 we can't get 2 more numbers

```java
    path.add(i);
    sum += i;
    backtracking(targetSum, k, i + 1, sum);
    path.removeLast();
    sum -= i;
```
- **Choose**: Add number to path and add to sum
- **Explore**: Recursively try next numbers
- **Unchoose**: Remove number from path and subtract from sum

## Time and Space Complexity

- **Time Complexity**: O(C(9,k)) - we generate combinations of k numbers from 1-9
- **Space Complexity**: O(k) - depth of recursion stack

## Example Walkthrough

For k=3, n=7:

1. Start: path=[], sum=0, startIndex=1
2. Choose 1: path=[1], sum=1, call backtracking(7,3,2,1)
3. Choose 2: path=[1,2], sum=3, call backtracking(7,3,3,3)
4. Choose 4: path=[1,2,4], sum=7, size=3 → add [1,2,4] to results
5. Backtrack: path=[1,2], sum=3, choose 5: sum=8 > 7 → prune
6. Continue...

## Key Takeaways

1. **Early pruning**: Stop when sum exceeds target
2. **Sum tracking**: Keep track of current sum during recursion
3. **Proper backtracking**: Remove from both path and sum
4. **Combination pruning**: Use the same formula as basic combinations
5. **Validation**: Check both size and sum at termination

This problem combines the basic combination pattern with sum constraints, making it a good example of how to extend backtracking for additional requirements.
