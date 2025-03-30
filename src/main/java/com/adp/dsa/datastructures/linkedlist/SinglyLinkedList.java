package com.adp.dsa.datastructures.linkedlist;

/*
Boundary conditions
1. Empty data structure
2. Single element in the data structure
3. Adding/removing beginning of data structure
4. Adding/removing end of the data structure
5. Working in the middle


 */
public class SinglyLinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;
    public class Node<E> {
        private E data;
        private Node<E> next;
        public Node(E obj) {
            this.data = obj;
            this.next = null;
        }
    }

    public SinglyLinkedList() {
        this.head = this.tail = null;
        this.size = 0;
    }

    /*
        addFirst
        ------------

        1. Create new node
        2. If the list is empty, meaning head is null, the new node becomes both the first and the last node.
                You set both the head and tail of the list to point to this new node, as it's the only node in the list.
        3. If the list already has elements, you don't need to change the tail. You only need to update the head.
                You set the next pointer of the new node to point to the current head (the first node).
                Then, you set the head of the list to the new node, making it the new first node of the list.
        4. Increase the size by 1
     */

    public void addFirst(E value) {
        Node<E> newNode = new Node<E>(value);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            // Order of these line matters
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    // Method to add a node at the end of the list
    public void addLast(E value) {
        Node<E> newNode = new Node<E>(value);

        // Case 1: Add to an empty list
        if (isEmpty()) {
            head = tail = newNode;  // List is empty, both head and tail point to the new node
        } else {
            tail.next = newNode;  // Link the new node to the last node
            tail = newNode;       // Update the tail pointer to the new node
        }

        size++;  // Increase the size of the list
    }

    public void addLastWithoutTail(E value) {
        Node<E> newNode = new Node<E>(value);

        // Case 1 - list is empty
        if (isEmpty()) {
            head = newNode;
            size++;
            return;
        }

        // Case 2 - list is not empty, we need to reach to the end of the list by using temp pointer
        Node<E> temp = head;
        while (temp.next != null)
            temp = temp.next;
        temp.next = newNode;
        size++;
    }

    // Method to remove the first node from the linked list
    public void removeFirst() {
        if (head == null) {  // Case 1: List is empty
            System.out.println("List is empty, nothing to remove.");
            return;
        }

        if (head == tail) {  // Case 2: Only one node in the list
            head = tail = null;  // The list becomes empty after removal
        } else {  // Case 3: Multiple nodes in the list
            head = head.next;  // Move the head pointer to the next node
        }

        size--;  // Decrease the size of the list
    }

    // Get the size of the list
    public int getSize() {
        return size;
    }

    // Check if the list is empty
    public boolean isEmpty() {
        return head == null;
    }

    // Method to print the entire linked list
    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>();
        singlyLinkedList.addLast(1);
        singlyLinkedList.addLast(2);
        singlyLinkedList.addLast(3);
        singlyLinkedList.printList();
    }

}


