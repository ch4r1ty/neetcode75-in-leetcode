import java.util.*;

public class Main {

    static List<Integer> path = new ArrayList<>();
    static List<List<Integer>> res = new ArrayList<>();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] graph = new int[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            int s = scanner.nextInt();
            int t = scanner.nextInt();
            graph[s][t] = 1;
        }
        
        path.add(1);
        dfs(graph, 1, n);

        for (List<Integer> pa : res) {
            for (int i = 0; i < pa.size() - 1; i++) {
                System.out.print(pa.get(i) + " ");
                
            }
            System.out.println(pa.get(pa.size() - 1));
        }

        return;
    }

    static void dfs(int[][] graph, int x, int n) {
        if (x == n) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 1; i < n + 1; i++) {
            if (graph[x][i] == 1) {
                path.add(i);
                dfs(graph, i, n);
                path.remove(path.size() - 1);
            }
        }
    }
}