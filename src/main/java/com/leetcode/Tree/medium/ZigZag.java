package com.leetcode.Tree.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author manoji on 4/15/20.
 */
public class ZigZag {

  public static int log(int x) {
    return (int) (Math.log(x) / Math.log(2));
  }


  private int getParent(int label, int level) {
    int numParentNodes = (int) (Math.pow(2, level - 1));
    int p = label / 2;
    int parentLevelEnd = (int) Math.pow(2, level) - 1;
    int parentLevelBegin = parentLevelEnd - numParentNodes + 1;
    int distFromBegin = p - parentLevelBegin;
    return parentLevelEnd - distFromBegin;
  }

  public List<Integer> pathInZigZagTree(int label) {
    List<Integer> list = new ArrayList();
    int height = log(label);
    int current = label;
    list.add(current);

    while (height != 0) {
      int parent = getParent(current, height);
      list.add(0, parent);
      current = parent;
      height--;
    }
    return list;
  }

  public static void main(String args[]) {
    ZigZag zigZag = new ZigZag();
    System.out.println(zigZag.pathInZigZagTree(1));
  }

}
