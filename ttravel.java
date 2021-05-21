
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
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

public class ttravel {
    //very good problem - persistent stack
    public static void main(String[] args) throws IOException {
        StringBuilder st = new StringBuilder();
        st.re
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Node[] queries = new Node[N+1];
        queries[0] = new Node(-1, null);
        //for no cows
        for(int i = 1; i<=N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            char command = st.nextToken().charAt(0);
            if(command == 'a'){
                queries[i] = new Node(Integer.parseInt(st.nextToken()), queries[i-1]);
            }else if(command == 's'){
                queries[i] = queries[i-1].prev;
            }else{
                queries[i] = queries[Integer.parseInt(st.nextToken())-1];
            }
            System.out.println(queries[i].val);
        }
    }
    
    
    static class Node{
        public Node prev; //no need for next, add will create new timeline
        public int val;
        
        public Node(int v, Node n){
            prev = n;
            val = v;
        }
    }
    
    
}
