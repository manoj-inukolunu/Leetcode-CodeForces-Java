package com.leetcode.random;

import java.util.Arrays;

public class BagOfTokens {

  public int bagOfTokensScore(int[] tokens, int P) {
    Arrays.sort(tokens);
    int start = 0;
    int ans = 0;
    int curr = P, score = 0, end = tokens.length - 1;
    while (start < tokens.length) {
      ans = Math.max(ans, score);
      if (curr < tokens[start] && score == 0) {
        break;
      } else if (curr >= tokens[start]) {
        curr -= tokens[start];
        score++;
        start++;
      } else {
        score--;
        curr += tokens[end];
        end--;
      }

    }
    return ans;
  }

  public static void main(String args[]) {
    int[] arr = new int[]{100, 200, 300, 400};
    int power = 200;
    BagOfTokens b = new BagOfTokens();
    System.out.println(b.bagOfTokensScore(arr, power));
  }

}
