package tutorial.learn.datastructures;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BinarySearchTree {

    private Node root;

    public void inorderTreeWalk(Node x) {
        if (x != null) {
            inorderTreeWalk(x.left);
            System.out.println(x.key);
            inorderTreeWalk(x.right);
        }
    }

    public Node treeSearchRecursive(Node x, int k) {
        if (x == null || x.key == k) return x;
        if (k > x.key) return treeSearchRecursive(x.right, k);
        else return treeSearchRecursive(x.left, k);
    }

    public Node treeSearchIterative(Node x, int k) {
        while (x != null) {
            if (k > x.key) x = x.right;
            else if (k < x.key) x = x.left;
            else return x;
        }
        return x;
    }

    public Node treeMinimum(Node x) {
        while (x.left != null) x = x.left;
        return x;
    }

    public Node treeMaximum(Node x) {
        while (x.right != null) x = x.right;
        return x;
    }

    public void treeInsert(Node z) {
        Node x = root;
        Node y = null;

        while (x != null) {
            y = x;
            if (z.key > x.key) x = x.right;
            else x = x.left;
        }
        z.parent = y;
        if (y == null) root = z;
        else if (z.key > y.key) y.right = z;
        else y.left = z;
    }

    public Node treeSuccessor(Node x) {
        if (x.right != null) return treeMinimum(x.right);
        Node y = x.parent;
        while (y != null && x == y.right) {
            x = y;
            y = y.parent;
        }
        return y;
    }

    public Node treePredecessor(Node x) {
        if (x.left != null) return treeMaximum(x.left);
        Node y = x.parent;
        while (y != null && x == y.left) {
            x = y;
            y = y.parent;
        }
        return y;
    }

    private void transplant(Node u, Node v) {
        if (u.parent == null) root = v;
        else if (u == u.parent.left) u.parent.left = v;
        else u.parent.right = v;
        if (v != null) v.parent = u.parent;
    }

    public void treeDelete(Node z) {
        if (z.left == null) transplant(z, z.right);
        else if (z.right == null) transplant(z, z.left);
        else {
            Node y = treeMinimum(z.right);
            if (y.parent != z) {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
        }
    }

    @Setter
    @Getter
    private static class Node {
        private int key;
        private Node left;
        private Node right;
        private Node parent;

        public Node(int key) {
            this.key = key;
        }
    }
}
