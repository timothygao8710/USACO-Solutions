
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class split {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("split.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("split.out")));
        
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(System.out);
        
        int N = Integer.parseInt(br.readLine());
        long[][] coords = new long[N][2];
        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            coords[i] = new long[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }
        Arrays.sort(coords, (long[] a, long[] b) -> Long.compare(a[0], b[0]));

        long og = coords[coords.length-1][0] - coords[0][0];
        long ret = solve(coords);
//        System.out.println(ret);
        for(int i = 0; i<coords.length; i++){
            long t = coords[i][0];
            coords[i][0] = coords[i][1];
            coords[i][1] = t;
        }
        Arrays.sort(coords, (long[] a, long[] b) -> Long.compare(a[0], b[0]));
        og *= coords[coords.length-1][0] - coords[0][0];
        ret = Math.min(ret, solve(coords));
        
        pw.println(og - ret);
        pw.close();
        br.close();
    }
    
    static long solve(long[][] coords){
        long minY = Integer.MAX_VALUE;
        long maxY = 0;
        long ret = Long.MAX_VALUE;
        
        long[] suf = new long[coords.length+1];
        suf[coords.length] = 0;
        for(int i = coords.length-1; i>=0; i--){
            minY = Math.min(coords[i][1], minY);
            maxY = Math.max(coords[i][1], maxY);
            suf[i] = (maxY - minY)*(coords[coords.length-1][0] - coords[i][0]);
        }

        minY = Integer.MAX_VALUE;
        maxY = 0;
        
        for(int i = 0; i<coords.length;){
            int j = i;
            while(j < coords.length && coords[i][0] == coords[j][0]){
                minY = Math.min(minY, coords[j][1]);
                maxY = Math.max(maxY, coords[j][1]);
                j++;
            }
            ret = Math.min(ret, (coords[i][0] - coords[0][0])*(maxY-minY) + suf[j]);
            i = j;
        }
        return ret;
    }
}
