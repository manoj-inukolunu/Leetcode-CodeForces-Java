package com.leetcode.string;

import com.craftinginterpreters.lox.Expr;
import java.util.HashMap;

/**
 * @author manoji on 4/23/20.
 */
public class ExpressiveWords {

  public int expressiveWords(String S, String[] words) {

    StringBuffer buffer = getRunLengthString(S);

    int answer = 0;

    for (String str : words) {
      if (isStretchy(str, buffer)) {
        answer++;
      }
    }

    return answer;
  }

  private StringBuffer getRunLengthString(String S) {
    StringBuffer buffer = new StringBuffer();
    int i = 0;
    int count = 1;
    char[] arr = S.toCharArray();
    while (i + 1 < arr.length) {
      if (count == 1) {
        buffer.append(arr[i]);
      }
      if (arr[i] == arr[i + 1]) {
        count++;
      } else {
        buffer.append(count);
        count = 1;
      }
      i++;
    }

    if (count > 1) {
      buffer.append(count);
    } else if (count == 1) {
      buffer.append(arr[arr.length - 1]);
      buffer.append(count);
    }

    return buffer;
  }

  private boolean isStretchy(String str, StringBuffer buffer) {
    StringBuffer buff = getRunLengthString(str);
    int i = 0;
    while (i + 1 < buff.length()) {
      if (buff.charAt(i) == buffer.charAt(i)) {
        int curr = Character.getNumericValue(buff.charAt(i + 1));
        int main = Character.getNumericValue(buffer.charAt(i + 1));
        i = i + 2;
        if (main == curr) {
          continue;
        } else if (main < curr) {
          return false;
        } else if (main > curr && main >= 3) {
          continue;
        } else {
          return false;
        }
      } else {
        return false;
      }
    }
    return true;
  }


  public static void main(String args[]) {
    String str = "heeellooo";

    String[] words = new String[]{"hello", "hi", "helo"};

    ExpressiveWords e = new ExpressiveWords();

    System.out.println(e.expressiveWords(str, words));
  }

}
