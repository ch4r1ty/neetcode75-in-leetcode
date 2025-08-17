# Java中null與空集合的區別

## 核心區別

```java
List<String> nullList = null;           // null引用
List<String> emptyList = new ArrayList<>();  // 空集合

System.out.println(nullList);    // 輸出: null
System.out.println(emptyList);   // 輸出: []
```

## 關鍵差異

| 比較項目 | null | 空集合 |
|---------|------|--------|
| 值 | `null` | `[]` |
| 調用方法 | 拋出 `NullPointerException` | 正常執行 |
| 判斷 | `== null` 為 `true` | `== null` 為 `false` |

## 實際問題

### 錯誤寫法
```java
List<String> res = new ArrayList<>();

if (digits.length() == 0) 
    return null;  // ❌ 返回null
```

### 正確寫法
```java
List<String> res = new ArrayList<>();

if (digits.length() == 0) 
    return res;   // ✅ 返回空集合[]
```

## 黃金法則

**集合類型的方法應該返回空集合，而不是null**

這樣調用者就不需要檢查null，避免 `NullPointerException`。

# 17. Letter Combinations of a Phone Number - Interview Guide

## Problem Analysis

**Problem**: Given a string containing digits from 2-9, return all possible letter combinations that the number could represent on a phone keypad.

**Phone Keypad Mapping**:
- 2: abc, 3: def, 4: ghi, 5: jkl
- 6: mno, 7: pqrs, 8: tuv, 9: wxyz

**Example**: digits = "23"
- Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
- Explanation: 2 maps to "abc", 3 maps to "def"
- We combine each letter from "abc" with each letter from "def"

**Key Points**:
- Each digit maps to 3-4 letters
- We need to generate all possible combinations
- Order matters (this is different from combinations problem)
- Empty input should return empty list

## Solution Approach

This is a **backtracking** problem that's different from typical combination problems. Let me explain why:

1. **Why backtracking?**
   - We need to explore all possible letter combinations
   - Each digit gives us multiple choices (3-4 letters)
   - We need to try all combinations systematically

2. **Key Differences from Basic Combinations**:
   - We're building strings, not selecting numbers
   - Each level represents a different digit position
   - We process digits in order (left to right)
   - We use a StringBuilder for efficient string building

3. **Tree Structure**:
   - Root level: choose first letter from first digit
   - Second level: choose second letter from second digit
   - Continue until we've processed all digits
   - Each path from root to leaf gives us one combination

## Backtracking Three Steps

### Step 1: Determine function parameters and return value

```java
private void backtracking(String digits, int num)
```

- `digits`: the input string of digits
- `num`: current position/index in the digits string
- Return type: void (we use global variables to store results)

### Step 2: Determine termination condition

```java
if (num == digits.length()) {
    res.add(sb.toString());
    return;
}
```

- When we've processed all digits (num equals digits length)
- We've built a complete combination, add it to results
- Convert StringBuilder to String when adding

### Step 3: Determine search traversal process

```java
String level = map[digits.charAt(num) - '0'];
for (int i = 0; i < level.length(); i++) {
    sb.append(level.charAt(i));
    num++;
    backtracking(digits, num);
    num--;
    sb.deleteCharAt(sb.length() - 1);
}
```

- Get the letters for current digit
- Loop through each letter
- Add letter to StringBuilder
- Recursively process next digit
- Remove letter (backtrack)

## Code Walkthrough

Let me walk through the code line by line:

```java
class Solution {
    List<String> res = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    String[] map = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
```
- `res`: stores all valid letter combinations
- `sb`: current combination being built (StringBuilder is more efficient than String concatenation)
- `map`: phone keypad mapping (index 0 and 1 are empty since they don't have letters)

```java
public List<String> letterCombinations(String digits) {
    if (digits == null || digits.length() == 0) return res;
    backtracking(digits, 0);
    return res;
}
```
- **Important edge case**: if input is empty, return empty list (not [""])
- Start backtracking from position 0
- Return all combinations

```java
private void backtracking(String digits, int num) {
    if (num == digits.length()) {
        res.add(sb.toString());
        return;
    }
```
- **Termination condition**: when we've processed all digits
- Convert StringBuilder to String and add to results

```java
String level = map[digits.charAt(num) - '0'];
```
- Get the current digit: `digits.charAt(num) - '0'` converts char to int
- Look up the corresponding letters in our map
- **Example**: if digit is '2', we get "abc"

```java
for (int i = 0; i < level.length(); i++) {
    sb.append(level.charAt(i));
    num++;
    backtracking(digits, num);
    num--;
    sb.deleteCharAt(sb.length() - 1);
}
```
- **Loop through letters**: try each letter for current digit
- **Choose**: Add current letter to StringBuilder
- **Increment position**: move to next digit
- **Explore**: Recursively process next digit
- **Decrement position**: go back to current digit
- **Unchoose**: Remove current letter (backtrack)

## Time and Space Complexity

- **Time Complexity**: O(4^N × N) where N is length of digits
  - Each digit can have up to 4 letters
  - We generate 4^N combinations
  - Each combination takes O(N) to build
- **Space Complexity**: O(N) - depth of recursion stack

## Example Walkthrough

For digits = "23":

1. Start: sb="", num=0, digit='2' → letters="abc"
2. Choose 'a': sb="a", num=1, digit='3' → letters="def"
3. Choose 'd': sb="ad", num=2 → add "ad" to results
4. Backtrack: sb="a", choose 'e': sb="ae", num=2 → add "ae"
5. Continue...

## Key Takeaways

1. **StringBuilder efficiency**: Much faster than String concatenation
2. **Digit to letter mapping**: Use array for O(1) lookup
3. **Position tracking**: Use index to track current digit position
4. **Proper backtracking**: Remove from StringBuilder and decrement position
5. **Edge case handling**: Empty input should return empty list
6. **Character conversion**: `char - '0'` converts char digit to int

This problem demonstrates how backtracking can be used for string generation and how to handle different types of choices at each level.