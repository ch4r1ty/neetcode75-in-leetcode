class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) return mid;

            // 判断 1.左、右哪半部分有序；2.target在左边；这是两件不同的事情
            // 比右區間最右邊還大的，只能在左區間；比左區間最左邊還小的，只能在右區間
            if (nums[left] <= nums[mid]) {
                // 畫個圖，很好理解
                if (target > nums[mid] || target < nums[left]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (target < nums[mid] || target > nums[right]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }

        return -1;
    }
}
