//Tristan Hayes
//Program 4
public class MyLinkedStack<E> {
    private Node top;
    private class Node {
        E data;
        Node next;

        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public void push(E newData){
        top = new Node(newData,top);
    }

    public E pop(){
        if(empty())
            throw new java.util.EmptyStackException();
        E tmp = top.data;
        top = top.next;
        return tmp;

    }

    public E peek(){
        return top.data;
    }

    public boolean empty(){
        return top==null;
    }

    public int size(){
        int c = 0;
        Node temp = top;
        while(temp!=null){
            c += 1;
            temp = temp.next;
        }
        return c;
    }
}
