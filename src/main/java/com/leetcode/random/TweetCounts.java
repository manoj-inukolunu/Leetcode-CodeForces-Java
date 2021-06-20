package com.leetcode.random;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class TweetCounts {

  TreeMap<String, TreeSet<Integer>> map = new TreeMap<>();

  public TweetCounts() {

  }

  public void recordTweet(String tweetName, int time) {
    TreeSet<Integer> set = map.getOrDefault(tweetName, new TreeSet<>());
    set.add(time);
    map.put(tweetName, set);
  }

  public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
    List<Integer> ans = new ArrayList<>();
    if (freq.equals("day")) {
      get(tweetName, startTime, endTime, ans, map, 86400);
    } else if (freq.equals("minute")) {
      get(tweetName, startTime, endTime, ans, map, 60);
    } else {
      get(tweetName, startTime, endTime, ans, map, 3600);
    }
    return ans;
  }

  private void get(String tweetName, int startTime, int endTime, List<Integer> ans, TreeMap<String, TreeSet<Integer>> map, int count) {
    TreeSet<Integer> set = map.get(tweetName);
    Integer low = startTime;
    int i = 1;
    Integer high = startTime + (i * count);
    boolean last = false;
    if (high > endTime) {
      high = endTime + 1;
      last = true;
    }
    int curr = 0;
    while (true) {
      while (low != null && low < high) {
        if (set.contains(low)) {
          curr++;
        }
        low = set.higher(low);
      }
      ans.add(curr);
      curr = 0;
      if (last) {
        break;
      }
      i++;
      low = high;
      high = startTime + (count * i);
      if (high > endTime) {
        high = endTime + 1;
        last = true;
      }
    }
  }

  public static void main(String args[]) {
    TweetCounts t = new TweetCounts();
    t.recordTweet("a", 0);
    t.recordTweet("a", 60);
    t.recordTweet("a", 10);
    System.out.println(t.getTweetCountsPerFrequency("minute", "a", 0, 59));
    System.out.println(t.getTweetCountsPerFrequency("minute", "a", 0, 60));
    t.recordTweet("a", 120);
    System.out.println(t.getTweetCountsPerFrequency("hour", "a", 0, 210));
  }


}
