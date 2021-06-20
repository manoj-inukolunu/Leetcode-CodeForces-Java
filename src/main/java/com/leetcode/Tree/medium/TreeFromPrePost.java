package com.leetcode.Tree.medium;

import com.leetcode.dfs.TreeNode;
import java.util.HashMap;

/**
 * @author manoji on 4/30/20.
 */
public class TreeFromPrePost {

  private void populateMaps(int[] arr, HashMap<Integer, Integer> map) {
    for (int i = 0; i < arr.length; i++) {
      map.put(arr[i], i);
    }
  }

  HashMap<Integer, Integer> preMap = new HashMap<>();
  HashMap<Integer, Integer> postMap = new HashMap<>();


  public TreeNode constructFromPrePost(int[] pre, int[] post) {
    populateMaps(pre, preMap);
    populateMaps(post, postMap);

    return dfs(pre, post, 0, pre.length - 1, 0, post.length - 1);

  }

  /*
  2, 1, 3
  3, 1, 2
   */
  private TreeNode dfs(int[] pre, int[] post, int preStart, int preEnd, int postStart, int postEnd) {

    if (preStart > preEnd || postStart > postEnd) {
      return null;
    }

    if (preStart == preEnd) {
      return new TreeNode(pre[preStart]);
    }

    TreeNode root = new TreeNode(pre[preStart]);

    if (pre[preStart + 1] == post[postEnd - 1]) {
      //No Right SubTree
      if (postEnd - 1 >= 0) {
        root.left = dfs(pre, post, preStart + 1, preEnd, postStart, postMap.get(pre[preStart + 1]));
      }
    } else {
      root.left = dfs(pre, post, preStart + 1, preMap.get(post[postEnd - 1]), postStart, postMap.get(pre[preStart + 1]));
    }

    if (pre[preStart + 1] == post[postEnd - 1]) {
      root.right = null;
    } else if (postEnd - 1 >= 0) {
      root.right = dfs(pre, post, preMap.get(post[postEnd - 1]), preEnd, postMap.get(pre[preStart + 1]) + 1, postEnd - 1);
    }

    return root;

  }


  public static void main(String args[]) {
    int[] pre = new int[]{2, 1, 3};
    int[] post = new int[]{3, 1, 2};

    TreeFromPrePost t = new TreeFromPrePost();

    TreeNode val = t.constructFromPrePost(pre, post);
    System.out.println(val);
  }

}
