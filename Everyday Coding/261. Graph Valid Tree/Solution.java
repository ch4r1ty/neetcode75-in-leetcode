class Solution {
    public boolean validTree(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        Set<Integer> visited = new HashSet<>();
        if (!dfs(adj, visited, 0, -1)) return false;

        return visited.size() == n;
    }

    private boolean dfs(List<List<Integer>> adj, Set<Integer> visited, int node, int parent) {
        if (visited.contains(node)) return false;

        visited.add(node);
        for (int nei : adj.get(node)) {
            if (nei == parent) continue;
            if (!dfs(adj,visited, nei, node)) return false;
        }

        return true;
    }
}
