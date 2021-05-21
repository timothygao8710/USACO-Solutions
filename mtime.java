
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class mtime {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int N = Integer.parseInt(br.readLine());
        int[][] jobs = new int[N][2];
        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            jobs[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }
        Arrays.sort(jobs, (int[] a, int[] b) -> a[1] - b[1]);
        
        int t = jobs[N-1][1];
        for(int i = N-1; i>=0; i--){
            t = Math.min(t, jobs[i][1]);
            t -= jobs[i][0];
        }
        pw.println(t < 0 ? -1 : t);
        pw.close();
        br.close();
    }
}
