First we can come up easily with the brute force method.

We traverse the array, and do the squaring operation to each element. Then we sort the array, the time complexity will be O(n*logN). So we have to come up with another new method.

We can use the technique of two pointers to improve the efficiency. Since we are given a non-decreasing array, we use slow pointer s, and fast pointer f to traverse all the elements, from leftmost and rightmost. Each step, we compare the square of the element in pointer s, and the element in pointer f. The key point is that the largest squared value must come from one of two places: either from the leftmost side where we have the biggest negative numbers, or from the rightmost side where we have the biggest positive numbers.

Let me explain why this works:
1. In a sorted array like -4, -1, 0, 3, 10, we have negative numbers on the left side and positive numbers on the right side
2. When we square these numbers, the largest values will come from two possible places:
   - Either from the left side, where the negative numbers are the biggest in magnitude
   - Or from the right side, where the positive numbers are the biggest

Here's how we implement it:
1. Create a new array of the same size to store our results
2. Start to fill the result array from the end
3. Compare squares of numbers at both pointers:
   - If left pointer's square is larger, use it and move left pointer right
   - If right pointer's square is larger, use it and move right pointer left
4. Continue this process until all positions are filled

For example, with array -4, -1, 0, 3, 10:
- Compare: square of -4 is 16, square of 10 is 100: We put 100 at the end
- Compare: square of -4 is 16, square of 3 is 9: We put 16 next
- Compare: square of -1 is 1, square of 3 is 9: We put 9 next
- Compare: square of -1 is 1, square of 0 is 0: We put 1 next
- Finally put 0 at the beginning

The final result will be 0, 1, 9, 16, 100

Time complexity is O(n) since we only traverse the array once, and space complexity is O(n) for the result array. This is much more efficient than the brute force approach of squaring and sorting.

