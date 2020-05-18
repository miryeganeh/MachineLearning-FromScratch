package com.company;

public class DLL <E> implements  DS<E>{

    class Node<E> {
        E data;
        Node<E> prev;
        Node<E> next;
        Node(E d) {
            data = d;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }
    Node<E> head; // head of list

    public Node<E> getHead() {
        return head;
    }

    public void setHead(Node<E> head) {
        this.head = head;
    }

    // Add a node at the end of the list
    public void insert(E data){
        Node<E> new_node = new Node<>(data);
        Node<E> last = head;
        new_node.next = null;
        if (head == null) {
            new_node.prev = null;
            head = new_node;
            return;
        }
        while (last.next != null)
            last = last.next;
        last.next = new_node;
        new_node.prev = last;
    }
    public boolean delete(String value){
        Node<E> current=getHead();
        if (current.getData().equals(value)) {
            setHead(getHead().getNext());
            return true;
        }
        while(current.getNext()!=null){
            if ( current.getNext().getData().equals(value) && current.getNext().getNext()!=null){
                Node<E> nextNode=current.getNext().getNext();
                current.setNext(nextNode);
                nextNode.setPrev(current);
                return true;
            }
            current=current.getNext();
        }
        return false;
    }

    public boolean search(String value){
        Node<E> current=getHead();
        if (current.getData().equals(value))
            return true;
        while(current!=null) {
            if (current.getData().equals(value))
                return true;
            current=current.getNext();
        }
        return false;
    }

    public void print() {
        Node<E> currNode = this.head;
        System.out.print("Doubly LinkedList: ");
        while (currNode != null) {
            System.out.print(currNode.data + " ");
            currNode = currNode.getNext();
        }
    }

    public E getByValue(String value){
        Node<E> current=getHead();
        if(current!=null) {
            if (current.getData().equals(value))
                return current.getData();
            while (current != null) {
                if (current.getData().equals(value))
                    return current.getData();
                current = current.getNext();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        Node<E> currNode = this.head;
        String result="{";
        while (currNode != null) {
            result+=currNode.toString();
            currNode = currNode.getNext();
        }
        result+="}";
        return result;
    }
}

