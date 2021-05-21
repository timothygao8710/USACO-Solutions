
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeSet;

// package structures;
//this is the "cheap solve" version with more greedy
public class cardgame {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cardgame.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cardgame.out")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        TreeSet<Integer> bs = new TreeSet();
        for(int i=1; i<=2*N; i++) bs.add(i);
        for(int i = 0; i<N; i++){
            nums[i]=Integer.parseInt(br.readLine());
            bs.remove(nums[i]);
        }
        int[] pre = new int[N];
        int[] suf = new int[N+1];
        int last = 0;
        for(int i = 0; i<N; i++){
            Integer up = bs.ceiling(nums[i]);
            if(up != null){
                last++;
                bs.remove(up);
            }
            pre[i]=last;
        }
        
        //reinit
        for(int i = 1; i<=2*N; i++) bs.add(i);
        for(int i : nums) bs.remove(i);
        
        for(int i = N-1; i>=0; i--){
            suf[i]=suf[i+1];
            Integer down = bs.floor(nums[i]);
            if(down != null){
                suf[i]++;
                bs.remove(down);
            }
        }
        int ret = suf[0];
        for(int i = 0; i<N; i++){
            ret = Math.max(ret, pre[i] + suf[i+1]);
        }
//        System.out.println(ret);
        pw.println(ret);
        pw.close();
        br.close();
    }

}
