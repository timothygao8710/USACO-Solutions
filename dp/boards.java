
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeMap;

// package dp;
/**
 * - Long/int - Draw stuff - Use comments - Drink Water - Reread Problem
 *
 * - package/pw.close
 *
 * @author timothy
 */
public class boards {
    //optimization: log(n) getting min so far
    //math and sorting to eliminate to one var

    //idea similar to monoqueue:
    //from lower y to upper y monotonically increasing
    //because no point in having upper more than lower
    //one way to think about it is the max we're saving
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("boards.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("boards.out")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        TreeMap<Long, Long> dp = new TreeMap();
        long[][] coords = new long[2 * P][];

        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            coords[i] = new long[]{Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()),
                0, i
            };
            coords[P + i] = new long[]{Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()), 1, i};
        }
        //sort by end cuz start depends on pervious end
        Arrays.sort(coords, new Comparator<long[]>(){
                public int compare(long[] a, long[] b){
                    if(a[0] == b[0]){
                        if(a[1] == b[1]){
                            return Long.compare(b[2], a[2]);
                        }
                        return Long.compare(a[1], b[1]);
                    }
                    return Long.compare(a[0], b[0]);
                }
            }
        );
        
        long[] saves = new long[P];//lazy update
        for (long[] i : coords) {
            
            if (i[2] == 0) {
                Long curr = dp.floorKey(i[1]);
                if (curr == null) {
                    curr = 0L;
                } else {
                    curr = dp.get(curr);
                }
                
                saves[(int) i[3]] = curr + i[0] + i[1];
                
                continue;
            }

            long curr = saves[(int) i[3]] - i[1] - i[0];
            
            if (dp.floorKey(i[1]) != null && dp.get(dp.floorKey(i[1])) <= curr) {
                continue;
            }

            Long up = dp.ceilingKey(i[1]);
            while (up != null && curr <= dp.get(up)) {
                dp.remove(up);
                up = dp.ceilingKey(i[1]);
            }
            dp.put(i[1], curr);
        }

        Long ret = dp.floorKey(N);
        if (ret == null) {
            ret = 0L;
        } else {
            ret = dp.get(ret);
        }
//        System.out.println(2 * N + ret);
        pw.println(2*N + ret);
        pw.close();
        br.close();
    }

}
/*
7 3
1 1 3 6
3 6 7 7
3 2 7 7
*/