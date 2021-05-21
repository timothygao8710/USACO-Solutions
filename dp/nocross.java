package dp;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class nocross {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("nocross.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("nocross.out")));   
   
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[] B = new int[N];
        for(int i = 0; i<N; i++){
            A[i] = Integer.parseInt(br.readLine());
        }
        for(int i = 0; i<N; i++){
            B[i] = Integer.parseInt(br.readLine());
        }
        int ret = 0;
        int[] dp = new int[N];
        for(int i = 0; i<N; i++){
            int[] temp = new int[N];
            int max = 0;
            for(int j = 0; j<N; j++){
                if(Math.abs(A[i] - B[j]) <= 4){
                    temp[j] = 1 + max;
                }
                ret = Math.max(ret, temp[j]);
                max = Math.max(dp[j], max);
                temp[j] = Math.max(temp[j], max);
            }
            dp = temp;
        }
        pw.println(ret);
        pw.close();
        br.close();
//        System.out.println(ret);
    }
}
