import java.io.*;
import java.util.*;

public class buffet {

    static ArrayList<Integer>[] adj;
    static int[] values;
    static Integer[][] dp;
    static int E;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("buffet.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("buffet.out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        dp = new Integer[N + 1][N + 1];
        adj = new ArrayList[N + 1];
        values = new int[N + 1];
        values[0] = -1;
        
        for (int i = 1; i < adj.length; i++) {
            adj[i] = new ArrayList();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            values[i] = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            for (int j = 0; j < n; j++) {
                adj[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        int ret = 0;
        for (int i = 1; i <= N; i++) {
            ret = Math.max(ret, memo(i, 0));
        }
        
        pw.println(ret);
        pw.close();
        br.close();
    }

    static int memo(int curr, int last) {
        if (dp[curr][last] == null) {
            dp[curr][last] = 0;

            int leave = 0;
            int take = values[last] < values[curr] ? values[curr] : 0;

            for (int j : adj[curr]) {
                leave = Math.max(leave, memo(j, last) - E);
                if (values[last] < values[curr]) {
                    take = Math.max(take, memo(j, curr) + values[curr] - E);
                }
            }
            dp[curr][last] = Math.max(leave, take);
        }
        return dp[curr][last];
    }
}
