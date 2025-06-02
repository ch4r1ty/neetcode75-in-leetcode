# Valid Anagram - Interview Script

Let me explain how I would solve this problem. The main idea is to check if two strings are anagrams of each other, meaning they contain the same characters in the same frequency.

First, let me explain my overall approach:
1. We'll use an array of size 26 to count the frequency of each character
2. For the first string, we'll increment the count for each character
3. For the second string, we'll decrement the count for each character
4. If the strings are anagrams, all counts should be zero at the end

This approach is efficient because:
- We only need one pass through each string
- We use a fixed-size array instead of a hash map
- The space complexity is constant since we only use 26 slots

Now, let me show you the implementation step by step:

Step 1: Initialize the frequency array
```java
int[] dic = new int[26];
```
We use 26 because we're only dealing with lowercase English letters.

Step 2: Count characters in the first string
```java
for (int i = 0; i < s.length(); i++) {
    dic[s.charAt(i) - 'a']++;
}
```
Here, we subtract 'a' to convert the character to a 0-based index. For example, 'a' becomes 0, 'b' becomes 1, etc.

Step 3: Decrement counts for the second string
```java
for (int i = 0; i < t.length(); i++) {
    dic[t.charAt(i) - 'a']--;
}
```
If the strings are anagrams, this should balance out all the increments from step 2.

Step 4: Check if all counts are zero
```java
for (int i : dic) {
    if (i != 0) return false;
}
return true;
```
If any count is not zero, it means the strings have different character frequencies.

Let me explain the time and space complexity:
1. Time Complexity: O(n) where n is the length of the strings
   - We need to traverse each string once
   - The final check of the array is O(1) since it's always 26 operations
2. Space Complexity: O(1)
   - We use a fixed-size array of 26 elements
   - The space used doesn't grow with the input size

Would you like me to explain any part of this in more detail? 