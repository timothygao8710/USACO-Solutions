
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
@SuppressWarnings("unchecked")
public class snowboots {
    //max gap
        
        //observation: the gaps can only increase as you go from a better boot to a worse boot
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("snowboots.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("snowboots.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        
        List<Node> order = new ArrayList();
        order.add(new Node(0,0));
        
        for(int i = 1; i<=N; i++){
            order.add(new Node(order.get(order.size()-1), Integer.parseInt(st.nextToken()), i));
        }
        order.add(new Node(order.get(order.size()-1), 0, N+1));
        
        Collections.sort(order);
        
        int[][] q = new int[B][3];
        //depth - reach
        
        for(int i = 0; i<B; i++){
            st = new StringTokenizer(br.readLine());
            q[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i};
        }
        int[] ret = new int[B];
        
        Arrays.sort(q, (int[] a, int[] b) -> b[0] - a[0]);
        int index = 0;
        int max = 0;
        for(int i = 0; i<q.length; i++){
            while(order.get(index).val > q[i][0]){
                max = Math.max(max, order.get(index).remove());
                index++;
            }
            if(max > q[i][1]){
                ret[q[i][2]] = 0;
            }else{
                ret[q[i][2]] = 1;
            }
        }
        for(int i : ret){
            pw.println(i);
        }
        
        pw.close();
        br.close();
    }
    
    static class Node implements Comparable<Node>{
        public Node next;
        public Node prev;
        public int val;
        public int index;
        
        public Node(Node top, int a, int d){
            top.next = this; //this works even when "this" isn't done constructing yet
            this.prev = top;
            val = a;
            index = d;
        }
        
        public Node(int a, int b){
            val = a;
            index = b;
        }
        
        public int remove(){
            next.prev = prev;
            prev.next = next;
            return next.index - prev.index; //step must be >=
        }
        
        public int compareTo(Node other){
            return other.val - val;
        }
    }
}
