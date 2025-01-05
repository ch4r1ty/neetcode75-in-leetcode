class Solution {
    private Map<Integer, List<Integer>> preMap = new HashMap<>();
    private Set<Integer> visited = new HashSet<>();

    public boolean canFinish(int numCourses, int[][] prerequisites) { // numCourses是課程縂數量，看題！
        // 預留空列表
        for (int i = 0; i < numCourses; i++) {
            preMap.put(i, new ArrayList<>());
        }
        // 填充鄰接表
        for (int[] prereq : prerequisites) {
            preMap.get(prereq[0]).add(prereq[1]);
        }

        for (int c = 0; c < numCourses; c++) {
            if (!dfs(c)) {
                return false;
            }
        }

        return true;
    }

    private boolean dfs(int course) {
        if (visited.contains(course)) return false;
        if (preMap.get(course).isEmpty()) return true;

        visited.add(course);
        for (int pre : preMap.get(course)) {
            if (!dfs(pre)) return false;
        }
        visited.remove(course);
        preMap.put(course, new ArrayList<>());
        return true;
    }
}