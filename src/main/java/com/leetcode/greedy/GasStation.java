package com.leetcode.greedy;

/**
 * @author manoji on 6/1/20.
 */
public class GasStation {

  int startIndex;

  public int canCompleteCircuit(int[] gas, int[] cost) {
    for (int i = 0; i < gas.length; i++) {
      startIndex = i;
      if (gas[i] >= cost[i]) {
        if (dfs(gas, cost, i, 0, 0)) {
          return i;
        }
      }
    }
    return -1;
  }

  private boolean dfs(int[] gas, int[] cost, int currIndex, int currGas, int round) {
    currGas += gas[currIndex];
    if (currGas < cost[currIndex]) {
      return false;
    }
    if (startIndex == currIndex && round > 0) {
      return true;
    }
    int next = (currIndex + 1) % gas.length;
    return dfs(gas, cost, next, currGas - cost[currIndex], ++round);
  }

  public static void main(String args[]) {
    int[] gas2 = new int[]{1, 2, 3, 4, 5};
    int[] cost2 = new int[]{3, 4, 5, 1, 2};

    int[] gas = new int[]{5, 1, 2, 3, 4};
    int[] cost = new int[]{4, 4, 1, 5, 1};

    int[] gas1 = new int[]{2, 3, 4};
    int[] cost1 = new int[]{3, 4, 3};

    GasStation g = new GasStation();
    System.out.println(g.canCompleteCircuit(gas, cost));
  }

}
