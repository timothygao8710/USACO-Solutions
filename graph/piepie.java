
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
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
public class piepie {
    //a directed edge between nodes A and B to exist if Bessie can gift pie B to Elsie after receiving pie A from Elsie
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("piepie.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("piepie.out")));
        
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
   
        int[][][] pies = new int[2][N][2];
        //B or E
        //idx
        //according to b, according to e
        int[][] link = new int[N][2]; //for A only
        
        int[] ret = new int[N];
        Arrays.fill(ret, -1);
        Queue<int[]> bfs = new ArrayDeque();
        DSU[] taken = new DSU[2];
        taken[0] = new DSU(N+1);
        taken[1] = new DSU(N+1);
        
        //which cow : index : step
        for(int i = 0; i<2; i++){
            for(int j = 0; j<N; j++){
                st = new StringTokenizer(br.readLine());
                pies[i][j][0] = Integer.parseInt(st.nextToken());
                pies[i][j][1] = Integer.parseInt(st.nextToken());
                
                if(i == 0){
                    link[j] = new int[]{j, pies[i][j][1]};
                }
            }
        }
        Arrays.sort(link, (int[] a, int[] b) -> a[1] - b[1]);
        Arrays.sort(pies[0], (int[] a, int[] b) -> a[1] - b[1]);
        Arrays.sort(pies[1], (int[] a, int[] b) -> a[0] - b[0]);
        
        
        for(int i = 0; i<2; i++){
            for(int j = 0; j<N && pies[i][j][i^1] == 0; j++){
                bfs.add(new int[]{i, j, 1});
                if(j == 0){
                    taken[i].union(j, N);
                }else{
                    taken[i].union(j, j-1);
                }
            }
        }
        
        while(!bfs.isEmpty()){
            int[] curr = bfs.poll();
//            System.out.println(Arrays.toString(curr));
            if(curr[0] == 0){
                ret[link[curr[1]][0]] = curr[2];
            }
            
            int cow = curr[0]^1; //opposite cow gives
            int goodness = pies[curr[0]][curr[1]][curr[0]]; //pies must be >= goodness - D
            int idx = taken[cow].find(find(pies[cow], goodness, cow^1));
            int step = curr[2];
            
            while(idx != N && 
                    pies[cow][idx][cow^1] + D >= goodness
                    ){
                bfs.add(new int[]{cow, idx, step+1});
                if(idx == 0){
                    taken[cow].union(idx, N);
                }else{
                    taken[cow].union(idx, idx-1);
                }
                idx = taken[cow].find(idx);
            }
        }
        
//        for(int i : ret) System.out.println(i);
        for(int i : ret) pw.println(i);
        pw.close();
        br.close();
    } 
    //gives index
    static int find(int[][] nums, int val, int idx){
        int l = -1;
        int r = nums.length-1;
        while(l < r){
            int mid = (l+r+1)/2;
            if(nums[mid][idx] <= val){
                l = mid;
            }else{
                r = mid-1;
            }
        }
        return l == -1 ? nums.length : l;
    }
    
    static class DSU {
        public int[] parent;        
        public DSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        
        public int find(int u) {
            if (u != parent[u]) {
                parent[u] = find(parent[u]);
            }
            return parent[u];
        }
        
        public void union(int u, int v) {
            int ru = find(u);
            int rv = find(v);
            
            parent[ru] = rv;
        }
    }
}
