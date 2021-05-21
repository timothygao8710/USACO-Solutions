
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// package exam;

public class piggyback {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
     
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] costs = new int[]{Integer.parseInt(st.nextToken()), 
                Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        ArrayList<Integer>[] adj = new ArrayList[N];
        for(int i = 0; i<N; i++) adj[i] = new ArrayList();
        for(int i = 0; i<M; i++){
            int u = Integer.parseInt(st.nextToken())-1;
            int v = Integer.parseInt(st.nextToken())-1;
            adj[u].add(v);
            adj[v].add(u);
        }
        
        Queue<int[]> pq = new PriorityQueue(new Comparator<int[]>(){
           public int compare(int[] a, int[] b){
                return a[1] - b[1];
           } 
        });
        
        
        
        pw.close();
        br.close();
    }
}
