package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author manoji on 2/22/20.
 */
public class RussianDollEnvelopes {

  class Envelope {

    int width;
    int height;

    Envelope(int width, int height) {
      this.width = width;
      this.height = height;
    }

    public int getWidth() {
      return width;
    }

    public int getHeight() {
      return height;
    }

    @Override
    public String toString() {
      final StringBuilder sb = new StringBuilder("Envelope{");
      sb.append("width=").append(width);
      sb.append(", traverse=").append(height);
      sb.append('}');
      return sb.toString();
    }

		/*@Override
		public int compareTo(@NotNull Object o) {
			return Comparator.comparing(Envelope::getHeight).thenComparing(Envelope::getWidth).compare(this, (Envelope) o);
		}*/
  }

  public int maxEnvelopes(int[][] envelopes) {
    List<Envelope> envelopeList = new ArrayList();
    for (int[] row : envelopes) {
      envelopeList.add(new Envelope(row[0], row[1]));
    }

    Collections.sort(envelopeList, Comparator.comparing(Envelope::getHeight).thenComparing(Envelope::getWidth));

    int max = 1;
    int[] dp = new int[envelopeList.size()];
    Arrays.fill(dp, 1);
    for (int i = 1; i < envelopeList.size(); i++) {
      for (int j = 0; j < i; j++) {
        if (envelopeList.get(i).width > envelopeList.get(j).width && envelopeList.get(i).height > envelopeList.get(j).height) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
          if (dp[i] > max) {
            max = dp[i];
          }
        }
      }
    }
    return max;
  }


  public static void main(String args[]) {
    RussianDollEnvelopes russianDollEnvelopes = new RussianDollEnvelopes();

    int[][] list = new int[][]{
        {5, 4},
        {6, 4},
        {6, 7},
        {2, 3}
    };

    int val = russianDollEnvelopes.maxEnvelopes(list);

    System.out.println(val);
  }


}
