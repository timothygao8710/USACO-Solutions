
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * -Take it slow, think- Watch out for: - Long/Int - Edge cases (make test case)
 * - Unexpected behavior?
 *
 * @author timothy
 */
public class monthlyexpense {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] prices = new int[N];
        int max = 0;
        for(int i = 0; i<N; i++){
            prices[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, prices[i]);
        }
        int left = max;
        int right = 10000*N;
        while(left < right){
            int mid = (left + right)/2;
            if(isGood(prices, mid, M)){
                right = mid;
            }else{
                left = mid+1;
            }
        }
        System.out.println(right);
    }
    static boolean isGood(int[] p, int min, int groups){
        int curr = 0;
        for(int i : p){
            if(curr + i > min){
                groups--;
                if(groups == 0){
                    return false;
                }
                curr = i;
            }else{
                curr += i;
            }
        }
        return true;
    }
    
    //we don't need to do dp because there's a relationship because if a is possible and a<b, b is possible,
    //so we can do binary search
//    static int subset(int index, int curr, int max, int left){
//        if(left == 0){
//            return max;
//        }
//        if(prices.length - index < left){
//            return Integer.MAX_VALUE;
//        }
//        return Math.min((
//                subset(index+1, curr + prices[index], Math.max(max, curr + prices[index]), left)
//            ),
//            (
//                subset(index+1, prices[index], Math.max(max, Math.max(curr, prices[index])), left-1)    
//            )
//        );
//    }
}
