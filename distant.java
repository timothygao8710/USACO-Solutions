
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
public class distant {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //we can define it as a DAG since we never wanna go back
        //root at top left, going down.
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        
        int N =Integer.parseInt(st.nextToken());
        int same = Integer.parseInt(st.nextToken());
        int diff = Integer.parseInt(st.nextToken());
        char[][] grass = new char[N][N];
        int[][] dis = new int[N][N];
        
        for(int i = 0; i<N; i++)
        {
            String line = br.readLine();
            for(int j = 0; j<N; j++){
                grass[i][j] = line.charAt(j);
            }
        }
        
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                int left = i == 0 ? 100000000 : dis[i-1][j] + (grass[i-1][j] == grass[i][j] ? same : diff);
                int up = j == 0 ? 100000000 : dis[i][j-1] + (grass[i][j-1] == grass[i][j] ? same : diff);
                
                dis[i][j] = Math.min(left, up);
                dis[0][0] = 0;
            }
        }
        
        System.out.println(dis[dis.length-1][dis[0].length-1]);
    }
}
