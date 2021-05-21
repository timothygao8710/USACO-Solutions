
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * -Take it slow, think-
 * Watch out for:
 * - Long/Int
 * - Edge cases (make test case)
 * - Unexpected behavior?
 *
 * @author timothy
 */
public class chocmilk {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] inDeg = new int[N+1];
        ArrayList<Integer>[] connecs = new ArrayList[N+1];
        
        for(int i = 0; i<connecs.length; i++){
            connecs[i] = new ArrayList();
        }
        
        for(int i = 0; i<N-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            inDeg[to]++;
            connecs[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
        }
        Queue<Integer> topo = new LinkedList();
        //key observation: for ever increase-ret, there is exactly 2 topo.size == 1 situations
        int ret = 0;
        for(int i = 1; i<N+1; i++){
            if(inDeg[i] == 0){
                topo.add(i);
            }
        }
        if(topo.size() == 1){
            ret--;
            //edge case - this will cause milk inserter at a cow
        }
        
    }
}
