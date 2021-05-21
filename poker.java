
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
public class poker {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        //it's nice to visualize this problem as a graph where x - y means rank - number of cards of that rank
        //once we visualize it this way, we see that a straight is just 1 straight horzontal lines
        //so we are essentially scanning the graph for the number of disjoint horzontal line segments
        
        //we can do this in one go: see below logic
        int[] graph = new int[N+1];
        for(int i = 0; i<N; i++){
            graph[i] = Integer.parseInt(br.readLine());
        }
        int ret = 0;
        int last = 0;
        for(int i : graph){
            if(i < last){
                ret += last - i;
            }
            last = i;
        }
        ret += last;
        
        System.out.println(ret);
    }
}
