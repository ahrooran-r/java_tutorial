package tutorial.learn.datastructures;

import lombok.Getter;
import lombok.Setter;

public class Treap {

    private Node root;

    public void leftRotate(Node x) {
        Node y = x.right;

        x.right = y.left;
        if (y.left != null) y.left.parent = x;

        y.parent = x.parent;
        if (x.parent == null) root = y;
        else if (x == x.parent.left) x.parent.left = y;
        else x.parent.right = y;

        y.left = x;
        x.parent = y;
    }

    public void rightRotate(Node x) {
        Node y = x.left;

        x.left = y.right;
        if (y.right != null) y.right.parent = x;

        y.parent = x.parent;
        if (x.parent == null) root = y;
        else if (x == x.parent.right) x.parent.right = y;
        else x.parent.left = y;

        y.right = x;
        x.parent = y;
    }

    @Getter
    @Setter
    protected static class Node {
        private int key;
        private Node left;
        private Node right;
        private Node parent;
        private int priority;

        public Node(int key, int priority) {
            this.key = key;
            this.priority = priority;
        }
    }
}
