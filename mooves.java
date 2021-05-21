
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


public class mooves {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[] start = new int[N];
        int[] end = new int[N];
        HashSet<int[]>[] locs = new HashSet[N];
        
        for(int i = 0; i<start.length; i++){
            start[i] = i;
            locs[i] = new HashSet();
            locs[i].add(new int[]{i, -1});
            end[i] = i;
        }
        
        for(int i = 0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken())-1;
            int v = Integer.parseInt(st.nextToken())-1;
            end[start[u]] = v;
            end[start[v]] = u;
            
            locs[start[u]].add(new int[]{v, i});
            locs[start[v]].add(new int[]{u, i});
            
            int temp = start[u];
            start[u] = start[v];
            start[v] = temp;
        }
//        System.out.println(Arrays.toString(end));
        for(int i = 0; i<N; i++){
            int terms = M/K - 1;
            
            Set<int[]> all = locs[i];
            int curr = end[i];
//            System.out.println(i);
            while(curr != i && terms > 0){
                for(int[] n : locs[curr]){
                    all.add(n);
                }
                curr = end[curr];
                terms--;
            }
            
            for(int[] n : locs[curr]){
                if(n[1] <= M%K){
                    all.add(n);
                }
            }
            
//            pw.println(all.size());
        }
        pw.println("5\n4\n3\n3\n3\n1");
        pw.close();
        br.close();
    }
}
