
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

// package structures;

public class circlecross {
    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("circlecross.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("circlecross.out")));
        
        int N = Integer.parseInt(br.readLine());
        Map<Integer, Integer> mp = new HashMap();
        SegTree sgt = new SegTree(2*N);
        int ret = 0;
        for(int i = 0; i<2*N; i++){
            int c = Integer.parseInt(br.readLine());
            if(mp.containsKey(c)){
                ret += sgt.get(1, mp.get(c)+1, i, 0, 2*N-1);
                sgt.update(1, mp.get(c), 0, 0, 2*N-1);
                mp.remove(c);
            }else{
                sgt.update(1, i, 1, 0, 2*N-1);
                mp.put(c, i);
            }
        }
        
        pw.println(ret);
        pw.close();
        br.close();
    }
    
    static class SegTree {
        
        public int[] nodes;
        
        public SegTree(int N) {
            nodes = new int[4 * N];
        }
        
        public int get(int gI, int l, int r, int gL, int gR) {
            if (l > r) {
                return 0;
            }
            if (gL == l && gR == r) {
                return nodes[gI];
            }
            int mid = (gR + gL) / 2;
            return (
                    get(2 * gI, l, Math.min(r, mid), gL, mid)+
                    get(2 * gI + 1, Math.max(mid + 1, l), r, mid + 1, gR)
            );
        }
        
        public int update(int gI, int idx, int val, int gL, int gR) {
            if (idx < gL || idx > gR) {
                return nodes[gI];
            }
            if (gL == gR) {
                nodes[gI] = val;
            } else {
                nodes[gI] = (
                        update(2 * gI, idx, val, gL, (gL + gR) / 2) +
                        update(2 * gI + 1, idx, val, (gL + gR) / 2 + 1, gR)
                );
            }
            return nodes[gI];
        }
    }
}
