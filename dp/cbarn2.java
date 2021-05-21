
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// package dp;

public class cbarn2 {
    static long INF = 100000000000000L;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cbarn2.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cbarn2.out")));
        
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(System.out);
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        long[] nums = new long[2*N];
        for(int i = 0; i<N; i++) nums[i] = nums[N+i] = Integer.parseInt(br.readLine());
        
        long ret = INF;
        for(int s = 0; s<N; s++){ //start loc
            long[][] dp = new long[2*N][K];
            
            for(long[] i : dp){
                Arrays.fill(i, INF);
            }
            
            for(int i = s+N-1; i>=s; i--){
                long cost = 0;
                for(int j = i; j<s+N-1; j++){
                    for(int k = 1; k<K; k++){
                        dp[i][k] = Math.min(dp[i][k], dp[j+1][k-1] + cost);
                    }
                    cost += nums[j+1]*(j-i+1);
                }
                dp[i][0] = cost;
            }
            ret = Math.min(ret, dp[s][K-1]);
        }
        
        pw.println(K >= N ? 0 : ret);
        pw.close();
        br.close();
    }
    
}
