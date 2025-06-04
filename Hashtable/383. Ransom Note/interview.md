# Ransom Note - Interview Script

The problem asks us to determine if we can construct a ransom note using letters from a magazine. Let me walk you through my thought process.

First, let's understand what we're trying to do:
- We have two strings: ransomNote and magazine
- We need to check if we can construct ransomNote using letters from magazine
- Each letter in magazine can only be used once
- We need to return true if possible, false if not

The naive approach would be to use nested loops to check each character, but that would be inefficient.

Here's my optimized approach using an array:

1. We'll use an array of size 26 to store the frequency of each character in the magazine
2. Then we'll check if we have enough characters in the magazine to construct the ransom note
3. This gives us O(n) time complexity where n is the length of the magazine

Let me explain the solution step by step:

1. First, we create an array of size 26 (for lowercase letters):
   - For each character in magazine, we increment its count at the corresponding index
   - The index is calculated as character - 'a' (e.g., 'a' -> 0, 'b' -> 1, etc.)

2. Then, for each character in ransomNote:
   - We decrement the count at the corresponding index
   - If any count becomes negative, we return false

The time complexity is O(n) because:
- We need to process each character in magazine once
- We need to process each character in ransomNote once
- Array operations are O(1)

The space complexity is O(1) because:
- We only use a fixed-size array of 26 elements
- This is constant space regardless of input size

Here's the implementation:

```java
public boolean canConstruct(String ransomNote, String magazine) {
    // 使用長度為26的數組來記錄每個字母的出現次數
    // 數組的索引通過 char - 'a' 來計算，例如 'a' -> 0, 'b' -> 1
    int[] map = new int[26];
    
    // Step 1: Count characters in magazine
    for (int i = 0; i < magazine.length(); i++) {
        map[magazine.charAt(i) - 'a']++;
    }
    
    // Step 2: Decrement counts for characters in ransom note
    for (int i = 0; i < ransomNote.length(); i++) {
        map[ransomNote.charAt(i) - 'a']--;
    }
    
    // Step 3: Check if any count is negative
    for (int i : map) {
        if (i < 0) return false;
    }
    
    return true;
}
```

Let me explain each part of the code:

1. We create an array of size 26 to store character frequencies:
   - Index 0 represents 'a'
   - Index 1 represents 'b'
   - And so on...

2. In the first loop:
   - We iterate through each character in the magazine
   - We increment the count at the corresponding index
   - For example, if we see 'a', we increment map[0]

3. In the second loop:
   - We iterate through each character in the ransom note
   - We decrement the count at the corresponding index
   - If any count becomes negative, it means we don't have enough characters

The beauty of this solution is that it efficiently handles the problem by:
- Using a fixed-size array instead of a HashMap
- Having constant space complexity
- Being more efficient than the HashMap solution
- Only needing to process each character once

The Chinese comments explain that:
- We use an array of length 26 to record the frequency of each letter
- The array index is calculated using char - 'a'
- For example, 'a' maps to index 0, 'b' maps to index 1, etc.

Would you like me to explain any part of this in more detail?
