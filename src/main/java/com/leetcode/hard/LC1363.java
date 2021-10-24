package com.leetcode.hard;


import java.util.Arrays;
import java.util.Collections;

public class LC1363 {

    class Data {
        String str;
        int rem;

        public Data(String str, int rem) {
            this.str = str;
            this.rem = rem;
        }
    }

  /*public String largestMultipleOfThree(int[] digits) {
    Arrays.sort(digits);
    int[] dig = new int[digits.length];
    for (int i = 0, j = digits.length - 1; i < dig.length && j >= 0; i++, j--) {
      dig[i] = digits[j];
    }
    return go(dig, 0, new StringBuffer());
  }*/

    public String largestMultipleOfThree(int[] digits) {
        int[] arr =
                Arrays.stream(digits).boxed().sorted(Collections.reverseOrder()).mapToInt(Integer::intValue).toArray();
        Data data = solve(arr, 0, 0, "");
        StringBuffer buffer = new StringBuffer(data.str);
        for (int i = 0; i < data.str.length(); i++) {
            if (data.str.charAt(i) == '0') {
                buffer.deleteCharAt(0);
            } else {
                break;
            }
        }
        if (buffer.length() == 0 && data.str.length() > 0) {
            return "0";
        }
        return buffer.toString();
    }

    private Data solve(int[] arr, int idx, int rem, String data) {
        if (idx >= arr.length) {
            return new Data("", rem);
        }
        Data best = new Data("", -1);
        Data incl = solve(arr, idx + 1, (rem + (arr[idx] % 3)) % 3, data + arr[idx]);
        if (incl.rem == 0) {
            best.str = arr[idx] + incl.str;
            best.rem = 0;
        }
        Data excl = solve(arr, idx + 1, rem, data);
        if (excl.rem == 0) {
            if (best.rem == 0) {
                if (excl.str.length() > best.str.length()) {
                    best = excl;
                }
            } else {
                best = excl;
            }
        }
        return best;
    }

    public static void main(String[] args) {
        LC1363 l = new LC1363();
        System.out.println(l.largestMultipleOfThree(new int[]{0, 0, 0, 0, 0, 0}));
    }
}



