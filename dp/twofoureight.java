
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

// package dp;

public class twofoureight {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("248.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("248.out")));
        
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(System.out);
     
        int N = Integer.parseInt(br.readLine());
        int ret = -1;
        int[][] dp = new int[N][N];
        for(int i = 0; i<N; i++) dp[0][i] = Integer.parseInt(br.readLine());
        for(int i = 1; i<N; i++){
            for(int j = 0; j+i<N; j++){
                dp[i][j] = -1;
                for(int k = j; k<i+j; k++){
                    int l = dp[k-j][j];
                    int r = dp[i+j-k-1][k+1];
                    if(l == r){
                        dp[i][j] = Math.max(dp[i][j], l+1);
                    }
                }
                ret = Math.max(ret, dp[i][j]);
            }
        }
        pw.println(ret);
        pw.close();
        br.close();
    }
}
