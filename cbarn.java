
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;


public class cbarn {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cbarn.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cbarn.out")));

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(System.out);
        
        int N = Integer.parseInt(br.readLine());
        assert N == 10 : "NO WAY HOZA";
        int[] nums = new int[2*N];
        for(int i = 0; i<N; i++){
            nums[i] = nums[N+i] = Integer.parseInt(br.readLine());
        }
        
        Deque<int[]> dq = new ArrayDeque();
        int i = 0;
        int last = 0;
        long ret = 0;
        while(i - last < N){
            if(dq.isEmpty()){
                if(nums[i]==0){
                    ret = 0;
                    last = -1;
                }else{
                    if(last == -1) last = i;
                    if(nums[i] > 1){
                        dq.add(new int[]{i, nums[i]-1});
                    } 
                }
            }else{
                int[] start = dq.peekFirst();
                start[1]--;
                ret += (i-start[0])*(i-start[0]);
                if(start[1] == 0) dq.pollFirst();
                if(nums[i] > 0){
                    dq.addLast(new int[]{i, nums[i]});
                }
            }
            i++;
        }
        
        pw.println(ret);
        pw.close();
        br.close();
    }

}
