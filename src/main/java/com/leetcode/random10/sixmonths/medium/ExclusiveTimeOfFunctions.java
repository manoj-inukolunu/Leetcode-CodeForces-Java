package com.leetcode.random10.sixmonths.medium;

import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author manoji on 7/19/20.
 */
public class ExclusiveTimeOfFunctions {

  class Func {

    int id;
    boolean start;
    boolean end;
    int time;

    public Func(int id, boolean start, boolean end, int time) {
      this.id = id;
      this.start = start;
      this.end = end;
      this.time = time;
    }
  }

  private Func convert(String log) {
    String[] data = log.split(":");
    return new Func(Integer.parseInt(data[0]), data[1].equals("start"), data[1].equals("end"), Integer.parseInt(data[2]));
  }

  public int[] exclusiveTime(int n, List<String> logs) {
    int[] res = new int[n];
    Stack<Func> stack = new Stack<>();
    int i = 0;
    while (i < logs.size()) {
      Func curr = convert(logs.get(i++));
      if (!stack.isEmpty()) {
        Func top = stack.peek();
        if (top.id == curr.id && top.start && curr.end) {
          Func pop = stack.pop();
          res[top.id] += (curr.time - pop.time + 1);
          if (!stack.isEmpty()) {
            top = stack.peek();
            res[top.id] += -(curr.time - pop.time + 1);
          }
        } else {
          stack.push(curr);
        }
      } else {
        stack.push(curr);
      }
    }
    return res;
  }


  public static void main(String args[]) {
    ExclusiveTimeOfFunctions e = new ExclusiveTimeOfFunctions();

    List<String> logs = Lists.newArrayList("0:start:0", "0:end:0", "1:start:1", "1:end:1", "2:start:2", "2:end:2", "2:start:3", "2:end:3");

    System.out.println(Arrays.toString(e.exclusiveTime(3, logs)));
  }

}
