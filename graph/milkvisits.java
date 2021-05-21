
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

// package graph;

/**
 * - Long/int
 * - Draw stuff
 * - Use comments
 * - Drink Water
 * - Reread Problem
 *
 * - package/pw.close
 *
 * @author timothy
 */
public class milkvisits {
    //Idea: offline processing
    //as we run thru, we look at the latest of that type
//that's a lot of variables lmao
    static int[] tout;
    static int[] tin;
    static int idx;
    
    static ArrayList<int[]>[] queries;
    
    static int[] types;
    static Stack<Integer>[] latest;
    static boolean[] ret;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("milkvisits.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milkvisits.out")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] adj = new ArrayList[N]; 
        queries = new ArrayList[N];
        types = new int[N];
        latest = new Stack[N];
        tout = new int[N];
        tin = new int[N];
        idx = 0;
        ret = new boolean[Q];
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<adj.length; i++){
            adj[i] = new ArrayList();
            queries[i] = new ArrayList();
            latest[i] = new Stack();
            types[i] = Integer.parseInt(st.nextToken())-1;
        }
                
        
        for(int i = 0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken())-1;
            int v = Integer.parseInt(st.nextToken())-1;
            adj[u].add(v);
            adj[v].add(u);
        }
        euler(0, -1, adj);
        
        for(int i = 0; i<Q; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken())-1;
            int v = Integer.parseInt(st.nextToken())-1;
            int type = Integer.parseInt(st.nextToken())-1;
            
            queries[u].add(new int[]{v, type, i});
            queries[v].add(new int[]{u, type, i});
        }
        
        dfs(0, -1, adj);
        
        for(boolean a : ret){
            pw.print(a ? 1 : 0);
        }
        pw.close();
        br.close();
    }
    
    //we need it to happen immediately(for if curr is type) rather than next up but we can make expcetion so we good
    static void dfs(int curr, int last, ArrayList<Integer>[] adj){
        if(last != -1) latest[types[last]].push(curr);
        for(int[] q : queries[curr]){
            if(types[curr] == q[1] || (
                    !latest[q[1]].isEmpty() &&
                    !isAnc(latest[q[1]].peek(), q[0])
                )){
//                if(!latest[q[1]].isEmpty()) System.out.println(curr + " " + " " + q[1] + " "+  latest[q[1]].peek());
                ret[q[2]] = true;
            }
        }
        for(int n : adj[curr]){
            if(n == last) continue;
            dfs(n, curr, adj);
        }
        if(last != -1) latest[types[last]].pop();
    }
    
    static void euler(int curr, int last, ArrayList<Integer>[] adj){
        tin[curr] = idx;
        idx++;
        
        for(int n : adj[curr]){
            if(last == n) continue;
            euler(n, curr, adj);
        }
        
        tout[curr] = idx;
        idx++;
    }
    //is u an ancestor of v
    static boolean isAnc(int u, int v){
        return tin[u] <= tin[v] && tout[u] >= tout[v];
    }
    
}
