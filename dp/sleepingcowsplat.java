/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
 
/**
 *
 * @author is2ac
 */
public class G_CF {
 
    public static void main(String[] args) {
        FastScanner61 fs = new FastScanner61();
        PrintWriter pw = new PrintWriter(System.out);
        int t = fs.ni();
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < t; tc++) {
            int n = fs.ni(), m = fs.ni();
            long[] a = fs.longArray(n);
            long[] b = fs.longArray(m);
            long[] ans = new long[m];
            long s = 0;
            int max = 0;
            for (int i = 0; i < a.length; i++) {
                s += a[i];
                a[i] = s;
                if (a[max] < a[i]) {
                    max = i;
                }
            }
            for (int i = 0; i < b.length; i++) {
                b[i] -= a[max];
                if (s <= 0) {
                    if (b[i] > 0) {
                        ans[i] = -1;
                    }
                    b[i] += a[max];
                } else {
                    if (b[i] <= 0) {
                        b[i] += a[max];
                        continue;
                    }
                    ans[i] = b[i] / s;
                    if (b[i] % s != 0) {
                        ans[i]++;
                    }
                    b[i] -= ans[i] * s;
                    b[i] += a[max];
                    ans[i] *= n;
                }
            }
            List<Long> list = new ArrayList();
            Map<Long, Integer> map = new HashMap();
            for (int i = 0; i < b.length; i++) {
                if (b[i] > 0) {
                    list.add(b[i]);
                }
            }
            Collections.sort(list);
            int p = 0;
            for (int i = 0; i < a.length; i++) {
                while (p < list.size() && a[i] >= list.get(p)) {
                    map.put(list.get(p), i);
                    p++;
                }
            }
            map.put(0L, -1);
            for (int i = 0; i < b.length; i++) {
                if (b[i] > 0 && ans[i] != -1) {
                    ans[i] += map.get(b[i]);
                }
            }
            for (int i = 0; i < b.length; i++) {
                sb.append(ans[i] + " ");
            }
            sb.append("\n");
            pw.println();
        }
        pw.println(sb.toString());
        pw.close();
    }
}
 
class FastScanner61 {
 
    BufferedReader br;
    StringTokenizer st;
 
    public FastScanner61() {
        br = new BufferedReader(new InputStreamReader(System.in), 32768);
        st = null;
    }
 
    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }
 
    int ni() {
        return Integer.parseInt(next());
    }
 
    int[] intArray(int N) {
        int[] ret = new int[N];
        for (int i = 0; i < N; i++) {
            ret[i] = ni();
        }
        return ret;
    }
 
    long nl() {
        return Long.parseLong(next());
    }
 
    long[] longArray(int N) {
        long[] ret = new long[N];
        for (int i = 0; i < N; i++) {
            ret[i] = nl();
        }
        return ret;
    }
 
    double nd() {
        return Double.parseDouble(next());
    }
 
    String nextLine() {
        String str = "";
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}