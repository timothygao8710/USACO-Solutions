
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

// package graph;
public class lasers {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("lasers.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lasers.out")));

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(System.out);

        int ret = -1;
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] start = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        int[] end = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        
        HashMap<Integer, Integer>[] cons = new HashMap[2];
        cons[0] = new HashMap();
        cons[1] = new HashMap();
        boolean[][] vis = new boolean[N+2][2];
        ArrayList<Integer>[][] adj = new ArrayList[N+2][2];
        for (int i = 0; i < adj.length; i++) {
            adj[i][0] = new ArrayList();
            adj[i][1] = new ArrayList();
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int[] c = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            process(c, adj, cons, i);
        }

        process(start, adj, cons, N);
        process(end, adj, cons, N+1);
        Queue<int[]> bfs = new ArrayDeque<>(); //type : idx : steps
        
        bfs.add(new int[]{0, cons[0].get(start[0]), 0});
        bfs.add(new int[]{1, cons[1].get(start[1]), 0});
        
        while(!bfs.isEmpty()){
            int type = bfs.peek()[0];
            int i = bfs.peek()[1];
            int steps = bfs.poll()[2];
            
            if(cons[type].get(end[type]) == i){
                ret = steps;
                break;
            }
            vis[i][type] = true; //for first(i'm lazy)
            for(int c : adj[i][type]){
                if(vis[c][type^1]) continue;
                vis[c][type^1] = true;
                bfs.add(new int[]{type^1, c, steps+1});
            }

        }
        pw.println(ret);
        pw.close();
        br.close();
    }

    static void process(int[] c, ArrayList<Integer>[][] adj, HashMap<Integer, Integer>[] cons, int i) {
        for (int j = 0; j < 2; j++) {
            if (!cons[j].containsKey(c[j])) {
                cons[j].put(c[j], i);
            }
        }
        for (int j = 0; j < 2; j++) {
            adj[cons[j].get(c[j])][j].add(cons[j ^ 1].get(c[j ^ 1]));
        }
    }
}
