
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class mildredee {

    static Integer[] dp;
    static int[][] match;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        char[] chars = br.readLine().toCharArray();
        Map<Character, Integer> mp = new HashMap();
        for(int i = 0; i<chars.length; i++){
            if(mp.get(chars[i]) == null) mp.put(chars[i], mp.size());
        }
        dp = new Integer[1<<mp.size()]; //apparently mildred is never in ;__; gotta read problem better
        dp[dp.length-1] = 0;
        match = new int[mp.size()][mp.size()];
        for(int i = 1; i<chars.length; i++){
            match[mp.get(chars[i-1])][mp.get(chars[i])]++;
        }
        
        pw.println(memo(0));
        br.close();
        pw.close();
    }
    
    static int memo(int bitset){
        if(dp[bitset] != null) return dp[bitset];
        int ret = Integer.MAX_VALUE;
        for(int i = 0; i<match.length; i++){
            if(((1<<i)&(bitset)) != 0) continue;
            int bad = 0;
            if(i == 0) bad++; //this is the first character in sequence, special
            for(int j = 0; j<match.length; j++){
                if(((1<<j)&(bitset)) == 0){
                    bad += match[i][j];
                }                
            }
            ret = Math.min(ret, bad + memo(bitset + (1<<i))); //might need () around 1<<i
        }
        dp[bitset] = ret;
        return ret;
    }
}
