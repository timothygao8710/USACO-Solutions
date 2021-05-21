
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

// package dp;
public class poetry {

    static int mod = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("poetry.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("poetry.out")));

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] sizes = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            sizes[i] = new ArrayList();
        }

        int[] all = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            all[i] = Integer.parseInt(st.nextToken());
            sizes[Integer.parseInt(st.nextToken())].add(all[i]);
        }

        long[] dp = new long[K + 1];
        dp[0] = 1;
        for (int i = 0; i < K; i++) {
            for (int j : all) {
                if (j + i <= K) {
                    dp[i + j] += dp[i];
                    dp[i + j] %= mod;
                }
            }
        }
        long[] ends = new long[sizes.length];
        for (int i = 0; i < sizes.length; i++) {
            for (int j : sizes[i]) {
                ends[i] += dp[K - j];
                ends[i] %= mod;
            }
        }
        
        int[] chars = new int[26];
        for (int i = 0; i < M; i++) {
            chars[br.readLine().charAt(0) - 'A']++;
        }

        long ret = 1;
        for (int i : chars) {
            if(i == 0) continue;
            long curr = 0;
            for (long j : ends) {
                if(j == 0) continue;
                curr += pow(j, i);
                curr %= mod;
            }
            ret *= curr;
            ret %= mod;
        }
        pw.println(ret);
        pw.close();
        br.close();
    }

    static long pow(long base, int pow) {
        if (pow == 0) {
            return 1;
        }
        long half = pow(base, pow / 2);
        long ret = (half * half) % mod;
        if (pow % 2 == 1) {
            ret *= base;
        }
        ret %= mod;
        return ret;
    }
}
