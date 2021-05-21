
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// package dp;

public class cowmbat {
    /*
    Although we have i,j, meaning that there aer multiple relative locations, addition has an inverse
    So we can make all numbers relative to a single number by using the inverse of the overlap(overcounting OP)
    */
    
    static int INF =  1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cowmbat.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowmbat.out")));
        
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(System.out);
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int[] nums = new int[N];
        String str = br.readLine();
        for(int i = 0; i<N; i++){
            nums[i] = str.charAt(i) - 'a';
        }
        
        int[][] adj = new int[M][M];
        
        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++){
                adj[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        //floyd warshall
        for(int k = 0; k<M; k++){
            for(int i = 0; i<M; i++){
                for(int j = 0; j<M; j++){
                    adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
                }
            }
        }
        
//        for(int[] i : adj){
//            System.out.println(Arrays.toString(i));
//        }
        
        //prefixes
        int[][] pre = new int[M][N+1];
        for(int i = 0; i<M; i++){
            for(int j = 1; j<=N; j++){
                pre[i][j] = pre[i][j-1] + adj[nums[j-1]][i];
//                System.out.print(pre[i][j] + " ");
            }
//            System.out.println();
        }
        
        int[] best = new int[M]; //for each character we def have a best(relative to start)
        int[][] dp = new int[N+1][M];   
        for(int i = 0; i<N; i++) Arrays.fill(dp[i], INF);
        Arrays.fill(best, INF);
        for(int i = N-K; i>=0; i--){
            int add = i+K; //we can consider this now because it's outside of our k-range
            int min = INF;
            for(int j : dp[add]) min = Math.min(min, j);
//            System.out.println(min);
            for(int j = 0; j<M; j++){
                best[j] = Math.min(best[j], min + pre[j][add]); //1-indexed
                dp[i][j] = best[j] - pre[j][i];
            }
//            System.out.println(i + " " + Arrays.toString(dp[i]));
        }
        int ret = INF;
        for(int i : dp[0]) ret = Math.min(ret, i);
        pw.println(ret);
        pw.close();
        br.close();
    }
}
/*
5 5 5
abcde
0 1 1 1 1
1 0 1 1 1
1 1 0 1 1
1 1 1 0 1
1 1 1 1 0
*/