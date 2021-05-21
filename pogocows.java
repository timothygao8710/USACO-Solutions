
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// package exam;

public class pogocows {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
     
        int N = Integer.parseInt(br.readLine());
        int[][] coords = new int[N][2];
        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            coords[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }
        Arrays.sort(coords, (int[] a, int[] b) -> a[0] - b[0]);
        long ret = 0;
        long[][] dp = new long[N][N];
        for(int i = N-1; i>=0; i--){
            int p1 = 0;
            int p2 = N-1;
            long max = 0;
            
            while(p1 <= i){
                while(p2 > i && coords[i][0] - coords[p1][0] <= coords[p2][0] - coords[i][0]){
                    max = Math.max(max, dp[i][p2]);
                    p2--;
                }
                dp[p1][i] = max+coords[i][1];
                ret = Math.max(ret, dp[p1][i]);
                p1++;
            }
        }
        dp = new long[N][N];
        for(int i = N-1; i>=0; i--){
            int p1 = i-1;
            int p2 = i+1;
            long max = 0;
            
            while(p1 >= 0){
                while(p2 < N && coords[i][0] - coords[p1][0] >= coords[p2][0] - coords[i][0]){
                    max = Math.max(max, dp[i][p2]);
                    p2++;
                }
                dp[p1][i] = max+coords[i][1];
                dp[p1][p1] = Math.max(dp[p1][p1], dp[p1][i] + coords[p1][1]);
                ret = Math.max(ret, dp[p1][i]);
                ret = Math.max(ret, dp[p1][p1]);
                p1--;
            }
        }
        pw.println(ret);
        pw.close();
        br.close();
    }
}
