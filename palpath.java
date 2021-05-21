
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class palpath {
    static int mod = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("palpath.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("palpath.out")));
        
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(System.out);
        
        int N = Integer.parseInt(br.readLine());
        char[][] chars = new char[N][N];
        long[][] dp = new long[N][N];
        
        for(int i = 0; i<N; i++){
            String line = br.readLine();
            for(int j =0; j<N; j++){
                chars[i][j] = line.charAt(j);
            }
        }
        dp[0][0] = 1;
        
        for(int i = 0; i<N; i++){
            long[][] curr = new long[N][N];
            for(int j = 0; j<=i; j++){
                int y = j;
                int x = i-j;
                for(int k = 0; k<=i; k++){
                    int a.y = N-1-k;
                    int x1 = N-1-(i-k);
                    if(chars[y][x] != chars[a.y][x1]) continue;
                    curr[j][k] += dp[j][k];
                    if(k != 0) curr[j][k] += dp[j][k-1];
                    if(j != 0){
                        curr[j][k] += dp[j-1][k];
                        if(k != 0) curr[j][k] += dp[j-1][k-1];
                    }
                    curr[j][k] %= mod;
                }
            }
            dp = curr;
        }
        
        long ret = 0;
        for(int i = 0; i<N; i++){
            ret += dp[i][N-1-i];
            ret %= mod;
        }
        pw.println(ret);
        pw.close();
        br.close();
    }
}
