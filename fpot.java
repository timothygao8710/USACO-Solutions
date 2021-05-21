
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeMap;

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
public class fpot {    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        //sliding window
        //there can be 2 at the same X!!
        int[][] drops = new int[N][2];
        
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            drops[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }
        
        Arrays.sort(drops, (int[] a, int[] b) -> a[0] - b[0]);
        
        int ret = Integer.MAX_VALUE;
        
        TreeMap<Integer, Integer> window = new TreeMap(
        new Comparator<Integer>(){
            public int compare(Integer a, Integer b){
                return a - b;
            }
        }
        );
        
        int right = 0;
        c:
        for(int l = 0; l<drops.length; l++){
            while(window.isEmpty() || window.lastKey() - window.firstKey() < D){
                if(right == drops.length){
                    break c;
                }
                window.putIfAbsent(drops[right][1], 0);
                window.put(drops[right][1], window.get(drops[right][1])+1);
                right++;
            }
            ret = Math.min(drops[right-1][0] - drops[l][0], ret);
            if(window.get(drops[l][1]) == 1){
                window.remove(drops[l][1]);
            }else{
                window.put(drops[l][1], window.get(drops[l][1])-1);
            }
        }
        
        if(ret == Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(ret);
        }
    }
   
}
