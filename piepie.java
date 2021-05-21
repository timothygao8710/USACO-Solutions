
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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
public class piepie {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        //0 - bes
        //1 - els
        int[][] min = new int[2][N];
        Arrays.fill(min[0], -1);
        TreeSet<Node>[] pies = new TreeSet[2];
        
        pies[0] = new TreeSet();
        pies[1] = new TreeSet();
        
        Queue<Node> bfs = new LinkedList();
        
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            Node curr = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i, 0);
            if(curr.value[(curr.bes+1)%2] == 0){
                bfs.add(curr);
                min[curr.bes][curr.index] = 1;
            }else{
                pies[curr.bes].add(curr);
            }
        }
        
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            Node curr = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i, 1);
            if(curr.value[(curr.bes+1)%2] == 0){
                bfs.add(curr);
                min[curr.bes][curr.index] = 1;
            }else{
                pies[curr.bes].add(curr);
            }
        }
        
        while(!bfs.isEmpty()){
            Node curr = bfs.poll();
            //the new node is a dummy, so thing would work with the comparator
            Set<Node> other = pies[(curr.bes+1)%2].tailSet(new Node(curr.value[0] - D, curr.value[1] - D, 100000, (curr.bes+1)%2));
            List<Node> removes = new ArrayList();
            for(Node n : other){
                if(n.value[(n.bes+1)%2] > curr.value[curr.bes]){
                    break;
                }
                
                removes.add(n);
                min[n.bes][n.index] = min[curr.bes][curr.index]+1;
                bfs.add(n);
            }
            
            for(Node n : removes){
                pies[(curr.bes+1)%2].remove(n);
            }
        }
        
        for(int i : min[0]){
            System.out.println(i);
        }
    }
    static class Node implements Comparable<Node>{
        public int[] value = new int[2];
        public int index;
        public int bes;

        public Node(int q, int w, int i, int p){
            value[0] = q;
            value[1] = w;
            index = i;
            bes = p;
        }

        public int compareTo(Node other){
            return value[bes] - other.value[bes];
        }
    }
}
