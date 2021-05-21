
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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
public class game1 {
    static int[][] dp;
    //we don't need seperatly dpA and dpB since
    //they are uniquely determined by how many are missing
    static int[] points;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //simliar to Erricho line of wines but minimax
        int N = Integer.parseInt(br.readLine());
        points = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int tot = 0;
        int j = 0;
        while(j < N){
            if(!st.hasMoreTokens()){
                st = new StringTokenizer(br.readLine());
            }
            points[j] = Integer.parseInt(st.nextToken());
            tot += points[j];
            j++;
        }
        
        dp = new int[N][N];

        int two = (tot - minimax(0, points.length-1))/2;
        System.out.println((tot - two) + " " + two);
    }
    
    static int minimax(int left, int right){ //gives the modifier
        if(left == right){
            return points[left];
        }
        if(dp[left][right] != 0){
            return dp[left][right];
        }
        dp[left][right] = Math.max(-minimax(left+1, right) + points[left], -minimax(left, right-1) + points[right]);
        return dp[left][right];
    }
}
