public class RedBlackTree<T extends Comparable<T>> {

    /* Root of the tree. */
    RBTreeNode<T> root;

    static class RBTreeNode<T> {

        final T item;
        boolean isBlack;
        RBTreeNode<T> left;
        RBTreeNode<T> right;

        /* Creates a RBTreeNode with item ITEM and color depending on ISBLACK
           value. */
        RBTreeNode(boolean isBlack, T item) {
            this(isBlack, item, null, null);
        }

        /* Creates a RBTreeNode with item ITEM, color depending on ISBLACK
           value, left child LEFT, and right child RIGHT. */
        RBTreeNode(boolean isBlack, T item, RBTreeNode<T> left,
                   RBTreeNode<T> right) {
            this.isBlack = isBlack;
            this.item = item;
            this.left = left;
            this.right = right;
        }
    }

    /* Creates an empty RedBlackTree. */
    public RedBlackTree() {
        root = null;
    }

    /* Creates a RedBlackTree from a given 2-3 TREE. */
    public RedBlackTree(TwoThreeTree<T> tree) {
        Node<T> ttTreeRoot = tree.root;
        root = buildRedBlackTree(ttTreeRoot);
    }

    /* Builds a RedBlackTree that has isometry with given 2-3 tree rooted at
       given node R, and returns the root node. */
    RBTreeNode<T> buildRedBlackTree(Node<T> r) {
        if (r == null) {
            return null;
        }

        if (r.getItemCount() == 1) {
            RBTreeNode<T> left = buildRedBlackTree(r.getChildAt(0));
            RBTreeNode<T> right = buildRedBlackTree(r.getChildAt(1));
            return new RBTreeNode<T>(true, r.getItemAt(0), left, right);
        } else {
            RBTreeNode<T> subRoot = new RBTreeNode<T>(true, r.getItemAt(1));
            subRoot.left = new RBTreeNode<T>(false, r.getItemAt(0));
            subRoot.left.left = buildRedBlackTree(r.getChildAt(0));
            subRoot.left.right = buildRedBlackTree(r.getChildAt(1));
            subRoot.right = buildRedBlackTree(r.getChildAt(2));
            return subRoot;
        }
    }

    /* Flips the color of node and its children. Assume that NODE has both left
       and right children. */
    void flipColors(RBTreeNode<T> node) {
        boolean flag = node.isBlack;
        node.left.isBlack = flag;
        node.right.isBlack = flag;
        node.isBlack = !flag;
    }

    /* Rotates the given node to the right. Returns the new root node of
       this subtree. For this implementation, make sure to swap the colors
       of the new root and the old root!*/
    RBTreeNode<T> rotateRight(RBTreeNode<T> node) {
        boolean rootFlag = node.isBlack;
        RBTreeNode<T> temp = node.left.right;
        RBTreeNode<T> returnNode = node.left;
        node.left.right = node;

//        RBTreeNode<T> track = root;
//        while (track.left != node && track.right != node) {
//            if (track.item.compareTo(node.item) > 0) {
//                track = track.left;
//            }
//            if (track.item.compareTo(node.item) < 0) {
//                track = track.right;
//            }
//        }
//
//        if (track.right == node) {
//            track.right = node.left;
//        } else if (track.left == node) {
//            track.left = node.left;
//        }

        node.left = temp;
        if (rootFlag) {
            node.isBlack = !rootFlag;
        }
        returnNode.isBlack = rootFlag;

        return returnNode;
    }

    /* Rotates the given node to the left. Returns the new root node of
       this subtree. For this implementation, make sure to swap the colors
       of the new root and the old root! */
    RBTreeNode<T> rotateLeft(RBTreeNode<T> node) {
        boolean rootFlag = node.isBlack;
        RBTreeNode<T> temp = node.right.left;
        RBTreeNode<T> returnNode = node.right;
        node.right.left = node;

//        RBTreeNode<T> track = root;
//        while (track.left != node && track.right != node) {
//            if (track.item.compareTo(node.item) > 0) {
//                track = track.left;
//            }
//            if (track.item.compareTo(node.item) < 0) {
//                track = track.right;
//            }
//        }
//
//        if (track.right == node) {
//            track.right = node.right;
//        } else if (track.left == node) {
//            track.left = node.right;
//        }

        node.right = temp;
        if (rootFlag) {
            node.isBlack = !rootFlag;
        }
        returnNode.isBlack = rootFlag;

        return returnNode;
    }

    public void insert(T item) {
        root = insert(root, item);
        root.isBlack = true;
    }

    /* Inserts the given node into this Red Black Tree. Comments have been provided to help break
     * down the problem. For each case, consider the scenario needed to perform those operations.
     * Make sure to also review the other methods in this class! */
    private RBTreeNode<T> insert(RBTreeNode<T> node, T item) {
        // Insert (return) new red leaf node.
        if (node == null) {
            return new RBTreeNode<>(false, item);
        }

        // Handle normal binary search tree insertion.
        int comp = item.compareTo(node.item);
        if (comp == 0) {
            return node; // do nothing.
        } else if (comp < 0) {
            node.left = insert(node.left, item);
        } else {
            node.right = insert(node.right, item);
        }

        // Rotate left operation
        if (!isRed(node) && !isRed(node.left) && isRed(node.right)) {
            node = rotateLeft(node);
        }

        if (!isRed(node) && isRed(node.left) && isRed(node.left.right)) {
            node.left = rotateLeft(node.left);
        }

        // Rotate right operation
        if (!isRed(node) && isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }

        // Color flip
        if (!isRed(node) && isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node; //fix this return statement
    }

    /* Returns whether the given node is red. Null nodes (children of leaf
       nodes) are automatically considered black. */
    private boolean isRed(RBTreeNode<T> node) {
        return node != null && !node.isBlack;
    }

}
