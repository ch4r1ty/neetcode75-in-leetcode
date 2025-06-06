# 3Sum - Interview Script

## Opening
"Let me explain the problem. We're given an integer array, and we need to find all unique triplets that sum up to zero. We can't reuse the same element at the same position, and we need to avoid duplicate triplets."

## Approach
"Here's my approach:
First, I'll sort the array. This gives us two benefits:
First, it makes it easier to handle duplicates;
Second, it allows us to use the two-pointer technique.

We'll use three pointers:
- i: the current element we're looking at
- left: the element right after i
- right: the last element in the array

For each i, we'll:
- If nums[i] is greater than 0, we can return early since we can't form a sum of 0 with larger numbers
- Skip duplicate i values to avoid duplicate triplets
- Use left and right pointers to find pairs that sum to -nums[i]"

## Time Complexity
"The time complexity of this solution is O(n²):
- Sorting takes O(n log n)
- The two-pointer traversal takes O(n²)
Space complexity is O(1), not counting the output array."

## Code Implementation
"Let me write out the code:
```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            // Early termination if first number is positive
            if (nums[i] > 0) {
                return res;
            }

            // Skip duplicates for i
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] < 0) {
                    left++;
                } else if (nums[i] + nums[left] + nums[right] > 0) {
                    right--;
                } else {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    
                    // Skip duplicates for left and right
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    right--;
                    left++;
                }
            }
        }

        return res;
    }
}
```

## Code Explanation
"Let me walk through the key parts of the code:
1. First, we sort the array
2. For each i, we check if nums[i] is greater than 0, and if so, we return early
3. We skip duplicate i values
4. We use left and right pointers to find pairs that sum to -nums[i]
5. If the sum is less than 0, we move left pointer right
6. If the sum is greater than 0, we move right pointer left
7. If the sum equals 0, we found a valid triplet and skip duplicates for both pointers"

## Potential Follow-up Questions
"If the interviewer asks: 'How would you modify this to find triplets that sum to a target value instead of 0?'
I would say: 'We can simply replace 0 with the target value in our comparisons, keeping the rest of the logic the same.'

If they ask: 'How would you find quadruplets that sum to 0?'
I would say: 'We could use a similar approach with an additional loop, or optimize it using a hash map.'

If they ask: 'How would you optimize this for very large input arrays?'
I would say: 'We could consider using a hash map approach or parallel processing.'"

## Closing
"That's my solution. The main idea is using sorting and the two-pointer technique, with a time complexity of O(n²). The advantages of this approach are its clarity, concise code, and proper handling of all edge cases."
