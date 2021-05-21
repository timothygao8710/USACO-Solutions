
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ants {

    static int mod = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int A = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] sz = new int[T];
        for (int i = 0; i < A; i++) {
            sz[Integer.parseInt(br.readLine()) - 1]++;
        }

        int[] dp = new int[A + 1];
        dp[0] = 1;
        for (int i = 0; i < T; i++) {
            if (sz[i] == 0) {
                continue;
            }
            int[] temp = dp.clone();
            int l = 0;
            int r = 0;
            int sum = 0;
            while (r < A) {
                if (r - l < sz[i]) {
                    sum += dp[r];
                    sum %= mod;
                    r++;
                    temp[r] += sum;
                    temp[r] %= mod;
                } else {
                    sum = (mod + sum - dp[l]) % mod;
                    l++;
                }
            }
            dp = temp;
        }
//        System.out.println(Arrays.toString(dp));
        int ret = 0;
        for (int i = S; i <= L; i++) {
            ret = (ret + dp[i]) % mod;
        }
        pw.println(ret);
        pw.close();
        br.close();
    }
}
