
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class telephone {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[][] vis = new boolean[N][K+1];
        //|i-j| means linear edges
        
        int[] nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        
        boolean[][] con = new boolean[K+1][K+1];
        for(int i = 0; i<K; i++){
            String line = br.readLine();
            for(int j = 0; j<K; j++){
                con[i+1][j+1] = line.charAt(j) == '1';
            }
        }
        int ret = -1;
        con[0] = con[nums[0]].clone();
        Deque<int[]> bfs = new ArrayDeque<>();
        bfs.add(new int[3]); //type : index : step
        while(!bfs.isEmpty()){
            int[] curr = bfs.pollFirst();
            if(curr[1] == N-1 && curr[0] == nums[N-1]){
                ret = curr[2];
                break;
            }
            if(vis[curr[1]][curr[0]]) continue;
            vis[curr[1]][curr[0]] = true;
            
            if(con[curr[0]][nums[curr[1]]]){
                bfs.addFirst(new int[]{nums[curr[1]], curr[1], curr[2]});
            }
            
            curr[2]++;
            if(curr[1] != 0){
                bfs.addLast(new int[]{curr[0], curr[1]-1, curr[2]});
            }
            if(curr[1] != N-1){
                bfs.addLast(new int[]{curr[0], curr[1]+1, curr[2]});
            }
        }
        pw.println(ret);
        pw.close();
        br.close();
    }
}
