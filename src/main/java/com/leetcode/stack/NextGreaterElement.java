package com.leetcode.stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * @author manoji on 5/3/20.
 */
public class NextGreaterElement {


  public int[] nextGreaterElement(int[] nums1, int[] nums2) {

    Stack<Integer> stack = new Stack<>();

    HashMap<Integer, Integer> map = new HashMap();

    //4,1,2
    //1,3,4,2
    for (int j = 0; j < nums2.length; j++) {
      if (!stack.isEmpty()) {
        Integer top = stack.peek();
        while (top < nums2[j]) {
          if (!stack.isEmpty()) {
            top = stack.peek();
            if (top < nums2[j]) {
              map.put(top, nums2[j]);
              stack.pop();
            }
          } else {
            break;
          }
        }
        stack.push(nums2[j]);
      } else {
        stack.push(nums2[j]);
      }
    }

    for (int i = 0; i < nums1.length; i++) {
      if (stack.contains(nums1[i])) {
        nums1[i] = -1;
      } else if (map.containsKey(nums1[i])) {
        nums1[i] = map.get(nums1[i]);
      }
    }

    return nums1;
  }

  public static void main(String args[]) {
    NextGreaterElement n = new NextGreaterElement();
    int[] arr = n.nextGreaterElement(new int[]{1, 2, 1, 1, 2}, new int[]{1, 2, 1, 1, 2, 1});

    System.out.println(Arrays.toString(arr));
  }

}
