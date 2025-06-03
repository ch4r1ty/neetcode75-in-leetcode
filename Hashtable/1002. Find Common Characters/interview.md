# Find Common Characters - Interview Script

The problem is about finding common characters in a list of words. It asks us to return an array of all characters that show up in all strings within the list, including duplicates. Let's look at an example to understand what this means.

Take these words: "bella", "label", and "roller". Which characters are common to all three strings? Well, every word has an 'e' in it, and every word has 'l's in it. But the result isn't just "e l" - it's "e l l". Why? Because every word actually has two 'l's in it! "bella" has two 'l's, "label" has two 'l's, and "roller" has two 'l's. That's why we get two 'l's in the result.

Let me walk you through my thought process:

First, I thought about using a 2D array where:
- Each row represents a string
- Each column represents a character's count
But this approach would be inefficient because:
- We'd need to store counts for all strings separately
- The space would grow with the number of strings
- It's more complicated than necessary

Then I realized we can optimize this! Here's my better approach:

1. We'll use a single array of size 26 to store character counts
2. We'll start with the first word and count its characters - this gives us our baseline
3. For each subsequent word:
   - We'll count its characters in a temporary array
   - We'll update our main array to keep the minimum count for each character
   - This way, we're finding the intersection of all the counts
4. Finally, we'll convert our counts back to characters

The beauty of this solution is:
- We only need constant space (O(1)) because we're using fixed-size arrays
- We only need one pass through each string
- We're finding the minimum count for each character as we go

Time complexity is O(n * m) where:
- n is the number of strings
- m is the average length of the strings
But the space complexity is just O(1) because we only use fixed-size arrays!

Now, let me show you the implementation:

```java
public List<String> commonChars(String[] A) {
    List<String> res = new ArrayList<>();
    if (A.length == 0) return res;
    
    // Step 1: Count characters in first string
    int[] hash = new int[26];
    for (int i = 0; i < A[0].length(); i++) {
        hash[A[0].charAt(i) - 'a']++;
    }
    
    // Step 2: Process remaining strings
    for (int i = 1; i < A.length; i++) {
        int[] hashOtherStr = new int[26];
        for (int j = 0; j < A[i].length(); j++) {
            hashOtherStr[A[i].charAt(j) - 'a']++;
        }
        for (int k = 0; k < 26; k++) {
            hash[k] = Math.min(hash[k], hashOtherStr[k]);
        }
    }
    
    // Step 3: Convert counts to characters
    for (int i = 0; i < 26; i++) {
        while (hash[i] != 0) {
            char c = (char) (i + 'a');
            res.add(String.valueOf(c));
            hash[i]--;
        }
    }
    
    return res;
}
```

Let me explain each part of the code:

1. First, we initialize our result list and handle the edge case of an empty input array.

2. We create our main array `hash` to store character counts. We count characters in the first string by:
   - Converting each character to an index (0-25) using `charAt(i) - 'a'`
   - Incrementing the count at that index

3. For each subsequent string:
   - We create a temporary array to count its characters
   - We update our main array by taking the minimum count for each character
   - This ensures we only keep characters that appear in all strings

4. Finally, we convert our counts back to characters:
   - For each index in our array
   - We add the corresponding character to our result list as many times as its count

Would you like me to explain any part of this in more detail?
