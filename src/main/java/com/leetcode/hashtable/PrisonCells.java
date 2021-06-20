package com.leetcode.hashtable;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author manoji on 6/27/20.
 */
public class PrisonCells {


  HashMap<String, int[]> map = new HashMap<>();

  public int[] prisonAfterNDays(int[] cells, int N) {
    if (N == 0) {
      return cells;
    }
    while (N-- > 0) {
      if (!map.containsKey(cells.toString())) {
        int cell[] = new int[8];
        for (int i = 1; i < cells.length - 1; i++) {
          if ((cells[i - 1] == 1 && cells[i + 1] == 1) || (cells[i - 1] == 0 && cells[i + 1] == 0)) {
            cell[i] = 1;
          } else {
            cell[i] = 0;
          }
        }
        cell[0] = 0;
        cell[cells.length - 1] = 0;
        map.put(cells.toString(), cell);
        cells = cell;
      }
    }
    return cells;
  }


  public static void main(String args[]) {
    PrisonCells p = new PrisonCells();
		/*HashMap<int[], int[]> map = new HashMap<>();
		System.out.println(new int[]{0, 1, 0, 1, 1, 0, 0, 1}.hashCode());
		map.put(new int[]{0, 1, 0, 1, 1, 0, 0, 1}, new int[]{0, 1, 0, 1, 1, 0, 0, 1});
		System.out.println(map.containsKey(new int[]{0, 1, 0, 1, 1, 0, 0, 1}));*/

    System.out.println(Arrays.toString(p.prisonAfterNDays(new int[]{0, 1, 0, 1, 1, 0, 0, 1}, 1000000000)));
  }

}
