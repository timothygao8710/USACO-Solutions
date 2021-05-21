import java.io.*;
import java.util.*;

public class hayfeast {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("hayfeast.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("hayfeast.out")));
        
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int ret = Integer.MAX_VALUE;
        int N = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken());
        
        int[] num = new int[N];
        int[] spice = new int[N];
        
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            num[i] = Integer.parseInt(st.nextToken());
            spice[i] = Integer.parseInt(st.nextToken());
        }

        long curr = 0;
        Deque<Integer> mono = new ArrayDeque();
        
        int l = 0;
        int r = 0;
        
        while(r < N){
            if(curr < M){
                curr += num[r];
                while(!mono.isEmpty() && spice[r] > mono.getLast()){
                    mono.removeLast();
                }
                mono.addLast(spice[r]);
                r++;
            }else{
                curr -= num[l];
                if(spice[l] == mono.getFirst()) mono.removeFirst();
                l++;
            }
            if(curr >= M){
                ret = Math.min(ret, mono.getFirst());
            }
        }
        pw.println(ret);
        pw.close();
        br.close();
    }
}
