package com.leetcode.Tree.medium;

import com.leetcode.ListNode;
import com.leetcode.dfs.TreeNode;
import java.util.HashMap;

/**
 * @author manoji on 5/12/20.
 */
public class SortedListToBST {

  public TreeNode sortedListToBST(ListNode head) {

    HashMap<Integer, ListNode> map = new HashMap();
    ListNode temp = head;
    int i = 0;
    while (temp != null) {
      map.put(i++, temp);
      temp = temp.next;
    }

    return sortedListToBST(map, 0, map.size());
  }


  private TreeNode sortedListToBST(HashMap<Integer, ListNode> map, int start, int end) {
    if (start > end) {
      return null;
    }
    int mid = start + ((end - start) / 2);

    TreeNode root = new TreeNode(map.get(mid).val);

    root.left = sortedListToBST(map, start, mid - 1);
    root.right = sortedListToBST(map, mid + 1, end);

    return root;
  }


}
