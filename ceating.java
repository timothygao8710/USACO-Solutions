
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ceating {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
//min the max or max the min -> bsearch
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        
        int[] nums = new int[N];
        for(int i = 0; i<N; i++) nums[i] = Integer.parseInt(br.readLine());
        
        long l = 0;
        long r = Long.MAX_VALUE/3;
        while(l < r){
            long mid = (l+r+1)/2;
            if(!good(D, nums, mid)){
                r = mid-1;
            }else{
                l = mid;
            }
        }
        pw.println(l);
        pw.close();
        br.close();
    }
    static boolean good(int D, int[] nums, long min){
        long H = 0;
        int idx = 0;
        for(int i= 0; i<D; i++, H /= 2){
            while(H < min){
                if(idx == nums.length) return false;
                H += nums[idx];
                idx++;
            }
        }
        return true;
    }
}
