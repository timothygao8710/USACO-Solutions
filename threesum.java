
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class threesum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("threesum.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("threesum.out")));
        
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(System.out);
     
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        int[] nums = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for(int i =0; i<N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        long[] ret = new long[Q];
        int qi = 0;
        int[][] qs = new int[Q][3];
        for(int i = 0; i<Q; i++){
            st = new StringTokenizer(br.readLine());
            qs[i] = new int[]{Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, i};
        }
        
        Arrays.sort(qs, (int[] a, int[] b) -> a[1] == b[1] ? b[0] - a[0] : a[1] - b[1]); 
        long[] tsum = new long[N];

        Map<Integer, long[]> sums = new HashMap<>(); //sum : start point
        it:
        for(int i = 0; i<nums.length; i++){
            for(int l = i-1; l >= 0 && sums.get(-nums[i]) != null; l--){
                tsum[l] += sums.get(-nums[i])[l];
            }

            for(int j = i-1; j>=0; j--){
                int s = nums[i] + nums[j];
                if(sums.get(s) == null) sums.put(s, new long[N]);
                sums.get(s)[j]++;
            }
            
            long sum = 0;
            int j = i;
            while(qs[qi][1] == i){
                for(; j>=qs[qi][0]; j--){
                    sum += tsum[j];
                }
                ret[qs[qi][2]] = sum;
                qi++;
                if(qi == Q) break it;
            }
        }
        
        for(long a : ret){
            pw.println(a);
        }
        
        pw.close();
        br.close();
    }
}
