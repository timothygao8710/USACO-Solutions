
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ctravel {
    static int[] xDir = new int[]{0, 0, 1, -1};
    static int[] yDir = new int[]{1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        
        boolean[][] grid = new boolean[N][M];
        for(int i = 0; i<N; i++){
            String line = br.readLine();
            for(int j = 0; j<M; j++){
                grid[i][j] = line.charAt(j) == '.';
            }
        }
      
        int[][] dp = new int[N][M];
        st = new StringTokenizer(br.readLine());
        int sy = Integer.parseInt(st.nextToken())-1;
        int sx = Integer.parseInt(st.nextToken())-1;
        dp[sy][sx] = 1;
        for(int i = 0; i<T; i++){
            
            int[][] temp = new int[N][M];
            for(int j = 0; j<N; j++){
                for(int k = 0; k<M; k++){
                    for(int d = 0; d<4; d++){
                        int dY = j + yDir[d];
                        int dX = k + xDir[d];
                        if(dY < N && dX < M && dY >= 0 && dX >=0 && grid[dY][dX]){
                            temp[j][k] += dp[dY][dX];
                        }
                    }
                }
            }
            dp = temp;
        }
        pw.println(dp[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1]);
        pw.close();
        br.close();
    }
}
