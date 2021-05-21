package graph;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class replication {

    static int[] xDir = new int[]{0, 0, 1, -1};
    static int[] yDir = new int[]{1, -1, 0, 0};

    //Distance from A to B is undirected - the same as B to A distance
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        Queue<int[]> edgein = new ArrayDeque();
        Queue<int[]> starts = new ArrayDeque();
        Queue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() { //large affects small
            public int compare(int[] a, int[] b) {
                return b[2] - a[2];
            }
        });

        int[][] tos = new int[N][N];
        int[][] dists = new int[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                if (line.charAt(j) == '#') {
                    edgein.add(new int[]{i, j, 0});
                } else {
                    if (line.charAt(j) == 'S') {
                        starts.add(new int[]{i, j, 0});
                    }
                    tos[i][j] = -1;
                    dists[i][j] = -1;
                }
            }
        }

        while (!edgein.isEmpty()) {
            int[] curr = edgein.poll();
            for (int i = 0; i < 4; i++) {
                int dY = curr[0] + yDir[i];
                int dX = curr[1] + xDir[i];
                if (dY < N && dX < N && dY >= 0 && dX >= 0 && dists[dY][dX] == -1) {
                    dists[dY][dX] = curr[2] + 1;
                    edgein.add(new int[]{dY, dX, curr[2] + 1});
                }
            }
        }

        //if can expand, then can move in all 4
        //if can move in all 4, then can expand
        while (!starts.isEmpty()) {
            int[] curr = starts.poll();
            pq.add(new int[]{curr[0], curr[1], dists[curr[0]][curr[1]] - 1});
            if (curr[2] / D >= dists[curr[0]][curr[1]]) {
                continue;
            }
            curr[2]++;
//            System.out.println(Arrays.toString(curr));
            for (int i = 0; i < 4; i++) {
                int dY = curr[0] + yDir[i];
                int dX = curr[1] + xDir[i];
                if (dY < N && dX < N && dY >= 0 && dX >= 0 && tos[dY][dX] == -1) {
                    tos[dY][dX] = 1;
                    starts.add(new int[]{dY, dX, curr[2]});
                }
            }
        }

        boolean[][] vis = new boolean[N][N];
        int ret = 0;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
//            System.out.println(Arrays.toString(curr));
            if (vis[curr[0]][curr[1]] || curr[2] < 0) {
                continue;
            }
            vis[curr[0]][curr[1]] = true;
            ret++;
            for (int i = 0; i < 4; i++) {
                int dY = curr[0] + yDir[i];
                int dX = curr[1] + xDir[i];
                if (dY < N && dX < N && dY >= 0 && dX >= 0 && !vis[dY][dX]) {
                    pq.add(new int[]{dY, dX, curr[2] - 1});
                }
            }
        }
//        for(boolean[] a : vis){
//            for(boolean b : a){
//                System.out.print(b ? 1 : 0);
//            }
//            System.out.println();
//        }
        pw.println(ret);
        pw.close();
        br.close();
    }
}
