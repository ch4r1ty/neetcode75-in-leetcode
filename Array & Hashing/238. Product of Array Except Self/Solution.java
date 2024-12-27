class Solution {
    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int left[] = new int[length];
        int right[] = new int[length];
        int res[] = new int[length];

        for (int i = 0; i < length; i++) {
            left[i] = 1;
            right[i] = 1;
        }

        for (int i = 1; i < length; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }

        for (int i = length - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < length; i++) {
            res[i] = left[i] * right[i];
        }

//        System.out.println(Arrays.toString(left));
//        System.out.println(Arrays.toString(right));

        return res;
    }
}
