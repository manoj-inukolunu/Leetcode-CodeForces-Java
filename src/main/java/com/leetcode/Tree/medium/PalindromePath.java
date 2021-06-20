package com.leetcode.Tree.medium;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;
import java.util.HashMap;

/**
 * @author manoji on 5/26/20.
 */
public class PalindromePath {

  private int ans = 0;

  public int pseudoPalindromicPaths(TreeNode root) {
//		int[] num = new int[10];
    dfs(root, new HashMap<>());
    return ans;
  }


  private boolean check(HashMap<Integer, Integer> map) {
    int odd = 0;
    for (int num : map.keySet()) {
      if ((map.get(num) & 1) == 1) {
        odd++;
      }
      if (odd > 1) {
        return false;
      }
    }

    return true;
  }

  private void dfs(TreeNode root, HashMap<Integer, Integer> map) {
    if (root == null) {
      return;
    }
    if (root.left == null && root.right == null) {
      //check palindrome in map;
      map.put(root.val, map.getOrDefault(root.val, 0) + 1);
      if (check(map)) {
        ans++;
      }
      if (map.get(root.val) > 1) {
        map.put(root.val, map.get(root.val) - 1);
      } else {
        map.put(root.val, 0);
      }
      return;
    }
    map.put(root.val, map.getOrDefault(root.val, 0) + 1);
    dfs(root.left, map);
    dfs(root.right, map);
    if (map.get(root.val) > 1) {
      map.put(root.val, map.get(root.val) - 1);
    } else {
      map.put(root.val, 0);
    }
  }

  public static void main(String args[]) {
    PalindromePath p = new PalindromePath();

    TreeNode root = new Codec().deserialize("9");

    System.out.println(p.pseudoPalindromicPaths(root));
  }

}
