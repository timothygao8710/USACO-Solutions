
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

// package dp;
public class bovineII {

    /*
    Idea: instead of transitioning back indicies we transition back characters because only the realtive ordering of
    characters matter, not the actual indices those characters are at
    
    Storing invalids for future use
     */
    static final char[] chars = new char[]{'A', 'C', 'G', 'T'};
    static int mod = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String s = br.readLine();

        //|a  ||b  m|...k
        //a for validating m
        //b for validating m'
        //m as the prev
        long[][][][] dp = new long[s.length()][4][4][4];
        for (int i = 0; i < 4; i++) {
            if (s.charAt(0) == chars[i] || s.charAt(0) == '?') {
                for (int a = 0; a < 4; a++) {
                    dp[0][a][i][i] = 1;
                }
            }
        }
        for (int i = 1; i < s.length(); i++) {
            for (int j = 0; j < 4; j++) {
                if (s.charAt(i) != chars[j] && s.charAt(i) != '?') {
                    continue;
                }
                for (int a = 0; a < 4; a++) {
                    for (int b = 0; b < 4; b++) {
                        for (int m = 0; m < 4; m++) {
                            if (j != m) {
                                dp[i][a][b][j] += dp[i - 1][a][b][m];
                                dp[i][a][b][j] %= mod;
                            }
                            if (m == a) {
                                dp[i][b][j][j] += dp[i - 1][a][b][m];
                                dp[i][b][j][j] %= mod;
                            }
                        }
                    }
                }
            }
        }

        long ret = 0;
        for (int b = 0; b < 4; b++) {
            for (int a = 0; a < 4; a++) {
                ret += dp[s.length() - 1][a][b][a];
                ret %= mod;
            }
        }

        pw.println(ret);

        pw.close();

        br.close();
    }
}
