
import java.io.*;
import java.util.*;

// package graph;
public class atlarge {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("atlarge.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("atlarge.out")));

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken()) - 1;
        ArrayList<Integer>[] adj = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList();
        }
        Queue<int[]> leafs = new ArrayDeque();
        Queue<Integer> bes = new ArrayDeque();
        int[] dists = new int[N];
        boolean[] vis = new boolean[N];

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;

            adj[u].add(v);
            adj[v].add(u);
        }
        int ret = 0;
        bes.add(K);
        while (!bes.isEmpty()) {
            int u = bes.poll();
            if (adj[u].size() == 1) {
                leafs.add(new int[]{u, 0});
                vis[u] = true;
                continue;
            }
            for (int n : adj[u]) {
                if (dists[n] == 0 && n != K) {
                    dists[n] = dists[u] + 1;
                    bes.add(n);
                }
            }
        }

        while (!leafs.isEmpty()) {
            int[] u = leafs.poll();
            for (int n : adj[u[0]]) {
                if (vis[n]) {
                    continue;
                }
                if(dists[n] < u[1] + 1){
                    ret++;
                    continue;
                }
                vis[n] = true;
                leafs.add(new int[]{n, u[1] + 1});
            }
        }

        pw.println(ret);
        pw.close();
        br.close();
    }
}
