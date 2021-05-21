import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// package dp;
public class talent {

    //There is no greedy here because fractions affect the final results by different amounts
    //ratios can be reduced while our final result cannot(we can't reduceweight and cows both by 2 and have that for sure be a thing)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("talent.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("talent.out")));

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        long[][] cows = new long[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cows[i] = new long[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }
        int l = 0;
        int r = Integer.MAX_VALUE / 2 - 5;
       
        while (l < r) {
            int mid = (l + r + 1) / 2;
            if (good(cows, mid, W)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        pw.println(l);
        pw.close();
        br.close();
    }

    //as ratio decreases, more cows are able to be taken
    static boolean good(long[][] cows, int ratio, int W) {
        //talent - weight*ratio
        long[][] dp = new long[cows.length+1][W+1];
        
        for(long[] i : dp) Arrays.fill(i, -10000000000000L);
        dp[cows.length][0] = 0;
        for(int i = cows.length-1; i>=0; i--){
            long worth = 1000*cows[i][1] - ratio * cows[i][0];
            for(int j = 0; j<=W; j++){//surjection
                dp[i][j] = Math.max(dp[i][j], dp[i+1][j]);
                if(j + cows[i][0] >= W){
                    dp[i][W] = Math.max(dp[i][W], dp[i+1][j] + worth);
                }else{
                    dp[i][j + (int)cows[i][0]] = dp[i+1][j] + worth;
                }
            }
        }
        return dp[0][W] >= 0;
    }
}
