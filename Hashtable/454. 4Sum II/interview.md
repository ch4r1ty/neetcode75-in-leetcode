# 4Sum II - Interview Script

The problem asks us to find the number of tuples (i, j, k, l) such that nums1[i] + nums2[j] + nums3[k] + nums4[l] = 0. Let me walk you through my thought process.

First, let's understand what we're trying to do:
- We have four arrays of integers
- We need to find all combinations where the sum equals zero
- We need to return the count of such combinations

The naive approach would be to use four nested loops, but that would be O(n⁴) time complexity, which is not efficient.

Here's my optimized approach:

1. We'll use a HashMap to store the sum of pairs from the first two arrays
2. Then we'll check if the negative of the sum of pairs from the other two arrays exists in our map
3. This reduces the time complexity to O(n²)

Let me explain the solution step by step:

1. First, we create a HashMap to store the sum of pairs from nums1 and nums2:
   - For each pair (i, j), we calculate nums1[i] + nums2[j]
   - We store this sum as a key in our map
   - The value will be the count of how many times this sum appears

2. Then, for each pair from nums3 and nums4:
   - We calculate nums3[i] + nums4[j]
   - We look for the negative of this sum in our map
   - If found, we add the count to our result

The time complexity is O(n²) because:
- We have two nested loops for the first two arrays
- We have two nested loops for the second two arrays
- Each operation in the HashMap is O(1)

The space complexity is O(n²) because:
- In the worst case, we might store all possible pairs from the first two arrays

Here's the implementation:

```java
public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
    // 四個表的O(n^4)的暴力法，可以拆解成兩個O(n^2)的哈希法
    // 道理是一樣的，只是減少了時間複雜度，增加了一點空間複雜度
    Map<Integer, Integer> map = new HashMap<>();
    int sum = 0;
    int res = 0;
    
    // Step 1: Store sums of pairs from first two arrays
    for (int i = 0; i < nums1.length; i++) {
        for (int j = 0; j < nums2.length; j++) {
            sum = nums1[i] + nums2[j];
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
    }
    
    // Step 2: Check for matching pairs from other two arrays
    for (int i = 0; i < nums3.length; i++) {
        for (int j = 0; j < nums4.length; j++) {
            sum = 0 - (nums3[i] + nums4[j]);
            res += map.getOrDefault(sum, 0);
        }
    }
    
    return res;
}
```

Let me explain each part of the code:

1. We initialize our result counter and create a HashMap to store our sums.

2. In the first nested loop:
   - We iterate through all pairs from nums1 and nums2
   - For each pair, we calculate their sum
   - We store this sum in our map, incrementing its count

3. In the second nested loop:
   - We iterate through all pairs from nums3 and nums4
   - For each pair, we calculate their sum
   - We look for the negative of this sum in our map
   - If found, we add the count to our result

The beauty of this solution is that it efficiently handles the problem by:
- Reducing the time complexity from O(n⁴) to O(n²)
- Using a HashMap to store intermediate results
- Avoiding redundant calculations

The Chinese comments explain that:
- The original O(n⁴) brute force approach with four nested loops can be optimized
- We can split it into two O(n²) hash-based solutions
- While the logic remains the same, we trade a bit of space complexity for better time complexity

Would you like me to explain any part of this in more detail? 