
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

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
public class art2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] ends = new int[N+1];
        int[] paints = new int[N];
        for(int i = 0; i<N; i++){
            paints[i] = Integer.parseInt(br.readLine());
        }
        for(int i = paints.length-1; i>=0; i--){
            if(ends[paints[i]] == 0){
                ends[paints[i]] = i;
            }
        }

//idea:
//we want the min possible size for each color, marked by the beg and end of it's apperance

/*
let's say we see a color twice. it's either under or above the intermidate bit
if it's above it's not possible
if it's below it's possible. So only if it's below it's possible

That is why, whenever we encouter a piece of paint that is not the end, it's under the intermediate bit, and we can do that with a stack

*/

        int maxHeight = 0; //max number of non-disjoint at one time
        Stack<Integer> curr = new Stack(); //0 is also a layer
        boolean[] visited = new boolean[N+1];

        for(int i = 0; i<N; i++){
            if(paints[i] == 0){
                if(curr.size() > 0){
                    maxHeight = -1;
                    break;
                }
                continue;
            }

            if(i == ends[paints[i]]){
                if(visited[paints[i]]){
                    if (curr.pop() != paints[i]) {
                        maxHeight = -1;
                        break;
                    }
                }else{
                    maxHeight = Math.max(maxHeight, curr.size() + 1);
                }
            }else{
                if(visited[paints[i]]){
                    if(curr.peek() != paints[i]){
                        maxHeight = -1;
                        break;
                    }
                }else{
                    curr.push(paints[i]);
                    maxHeight = Math.max(maxHeight, curr.size());
                }
            }
            visited[paints[i]] = true;
        }
        System.out.println(maxHeight);
    }
}
