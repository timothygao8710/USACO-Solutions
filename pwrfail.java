
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * -Take it slow, think- Watch out for: - Long/Int - Edge cases (make test case)
 * - Unexpected behavior?
 *
 * @author timothy
 */
@SuppressWarnings("unchecked")
public class pwrfail {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        double M = Double.parseDouble(br.readLine());
        ArrayList<Integer>[] tos = new ArrayList[N + 1];
        ArrayList<Double>[] ws = new ArrayList[N + 1];
        coord[] coords = new coord[N];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            coords[i - 1] = new coord(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            tos[i] = new ArrayList();
            ws[i] = new ArrayList();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            tos[from].add(to);
            tos[to].add(from);

            ws[from].add(0.0);
            ws[to].add(0.0);
        }

        Arrays.sort(coords);
        
        for (int i = 0; i < coords.length; i++) {
            for (int j = i + 1; j < coords.length && coords[i].x + M >= coords[j].x; j++) {
                double d = dist(coords[i], coords[j]);
                if (d <= M) {
                    tos[coords[j].index].add(coords[i].index);
                    tos[coords[i].index].add(coords[j].index);

                    ws[coords[i].index].add(d);
                    ws[coords[j].index].add(d);
                }
            }
        }

        boolean vis = false;

        Queue<curr> dj = new PriorityQueue<>();
        double[] min = new double[N + 1];
        for (int i = 0; i < min.length; i++) {
            min[i] = Double.POSITIVE_INFINITY;
        }
        min[1] = 0.0;
        dj.add(new curr(1, 0));
        while (!dj.isEmpty()) {
            curr pol = dj.poll();
            if (min[pol.node] < pol.relative) {
                continue;
            }

            if (pol.node == N) {
                vis = true;
                System.out.println((int) (pol.relative * 1000));
                break;
            }

            for (int i = 0; i < tos[pol.node].size(); i++) {
                double dist = pol.relative + ws[pol.node].get(i);
                if (dist + pol.relative < min[tos[pol.node].get(i)]) {
                    min[tos[pol.node].get(i)] = dist;
                    dj.add(new curr(tos[pol.node].get(i), dist));
                }
            }
        }

        if (!vis) {
            System.out.println(-1);
        }
    }

    static class curr implements Comparable<curr> {

        public double relative;
        public int node;

        public curr(int n, double r) {
            node = n;
            relative = r;
        }

        public int compareTo(curr other) {
            if (relative < other.relative) {
                return -1;
            }
            return 1;
        }
    }

    static double dist(coord a, coord b) {
        return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
    }

    static class coord implements Comparable<coord> {

        public int index;
        public int x;
        public int y;

        public coord(int a, int b, int c) {
            index = a;
            x = b;
            y = c;
        }

        public int compareTo(coord other) {
            return x - other.x;
        }
    }
}
