package com.leetcode.hard;

import java.util.Arrays;

public class LC1246 {
    class Solution {
        public int minimumMoves(int[] arr) {
            int[][] dp = new int[arr.length][arr.length];
            for(int [] row:dp){
                Arrays.fill(row,-1);
            }
            return go(arr,0,arr.length-1,dp);
        }
        private int go(int [] arr ,int start,int end,int[][]dp){
            if(start>end){
                return 0;
            }
            if(end-start==1){
                return arr[start]==arr[end]?1:2;
            }
            if(start==end){
                return 1;
            }
            if(dp[start][end]!=-1){
                return dp[start][end];
            }
            if(arr[start]==arr[end]){
                return dp[start][end] = go(arr,start+1,end-1,dp);
            }
            int ret = Integer.MAX_VALUE;
            for(int i=start;i<end;i++){
                ret = Math.min(ret,go(arr,start,i,dp)+go(arr,i+1,end,dp));
            }
            return dp[start][end]=ret;
        }
    }
}
