
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// package dp;

public class spainting {
    static int mod = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("spainting.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("spainting.out")));
        
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(System.out);
     
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
      
        long pow = 1;
        long[] dp = new long[N];
        long[] pre = new long[N+1];
        
        for(int i = 0; i<N; i++){
            pow *= M;
            pow %= mod;
            
            dp[i] = (pre[i] - pre[Math.max(0,i-K+1)]+mod)%mod;
            dp[i] *= M-1;
            dp[i] %= mod;
            
            if(i-K+1 < 0) dp[i]++; //considering all without b4
            
            pre[i+1] = pre[i] + dp[i];
            pre[i+1] %= mod;
        }
        
        pw.println((pow + mod - (dp[N-1]*M)%mod)%mod);
        pw.close();
        br.close();
    }
}
