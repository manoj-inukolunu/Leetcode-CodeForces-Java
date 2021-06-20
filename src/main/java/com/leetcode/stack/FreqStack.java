package com.leetcode.stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

public class FreqStack {

  HashMap<Integer, Integer> map = new HashMap();
  Stack<Integer> stack = new Stack();
  TreeMap<Integer,
      Set<Integer>> valMap = new TreeMap();

  public FreqStack() {

  }

  public void push(int x) {
    stack.push(x);
    int freqX = map.getOrDefault(x, 0);
    map.put(x, freqX + 1);
    Set<Integer> set = valMap.getOrDefault(freqX + 1, new HashSet<>());
    set.add(x);
    valMap.put(freqX + 1, set);
    if (valMap.containsKey(freqX)) {
      valMap.get(freqX).remove(x);
    }
  }

  public int pop() {
    List<Integer> hold = new ArrayList<>();
    int maxFreq = valMap.lastKey();
    Set<Integer> max = valMap.get(maxFreq);
    while (!stack.isEmpty()) {
      Integer curr = stack.pop();
      if (max.contains(curr)) {
        for (int i = 0; i < hold.size(); i++) {
          stack.push(hold.get(i));
        }
        int val = map.get(curr);
        if (val - 1 != 0) {
          map.put(curr, val - 1);
          Set<Integer> set = valMap.get(val - 1);
          if (set != null) {
            valMap.get(val - 1).add(curr);
          } else {
            set = new HashSet<>();
            set.add(curr);
            valMap.put(val - 1, set);
          }
        }
        max.remove(curr);
        if (max.isEmpty()) {
          valMap.remove(maxFreq);
        }
        return curr;
      } else {
        hold.add(0, curr);
      }
    }
    return -1;
  }

  public static void main(String args[]) {
    FreqStack f = new FreqStack();
    f.push(7);
    f.push(6);
    f.push(2);
    f.push(6);
    f.push(3);
    f.push(3);
    System.out.println(f.pop());
    f.push(2);
    System.out.println(f.pop());
    f.push(2);
    System.out.println(f.pop());
    f.push(5);
    System.out.println(f.pop());
    f.push(6);
    System.out.println(f.pop());
    System.out.println(f.pop());
    System.out.println(f.pop());
    System.out.println(f.pop());
    System.out.println(f.pop());
    System.out.println(f.pop());
  }
}
