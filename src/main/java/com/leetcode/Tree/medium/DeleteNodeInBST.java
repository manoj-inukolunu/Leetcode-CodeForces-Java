package com.leetcode.Tree.medium;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;
import java.util.ArrayList;
import java.util.List;


/**
 * @author manoji on 4/10/20.
 */
public class DeleteNodeInBST {

  public TreeNode deleteNode(TreeNode root, int key) {
    if (root == null || (root.val == key && root.left == root.right)) {
      return null;
    }
    List<Integer> list = new ArrayList();
    dfs(root, list);
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i) == key) {
        list.remove(i);
        break;
      }
    }
    return buildTree(list, 0, list.size() - 1);
  }

  private TreeNode buildTree(List<Integer> list, int begin, int end) {
    if (begin > end) {
      return null;
    }
    int mid = begin + (end - begin) / 2;

    TreeNode node = new TreeNode(list.get(mid));

    node.left = buildTree(list, begin, mid - 1);
    node.right = buildTree(list, mid + 1, end);

    return node;
  }

  private void dfs(TreeNode root, List<Integer> list) {
    if (root == null) {
      return;
    }
    dfs(root.left, list);
    list.add(root.val);
    dfs(root.right, list);
  }

  public static void main(String args[]) {
    TreeNode root = new Codec().deserialize("3,1,4,null,2");
    DeleteNodeInBST d = new DeleteNodeInBST();
    TreeNode node = d.deleteNode(root, 3);
    System.out.println(new Codec().serialize(node));
  }

}
