package com.company;

public class SLL<E> implements DS<E> {

    class Node<E> {
        E data;
        Node<E> next;
        Node(E d) {
            data = d;
            next = null;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }

    Node<E> head;

    public SLL() {
    }

    public Node<E> getHead() {
        return head;
    }

    public void setHead(Node<E> head) {
        this.head = head;
    }

    public void insert(E data) {
        Node<E> new_node = new Node(data);
        new_node.next = null;

        if (this.head == null) {
            this.head = new_node;
        } else {
            Node<E> last = this.head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = new_node;
        }
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

    public boolean delete(String value){
        Node<E> current=getHead();
        if (current.getData().equals(value)) {
            setHead(getHead().getNext());
            return true;
        }
        while(current!=null && current.getNext()!=null){
            if ( current.getNext().getData().equals(value) && current.getNext().getNext()!=null){
                current.setNext(current.getNext().getNext());
                return true;
            }
            current=current.getNext();
        }
        return false;
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

    public void print() {
        Node<E> currNode = this.head;
        while (currNode != null) {
            System.out.print(currNode.getData() + "\n");
            currNode = currNode.getNext();
        }
    }

    @Override
    public String toString() {
        Node<E> currNode = getHead();
        String result="{";
        while (currNode != null) {
            result+=currNode.toString();
            currNode = currNode.next;
        }
        result+="}";
        return result;
    }
}