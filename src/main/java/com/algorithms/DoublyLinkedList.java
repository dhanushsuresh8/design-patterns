package main.java.com.algorithms;

import java.util.Iterator;

/*
 * A linked list is a sequential list of 
 * nodes that hold data which point to other nodes also containing data.
 * 
 * Where is it used?
 * 1. Used in many list, queue and statck implementation
 * 2. Great for creating circular lists.
 * 3. Can easily model real world such as trains.
 * 4. Used in seperate chaining, which is present in certain hashtable implementation
 *      to deal with hashing collisions.
 * 5. Often used in the implementation of agjacency lists for graphs.
 * 
 * 
 * Terminology:
 * Head: The first node 
 * Tail : The last node
 * Pointer : refernece for another node
 * Node : is a object holding data
 * 
 * SinglyLinked list : Has pointer for next next node
 * DoublyLinked list: Has pointer for next and previous nodes.
 * 
 *                          Singly          Doubly
 * Searching :              O(n)             O(n)
 * Insert at head:          O(1)            O(1)
 * Inset at tail:           O(1)            O(1)
 * Remove at head:          O(1)            O(1)
 * Remove at tail:          O(n)            O(1)
 * Remove in the middle:    O(n)            O(n)
 */
public class DoublyLinkedList<T> implements Iterable<T> {
    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;
    
    /*
     * Internal class to reperesent data.
     */
    private static class Node<T> {
        private T data;
        private Node<T> prev, next;

        public Node(T data, Node<T> prev, Node<T> next){
            this.data=data;
            this.prev=prev;
            this.next=next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    /**
     * Empty the linked list.
     */
    public void clear(){
        Node<T> trav = head;
        while (trav!=null) {
            Node<T> next = trav.next;
            clearNode(next);
            trav = next;
        }
        head = tail = trav =null;
        size = 0;
    }

    /**
     * Clears the data, previous and next pointers in a Node.
     * @param node {@link Node} the node to remove the contents.
     */
    private void clearNode(Node<T> node){
        node.prev = null;
        node.next = null;
        node.data = null;
    }

    /**
     * Gets the size of the linked list.
     * @return size of the linked list.
     */
    public int size(){
        return size;
    }

    /**
     * Checks if the list is empty.
     * 
     * @return {@code true} is the linked list is empty,
     *  {@code false} is the list is not empty.
     */
    public boolean isEmpty(){
        return size()==0;
    }

    /**
     * Add element to the tail of the list.
     * O(1)
     * 
     * @param elem Element to add into the linked list.
     */
    public void add(T elem){
        addLast(elem);
    }

    /**
     * Add element to the tail of the list.
     * O(1)
     * 
     * @param elem Element to add into the linked list.
     */
    public void addLast(T elem){
        if (isEmpty()) {
            head = tail = new Node<T>(elem,null,null);
        }else{
            tail.next = new Node<T>(elem, tail, null);
            tail = tail.next;
        }
        size++;
    }

    /**
     * Add element to the head of the list.
     * O(1)
     * 
     * @param elem The element to add into the list.
     */
    public void addFirst(T elem){
        if (isEmpty()) {
            head = tail = new Node<T>(elem,null,null);
        }else{
            head.prev = new Node<T>(elem,null,head);
            head = head.prev;
        }
        size++;
    }

    /**
     * Add element to a particular index.
     * 
     * @param index The index at which the element has to be added.
     * @param elem The element to add into the list.
     */
    public void addAt(int index, T elem){
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Not a valid index.");
        }

        if (index==0) {
            addFirst(elem);
            return;
        }

        if (index == size) {
            addLast(elem);
            return;
        }

        Node<T> temp = head;
        for (int i = 0; i < index-1; i++) {
            temp = temp.next;
        }
        Node<T> newNode = new Node<T>(elem, temp, temp.next);
        temp.next.prev = newNode;
        temp.next = newNode;

        size++;
    }

    /**
     * Check the value of first node if it exist, 0(1)
     * 
     * @return T the content in the first node.
     */
    public T peekFirst(){
        if (isEmpty()) {
            throw new RuntimeException("The list is empty");
        }
        return head.data;
    }

    /**
     * Check the value of tail node if it exist, 0(1)
     * @return T the content in the tail node.
     */
    public T peekLast(){
        if (isEmpty()) {
            throw new RuntimeException("The list is empty");
        }
        return tail.data;
    }
    
    /**
     * Remove the head element from the list
     * @return T the element removed from the head of the list.
     */
    public T removeFirst(){
        if (isEmpty()) {
            throw new RuntimeException("The list is empty");
        }
        
        T data = head.data;
        head = head.next;
        --size;
        
        if (isEmpty()) {
            tail=null;
        }
        else{
            head.prev= null;
        }
        
        return data;
    }
    
    /**
     * Remove the tail element from the list
     * 
     * @return T the element removed from the tail of the list.
     */
    public T removeLast(){
        if (isEmpty()) {
            throw new RuntimeException("The list is empty");
        }

        T data = tail.data;
        tail = tail.prev;
        --size;

        if (isEmpty()) {
            head = null;
        }else{
            tail.next = null;
        }

        return data;
    }

    /**
     * Remove the arbitrary node from the list, O(1)
     * 
     * @return The data from hte node.
     */
    private T remove(Node<T> node){
        //If node to be removed is head/tail.
        if (node.prev == null) {
            return removeFirst();
        }
        if (node.next == null) {
            return removeLast();
        }

        node.next.prev = node.prev;
        node.prev.next = node.next;

        T data = node.data;

        // Memory clean up
        node.next = node.prev = null;
        node.data = null;
        
        --size;

        return data;
    }

    /**
     *  Remove a node at particular position in the list.
     * 
     * @param index The index from where the Node to be removed.
     * @return The data from the removed node.
     */
    public T removeAt(int index){
        if (isEmpty()) {
            throw new RuntimeException("The list is empty");
        }

        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Not a valid index.");
        }

        Node<T> trav;

        //Search from the front of the list
        if(index < size/2){
            trav = head;
            for (int i=0; i!=index; i++) {
                trav = trav.next;
            }
        }
        //Search from the back of the list.
        else{
            trav = tail;
            for (int i=size-1; i!=index; i--) {
                trav = trav.prev;
            }
        }

        return remove(trav);
    }

    /**
     * Finds the index of the first occurence of a particular value.
     * @param data The value for which the index needs to be searched.
     * @return The index of the particular value if found else -1.
     */
    public int indexOf(Object data){
        int index = 0;
        Node<T> trav = head;

        if (data==null) {
            for(;trav!=null;trav= trav.next, index++){
                if (trav.data == null) {
                    return index;
                }
            }
        }else{
            for (; trav!=null; trav = trav.next, index++) {
                if (data.equals(trav.data)) {
                    return index;
                }
            }
        }
        return -1;
    }

    /**
     * Check if the value is contained in the linked list.
     * @param obj The Object to check if present in the list.
     * @return {@code true} if the object is found else {@code false}.
     */
    public boolean contains(Object obj){
        return indexOf(obj) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> trav = head;

            @Override
            public boolean hasNext(){
                return trav != null;
            }

            @Override
            public T next() {
                T data = trav.data;
                trav = trav.next;
                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" [ ");
        Node<T> trav = head;
        while(trav != null){
            sb.append(trav.data);
            if (trav.next!=null) {
                sb.append(", ")
            }
            trav = trav.next;
        }
        sb.append(" ]");
        return sb.toString();
    }

}
