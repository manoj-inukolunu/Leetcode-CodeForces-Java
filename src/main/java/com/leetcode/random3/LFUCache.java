package com.leetcode.random3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class LFUCache {

  int maxSize;

  int time = 0;

  HashMap<Integer, Integer> map = new HashMap<>();
  HashMap<Integer, Integer> currFreqMap = new HashMap<>();
  TreeMap<Integer, Set<Integer>> frequencyMap = new TreeMap<>();
  HashMap<Integer, Integer> recencyMap = new HashMap<>();


  public LFUCache(int capacity) {
    this.maxSize = capacity;
  }

  public int get(int key) {
    if (maxSize == 0) {
      return -1;
    }
    if (map.containsKey(key)) {
      updateFrequency(key);
      time++;
      recencyMap.put(key, time);
      return map.get(key);
    }
    return -1;
  }

  private void updateFrequency(int key) {
    int keyCurrentFreq = currFreqMap.getOrDefault(key, 0);
    Set<Integer> keys = frequencyMap.getOrDefault(keyCurrentFreq, new HashSet<>());
    keys.remove(key);
    if (keys.isEmpty()) {
      frequencyMap.remove(keyCurrentFreq);
    }
    Set<Integer> nextFrequency = frequencyMap.getOrDefault(keyCurrentFreq + 1, new HashSet<>());
    nextFrequency.add(key);
    frequencyMap.put(keyCurrentFreq + 1, nextFrequency);
    currFreqMap.put(key, keyCurrentFreq + 1);
  }


  public void put(int key, int value) {
    if (maxSize == 0) {
      return;
    }
    if (map.size() == maxSize && !map.containsKey(key)) {
      int least = frequencyMap.firstKey();
      int elemToRemove = getElemToRemove(frequencyMap.get(least));
      frequencyMap.get(least).remove(elemToRemove);
      if (frequencyMap.get(least).isEmpty()) {
        frequencyMap.remove(least);
      }
      remove(elemToRemove);
    }
    map.put(key, value);
    updateFrequency(key);
    time++;
    recencyMap.put(key, time);
  }

  private int getElemToRemove(Set<Integer> integers) {
    int min = Integer.MAX_VALUE;
    Integer toRemove = null;
    for (int elem : integers) {
      int recency = recencyMap.get(elem);
      if (recency < min) {
        toRemove = elem;
        min = recency;
      }
    }
    return toRemove;
  }

  private void remove(int element) {
    map.remove(element);
    currFreqMap.remove(element);
  }

  public static void main(String args[]) {
    LFUCache lfu = new LFUCache(2);
    lfu.get(2);
    lfu.put(2, 6);
    lfu.get(1);
    lfu.put(1, 5);
    lfu.put(1, 2);
    System.out.println(lfu.get(1));
    System.out.println(lfu.get(2));

  }
}


