
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

@SuppressWarnings("unchecked")
public class feast {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        boolean[] Notdp = new boolean[2 * T];
        boolean[] dp = new boolean[2 * T];
        Notdp[0] = true;
        dp[0] = true;
        for (int i = 0; i < T; i++) {
            if (Notdp[i]) {
                Notdp[i + A] = true;
                Notdp[i + B] = true;
                dp[i / 2] = true;
            }
        }
        for (int i = 0; i < T; i++) {
            if (dp[i]) {
                dp[i + A] = true;
                dp[i + B] = true;
            }
        }
        for (int i = T; i >= 0; i--) {
            if (dp[i] || Notdp[i]) {
                System.out.println(i);
                break;
            }
        }
    }

    //the idea is that we have /2 for drink water,
    //as order doesn't matter, and for any arrangements of /2 and regulars,
    //we can arrange them in such a way that all the /2s go first before regulars
    //BUT this doesn't work if say limit is 10 and A is 9. Then we can't do A/2 more than 1 time
    //becuz we would need to go over 10 to be able to go back A/2 in the first place
    //So instead we can't use this trick and we have to go with the regular version of 2d dp
}
