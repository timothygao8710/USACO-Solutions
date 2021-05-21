
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// package graph;
public class cowland {

    static int[] tins;
    static int[] size;
    static BIT tour;
    static int[] vals;
    static int idx;

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("REPLACE.in"));
//        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("REPLACE.out")));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] adj = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList();
        }
        tins = new int[N];
        size = new int[N];
        Arrays.fill(size, 1);
        vals = new int[N];
        tour = new BIT(N);
        idx = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            vals[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken())-1;
            int v = Integer.parseInt(st.nextToken())-1;
            adj[u].add(v);
            adj[v].add(u);
        }
        lca par = new lca(adj, N);
        dfs(adj, 0, -1, 0);
        
        for(int i = 0; i<Q; i++){
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            if(type == 1){
                //update
                
            }else{
                //query
                int u = Integer.parseInt(st.nextToken())-1;
                int v =  Integer.parseInt(st.nextToken())-1;
                pw.println(
                        tour[tins[u]]^tour[tins[v]]^tour[tins[par.get(u, v)]]
                );
            }
        }
        pw.close();
        br.close();
    }

    static int dfs(ArrayList<Integer>[] adj, int curr, int last, int val) {
        val ^= vals[curr];
        tins[curr] = idx;
        tour.update(idx, idx, val);
        idx++;
        for (int i = 0; i < adj[curr].size(); i++) {
            if (adj[curr].get(i) == last) {
                continue;
            }
            size[curr] += dfs(adj, adj[curr].get(i), curr, val);
        }
        return size[curr];
    }

    static class lca {

        public int idx;
        public int[] tins;
        public int[] deps;
        public int[] tour;
        public sparse table;

        public lca(ArrayList<Integer>[] adj, int N) {
            idx = 0;
            tins = new int[N];
            tour = new int[4 * N]; //more than enough
            deps = new int[tour.length];
            euler(adj, 0, -1, 0);
            table = new sparse(deps);
        }

        public int get(int u, int v) {
            return tour[table.get(
                    Math.min(tins[u], tins[v]),
                    Math.max(tins[u], tins[v])
            )];
        }

        public void euler(ArrayList<Integer>[] adj, int curr, int last, int dep) {
            tins[curr] = idx;
            tour[idx] = curr;
            deps[idx] = dep;
            idx++;

            for (int n : adj[curr]) {
                if (n == last) {
                    continue;
                }
                euler(adj, n, curr, dep + 1);
                tour[idx] = curr;
                deps[idx] = dep;
                idx++;
            }
        }

        class sparse {

            public int[][][] table; //also stores info on index

            public sparse(int[] nums) {
                int maxFit = maxPow(nums.length);
                table = new int[maxFit][nums.length][2];

                for (int i = 0; i < nums.length; i++) {
                    table[0][i] = new int[]{i, nums[i]};
                }

                for (int i = 1; i < maxFit; i++) {
                    for (int j = 0; j + (1 << i) - 1 < nums.length; j++) {
                        int[] left = table[i - 1][j];
                        int[] right = table[i - 1][j + (1 << (i - 1))];
                        if (left[1] < right[1]) {
                            table[i][j] = left;
                        } else {
                            table[i][j] = right;
                        }
                    }
                }
            }

            public int get(int l, int r) {
                int max = maxPow(r - l + 1) - 1; //maxpow includes 0th
                int[] left = table[max][l];
                int[] right = table[max][r - (1 << max) + 1];
                if (left[1] < right[1]) {
                    return left[0];
                } else {
                    return right[0];
                }
            }

            //returns the power not the number
            private int maxPow(int num) {
                int ret = 0;
                while (num > 0) {
                    num >>= 1;
                    ret++;
                }
                return ret;
            }
        }
    }

    static class BIT { //AKA BIT

        public int[] bit;
        
        public BIT(int N) {
            bit = new int[N + 1];
        }
        
        public long sum(int r) {
            r++;
            long ret = 0;
            while (r > 0) {
                ret += bit[r];
                r -= r & -r;
            }
            return ret;
        }

        //updates # at pos nums[idx]
        //does not reset nums[idx], but updates it (+/-)
        public void update(int idx, long v) {
            idx++;
            while (idx < bit.length) {
                bit[idx] += v;
                idx += idx & -idx;
            }
        }
    }
}
