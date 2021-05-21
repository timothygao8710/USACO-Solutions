
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

// package structures;

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
public class mincross {
    //relative order maintained even when shifting
    //the ones before wont change, only the ones after will
    
    //theme: using sorting to eliminate one dimension
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("mincross.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mincross.out")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] a = new int[N];
        int[] b = new int[N];
        for(int i = 0; i<N; i++) a[i] = Integer.parseInt(br.readLine());
        for(int i = 0; i<N; i++) b[i] = Integer.parseInt(br.readLine());
        
        pw.println(Math.min(solve(b, a),solve(a, b)));
        pw.close();
        br.close();
    }
    
    static long solve(int[] a, int[] b){
        int N = a.length;
        int[] indexInSec = new int[N+1];
        for(int i = 0; i<N; i++) indexInSec[a[i]] = i;
        int[] first = new int[N];
        for(int i =0 ; i<N; i++){
            first[i] = indexInSec[b[i]];
        }
        
        long ret = 0;
        long save = 0;
        long maxSave =0;
        
        BIT sec = new BIT(N);
        for(int i : first){
            int aboves = sec.sum(i, N-1);
            ret += aboves;
            save += (long)i - (N-(i+1)); //Note that this will be the new first(disregarding previous ones)
            maxSave = Math.max(save, maxSave);
//            System.out.println(save);
            sec.update(i, 1);
        }
        return ret-maxSave;
    }
    
    static class BIT { //AKA BIT
        public int[] bit;

        //O(N) build
        public BIT(int N) {
            bit = new int[N+1];
        }

        //sum [l,r]
        public int sum(int l, int r) {
            return sum(r + 1) - sum(l);
        }

        //sum prefix
        //****DO NOT*** USE DIRECTLY
        //NOT 1-INDEXED!!!
        private int sum(int r) {
            int ret = 0;
            while (r > 0) {
                ret += bit[r];
                r -= r & -r;
            }
            return ret;
        }

        //updates # at pos nums[idx]
        //does not reset nums[idx], but updates it (+/-)
        public void update(int idx, long v) {
            idx++;
            while (idx < bit.length) {
                bit[idx] += v;
                idx += idx & -idx;
            }
        }
    }
}
