
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class allow {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        
        int[][] coins = new int[N][2];
        for(int i = 0; i<N; i++){
            coins[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }//val : amount
        
        int ret = 0;
        
        Arrays.sort(coins, (int[] a, int[] b) -> b[0] - a[0]);
        
        for(int i = 0; i<coins.length; i++){
            int c = C;
            int last = -1;
            for(int j = i; j<N; j++){
                int sub = Math.min(coins[j][1], c/Math.min(c,coins[j][0]));
                        
                if(c <= 0){
                    ret++;
                    break;
                }
            }
        }
        
        pw.close();
        br.close();
    }
}
