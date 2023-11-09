//Tristan Hayes
//Program 3
public class ArrayStackClass<E> {
    private ArrayStackClass<E> data;
    private E newData;
    ArrayStackClass(){

    }

    ArrayStackClass(E newData){
        this.newData = newData;
    }

    ArrayStackClass(ArrayStackClass<E> data, E newData){
        this.data = data;
        this.newData = newData;
    }

    public void push(E newData){
        this.data = new ArrayStackClass<>(this.data,this.newData);
        this.newData =newData;
    }

    public E pop(){
        if(empty())
            throw new java.util.EmptyStackException();
        E top = this.newData;
        this.newData = this.data.newData;
        this.data = this.data.data;
        return top;

    }

    public E peek(){
        return this.newData;
    }

    public boolean empty(){
        return this.newData == null;

    }

    public int size(){
        return this.empty() ? 0 : 1+this.data.size();
    }
}
