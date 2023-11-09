//Tristan Hayes
//Program 5
import java.util.Stack;
public class Program5 {
    public static void main(String[] args) {
        Stack<Integer> num = new Stack<>();
        for(int x=0;x<32;x++){
            num.push(x);
        }
        String equation = "42 / 9.2 / 3.1 * 6";
        if(!isValid(equation))
            throw new RuntimeException("Invalid Symbol");
        System.out.println(InfixToPostfix(equation));
    }
    public static String InfixToPostfix (String s) {
        Stack<Character> stack = new Stack<>();
        String output = "";
        for (int x = 0; x <s.length() ; x++) {
            char c = s.charAt(x);
            if(symbol(c)>0){
                while(!stack.empty() && symbol(stack.peek())>=symbol(c)){
                    output += stack.pop();
                }
                stack.push(c);
            }else if(c==')'){
                char c2 = stack.pop();
                while(c2!='('){
                    output += c2;
                    c2 = stack.pop();
                }
            }else if(c=='('){
                stack.push(c);
            }else{
                output += c;
            }
        }
        for (int i = 0; i <=stack.size() ; i++) {
            output += " "+stack.pop();
        }
        return output;
    }

    static boolean isValid(String s){
        for(Character c:s.toCharArray()){
            if(!Character.isDigit(c)&&
                    c!= ' '&&
                    c!='('&&
                    c!=')'&&
                    c!='+'&&
                    c!='–'&&
                    c!='*'&&
                    c!='/'&&
                    c!='%'&&
                    c!='.'
            ){
                System.out.println(c);
                return false;
            }
        }
        return true;
    }

    static int symbol(char c){
        switch (c){
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
            case '%':
                return 2;
        }
        return -1;
    }
}