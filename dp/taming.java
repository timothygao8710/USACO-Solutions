
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// package dp;

public class taming {
    static int INF = (int)(1e9+7);
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("taming.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("taming.out")));
        
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(System.out);
        //the question comes down to: do we start a new breakout or not?
        //not always greedy
        //although we could make transitions O(1) by
        //storing for each element the "intended" start(there is always a single unique one because -1 each time)
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        int[][] dp = new int[N][N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
            Arrays.fill(dp[i], INF);
        }
        
        for(int i = N-1; i>=0; i--){
            int changes = 0; //by xxxtencion r.i.p
            for(int j = 0; i+j<N-1; j++){
                if(nums[i+j] != j) changes++;
                for(int k = 1; k<N; k++){
                    dp[i][k] = Math.min(dp[i][k], dp[i+j+1][k-1] + changes);
                }
            }
            if(nums[N-1] != N-1-i) changes++;
            dp[i][0] = changes;
        }
        
        for(int i : dp[0]) pw.println(i);
        
        pw.close();
        br.close();
    }
}
