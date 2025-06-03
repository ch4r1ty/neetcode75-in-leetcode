# Intersection of Two Arrays - Interview Script

"Let me walk you through my solution for this problem:

First, this is a set operation problem. We're given two arrays, and we need to find their intersection - meaning we need to find all elements that appear in both arrays. The key points are:
1. Each element in the result must be unique
2. The result can be in any order

Let's start with the simplest approach. We could:
1. Go through each number in the first array, and for each number, check every number in the second array
2. Keep track of the numbers that appear in both arrays
However, this would be very slow because we're checking every number against every other number, giving us a time complexity of O(n*m).

We can do better using a HashSet. Here's why:
1. HashSet provides O(1) time complexity for add and contains operations
2. It automatically handles duplicates for us
3. We can use it to efficiently track which elements we've seen

Here's how the solution works:
1. First, we create a HashSet to store elements from the first array
2. We go through the first array and add all numbers to this set
3. Then, we create another HashSet for our result
4. We go through the second array and check if each number exists in our first set
5. If it does, we add it to our result set
6. Finally, we convert the result set to an array

Let me illustrate with an example:
Input: nums1 = [1,2,2,1], nums2 = [2,2]
- First, we add all numbers from nums1 to set1: {1, 2}
- Then, we check nums2:
  - 2 exists in set1, so we add it to result set
  - 2 exists in set1, but it's already in result set (HashSet handles duplicates)
- Final result: [2]

Time Complexity: O(n + m) where n and m are the lengths of the input arrays
- We go through each array once
- HashSet operations are O(1)

Space Complexity: O(min(n, m))
- We store at most min(n, m) elements in our result set
- The first set could store at most n elements

Edge cases to consider:
- Empty arrays
- Arrays with no common elements
- Arrays with all common elements
- Arrays with duplicates
- Arrays with different lengths"
