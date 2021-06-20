package com.leetcode.Tree.medium;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author manoji on 4/12/20.
 */
public class SerBST {


  public int firstUniqChar(String s) {
    int[] arr = new int[26];
    Arrays.fill(arr, 0);
    for (int i = 0; i < s.length(); i++) {
      arr[s.charAt(i) - 97]++;
    }
    int index = Integer.MAX_VALUE;
    for (int i = 0; i < arr.length; i++) {
      int c = s.indexOf((char) (i + 97));
      if (arr[i] == 1 && c < index) {
        index = c;
      }
    }

    return index == Integer.MAX_VALUE ? -1 : index;
  }


  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    if (root == null) {
      return "";
    }
    Queue<TreeNode> queue = new LinkedList<>();
    StringBuffer stringBuffer = new StringBuffer();
    queue.add(root);
    while (!queue.isEmpty()) {
      TreeNode current = queue.poll();
      if (current == null) {
        stringBuffer.append("#,");
      } else {
        stringBuffer.append(current.val).append(",");
        queue.add(current.left);
        queue.add(current.right);
      }
    }
    return stringBuffer.toString().trim();
  }

  public TreeNode deserialize(String data) {
    if (data.equals("")) {
      return null;
    }
    String[] split = data.split(",");
    if (split.length == 0) {
      return null;
    }

    TreeNode root = new TreeNode(Integer.parseInt(split[0]));
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    TreeNode current;
    for (int i = 1; i < split.length; /*i++*/) {
      current = queue.poll();
      String val = split[i++];
      if (val.equalsIgnoreCase("#")) {
        current.left = null;
      } else {
        current.left = new TreeNode(Integer.parseInt(val));
        queue.add(current.left);
      }

      if (i < split.length) {
        val = split[i++];
        if (val.equalsIgnoreCase("#")) {
          current.right = null;
        } else {
          current.right = new TreeNode(Integer.parseInt(val));
          queue.add(current.right);
        }
      }
    }
    return root;
  }


  public static void main(String args[]) {
    SerBST s = new SerBST();
    System.out.println(s.firstUniqChar("ll"));
  }

}
