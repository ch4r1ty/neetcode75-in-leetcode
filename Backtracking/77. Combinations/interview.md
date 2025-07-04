# 77. Combinations - Interview Guide

## Problem Analysis

**Problem**: Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].

**Example**: n = 4, k = 2
- Output: [[1,2], [1,3], [1,4], [2,3], [2,4], [3,4]]

**Key Points**:
- We need to find all combinations, not permutations (order doesn't matter)
- Numbers are from 1 to n
- Each combination should have exactly k numbers
- No duplicates allowed

## Solution Approach

This is a classic **backtracking** problem. Let me explain why:

1. **Why backtracking?** 
   - We need to explore all possible combinations
   - We can't use simple loops because k could be large (imagine n=100, k=50 - we'd need 50 nested loops!)
   - Backtracking uses recursion to handle variable depth

2. **Tree Structure**:
   - Think of it as a tree where each level represents choosing one number
   - Root level: choose first number from [1,2,3,4]
   - Second level: choose second number from remaining numbers
   - Continue until we have k numbers

3. **Key Insight**:
   - Use `startIndex` to avoid duplicates and ensure we only go forward
   - When we choose number i, next recursion starts from i+1
   - This ensures combinations like [1,2] but not [2,1]

## Backtracking Three Steps

### Step 1: Determine function parameters and return value

```java
private void backtracking(int n, int k, int startIndex)
```

- `n`: upper bound of numbers (1 to n)
- `k`: size of each combination
- `startIndex`: where to start choosing numbers (prevents duplicates)
- Return type: void (we use global variables to store results)

### Step 2: Determine termination condition

```java
if (path.size() == k) {
    res.add(new ArrayList<>(path));
    return;
}
```

- When our current path has exactly k numbers, we found a valid combination
- Add a copy of the path to results (important: use `new ArrayList<>(path)`)

### Step 3: Determine search traversal process

```java
for (int i = startIndex; i <= n - (k - path.size()) + 1; i++) {
    path.add(i);
    backtracking(n, k, i + 1);
    path.removeLast();
}
```

- Loop through all possible numbers starting from `startIndex`
- Add current number to path
- Recursively choose next number (starting from i+1)
- Remove current number (backtrack)

## Code Walkthrough

Let me walk through the code line by line:

```java
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
```
- `res`: stores all valid combinations
- `path`: current combination being built (using LinkedList for efficient add/remove)

```java
public List<List<Integer>> combine(int n, int k) {
    backtracking(n, k, 1);
    return res;
}
```
- Main function that starts the backtracking process
- Start from 1 (since numbers are 1 to n)
- Return all found combinations

```java
private void backtracking(int n, int k, int startIndex) {
    if (path.size() == k) {
        res.add(new ArrayList<>(path));
        return;
    }
```
- **Termination condition**: when we have k numbers in our path
- **Important**: We create a new ArrayList to avoid reference issues

```java
for (int i = startIndex; i <= n - (k - path.size()) + 1; i++) {
```
- **Optimization (pruning)**: We don't need to check all numbers
- If we need k numbers total and already have `path.size()` numbers
- We only need `k - path.size()` more numbers
- So we can stop at `n - (k - path.size()) + 1`
- **Example**: n=4, k=3, path.size()=1, we need 2 more numbers
- We can stop at 4-(3-1)+1 = 3, because from 4 we can't get 2 more numbers

```java
    path.add(i);
    backtracking(n, k, i + 1);
    path.removeLast();
```
- **Choose**: Add current number to path
- **Explore**: Recursively try next numbers (starting from i+1)
- **Unchoose**: Remove current number (backtrack)

## Time and Space Complexity

- **Time Complexity**: O(C(n,k)) - we generate all combinations
- **Space Complexity**: O(k) - depth of recursion stack

## Example Walkthrough

For n=4, k=2:

1. Start: path=[], startIndex=1
2. Choose 1: path=[1], call backtracking(4,2,2)
3. Choose 2: path=[1,2], size=2 → add [1,2] to results
4. Backtrack: path=[1], choose 3: path=[1,3], size=2 → add [1,3]
5. Continue...

## Key Takeaways

1. **Backtracking pattern**: Choose → Explore → Unchoose
2. **startIndex prevents duplicates**: ensures we only go forward
3. **Pruning optimization**: reduces unnecessary iterations
4. **Copy path when adding**: avoid reference issues
5. **Use LinkedList**: efficient add/remove operations

This is a fundamental backtracking problem that demonstrates the core pattern used in many combination/permutation problems.
