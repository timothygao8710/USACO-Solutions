
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * -Take it slow, think-
 * Watch out for:
 * - Long/Int
 * - Edge cases (make test case)
 * - Unexpected behavior?
 *
 * @author timothy
 */
public class jobhunt {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int make = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());
        
        int flights = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        
        int[][] edges = new int[flights + e][3];
        
        int idx = 0;
        for(; idx<e; idx++){
            st = new StringTokenizer(br.readLine());
            edges[idx] = new int[]{Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, make};
        }
        
        for(; idx<edges.length; idx++){
            st = new StringTokenizer(br.readLine());
            edges[idx] = new int[]{Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, -1*Integer.parseInt(st.nextToken())};
        }
        
        int ret = 0;
        
        int[] bf = new int[v];
        Arrays.fill(bf, Integer.MIN_VALUE);
        bf[start-1] = 0;
        
        for(int i = 0; i<v-1; i++){
            for(int[] a : edges){
                bf[a[1]] = Math.max(bf[a[1]], bf[a[0]] + a[2]);
            }
        }
        for(int i : bf) ret = Math.max(ret, i);
        for(int[] a : edges){
            if(bf[a[1]] < a[0] + a[2]){
                ret = -1;
                break;
            }
        }
        
        System.out.println(ret == -1 ? -1 : ret+make);
    }   
}
