package com.leetcode.slidingwindow;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * @author manoji on 6/27/20.
 */
public class FruitIntoBaskets {


  public int totalFruit(int[] tree) {
    HashMap<Integer, Integer[]> map = new HashMap<>();
    Integer max = Integer.MIN_VALUE;
    for (int i = 0; i < tree.length; i++) {
      if (map.isEmpty()) {
        map.put(tree[i], new Integer[]{i, i});
      } else if (map.containsKey(tree[i])) {
        int start = map.get(tree[i])[1];
        map.put(tree[i], new Integer[]{i, start});
      } else if (!map.containsKey(tree[i]) && map.size() < 2) {
        int start = map.get(tree[i - 1])[1];
        map.put(tree[i], new Integer[]{i, start});
      } else if (!map.containsKey(tree[i]) && map.size() == 2) {
        Integer[] arr = map.get(tree[i - 1]);
        int val = i - arr[1];
        if (val > max) {
          max = val;
        }
        int keyToDel = -1;
        for (int key : map.keySet()) {
          if (key != tree[i - 1]) {
            keyToDel = key;
            break;
          }
        }
        int start = map.get(keyToDel)[0];
        map.remove(keyToDel);
        map.put(tree[i], new Integer[]{i, start + 1});
        map.put(tree[i - 1], new Integer[]{i - 1, start + 1});
      }
    }

    return getmax(max, map, tree);
  }

  private int getmax(Integer max, HashMap<Integer, Integer[]> map, int[] tree) {
    if (max == Integer.MIN_VALUE) {
      return tree.length - map.entrySet().iterator().next().getValue()[1];
    } else if (tree.length - map.entrySet().iterator().next().getValue()[1] > max) {
      return tree.length - map.entrySet().iterator().next().getValue()[1];
    } else {
      return max;
    }
  }

  public static void main(String args[]) {
    FruitIntoBaskets f = new FruitIntoBaskets();
    System.out.println(f.totalFruit(new int[]{1, 2, 1}));
    System.out.println(f.totalFruit(new int[]{0, 1, 2, 2}));
    System.out.println(f.totalFruit(new int[]{1, 2, 3, 2, 2}));
    System.out.println(f.totalFruit(new int[]{3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4}));

  }

}
