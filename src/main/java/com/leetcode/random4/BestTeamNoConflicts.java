package com.leetcode.random4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BestTeamNoConflicts {

  class Pair {

    int score;
    int age;

    public Pair(int score, int age) {
      this.score = score;
      this.age = age;
    }
  }

  public int bestTeamScore(int[] scores, int[] ages) {
    List<Pair> list = new ArrayList<>();
    for (int i = 0; i < scores.length; i++) {
      list.add(new Pair(scores[i], ages[i]));
    }
    Collections.sort(list, (o1, o2) -> {
      if (o1.age == o2.age) {
        return Integer.compare(o1.score, o2.score);
      }
      return Integer.compare(o1.age, o2.age);
    });
    int[] dp = new int[ages.length];
    int max = 0;
    for (int i = 0; i < list.size(); i++) {
      dp[i] = list.get(i).score;
      for (int j = i - 1; j >= 0; j--) {
        if (list.get(i).score >= list.get(j).score) {
          dp[i] = Math.max(dp[j] + list.get(i).score, dp[i]);
          max = Math.max(max, dp[i]);
        }
      }
    }
    return max;
  }

  public static void main(String[] args) {
    BestTeamNoConflicts b = new BestTeamNoConflicts();
    int[] scores = new int[]{1, 2, 3, 5};
    int[] ages = new int[]{8, 9, 10, 1};
    System.out.println(b.bestTeamScore(scores, ages));
  }

}
