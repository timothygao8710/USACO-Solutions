
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

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
public class photo {
    //Intuition: The fact that every cow has to go to the right, and does so only once, means that
    //given any pair of cows, the left one can go to the right of the right one at most 1 time
        static HashMap<Integer, Integer>[] indexes;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = parse(br.readLine());
        indexes = new HashMap[3];
        Integer[] curr = new Integer[N];
        for(int i = 0; i<3; i++){
            indexes[i] = new HashMap();
            for(int j = 0; j<N; j++){
                curr[j] = parse(br.readLine());
                indexes[i].put(curr[j], j);
            }
        }
        
        Arrays.sort(curr, new sort());
        
        for(int i : curr){
            System.out.println(i);
        }
    }
    
    static int parse(String str){
        while(str.charAt(str.length()-1) == ' '){
            str = str.substring(0, str.length()-1);
        }
        return Integer.parseInt(str);
    }
    
    static class sort implements Comparator<Integer>{
        public int compare(Integer a, Integer b){
            int ca = 0;
            int cb = 0;
            for(HashMap<Integer,Integer> curr : indexes){
                if(curr.get(a) > curr.get(b)){
                    ca++;
                }else{
                    cb++;
                }
            }
            
            //does a appear after b more?
            return ca - cb;
        }
    }
}
