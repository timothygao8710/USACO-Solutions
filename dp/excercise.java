// package dp;

import java.io.*;
import java.util.*;

public class excercise {
    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("REPLACE.in"));
//        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("REPLACE.out")));
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
     
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int mod = Integer.parseInt(st.nextToken());
        
        List<Integer> primes = new ArrayList();
        Set<Integer> com = new HashSet();
        s:
        for(int i = 2; i<=N; i++){
            if(com.contains(i)) continue;
            primes.add(i);
            for(int j = i*2; j<=N; j += i){
                com.add(j);
            }
        }
        
        long[] dp = new long[N+1];
        dp[0] = 1;
        for(int p : primes){
            for(int sz = 1; sz<=N; sz *= p){
                for(int i = N-sz; i>=0; i--){
                    dp[i+sz] = Math.max(dp[i+sz], (dp[i]*sz)%mod);
                }
            }
        }
        
        long ret = ((dp[N]+1)*(dp[N]))/2;
        pw.println(ret%mod);
        pw.close();
        br.close();
    }
}
