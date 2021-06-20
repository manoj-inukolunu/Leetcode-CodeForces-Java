package com.leetcode.random;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class Twitter {

  HashMap<Integer, PriorityQueue<Integer[]>> userMap = new HashMap();
  HashMap<Integer, HashSet<Integer>> fMap = new HashMap();
  int time = 0;

  /**
   * Initialize your data structure here.
   */
  public Twitter() {

  }

  /**
   * Compose a new tweet.
   */
  public void postTweet(int userId, int tweetId) {
    PriorityQueue<Integer[]> p = userMap.get(userId);
    if (p == null) {
      p = new PriorityQueue(10, new Comparator<Integer[]>() {
        @Override
        public int compare(Integer[] o1, Integer[] o2) {
          return -Integer.compare(o1[1], o2[1]);
        }
      });
    }
    p.add(new Integer[]{tweetId, time});
    HashSet<Integer> set = fMap.getOrDefault(userId, new HashSet<>());
    set.add(userId);
    fMap.put(userId, set);
    time++;
  }

  /**
   * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the
   * user herself. Tweets must be ordered from most recent to least recent.
   */
  public List<Integer> getNewsFeed(int userId) {
    List<Integer> ans = new ArrayList<>();
    PriorityQueue<Integer[]> p = new PriorityQueue(10, new Comparator<Integer[]>() {
      @Override
      public int compare(Integer[] o1, Integer[] o2) {
        return -Integer.compare(o1[1], o2[1]);
      }
    });
    if (fMap.containsKey(userId)) {
      for (int user : fMap.get(userId)) {
        p.addAll(userMap.get(userId));
      }
      int i = 10;
      while (i > 0 && !p.isEmpty()) {
        ans.add(p.poll()[0]);
        i--;
      }
    }
    return ans;
  }

  /**
   * Follower follows a followee. If the operation is invalid, it should be a no-op.
   */
  public void follow(int followerId, int followeeId) {
    HashSet<Integer> set = fMap.getOrDefault(followeeId, new HashSet());
    set.add(followerId);
    fMap.put(followeeId, set);
  }

  /**
   * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
   */
  public void unfollow(int followerId, int followeeId) {
    if (fMap.containsKey(followeeId)) {
      fMap.get(followeeId).remove(followerId);
    }
  }
}
