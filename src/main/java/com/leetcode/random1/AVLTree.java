package com.leetcode.random1;

public class AVLTree {

  public class Node {

    public int bf;
    public Integer value;
    public int height;
    public Node left, right;

    public Node(Integer value) {
      this.value = value;
    }
  }

  public Node root;
  private int nodeCount = 0;

  public boolean contains(Integer value) {
    return contains(root, value);
  }

  // Recursive contains helper method.
  private boolean contains(Node node, Integer value) {
    if (node == null) {
      return false;
    }

    // Compare current value to the value in the node.
    int cmp = value.compareTo(node.value);

    // Dig into left subtree.
    if (cmp < 0) {
      return contains(node.left, value);
    }

    // Dig into right subtree.
    if (cmp > 0) {
      return contains(node.right, value);
    }

    // Found value in tree.
    return true;
  }

  // Insert/add a value to the AVL tree. The value must not be null, O(log(n))
  public boolean insert(Integer value) {
    if (value == null) {
      return false;
    }
    root = insert(root, value);
    nodeCount++;
    return true;
  }

  // Inserts a value inside the AVL tree.
  private Node insert(Node node, Integer value) {
    // Base case.
    if (node == null) {
      return new Node(value);
    }

    // Compare current value to the value in the node.
    int cmp = value.compareTo(node.value);

    // Insert node in left subtree.
    if (cmp < 0) {
      node.left = insert(node.left, value);

      // Insert node in right subtree.
    } else {
      node.right = insert(node.right, value);
    }

    // Update balance factor and height values.
    update(node);

    // Re-balance tree.
    return balance(node);
  }

  // Update a node's height and balance factor.
  private void update(Node node) {
    int leftNodeHeight = (node.left == null) ? -1 : node.left.height;
    int rightNodeHeight = (node.right == null) ? -1 : node.right.height;

    // Update this node's height.
    node.height = 1 + Math.max(leftNodeHeight, rightNodeHeight);

    // Update balance factor.
    node.bf = rightNodeHeight - leftNodeHeight;
  }

  // Re-balance a node if its balance factor is +2 or -2.
  private Node balance(Node node) {
    // Left heavy subtree.
    if (node.bf == -2) {

      // Left-Left case.
      if (node.left.bf <= 0) {
        return leftLeftCase(node);

        // Left-Right case.
      } else {
        return leftRightCase(node);
      }

      // Right heavy subtree needs balancing.
    } else if (node.bf == +2) {

      // Right-Right case.
      if (node.right.bf >= 0) {
        return rightRightCase(node);

        // Right-Left case.
      } else {
        return rightLeftCase(node);
      }
    }

    // Node either has a balance factor of 0, +1 or -1 which is fine.
    return node;
  }

  private Node leftLeftCase(Node node) {
    return rightRotation(node);
  }

  private Node leftRightCase(Node node) {
    node.left = leftRotation(node.left);
    return leftLeftCase(node);
  }

  private Node rightRightCase(Node node) {
    return leftRotation(node);
  }

  private Node rightLeftCase(Node node) {
    node.right = rightRotation(node.right);
    return rightRightCase(node);
  }

  private Node leftRotation(Node node) {
    Node newParent = node.right;
    node.right = newParent.left;
    newParent.left = node;
    update(node);
    update(newParent);
    return newParent;
  }

  private Node rightRotation(Node node) {
    Node newParent = node.left;
    node.left = newParent.right;
    newParent.right = node;
    update(node);
    update(newParent);
    return newParent;
  }

  // Remove a value from this binary tree if it exists, O(log(n))
  public boolean remove(Integer elem) {
    if (elem == null) {
      return false;
    }

    if (contains(root, elem)) {
      root = remove(root, elem);
      nodeCount--;
      return true;
    }

    return false;
  }

  // Removes a value from the AVL tree.
  private Node remove(Node node, Integer elem) {
    if (node == null) {
      return null;
    }
    int cmp = elem.compareTo(node.value);
    if (cmp < 0) {
      node.left = remove(node.left, elem);
    } else if (cmp > 0) {
      node.right = remove(node.right, elem);
    } else {
      if (node.left == null) {
        return node.right;
      } else if (node.right == null) {
        return node.left;
      } else {
        if (node.left.height > node.right.height) {
          Integer successorValue = findMax(node.left);
          node.value = successorValue;
          node.left = remove(node.left, successorValue);
        } else {
          Integer successorValue = findMin(node.right);
          node.value = successorValue;
          node.right = remove(node.right, successorValue);
        }
      }
    }
    update(node);
    return balance(node);
  }

  private Integer findMin(Node node) {
    while (node.left != null) {
      node = node.left;
    }
    return node.value;
  }


  private Integer findMax(Node node) {
    while (node.right != null) {
      node = node.right;
    }
    return node.value;
  }
}