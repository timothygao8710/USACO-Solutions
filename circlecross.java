//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.PriorityQueue;
//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
///**
// * -Take it slow, think-
// * Watch out for:
// * - Long/Int
// * - Edge cases (make test case)
// * - Unexpected behavior?
// *
// * @author timothy
// */
//public class circlecross {
////imo its more intuitive if you visualize it as a line
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int N = Integer.parseInt(br.readLine());
//        int[] pos = new int[N+1];
//        PriorityQueue<Integer> pq = new PriorityQueue();
//        pq.
//        List<Integer> removedPoints = new ArrayList();
//        int ret = 0;
//        for(int i = 1; i<=2*N; i++){ //using 0 as unvisited
//            int curr = Integer.parseInt(br.readLine());
//            if(pos[curr] == 0){
//                pos[curr] = i;
//            }else{
//                ret += (i - pos[curr] - 1 - binSearch(removedPoints, pos[curr]));
//                removed += 2;
//            }
//        }
//
//        System.out.println(ret);
//    }
//
//    static int binSearch(List<Integer> points, int start){
//        if(points.get(points.size()-1) < start){
//            return 0;
//        }
//
//        int left = 0;
//        int right = points.size()-1;
//        while(left <= right){
//            int mid = (left + right)/2;
//            if(points.get(mid) > start){
//                right = mid;
//            }else{
//                left = mid+1;
//            }
//        }
//        return points.size() - right;
//    }
//}
