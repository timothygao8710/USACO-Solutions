
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class modern {

    static Long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        dp = new Long[N][N];
        pw.println(memo(nums, 0, N - 1));
        pw.close();
        br.close();
    }

    static long memo(int[] n, int l, int r) {
        if (l > r) {
            return 0;
        }
        if(dp[l][r] != null) return dp[l][r];
        int o = 0;
        for(int i = l; i<=r; i++){
            if(n[i] == n[l]) o++;
        }
        if(o == 1){
            if(n[l] == 0) return memo(n, l+1, r);
            return memo(n, l+1, r) + 1;
        }
        long ret = 1 + memo(n, l+1, r);
        for(int i = l+1; i<=r; i++){
            if(n[l] == n[i]){
                ret = Math.min(ret, memo(n, l+1, i-1) + memo(n, i, r));
            }
        }
        dp[l][r] = ret;
        return dp[l][r];
    }
}
/*
10
1 2 3 4 5 6 7 8 0 {
*/