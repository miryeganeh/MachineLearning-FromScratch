package com.company;

public class BST <E extends Comparable<Object>>  implements DS<E> {

    class Node <E>{
        E key;
        Node<E> left, right;

        public Node(E item) {
            key = item;
            left = right = null;
        }
        public E getKey() {
            return key;
        }

        public void setKey(E key) {
            this.key = key;
        }

        public Node<E> getLeft() {
            return left;
        }

        public Node<E> getRight() {
            return right;
        }
    }

    Node<E> root;

    BST() {
        root = null;
    }

    public Node<E> getRoot() {
        return root;
    }

    public void setRoot(Node<E> root) {
        this.root = root;
    }

    public void insert(E data) {
        setRoot(insertRec(getRoot(), data));
    }

    Node<E> insertRec(Node<E> root, E key) {

        /* If the tree is empty, return a new node */
        if (root == null) {
            root = new Node<E>(key);
            return root;
        }

        /* Otherwise, recur down the tree */
        if (key.compareTo(root.getKey())<0)
            root.left = insertRec(root.getLeft(), key);
        else
            root.right = insertRec(root.getRight(), key);

        /* return the (unchanged) node pointer */
        return root;
    }

    // This method mainly calls InorderRec()
    public void print() {
        inorderRec(root);
    }

    public  Node<E> minimumKey(Node<E> curr)
    {
        Node<E> parent =  curr;
        if (curr.getLeft() == null)
            return null;
        curr = curr.getLeft();
        while(curr.getLeft() != null) {
            curr = curr.getLeft();
        }
        return parent;
    }

    public boolean delete(String value){
        return deleteNodeByValue(getRoot(),value);
    }


    // Function to delete node from a BST
    public boolean deleteNodeByValue(Node<E> root, String key)
    {
        // pointer to store parent node of current node
        Node<E> parent = null;

        // start with root node
        Node<E> curr = root;

        // search key in BST and set its parent pointer
        while (curr != null && ! curr.getKey().equals(key) )
        {
            // update parent node as current node
            parent = curr;

            // if given key is less than the current node, go to left subtree
            // else go to right subtree
            if (((E)curr.getKey()).compareTo(key)>0) {
                curr = curr.getLeft();
            }
            else {
                curr = curr.getRight();
            }
        }

        // return if key is not found in the tree
        if (curr == null) {
            return false;
        }

        // Case 1: node to be deleted has no children i.e. it is a leaf node
        if (curr.left == null && curr.right == null)
        {
            // if node to be deleted is not a root node, then set its
            // parent left/right child to null
            if (curr != root) {
                if (parent.left == curr) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
            // if tree has only root node, delete it and set root to null
            else {
                setRoot(null);
            }
        }

        // Case 2: node to be deleted has two children
        else if (curr.left != null && curr.right != null)
        {
            // find its in-order successor node
            Node<E> successor = curr.getRight();
            Node<E> successorParent  = minimumKey(successor);

            // store successor value
            E val = (E)successor.getKey();

            // recursively delete the successor. Note that the successor
            // will have at-most one child (right child)
            deleteNode(successorParent, successor);

            // Copy the value of successor to current node
            curr.setKey(val);
        }

        // Case 3: node to be deleted has only one child
        else
        {
            // find child node
            Node<E> child = (curr.getLeft() != null)? curr.getLeft(): curr.getRight();

            // if node to be deleted is not a root node, then set its parent
            // to its child
            if (curr != root)
            {
                if (curr == parent.left) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
            }

            // if node to be deleted is root node, then set the root to child
            else {
                setRoot(child);
            }
        }

        return true;
    }



    // Function to delete node from a BST
    public void deleteNode(Node<E> parent, Node<E> nodeToDelete)
    {
        if (parent.left.equals(nodeToDelete)) {
            parent.left = null;
        } else {
            parent.right = null;
        }
    }



    public boolean search(String value){
        if (recursiveSearch(getRoot(),value)==null)
            return false;
        return true;
    }

    public E getByValue(String value){
        return recursiveSearch(getRoot(),value);
    }

    public E recursiveSearch(Node<E> node, String value){
        if(node==null)
            return null;
        if(node.getKey().equals(value))
            return (E) node.getKey();

        if (node.getKey().compareTo(value)>0)
            return recursiveSearch(node.getLeft(),value);
        else
            return recursiveSearch(node.getRight(),value);

    }
    // A utility function to do inorder traversal of BST
    void inorderRec(Node<E> root) {
        if (root != null) {
            inorderRec(root.getLeft());
            System.out.println(root.key);
            inorderRec(root.getRight());
        }
    }

}