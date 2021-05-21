
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// package exam;

public class balancing {
    static final int maxY = 1_000_001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
     
        int N = Integer.parseInt(br.readLine());
        int[][] coords = new int[N][2];
        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            coords[i] = new int[]{Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())};
        }
        
        BIT left = new BIT(maxY);//maybe: coord compress l8r
        BIT right = new BIT(maxY);
        for(int[] i : coords){
            right.update(i[1], 1);
        }
        int ret = N;
        Arrays.sort(coords, (int[] a, int[] b) -> a[0] - b[0]);
        
        for(int i = 0; i<N;){
            int j = i;
            while(j < N && coords[j][0] == coords[i][0]){
                left.update(coords[j][1], 1);
                right.update(coords[j][1], -1);
                j++;
            }
            i = j;
            
            int l = 0;
            int r = (maxY-1)/2;
            while(l <= r){
                int mid = (l+r)/2;
                int up = Math.max(
                        i - left.sum(2*mid),
                        N-i-right.sum(2*mid)
                );
                int down = Math.max(
                        left.sum(2*mid),
                        right.sum(2*mid)
                );
                if(up > down){
                    l = mid+1;
                }else if(up < down){
                    r = mid-1;
                }else{
                    ret = Math.min(ret, down);
                    break;
                }
            }
        }
        pw.println(ret);
        pw.close();
        br.close();
    }
    
    
    static class BIT {

        public int[] bit;
        
        public BIT(int N) {
            bit = new int[N + 1];
        }
        
        public int sum(int r) {
            r++;
            int ret = 0;
            while (r > 0) {
                ret += bit[r];
                r -= r & -r;
            }
            return ret;
        }

        public void update(int idx, int v) {
            idx++;
            while (idx < bit.length) {
                bit[idx] += v;
                idx += idx & -idx;
            }
        }
    }
}
