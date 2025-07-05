class Solution {
    LinkedList<Integer> path = new LinkedList<>();
    List<List<Integer>> res = new ArrayList<>();
    int sum = 0;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        backtracking(candidates, target, 0);
        return res;
    }

    private void backtracking(int[] candidates, int target, int startIndex) {
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i < candidates.length && sum + candidates[i] <= target; i++) {
            // 正确剔除重复解的办法
            // 跳过同一树层使用过的元素
            if (i > startIndex && candidates[i] == candidates[i - 1]) continue;
            sum += candidates[i];
            path.add(candidates[i]);
            // i+1 代表当前组内元素只选取一次
            backtracking(candidates, target, i + 1);
            path.removeLast();
            sum -= candidates[i];
        }

    }
}