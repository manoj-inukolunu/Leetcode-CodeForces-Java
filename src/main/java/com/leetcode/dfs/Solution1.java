package com.leetcode.dfs;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * @author manoji on 2020-01-05.
 */
public class Solution1 {

  public TreeNode buildTreeNode() {
    TreeNode root = new TreeNode(3);
    root.left = new TreeNode(5);
    root.right = new TreeNode(1);
    root.left.left = new TreeNode(6);
    root.left.right = new TreeNode(2);
    root.left.right.left = new TreeNode(7);
    root.left.right.right = new TreeNode(4);

    root.right.left = new TreeNode(9);
    root.right.right = new TreeNode(8);

    return root;
  }

  public boolean leafSimilar(TreeNode root1, TreeNode root2) {
    List<Integer> leaves1 = traverseTree(root1);
    List<Integer> leaves2 = traverseTree(root2);

    if (leaves1.size() == leaves2.size()) {
      for (int i = 0; i < leaves1.size(); i++) {
        if (leaves1.get(i) != leaves2.get(i)) {
          return false;
        }
      }
      return true;
    }

    return false;
  }

  private List<Integer> traverseTree(TreeNode root) {
    Stack<TreeNode> stack = new Stack<>();
    Set<TreeNode> visited = new HashSet<>();
    List<Integer> leaves = new ArrayList();

    stack.push(root);
    while (!stack.isEmpty()) {
      TreeNode current = stack.pop();
      if (isLeaf(current)) {
        leaves.add(current.val);
      }
      if (!visited.contains(current)) {
        visited.add(current);
        if (current.left != null) {
          stack.push(current.left);
        }
        if (current.right != null) {
          stack.push(current.right);
        }
      }
    }
    return leaves;
  }

  private boolean isLeaf(TreeNode node) {
    return node.left == null && node.right == null;
  }


  public int getImportance(List<Employee> employees, int id) {
    HashMap<Integer, Employee> map = new HashMap<Integer, Employee>();
    for (int i = 0; i < employees.size(); i++) {
      Employee employee = employees.get(i);
      map.put(employee.id, employee);
    }
    Employee emp = map.get(id);
    Stack<Employee> stack = new Stack();
    HashSet<Integer> visited = new HashSet<>();
    stack.push(emp);
    int sum = 0;
    while (!stack.isEmpty()) {
      Employee current = stack.pop();
      if (!visited.contains(current.id)) {
        visited.add(current.id);
        sum += current.importance;
        for (Integer subord : current.subordinates) {
          stack.push(map.get(subord));
        }
      }
    }
    return sum;
  }


  public static void main(String args[]) {
    Solution1 solution1 = new Solution1();
    /*
    [[1,5,[2,3]],[2,3,[4]],[3,4,[]],[4,1,[]]]1
     */
    List<Employee> employees = Lists.newArrayList(
        new Employee(1, 2, Lists.newArrayList(2)),
        new Employee(2, 3, Lists.newArrayList())
//        new Employee(3, 4, Lists.newArrayList()),
//        new Employee(4, 1, Lists.newArrayList())
    );
    System.out.println(solution1.getImportance(employees, 2));
  }

}
