
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;


public class fuel {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int cap = Integer.parseInt(st.nextToken());
        int gas = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int[][] stations = new int[N+1][2];
        stations[0] = new int[]{0, Integer.MAX_VALUE};
        for(int i = 1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            stations[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }
        Arrays.sort(stations, (int[] a, int[] b) -> a[0] - b[0]);
        pw.println(solve(stations, cap, gas, end));
        pw.close();
        br.close();
    }
    
    static long solve(int[][] nodes, int mx, long curr, int D){
        if(curr < nodes[0][0] || D - nodes[nodes.length-1][0] > mx) return -1;        
        long ret = 0;
        Stack<int[]> mono = new Stack<>();
        mono.push(new int[]{D, -1});
        int[] smaller = new int[nodes.length];
        for(int i = nodes.length-1; i>=0; i--){
            while(mono.peek()[1] > nodes[i][1]){
                mono.pop();
            }
            smaller[i] = mono.peek()[0] - nodes[i][0];
            mono.push(nodes[i]);
        }
//        System.out.print(Arrays.toString(smaller));
        for(int i = 1; i<nodes.length; i++){
            curr -= nodes[i][0] - nodes[i-1][0];
            if(curr < 0) return -1;
            long add = Math.max(0,Math.min(smaller[i], mx) - curr);
            curr += add;
            
            ret += add*nodes[i][1];
        }
        return ret;
    }
}
