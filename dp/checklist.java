
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// package dp;
public class checklist {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("checklist.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("checklist.out")));

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());

        int[][] hc = new int[H][2];
        int[][] gc = new int[G][2];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            hc[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }

        for (int i = 0; i < G; i++) {
            st = new StringTokenizer(br.readLine());
            gc[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }

        long[][][] dp = new long[H + 1][G + 1][2];
        for (long[][] a : dp) {
            for (long[] b : a) {
                Arrays.fill(b, 1_000_000_420_6969L);
            }
        }
        dp[1][0][0] = 0;

        for (int i = 0; i <= H; i++) {
            for (int j = 0; j <= G; j++) {
                if (i - 1 >= 0) {
                    dp[i][j][0] = Math.min(dp[i][j][0],
                            Math.min((i >= 2) ? dp[i - 1][j][0] + dist(hc[i - 2], hc[i - 1]) : Long.MAX_VALUE,
                                    (j - 1 < 0) ? Long.MAX_VALUE : dp[i - 1][j][1] + dist(gc[j - 1], hc[i - 1]))
                    );
                }

                if (j - 1 >= 0) {
                    dp[i][j][1] = Math.min(
                            dp[i][j][1],
                            Math.min(
                                    (i - 1 < 0) ? Long.MAX_VALUE : dp[i][j - 1][0] + dist(gc[j - 1], hc[i - 1]),
                                    (j >= 2) ? dp[i][j - 1][1] + dist(gc[j - 1], gc[j - 2]) : Long.MAX_VALUE
                            )
                    );
                }
            }
        }
        pw.println(dp[H][G][0]);
        pw.close();
        br.close();
    }

    static long dist(int[] a, int[] b) {
        return ((long) (a[0] - b[0]) * (a[0] - b[0]))
                + ((long) (a[1] - b[1]) * (a[1] - b[1]));
    }
}
/*
3 2
0 0
1 0
2 0
0 3
1 3
*/