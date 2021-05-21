
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class stonegame {
    static Boolean[][][] dp;
    static int m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] temp = new int[N];
        int t = 0;
        for(int i = 0; i<N; i++){
            temp[i] = Integer.parseInt(st.nextToken());    
            t = Math.max(temp[i], t);
        }
        N = t;
        int[] occ = new int[N+1];
        for(int i : temp){
            occ[i]++;
        }
        
        //if not all elements after first pick occurs an even number of times, the second pick can be the largest odd-occuring number
        //if first does not force second, second will force first
        
        //harmonic series -> log (n)
        //https://discuss.codechef.com/t/more-intuitive-explanation-for-the-harmonic-seriess-sum/67287
        long ret = 0;
        for(int i = 1; i<occ.length; i++) occ[i] += occ[i-1];
        it:
        for(int i = 1; i<=N; i++){
            int last = 0;
            int cr = 0;
            int odds = 0;
            for(int j = i; j<occ.length; j+=i){
                int c = get(occ, j, i);
                if(c % 2 == 1){
                    if(last %2 == 1) cr = c;
                    odds++;
                }
                last = c;
            }
            if(odds <= 2) ret += cr;
            if(odds == 1 && get(occ, i, i)%2 == 1) ret+= get(occ, i, i);
        }
        pw.println(ret);
        pw.close();
        br.close();
    }
    
    static int get(int[] oc, int idx, int sz){
        return oc[Math.min(oc.length, idx+sz)-1] - oc[idx-1];
    }
}
