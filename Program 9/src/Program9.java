//Tristan Hayes
//Program 9

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Math.max;

public class Program9{
    static int size=0;
    public static void main(String [] args) throws IOException {
        RedBlackTree bst = new RedBlackTree();
        long startTime = System.nanoTime();
        long endTime;
        long lineCount;
        BufferedReader bf = new BufferedReader(new FileReader("dracula.txt"));
        List<String> strings = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Path.of("dracula.txt"), StandardCharsets.UTF_8)) {
            lineCount = stream.count();
        }
        for(int x=0;x<lineCount;x++){
            for(String string:bf.readLine().toLowerCase().replaceAll("[^a-z ]", "").split(" "))
                strings.add(string);
        }
        for(String string:strings){
            bst.insert(string);
        }
        endTime = System.nanoTime();
        count(bst.getRoot());
        System.out.println("Time Taken: "+(endTime-startTime)+
                "\nTotal Strings: "+strings.size()+
                "\nNodes: "+ size+
                "\nHeight of tree: "+ findHeight(bst.getRoot())+
                "\nThe word \"the\" appeared "+bst.getCount(bst.searchTree("the"))+" times"+
                "\n");
        bst.prettyPrint();
    }
    static int findHeight(Node root){
        if (root == null)
            return 0;
        return 1 + max(findHeight(root.left), findHeight(root.right));
    }
    static void count(Node node) {
        if (node != null) {
            count(node.left);
            count(node.right);
            size++;
        }
    }
}

class Node {
    String data; // holds the key
    Node parent; // pointer to the parent
    Node left; // pointer to left child
    Node right; // pointer to right child
    int color; // 1 . Red, 0 . Black
    int count = 1;
    public void increaseCount(){
        count++;
    }
}
// class RedBlackTree implements the operations in Red Black Tree
class RedBlackTree {
    private Node root;
    private Node TNULL;
    public final int RED = 1;
    public final int BLACK = 0;
    int getCount(Node root){
        return root.count;
    }
    private void preOrderHelper(Node node) {
        if (node != TNULL) {
            System.out.print(node.data + " ");
            preOrderHelper(node.left);
            preOrderHelper(node.right);
        }
    }
    private void inOrderHelper(Node node) {
        if (node != TNULL) {
            inOrderHelper(node.left);
            System.out.print(node.data + " ");
            inOrderHelper(node.right);
        }
    }
    private void postOrderHelper(Node node) {
        if (node != TNULL) {
            postOrderHelper(node.left);
            postOrderHelper(node.right);
            System.out.print(node.data + " ");
        }
    }
    private Node searchTreeHelper(Node node, String key) {
// base case
        if (node == TNULL || key.equalsIgnoreCase(node.data)) {
            return node;
        }
// search left subtree
        if (key.compareToIgnoreCase(node.data)>0) {
            return searchTreeHelper(node.left, key);
        }
// search right subtree
        return searchTreeHelper(node.right, key);
    }
    // fix the rb tree modified by the delete operation
    private void fixDelete(Node x) {
        Node s;
        while (x != root && x.color == BLACK) {
            if (x == x.parent.left) {
                s = x.parent.right;
                if (s.color == RED) {
// case 3.1
                    s.color = BLACK;
                    x.parent.color = RED;
                    leftRotate(x.parent);
                    s = x.parent.right;
                }
                if (s.left.color == BLACK && s.right.color == BLACK) {
// case 3.2
                    s.color = RED;
                    x = x.parent;
                } else {
                    if (s.right.color == BLACK) {
// case 3.3
                        s.left.color = BLACK;
                        s.color = RED;
                        rightRotate(s);
                        s = x.parent.right;
                    }
// case 3.4
                    s.color = x.parent.color;
                    x.parent.color = BLACK;
                    s.right.color = BLACK;
                    leftRotate(x.parent);
                    x = root;
                }
            } else {
                s = x.parent.left;
                if (s.color == RED) {
// case 3.1
                    s.color = BLACK;
                    x.parent.color = RED;
                    rightRotate(x.parent);
                    s = x.parent.left;
                }
                if (s.right.color == BLACK && s.right.color == BLACK) {
// case 3.2
                    s.color = RED;
                    x = x.parent;
                } else {
                    if (s.left.color == BLACK) {
// case 3.3
                        s.right.color = BLACK;
                        s.color = RED;
                        leftRotate(s);
                        s = x.parent.left;
                    }
// case 3.4
                    s.color = x.parent.color;
                    x.parent.color = BLACK;
                    s.left.color = BLACK;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = BLACK;
    }
    private void rbTransplant(Node u, Node v){
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left){
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }
    private void deleteNodeHelper(Node node, String key) {
// find the node containing key
        Node z = TNULL;
        Node x, y;
        while (node != TNULL){
            if (node.data.equals(key)) {
                z = node;
            }
            if (node.data.compareToIgnoreCase(key) <= 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        if (z == TNULL) {
            System.out.println("Couldn't find key in the tree");
            return;
        }
        y = z;
        int yOriginalColor = y.color;
        if (z.left == TNULL) {
            x = z.right;
            rbTransplant(z, z.right);
        } else if (z.right == TNULL) {
            x = z.left;
            rbTransplant(z, z.left);
        } else {
            y = minimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent == z) {
                x.parent = y;
            } else {
                rbTransplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            rbTransplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if (yOriginalColor == BLACK){
            fixDelete(x);
        }
    }
    // fix the red-black tree
    private void fixInsert(Node k){
        Node u;
        while (k.parent.color == RED) {
            if (k.parent == k.parent.parent.right) {
                u = k.parent.parent.left; // uncle
                if (u.color == RED) {
// case 3.1
                    u.color = BLACK;
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
// case 3.2.2
                        k = k.parent;
                        rightRotate(k);
                    }
// case 3.2.1
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    leftRotate(k.parent.parent);
                }
            } else {
                u = k.parent.parent.right; // uncle
                if (u.color == RED) {
// mirror case 3.1
                    u.color = BLACK;
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
// mirror case 3.2.2
                        k = k.parent;
                        leftRotate(k);
                    }
// mirror case 3.2.1
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    rightRotate(k.parent.parent);
                }
            }
            if (k == root) {
                break;
            }
        }
        root.color = BLACK;
    }
    private void printHelper(Node root, String indent, boolean last) {
// print the tree structure on the screen
        if (root != TNULL) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += " ";
            } else {
                System.out.print("L----");
                indent += "| ";
            }
            String sColor = root.color == 1?"RED":"BLACK";
            System.out.println(root.data + "(" + sColor + ")");
            printHelper(root.left, indent, false);
            printHelper(root.right, indent, true);
        }
    }
    private void printHelperPartial(Node root, String indent, boolean last, int currentLevel, int maxLevels) {
        if (root != TNULL && currentLevel <= maxLevels) {
            String tmp ="";
            tmp+=indent;
            if (last) {
                tmp+="R----";
                indent += " ";
            } else {
                tmp+="L----";
                indent += "| ";
            }
            String sColor = root.color == 1 ? "RED" : "BLACK";
            tmp+=root.data + "(" + sColor + ")"+"\n";
            try {
                Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
                path = path.resolve("rbtree.txt");
                BufferedWriter out = new BufferedWriter(new FileWriter(path.toString(), true));
                out.write(tmp);
                out.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            printHelperPartial(root.left, indent, false, currentLevel + 1, maxLevels);
            printHelperPartial(root.right, indent, true, currentLevel + 1, maxLevels);
        }
    }
    public RedBlackTree() {
        TNULL = new Node();
        TNULL.color = BLACK;
        TNULL.left = null;
        TNULL.right = null;
        root = TNULL;
    }
    // Pre-Order traversal
// Node.Left Subtree.Right Subtree
    public void preorder() {
        preOrderHelper(this.root);
    }
    // In-Order traversal
// Left Subtree . Node . Right Subtree
    public void inorder() {
        inOrderHelper(this.root);
    }
    // Post-Order traversal
// Left Subtree . Right Subtree . Node
    public void postorder() {
        postOrderHelper(this.root);
    }
    // search the tree for the key k
// and return the corresponding node
    public Node searchTree(String k) {
        return searchTreeHelper(this.root, k);
    }
    // find the node with the minimum key
    public Node minimum(Node node) {
        while (node.left != TNULL) {
            node = node.left;
        }
        return node;
    }
    // find the node with the maximum key
    public Node maximum(Node node) {
        while (node.right != TNULL) {
            node = node.right;
        }
        return node;
    }
    // find the successor of a given node
    public Node successor(Node x) {
// if the right subtree is not null,
// the successor is the leftmost node in the
// right subtree
        if (x.right != TNULL) {
            return minimum(x.right);
        }
// else it is the lowest ancestor of x whose
// left child is also an ancestor of x.
        Node y = x.parent;
        while (y != TNULL && x == y.right) {
            x = y;
            y = y.parent;
        }
        return y;
    }
    // find the predecessor of a given node
    public Node predecessor(Node x) {
// if the left subtree is not null,
// the predecessor is the rightmost node in the
// left subtree
        if (x.left != TNULL) {
            return maximum(x.left);
        }
        Node y = x.parent;
        while (y != TNULL && x == y.left) {
            x = y;
            y = y.parent;
        }
        return y;
    }
    // rotate left at node x
    public void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != TNULL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }
    // rotate right at node x
    public void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != TNULL) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }
    // insert the key to the tree in its appropriate position
// and fix the tree
    public void insert(String key) {
// Ordinary Binary Search Insertion
        Node node = new Node();
        node.parent = null;
        node.data = key;
        node.left = TNULL;
        node.right = TNULL;
        node.color = RED; // new node must be red
        Node y = null;
        Node x = this.root;
        while (x != TNULL) {
            y = x;
            if (node.data.compareToIgnoreCase(x.data) > 0) {
                x = x.left;
            } else if (node.data.compareToIgnoreCase(x.data) < 0) {
                x = x.right;
            } else {
                x.increaseCount();
                return;
            }
        }
// y is parent of x
        node.parent = y;
        if (y == null) {
            root = node;
        } else if (node.data.compareToIgnoreCase(y.data) > 0) {
            y.left = node;
        }
        else {
            y.right = node;
        }
// if new node is a root node, simply return
        if (node.parent == null){
            node.color = BLACK;
            return;
        }
// if the grandparent is null, simply return
        if (node.parent.parent == null) {
            return;
        }
// Fix the tree
        fixInsert(node);
    }
    public Node getRoot(){
        return this.root;
    }
    // delete the node from the tree
    public void deleteNode(String data) {
        deleteNodeHelper(this.root, data);
    }
    // print the tree structure on the screen
    public void prettyPrint() {
        printHelperPartial(this.root, "", true, 0, 3);
    }

}