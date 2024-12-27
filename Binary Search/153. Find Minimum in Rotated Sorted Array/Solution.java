class Solution {
    public int findMin(int[] nums) {
        int length = nums.length;
        int left = 0;
        int right = length - 1;

        while (left < right) { // 關鍵1：爲什麽不能是<=，因爲假如等於，那最後會在left == right 的時候，在進行一次判斷，left到right右邊去了
            int mid = (right - left) / 2 + left;

            // 關鍵2
            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return nums[left];
    }
}
