package com.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;

/**
 * @author manoji on 2/28/20.
 */
public class LRUCache {

  HashMap<Integer, Integer> timeMap = new HashMap();
  HashMap<Integer, Integer> cache = new HashMap();
  int time = 0;
  int cap;

  public LRUCache(int capacity) {
    this.cap = capacity;
  }

  public int get(int key) {
    if (cache.containsKey(key)) {
      timeMap.put(key, ++time);
      return cache.get(key);
    } else {
      return -1;
    }
  }

  public void put(int key, int value) {
    if (cache.containsKey(key)) {
      timeMap.put(key, ++time);
      cache.put(key, value);
      return;
    }
    if (cache.size() >= cap) {
      int minTime = Integer.MAX_VALUE;
      int key1 = -1;
      for (Integer _key : timeMap.keySet()) {
        if (timeMap.get(_key) < minTime) {
          minTime = timeMap.get(_key);
          key1 = _key;
        }
      }
      timeMap.put(key, ++time);
      cache.remove(key1);
      timeMap.remove(key1);
      cache.put(key, value);
    } else {
      timeMap.put(key, ++time);
      cache.put(key, value);
    }
  }

  public static void main(String args[]) {
    LRUCache lruCache = new LRUCache(2);
    System.out.println(lruCache.get(2));
    lruCache.put(2, 6);
    System.out.println(lruCache.get(1));
    lruCache.put(1, 5);
    lruCache.put(1, 2);
    System.out.println(lruCache.get(1));
    System.out.println(lruCache.get(2));

  }
}

