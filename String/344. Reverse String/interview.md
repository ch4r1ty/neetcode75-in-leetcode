# 344. Reverse String

## Problem Description
Write a function that reverses a string. The input string is given as an array of characters `s`.

You must do this by modifying the input array in-place with O(1) extra memory.

## Solution Approach

### Two Pointers Technique
The solution uses a two-pointer approach to reverse the string in-place:

1. Initialize two pointers:
   - `left` starting from the beginning of the array (index 0)
   - `right` starting from the end of the array (index length-1)

2. While `left` is less than `right`:
   - Swap the characters at `left` and `right` positions
   - Move `left` pointer one step forward
   - Move `right` pointer one step backward

### Time and Space Complexity
- Time Complexity: O(n), where n is the length of the string
- Space Complexity: O(1), as we only use a single temporary variable for swapping

### Code Explanation
```java
public void reverseString(char[] s) {
    int left = 0;
    int right = s.length - 1;
    while (left < right) {
        char temp = s[left];
        s[left] = s[right];
        s[right] = temp;
        
        left++;
        right--;
    }
}
```

### Key Points for Interview
1. The solution modifies the array in-place, meeting the O(1) extra space requirement
2. The two-pointer technique is efficient and intuitive
3. The loop continues until the pointers meet in the middle
4. Each iteration swaps two characters and moves both pointers

### Common Mistakes to Avoid
1. Not handling empty arrays or single-character arrays
2. Using extra space (like creating a new array)
3. Not properly incrementing/decrementing the pointers
4. Using a more complex solution when a simple two-pointer approach works

### Follow-up Questions
1. How would you handle the case if the input was a String instead of a char array?
2. Can you modify the solution to reverse only a portion of the string?
3. How would you handle the case if the string contains Unicode characters?
