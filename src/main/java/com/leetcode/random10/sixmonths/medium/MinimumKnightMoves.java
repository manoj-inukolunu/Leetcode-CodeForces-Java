package com.leetcode.random10.sixmonths.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public class MinimumKnightMoves {


  class Data {

    int xPos;
    int yPos;

    int dist;

    public Data(int x, int y, int dist) {
      this.xPos = x;
      this.yPos = y;
      this.dist = dist;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Data data = (Data) o;
      return xPos == data.xPos &&
          yPos == data.yPos;
    }

    @Override
    public int hashCode() {
      return Objects.hash(xPos, yPos);
    }
  }

  public int minKnightMoves(int x, int y) {
    Data start = new Data(0, 0, 0);
    Data end = new Data(x, y, 0);
    Queue<Data> queueStart = new LinkedList<>();
    HashMap<Data, Integer> visitedStart = new HashMap<>();
    queueStart.add(start);

    Queue<Data> queueEnd = new LinkedList<>();
    HashMap<Data, Integer> visitedEnd = new HashMap<>();
    queueEnd.add(end);

    while (!queueStart.isEmpty() && !queueEnd.isEmpty()) {
      Data currStart = queueStart.poll();
      Data currEnd = queueEnd.poll();
      if (currStart.xPos == x && currStart.yPos == y) {
        return currStart.dist;
      }
      if (currEnd.xPos == 0 && currEnd.yPos == 0) {
        return currEnd.dist;
      }

     /* if (visitedStart.containsKey(currEnd)) {
        return currEnd.dist + visitedStart.get(currEnd);
      }
      if (visitedEnd.containsKey(currStart)) {
        return currStart.dist + visitedEnd.get(currStart);
      }*/

      //Start From Start Vertex
      if (!visitedStart.containsKey(currStart)) {
        visitedStart.put(currStart, currStart.dist);
        for (Data next : getPos(currStart)) {
          if (visitedEnd.containsKey(next)) {
            return currStart.dist + visitedEnd.get(next) + 1;
          }
          if (next.xPos >= -1 && next.yPos >= -1) {
            queueStart.add(next);
          }
        }
      }

      //Search From End Vertex
      if (!visitedEnd.containsKey(currEnd)) {
        visitedEnd.put(currEnd, currEnd.dist);
        for (Data next : getPos(currEnd)) {
          if (visitedStart.containsKey(next)) {
            return currEnd.dist + visitedStart.get(next) + 1;
          }
          if (next.xPos >= -1 && next.yPos >= -1) {
            queueEnd.add(next);
          }
        }
      }
    }
    return -1;
  }

  Set<Data> getPos(Data curr) {
    Set<Data> set = new HashSet<>();
    set.add(new Data(curr.xPos - 1, curr.yPos - 2, curr.dist + 1));
    set.add(new Data(curr.xPos - 1, curr.yPos + 2, curr.dist + 1));
    set.add(new Data(curr.xPos + 1, curr.yPos - 2, curr.dist + 1));
    set.add(new Data(curr.xPos + 1, curr.yPos + 2, curr.dist + 1));

    set.add(new Data(curr.xPos - 2, curr.yPos + 1, curr.dist + 1));
    set.add(new Data(curr.xPos + 2, curr.yPos + 1, curr.dist + 1));
    set.add(new Data(curr.xPos - 2, curr.yPos - 1, curr.dist + 1));
    set.add(new Data(curr.xPos + 2, curr.yPos - 1, curr.dist + 1));

    return set;
  }

  public static void main(String args[]) {
    MinimumKnightMoves m = new MinimumKnightMoves();
    System.out.println(m.minKnightMoves(0, 1));
  }


}
