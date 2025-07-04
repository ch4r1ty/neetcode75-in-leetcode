class Solution {
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        backtracking(n, k, 1, 0);
        return res;
    }

    private void backtracking(int targetSum, int k, int startIndex, int sum) {
        // 剪枝
        if (sum > targetSum) return;
        if (path.size() == k) {
            if (sum == targetSum) res.add(new ArrayList<>(path));
            return;
        }

        // 剪枝 9 - (k - path.size()) + 1
        for (int i = startIndex; i <= 9 - (k - path.size()) + 1; i++) {
            path.add(i);
            sum += i;
            backtracking(targetSum, k, i + 1, sum);
            path.removeLast();
            // 回溯
            sum -= i;
        }
    }
}