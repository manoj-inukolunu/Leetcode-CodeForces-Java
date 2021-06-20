package com.leetcode.random1;

import com.leetcode.dfs.TreeNode;
import java.util.HashMap;
import java.util.Objects;

public class BinaryTreeCameras {

  class Pair {

    TreeNode root;
    boolean cam;
    boolean parentCam;

    public Pair(boolean cam, boolean parentCam, TreeNode root) {
      this.cam = cam;
      this.parentCam = parentCam;
      this.root = root;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Pair pair = (Pair) o;
      return cam == pair.cam && parentCam == pair.parentCam && Objects.equals(root, pair.root);
    }

    @Override
    public int hashCode() {
      return Objects.hash(root, cam, parentCam);
    }
  }

  HashMap<Pair, Integer> dp = new HashMap<>();

  public int minCameraCover(TreeNode root) {
    return Math.min(solve(root, true, false), solve(root, false, false));
  }

  private int solve(TreeNode root, boolean camera, boolean pCamera) {
    Pair p = new Pair(camera, pCamera, root);
    if (root == null) {
      if (camera) {
        return 10000;
      } else {
        return 0;
      }
    }
    if (root.left == null && root.right == null) {
      if (camera) {
        return 1;
      } else {
        if (pCamera) {
          return 0;
        } else {
          return 10000;
        }
      }
    }
    if (dp.containsKey(p)) {
      return dp.get(p);
    }
    if (camera) {
      int curr = 1 + Math.min(solve(root.left, false, true), solve(root.left, true, true)) + Math
          .min(solve(root.right, false, true), solve(root.right, true, true));
      dp.put(p, curr);
      return curr;
    } else {
      if (pCamera) {
        int curr = Math.min(solve(root.left, false, false), solve(root.left, true, false)) + Math.min(solve(root.right, false, false),
            solve(root.right
                , true,
                false));
        dp.put(p, curr);
        return curr;
      } else {
        int op1 = solve(root.left, true, false) + Math.min(solve(root.right, false, false), solve(root.right, true, false));
        int op2 = solve(root.right, true, false) + Math.min(solve(root.left, false, false), solve(root.left, true, false));
        int min = Math.min(op1, op2);
        dp.put(p, min);
        return min;
      }
    }
  }


}
