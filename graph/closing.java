//package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

 

public class closing {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("closing.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("closing.out")));
        
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(System.out);
     
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] adj = new ArrayList[N+1];
        for(int i = 0; i<=N; i++) adj[i] = new ArrayList();
        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }
        DSU dsu = new DSU(N+1);
        int[] qs = new int[N];
        for(int i = 0; i<N; i++) qs[i] = Integer.parseInt(br.readLine());
        boolean[] ret = new boolean[N];
        boolean[] inGraph = new boolean[N+1];
        for(int i = N-1; i>=0; i--){
            if(dsu.size[dsu.find(qs[i])] == 1){
                dsu.groups++;
            }
            for(int j : adj[qs[i]]){
                if(inGraph[j]){
                    dsu.union(j, qs[i]);
                }
            }
            
            ret[i] = dsu.groups == 1;
            inGraph[qs[i]] = true;
        } 
        for(boolean a : ret) pw.println(a ? "YES" : "NO");
        pw.close();
        br.close();
    }
    static class DSU {

        public int[] parent;
        public int[] size;
        public int groups;
        
        public DSU(int n) {
            parent = new int[n];
            size = new int[n];
            groups = 0;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        
        public int find(int u) {
            if (u != parent[u]) {
                parent[u] = find(parent[u]);
            }
            return parent[u];
        }
        
        public void union(int u, int v) {
            int ru = find(u);
            int rv = find(v);
            
            if (ru == rv) {
                return;
            }
            groups--;
            if (size[ru] > size[rv]) {
                parent[rv] = ru;
                size[ru] += size[rv];
            } else {
                parent[ru] = rv;
                size[rv] += size[ru];
            }
        }
    }
}
