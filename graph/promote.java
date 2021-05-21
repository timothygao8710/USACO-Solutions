// package graph;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class promote {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("promote.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("promote.out")));
        
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(System.out);
     
        int N = Integer.parseInt(br.readLine());
        int[][] ratings = new int[N][2];
        for(int i = 0; i<N; i++){
            ratings[i] = new int[]{i, Integer.parseInt(br.readLine())};
        }
        ArrayList<Integer>[] adj = new ArrayList[N];
        
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for(int i = 1; i<N; i++){
            int par = Integer.parseInt(br.readLine())-1;
            adj[par].add(i);
        }
        int[] ret = new int[N];
        int[] tout = new int[N];
        int[] bac = new int[N];
        dfs(adj, tout, bac, 0, 0, -1);
        BIT bit = new BIT(N);
        Arrays.sort(ratings, (int[] a, int[] b) -> b[1] - a[1]);
        
        for(int i = 0; i<N;){
            int j = i;
            while(j < N && ratings[i][1] == ratings[j][1]){
                int c = ratings[i][0];
                ret[c] = bit.sum(tout[c]) - bit.sum(bac[c]-1);
                j++;
            }
            for(int k = i; k<j; k++){
                bit.update(tout[ratings[k][0]], 1);
            }
            i = j;
        }
        for(int i : ret) pw.println(i);
        pw.close();
        br.close();
    }
    
    static int dfs(ArrayList<Integer>[] adj, int[] t, int[] l, int idx, int cur, int last){
        l[cur] = idx;
        for(int n : adj[cur]){
            if(n == last) continue;
            idx = dfs(adj, t, l, idx, n, cur);
        }
        t[cur] = idx;
        return idx+1;
    }
    
    static class BIT {
        public int[] bit;
        public BIT(int N) {
            bit = new int[N + 1];
        }

        public int sum(int r) {
            r++;
            int ret = 0;
            while (r > 0) {
                ret += bit[r];
                r -= r & -r;
            }
            return ret;
        }

        public void update(int idx, int v) {
            idx++;
            while (idx < bit.length) {
                bit[idx] += v;
                idx += idx & -idx;
            }
        }
    }
}
