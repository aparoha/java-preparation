package com.adp.dsa.datastructures.linkedlist.questions;

import com.adp.dsa.datastructures.linkedlist.SinglyLinkedList;

/*

2 approaches

1. In place
2. Using addFirst
 */
public class _01_ReverseLinkedList {

    private class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    private ListNode tempHead, tempTail = null;

    // Approach 1 - reverse using add first
    public ListNode reverse(ListNode head) {
        ListNode current = head;
        while (current != null) {
            ListNode following = current.next;
            current.next = null;
            addFirst(current);
            current = following;
        }
        return tempHead;
    }

    private void addFirst(ListNode newNode) {
        if (tempHead == null) {
            tempHead = tempTail = newNode;
        } else {
            // Order of these line matters
            newNode.next = tempHead;
            tempHead = newNode;
        }
    }


    // Approach 2 - 3 pointers approach
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode current = head, following = head, previous = null;
        while(current != null) {
            following = current.next;
            current.next = previous;
            previous = current;
            current = following;
        }
        return previous;
    }
}
