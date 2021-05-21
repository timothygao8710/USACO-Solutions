
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * -Take it slow, think-
 * Watch out for:
 * - Long/Int
 * - Edge cases (make test case)
 * - Unexpected behavior?
 *
 * @author timothy
 */
public class quad {
    static Integer[][] dp;
    static int N;
    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        N = Integer.parseInt(br.readLine());
        
        for(int i = 1; i<=100; i++){
            N = i;
            dp = new Integer[N+1][5];
            System.out.print(memo(i, 4) + ",");
        }
    }
    
    static int memo(int len, int cuts){
        if(cuts == 0){
            if(len == 0) return 1;
            return 0;
        }
        if(len <= 0) return 0;
        
        if(dp[len][cuts] != null) return dp[len][cuts];
        dp[len][cuts] = 0;
        
        for(int i = 1; i<=(N-1)/2; i++){
            dp[len][cuts] += memo(len - i, cuts-1);
        }
        return dp[len][cuts];
    }
}
