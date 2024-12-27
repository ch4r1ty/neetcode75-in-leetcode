class Solution {
    public int maxArea(int[] heights) {
        int length = heights.length;
        int left = 0;
        int right = length - 1;
        int res = 0;

        while (left < right) {
            res = Math.max(res, (right - left) * Math.min(heights[left], heights[right]));
            if (heights[left] <= heights[right]) {
                left++;
            } else {
                right--;
            }
        }

        return res;
    }
}
