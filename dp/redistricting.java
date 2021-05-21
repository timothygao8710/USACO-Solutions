// package dp;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class redistricting {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("redistricting.in"));
//        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("redistricting.out")));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] nums = new int[N];
        String line = br.readLine();
        for (int i = 0; i < N; i++) {
            nums[i] = (line.charAt(i) == 'H' ? 1 : -1);
        }

        int[] suf = new int[N + 1];
        int[] dp = new int[N + 1];
        TreeMap<Integer, TreeMap<Integer, Integer>> window = new TreeMap<>();
        window.put(0, new TreeMap<>());
        window.get(0).put(0, 1);

        for (int i = N - 1; i >= 0; i--) {
            suf[i] = nums[i] - suf[i+1];
            Integer best = window.firstKey();
            dp[i] = best+1;
            if (window.get(best).lastKey() + suf[i] >= 1) {
//                System.out.println(i + " " + suf[i] + " " + window.get(best).lastKey());
                dp[i]--;
            }

            //add
            suf[i] *= -1;
            if(window.get(dp[i]) == null) window.put(dp[i], new TreeMap<>());
            TreeMap<Integer, Integer> k = window.get(dp[i]);
            if(k.get(suf[i]) == null) k.put(suf[i], 0);
            k.put(suf[i], k.get(suf[i])+1);
            
            
            //remove
            if (i + K <= N) {
                k = window.get(dp[i+K]);
                k.put(suf[i+K], k.get(suf[i+K])-1);
                if(k.get(suf[i+K]) == 0) k.remove(suf[i+K]);
                if(k.isEmpty()) window.remove(dp[i+K]);
            }
        }
//        System.out.println(Arrays.toString(dp));
        pw.println(window.firstKey());
        pw.close();
        br.close();
    }
}
