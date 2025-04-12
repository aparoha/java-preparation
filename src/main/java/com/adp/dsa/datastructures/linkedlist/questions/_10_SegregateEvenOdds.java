package com.adp.dsa.datastructures.linkedlist.questions;

/*
Given a LinkedList of integers. Modify the LinkedList in such a way that in Modified LinkedList all the even numbers appear before all the odd numbers in LinkedList.

Also, note that the order of even and odd numbers should remain the same.

Examples:

Example 1:
Input: 1→2→3→4→5→6→Null
Output: 2→4→6→1→3→5→Null
Explanation :
Odd Nodes in LinkedList are 1,3,5 and
Even Nodes in LinkedList are 2,4,6
In Modified LinkedList all even Nodes comes before
all Odd Nodes. So Modified LinkedList looks like
2→4→6→1→3→5→Null. Order of even and odd Nodes is
maintained in modified LinkedList.

Example 2:
Input: 1→3→5→Null
Output: 1→3→5→Null
Explantion: As there are no Even Nodes in LinkedList,
The Modified LinkedList is same as Original LinkedList.

Example 3:
Input: 2→4→6→8→Null
Output: 2→4→6→8→Null
Explanation: As there are no Odd Nodes in LinkedList,
The Modified LinkedList is same as Original LinkedList.
 */
public class _10_SegregateEvenOdds {
    private class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /*
            3 scenarios

            1. All evens
            2. All odds
            3. Mixture of evens and odds

           Approach - dummy node
           1. Create 2 dummy nodes - evenDummy, oddDummy
           2. Create 2 pointers pointing to above dummy nodes - evenTail, oddTail (to keep track of end of even or odd nodes found so far)
           3. Make third pointer with name "current" to iterate list
           4. Iterate over list with current pointer
                -> Check node, if it's odd
                    set oddTail.next to current
                    set oddTail to oddTail.next
                    Move current to next node
                -> Check node, if it's even
                    set evenTail.next to current
                    set evenTail to evenTail.next
                    Move current to next node
            5. We want to segregate even first then odd, once loop is finished
                attach evenTail.next to oddDummy.next (i.e. head of odd linkedlist)
                oddTail.next to null (very important, to avoid cycle)
                return evenDummy.next

     */

    public ListNode oddEvenList(ListNode head) {

        if (head == null || head.next == null)
            return head;

        ListNode evenDummy = new ListNode(-2);
        ListNode oddDummy = new ListNode(-1);
        ListNode evenTail = evenDummy;
        ListNode oddTail = oddDummy;
        ListNode current = head;

        while (current != null) {
            if (current.val % 2 == 0) {
                evenTail.next = current;
                evenTail = evenTail.next;
            } else {
                oddTail.next = current;
                oddTail = oddTail.next;
            }
            current = current.next;
        }
        evenTail.next = oddDummy.next;
        oddTail.next = null; // to avoid cycle
        return evenDummy.next;
    }
}
