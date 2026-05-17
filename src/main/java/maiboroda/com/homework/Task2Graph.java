package maiboroda.com.homework;

import java.util.*;


public class Task2Graph {

    static class Edge {
        int from, to;

        Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "(" + from + "-" + to + ")";
        }
    }

    static int N, M;
    static int[][] field;
    static int[][] regionId;
    static int regionCount;
    static List<Edge> edges;

    static final int[] DR = {-1, 1, 0, 0};
    static final int[] DC = {0, 0, -1, 1};
    static final String[] COLORS = {"R", "G", "B", "Y", "W", "P"};

    static void findRegions() {
        regionId = new int[N][M];
        for (int[] row : regionId) Arrays.fill(row, -1);
        regionCount = 0;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (regionId[r][c] == -1) {
                    int color = field[r][c];
                    Queue<int[]> q = new LinkedList<>();
                    q.add(new int[]{r, c});
                    regionId[r][c] = regionCount;
                    while (!q.isEmpty()) {
                        int[] cur = q.poll();
                        for (int d = 0; d < 4; d++) {
                            int nr = cur[0] + DR[d], nc = cur[1] + DC[d];
                            if (nr >= 0 && nr < N && nc >= 0 && nc < M
                                    && regionId[nr][nc] == -1
                                    && field[nr][nc] == color) {
                                regionId[nr][nc] = regionCount;
                                q.add(new int[]{nr, nc});
                            }
                        }
                    }
                    regionCount++;
                }
            }
        }
    }

    static void buildEdges() {
        edges = new ArrayList<>();
        Set<String> seen = new HashSet<>();

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                int[][] neighbors = {{r, c + 1}, {r + 1, c}};
                for (int[] nb : neighbors) {
                    int nr = nb[0], nc = nb[1];
                    if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
                        int a = regionId[r][c], b = regionId[nr][nc];
                        if (a != b) {
                            int lo = Math.min(a, b), hi = Math.max(a, b);
                            String key = lo + "-" + hi;
                            if (!seen.contains(key)) {
                                seen.add(key);
                                edges.add(new Edge(lo, hi));
                            }
                        }
                    }
                }
            }
        }
    }

    static void bfs(int start) {
        boolean[] visited = new boolean[regionCount];
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        System.out.print("BFS обхід від вершини " + start + ": ");
        while (!q.isEmpty()) {
            int v = q.poll();
            System.out.print(v + " ");
            for (Edge e : edges) {
                int neighbor = -1;
                if (e.from == v) neighbor = e.to;
                else if (e.to == v) neighbor = e.from;
                if (neighbor != -1 && !visited[neighbor]) {
                    visited[neighbor] = true;
                    q.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("=== Завдання 2: Граф ігрового поля (BFS) ===");

        Scanner sc = new Scanner(System.in);
        System.out.print("Введіть N (рядки, N<=20): ");
        N = sc.nextInt();
        System.out.print("Введіть M (стовпці, M<=25): ");
        M = sc.nextInt();

        field = new int[N][M];
        Random rnd = new Random(42);
        for (int r = 0; r < N; r++)
            for (int c = 0; c < M; c++)
                field[r][c] = rnd.nextInt(6);

        System.out.println("\nІгрове поле (" + N + "x" + M + "):");
        for (int r = 0; r < N; r++) {
            System.out.print("  ");
            for (int c = 0; c < M; c++)
                System.out.print(COLORS[field[r][c]] + " ");
            System.out.println();
        }

        findRegions();
        buildEdges();

        System.out.println("\nКількість регіонів (вершин): " + regionCount);
        System.out.println("Кількість ребер: " + edges.size());
        System.out.println("Масив ребер (перші 20):");
        for (int i = 0; i < Math.min(20, edges.size()); i++)
            System.out.print("  " + edges.get(i));
        System.out.println();

        System.out.print("\nВведіть стартову вершину для BFS (0-" + (regionCount - 1) + "): ");
        int start = sc.nextInt();
        bfs(start);
    }
}
