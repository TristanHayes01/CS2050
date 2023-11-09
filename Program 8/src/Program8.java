//Tristan Hayes
//Program 8
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Math.max;


public class Program8 {
    static int count = 0;
    public static void main(String[] args) throws IOException {
        long startTime = System.nanoTime();
        long endTime;
        long lineCount;
        BufferedReader bf = new BufferedReader(new FileReader("dracula.txt"));
        List<String> strings = new ArrayList<>();
        node root = null;
        try (Stream<String> stream = Files.lines(Path.of("dracula.txt"), StandardCharsets.UTF_8)) {
            lineCount = stream.count();
        }
        for(int x=0;x<lineCount;x++){
            for(String string:bf.readLine().toLowerCase().replaceAll("[^a-z ]", "").split(" "))
                strings.add(string);
        }
        root = insert(root, strings.get(0));
        for(String string:strings){
            insert(root,string);
        }
        size(root);
        endTime = System.nanoTime();
        System.out.println("Time Taken: "+(endTime-startTime)+
                "\nTotal Strings: "+strings.size()+
                "\nNodes: "+count+
                "\nHeight of tree: "+findHeight(root));
    }

    static class node{
        String key;
        node left,right;
    }
    static node newNode(String key){
        node temp = new node();
        temp.key = key;
        temp.left = temp.right = null;
        return temp;
    }

    static node insert(node node,String key){
        if(node == null)
            return newNode(key);
        if(key.compareTo(node.key) < 0)
            node.left = insert(node.left,key);
        else if(key.compareTo(node.key)>0)
            node.right = insert(node.right, key);
        return node;
    }

    static void size(node root)
    {
        if (root != null) {
            size(root.left);
            size(root.right);
            count++;
        }
    }

    static int findHeight(node root) {
        if (root == null)
            return 0;
        return 1 + max(findHeight(root.left), findHeight(root.right));
    }
}