package com.adp.dsa.datastructures.linkedlist;

/*
Boundary conditions
1. Empty data structure
2. Single element in the data structure
3. Adding/removing beginning of data structure
4. Adding/removing end of the data structure
5. Working in the middle

Important
--------------

Stop at last node --> while(temp.next != null)
Stop past the last node --> while(temp != null)

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
    public E removeFirst() {
        // Case 1: List is empty
        if (head == null) {
            return null;
        }
        E result = head.data;

        // Case 2: Only one node in the list
        if (head == tail) {
            head = tail = null;  // The list becomes empty after removal
        } else {  // Case 3: Multiple nodes in the list
            head = head.next;  // Move the head pointer to the next node
        }

        size--;  // Decrease the size of the list

        return result;
    }

    public E removeLast() {
        // Case 1: List is empty
        if (head == null) {
            return null;
        }

        // Case 2: Only one node in the list
        if (head == tail)
            return removeFirst();

        // Case 3: multiple nodes in the list - 2 pointers approach
        Node<E> current = head;
        Node<E> previous = null;
        while (current != tail) {
            //Order is important
            previous = current;
            current = current.next;
        }
        previous.next = null;
        tail = previous;
        size--;
        return current.data;
    }

    public E remove(E obj) {
        if (obj == null || head == null) {
            return null; // Early exit for null object or empty list
        }

        Node<E> current = head;
        Node<E> previous = null;
        Comparable<E> comparableObj = (Comparable<E>) obj;

        while (current != null) {
            // Check if matching node found
            if (comparableObj.compareTo(current.data) == 0) {
                // Case 1 = Found node is first node
                if (current == head) {
                    return removeFirst();
                }
                // Case 2 = Found node is the last node
                if (current == tail) {
                    return removeLast();
                }
                // Case 3 = Found node is in middle of first and last node
                previous.next = current.next;  // Bypass the node
                size--;
                return current.data;
            }
            // Keep moving until
            previous = current;
            current = current.next;
        }
        return null; // Object not found
    }

    public boolean contains(E obj) {
        if (obj == null || head == null) {
            return false; // Early exit for null object or empty list
        }

        Node<E> current = head;
        Comparable<E> comparableObj = (Comparable<E>) obj;

        while (current != null) {
            // Check if matching node found
            if (comparableObj.compareTo(current.data) == 0) {
                return true;
            }
            current = current.next;
        }
        return false; // Object not found
    }

    public E peekFirst() {
        if (head == null)
            return null;
        return head.data;
    }

    public E peekLast() {
        if (tail == null)
            return null;
        return tail.data;
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
        int n = 10;
        for (int i =0; i < n; i++)
            singlyLinkedList.addFirst(i);
        singlyLinkedList.printList();
        for (int i =n - 1; i > 0; i--)
            singlyLinkedList.removeFirst();
        singlyLinkedList.printList();
        for (int i =0; i <= n; i++)
            singlyLinkedList.removeLast();
        singlyLinkedList.printList();
    }

}


