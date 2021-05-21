// package dp;

import java.io.*;
import java.util.*;

public class sleepingcows {
    static int mod = 1_000_000_007;
    static Long[][][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
     
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[] B = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            B[i] = Integer.parseInt(st.nextToken());
            int l = 0;
            int r = N;
            while(l < r){
                int mid = (l + r)/2;
                if(A[mid] >= B[i]){
                    r = mid;
                }else{
                    l = mid+1;
                }
            }
            B[i] = N - 1 -r;
        }
        Arrays.sort(B);
        dp = new Long[N][2][N];
        pw.println(memo(B, 0, 0, 0));
        pw.close();
        br.close();
    }
    
    static long memo(int[] B, int idx, int mode, int fill){
        if(idx == B.length){
            if(fill != 0) return 0;
            return 1;
        }
        if(dp[idx][mode][fill] != null) return dp[idx][mode][fill];
        int ret = 0;
        if(mode == 0){
            ret = 
        }
    }
}
