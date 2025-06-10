# 151. Reverse Words in a String Interview Script

## Problem Understanding
This problem asks us to reverse the order of words in a string. For example:
- Input: "the sky is blue"
- Output: "blue is sky the"

We need to:
1. Remove extra spaces between words
2. Remove spaces at the beginning and end
3. Reverse the order of words

## Solution Approach
Let me break down my thinking process:

1. First, I thought about how to handle the spaces:
   - We need to remove extra spaces between words, that is we should keep only one space between words
   - We need to remove spaces at the beginning and end
   - This is important because the input might have multiple spaces

2. Then, I realized we can solve this in three steps:
   - Step 1: Clean up the string by removing extra spaces
   - Step 2: Reverse the entire string
   - Step 3: Reverse each word individually

3. For the space removal, I thought about using a two-pointer approach:
   - One pointer to read the characters
   - Another pointer to write the cleaned string
   - This way we can process the string in one pass

4. For the reversal process, I thought about:
   - First reversing the entire string to get the words in reverse order
   - Then reversing each word to get the correct word spelling
   - We can use the same two-pointer technique for both reversals

5. I also considered using StringBuilder because:
   - We need to modify the string multiple times
   - String is immutable in Java
   - StringBuilder is more efficient for string manipulation

## Code Implementation
Now that I have the approach clear, let me implement it:

```java
public String reverseWords(String s) {
    // Step 1: Remove extra spaces
    StringBuilder sb = removeSpace(s);
    
    // Step 2: Reverse the entire string
    reverseString(sb, 0, sb.length() - 1);
    
    // Step 3: Reverse each word
    reverseEachWord(sb);
    
    return sb.toString();
}
```

Let me explain each method:

1. `removeSpace` method:
```java
private StringBuilder removeSpace(String s) {
    // Find the first non-space character
    int start = 0;
    while (s.charAt(start) == ' ') start++;
    
    // Find the last non-space character
    int end = s.length() - 1;
    while (s.charAt(end) == ' ') end--;
    
    // Build the cleaned string
    StringBuilder sb = new StringBuilder();
    while (start <= end) {
        char c = s.charAt(start);
        // Only append if it's not a space or if the previous character is not a space
        if (c != ' ' || sb.charAt(sb.length() - 1) != ' ') {
            sb.append(c);
        }
        start++;
    }
    return sb;
}
```

2. `reverseString` method:
```java
public void reverseString(StringBuilder sb, int start, int end) {
    while (start <= end) {
        // Swap characters at start and end positions
        char temp = sb.charAt(start);
        sb.setCharAt(start, sb.charAt(end));
        sb.setCharAt(end, temp);
        start++;
        end--;
    }
}
```

3. `reverseEachWord` method:
```java
private void reverseEachWord(StringBuilder sb) {
    int start = 0;
    int end = 1;
    int n = sb.length();
    while (start < n) {
        // Find the end of the current word
        while (end < n && sb.charAt(end) != ' ') {
            end++;
        }
        // Reverse the current word
        reverseString(sb, start, end - 1);
        // Move to the next word
        start = end + 1;
        end = start + 1;
    }
}
```

## Time and Space Complexity
- Time Complexity: O(n), where n is the length of the string
- Space Complexity: O(n), as we need a StringBuilder to store the result

## Example Walkthrough
Let's see how it works with an example:
Input: "  hello   world  "
1. After removeSpace: "hello world"
2. After reverseString: "dlrow olleh"
3. After reverseEachWord: "world hello"

## Key Points
1. Using StringBuilder for efficient string manipulation
2. Two-pointer technique for reversing strings
3. Careful handling of spaces
4. Modular design with separate methods for each step

## Common Pitfalls
1. Not handling multiple spaces correctly
2. Forgetting to reverse individual words after reversing the entire string
3. Not properly handling edge cases (empty string, all spaces)
4. Not considering the performance impact of string operations

class Solution {
    public String reverseWords(String s) {
        StringBuilder res = removeSpace(s); // 去除多余空格
        reverseString(res, 0, res.length() - 1); // 整体反转字符串
        reverseEachWord(res); // 逐个单词反转
        return res.toString(); // 转成 String 返回
    }

    // 消除多余空格
    private StringBuilder removeSpace(String s) {
        char[] temp = s.toCharArray();
        int fast = 0;
        int slow = 0;
        // 先把最前面的空格消了，快指针先动
        while (temp[fast] == ' ') {
            fast++;
        }

        // 开始快慢指针
        while (fast < temp.length) {
            if (fast > 0 && temp[fast] == ' ' && temp[fast - 1] == ' ') {
                fast++;
                continue;
            }

            temp[slow] = temp[fast];
            fast++;
            slow++;
        }

        while (slow > 0 && temp[slow - 1] == ' ') {
            slow--;
        }

        return new StringBuilder(new String(temp, 0, slow));
    }

    // 反转整个string
    private void reverseString(StringBuilder s, int left, int right) {
        // int left = 0;
        // int right = s.length() - 1;
        char temp;
        while (left < right) {
            temp = s.charAt(left);
            s.setCharAt(left, s.charAt(right));
            s.setCharAt(right, temp);

            left++;
            right--;
        }
    }

    // 反转每个单词
    private void reverseEachWord(StringBuilder s) {
        int start = 0;
        int end = 0;
        while (start < s.length()) {
            while (end < s.length() && s.charAt(end) != ' ') {
                end++;
            }

            reverseString(s, start, end - 1);
            start = end + 1;
            end += 1;
        }
    }
}