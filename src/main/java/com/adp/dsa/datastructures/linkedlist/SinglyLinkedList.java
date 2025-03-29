package com.adp.dsa.datastructures.linkedlist;

import java.util.LinkedList;

public class SinglyLinkedList<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;
    public class Node<E> {
        E data;
        Node next;

        public Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    public SinglyLinkedList() {
        this.head = this.tail = null;
        this.size = 0;
    }

    // Method to add a node at the end of the list
    public void addLast(E value) {
        Node newNode = new Node(value);

        // Edge case 1: Add to an empty list
        if (isEmpty()) {
            head = tail = newNode;  // List is empty, both head and tail point to the new node
        } else {
            tail.next = newNode;  // Link the new node to the last node
            tail = newNode;       // Update the tail pointer to the new node
        }

        size++;  // Increase the size of the list
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
        return size == 0;
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


