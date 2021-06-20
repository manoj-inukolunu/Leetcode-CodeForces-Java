package net;


public class AddOne {

  static class ListNode {

    ListNode next;
    int val;

    public ListNode(int val) {
      this.val = val;
    }
  }

  private void printList(ListNode node) {
    if (node == null) {
      return;
    }
    System.out.print(node.val + " ");
    printList(node.next);
  }

  public ListNode addOne(ListNode node) {
    ListNode temp = node;
    ListNode nonNine = null;
    while (temp != null) {
      if (temp.val != 9) {
        nonNine = temp;
      }
      temp = temp.next;
    }
    if (nonNine != null) {
      nonNine.val++;
      nonNine = nonNine.next;
      while (nonNine != null) {
        nonNine.val = 0;
        nonNine = nonNine.next;
      }
    } else {
      ListNode head = new ListNode(1);
      temp = node;
      while (temp != null) {
        temp.val = 0;
        temp = temp.next;
      }
      head.next = node;
      return head;
    }
    return node;
  }

  public static void main(String args[]) {
    AddOne addOne = new AddOne();
    ListNode head = new ListNode(-1);
    ListNode temp = head;
    for (int i = 0; i < 5; i++) {
      temp.next = new ListNode(i);
      temp = temp.next;
    }
    /*temp.next = new ListNode(9);
    temp = temp.next;
    temp.next = new ListNode(6);
    temp = temp.next;
    temp.next = new ListNode(9);*/

    addOne.printList(head.next);
    System.out.println();

    ListNode ret = addOne.addOne(head.next);
    addOne.printList(ret);


  }
}



