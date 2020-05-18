package com.company;

class AVL<E extends Comparable<Object>>  implements DS<E>{

    class Node<E> {
        E key;
        int height;
        Node<E> left;
        Node<E> right;

        Node(E d) {
            key = d;
            height = 1;
        }

        public E getKey() {
            return key;
        }

        public void setKey(E key) {
            this.key = key;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }
    }

    Node<E> root;

    // A utility function to get the height of the tree
    int height(Node<E> N) {
        if (N == null)
            return 0;

        return N.height;
    }

    // A utility function to get maximum of two integers
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // A utility function to right rotate subtree rooted with y
    // See the diagram given above.
    Node<E> rightRotate(Node<E> y) {
        Node<E> x = y.left;
        Node<E> T2 = x.right;

        // Perform rotation
        x.setRight(y);
        y.setLeft(T2);

        // Update heights
        y.setHeight( max(height(y.left), height(y.right)) + 1);
        x.setHeight( max(height(x.left), height(x.right)) + 1);

        // Return new root
        return x;
    }

    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    Node<E> leftRotate(Node<E> x) {
        Node<E> y = x.getRight();
        Node<E> T2 = y.getLeft();

        // Perform rotation
        y.setLeft(x);
        x.setRight(T2);

        //  Update heights
        x.setHeight(max(height(x.getLeft()), height(x.getRight())) + 1);
        y.setHeight(max(height(y.getLeft()), height(y.getRight())) + 1);

        // Return new root
        return y;
    }

    // Get Balance factor of node N
    int getBalance(Node<E> N) {
        if (N == null)
            return 0;

        return height(N.getLeft()) - height(N.getRight());
    }

    public void insert(E data){
        setRoot(insert(getRoot(),data));
    }

    Node<E> insert(Node<E> node, E key) {

        /* 1.  Perform the normal BST insertion */
        if (node == null)
            return (new Node<E>(key));

        if (key.equals(node.getKey()))// Duplicate keys not allowed
            return node;
        if (key.compareTo(node.getKey())<0)
            node.setLeft( insert(node.getLeft(), key) );
        else
            node.setRight( insert(node.getRight(), key) );


        /* 2. Update height of this ancestor node */
        node.setHeight( 1 + max(height(node.left), height(node.right)));

        /* 3. Get the balance factor of this ancestor
      node to check whether this node became
      unbalanced */
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there
        // are 4 cases Left Left Case
        if (balance > 1 && key.compareTo(node.getLeft().getKey())<0)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && key.compareTo(node.getRight().getKey())>=0)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && key.compareTo(node.getLeft().getKey())>=0) {
            node.setLeft( leftRotate(node.left) );
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && key.compareTo(node.getRight().getKey())<0) {
            node.setRight( rightRotate(node.getRight()) );
            return leftRotate(node);
        }

        /* return the (unchanged) node pointer */
        return node;
    }

    public Node<E> getRoot() {
        return root;
    }

    public void setRoot(Node<E> root) {
        this.root = root;
    }

    public void print(){
        print_inOrder(getRoot());
    }

    void print_inOrder(Node<E> node) {
        if (node != null) {
            print_inOrder(node.getLeft());
            System.out.print(node.getKey() + " ");
            print_inOrder(node.getRight());
        }
    }

    public boolean search(String value){
        Node<E> current= getRoot();
        while (current!=null) {
            if (current.getKey().equals(value)) {
                return true;
            } else if ((current.getKey()).compareTo(value) > 0)
                current = current.getLeft();
            else
                current = current.getRight();
        }
        return false;
    }

    public E getByValue(String value){
        Node<E> current= getRoot();
            while (current!=null) {
                if (current.getKey().equals(value)) {
                    return current.getKey();
                } else if ((current.getKey()).compareTo(value) > 0)
                    current = current.getLeft();
                else
                    current = current.getRight();
            }
            return null;
    }

    public boolean delete(String value){
        setRoot(deleteNodebyValue(getRoot(),value));
        return true;
    }


    Node<E> minValueNode(Node<E> node)
    {
        Node current = node;

        /* loop down to find the leftmost leaf */
        while (current.left != null)
            current = current.left;

        return current;
    }



    Node<E> deleteNode(Node<E> root, E key)
    {
        // STEP 1: PERFORM STANDARD BST DELETE
        if (root == null)
            return root;

        // If the key to be deleted is smaller than
        // the root's key, then it lies in left subtree
        if (root.getKey().compareTo(key)>0)
            root.setLeft( deleteNode(root.getLeft(), key) );

            // If the key to be deleted is greater than the
            // root's key, then it lies in right subtree
        else if (root.getKey().compareTo(key)<0)
            root.setRight( deleteNode(root.getRight(), key) );

            // if key is same as root's key, then this is the node
            // to be deleted
        else
        {

            // node with only one child or no child
            if ((root.getLeft() == null) || (root.getRight() == null))
            {
                Node<E> temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;

                // No child case
                if (temp == null)
                {
                    temp = root;
                    root = null;
                }
                else // One child case
                    root = temp; // Copy the contents of
                // the non-empty child
            }
            else
            {

                // node with two children: Get the inorder
                // successor (smallest in the right subtree)
                Node<E> temp = minValueNode(root.getRight());

                // Copy the inorder successor's data to this node
                root.setKey(temp.getKey());

                // Delete the inorder successor
                root.setRight( deleteNode(root.getRight(), temp.getKey()) );
            }
        }

        // If the tree had only one node then return
        if (root == null)
            return root;

        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        root.setHeight( max(height(root.getLeft()), height(root.getRight())) + 1 );

        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
        // this node became unbalanced)
        int balance = getBalance(root);

        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && getBalance(root.getLeft()) >= 0)
            return rightRotate(root);

        // Left Right Case
        if (balance > 1 && getBalance(root.getLeft()) < 0)
        {
            root.setLeft( leftRotate(root.left) );
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.getRight()) <= 0)
            return leftRotate(root);

        // Right Left Case
        if (balance < -1 && getBalance(root.getRight()) > 0)
        {
            root.setRight( rightRotate(root.getRight()) );
            return leftRotate(root);
        }

        return root;
    }


    Node<E> deleteNodebyValue(Node<E> root, String key)
    {
        // STEP 1: PERFORM STANDARD BST DELETE
        if (root == null)
            return root;

        // If the key to be deleted is smaller than
        // the root's key, then it lies in left subtree
        if (root.getKey().compareTo(key)>0)
            root.setLeft( deleteNodebyValue(root.getLeft(), key) );

            // If the key to be deleted is greater than the
            // root's key, then it lies in right subtree
        else if (root.getKey().compareTo(key)<0)
            root.setRight( deleteNodebyValue(root.getRight(), key) );

            // if key is same as root's key, then this is the node
            // to be deleted
        else
        {

            // node with only one child or no child
            if ((root.getLeft() == null) || (root.getRight() == null))
            {
                Node<E> temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;

                // No child case
                if (temp == null)
                {
                    temp = root;
                    root = null;
                }
                else // One child case
                    root = temp; // Copy the contents of
                // the non-empty child
            }
            else
            {

                // node with two children: Get the inorder
                // successor (smallest in the right subtree)
                Node<E> temp = minValueNode(root.getRight());

                // Copy the inorder successor's data to this node
                root.setKey(temp.getKey());

                // Delete the inorder successor
                root.setRight( deleteNode(root.getRight(), temp.getKey()) );
            }
        }

        // If the tree had only one node then return
        if (root == null)
            return root;

        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        root.setHeight( max(height(root.getLeft()), height(root.getRight())) + 1 );

        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
        // this node became unbalanced)
        int balance = getBalance(root);

        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && getBalance(root.getLeft()) >= 0)
            return rightRotate(root);

        // Left Right Case
        if (balance > 1 && getBalance(root.getLeft()) < 0)
        {
            root.setLeft( leftRotate(root.left) );
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.getRight()) <= 0)
            return leftRotate(root);

        // Right Left Case
        if (balance < -1 && getBalance(root.getRight()) > 0)
        {
            root.setRight( rightRotate(root.getRight()) );
            return leftRotate(root);
        }

        return root;
    }
}