
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class countthecows {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long d = Long.parseLong(st.nextToken());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            System.out.println(i"SDF");
            pw.println(solve(x, y, d));
        }

        pw.close();
        br.close();
    }

    static long solve(long x, long y, long d) {
        long cyclen = 0;
        long pow = 1;
        long save = 1;
        boolean first = true;
        long sz = 1;
        for (int i = 0; i <= 40; i++) {
            long xb = x % 3;
            long yb = y % 3;
            if (xb != yb) {
                first = false;
                if (xb + yb != 2) {
                    d -= pow * (3 - Math.max(yb, xb)) - save;
                    if (yb > xb) {
                        y += pow*3;
                    } else {
                        x += pow*3;
                    }
                }
                cyclen += 3*pow;
            }else{
                save += pow*((3 - xb)%3);
                if(first) sz = 3*pow;
            }
            x /= 3;
            y /= 3;
            pow *= 3;
        }
        System.out.println(sz);
        if(d < 0) return 0;
        if (cyclen == 0) {
            return d+1;
        }
        
        return (d / cyclen)*save + Math.min(save, d % cyclen);
    }
}
