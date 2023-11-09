import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Program1 {
    public static void main(String[] args) {
        List<Album> albums = new ArrayList<Album>();
        for(int x=0;x<3;x++){
            albums.add(new Album(
                    askGrab("What is the album's name? "),
                    askGrab("Who is the performer? "),
                    askGrab("What is the genre? ",new String[]{"hip-hop","easy listening","orchestral","your parents","theatre"}),
                    askGrabInt("How many songs are in the album? ")
            ));
            System.out.println(albums.get(x).toString());
        }
    }
    public static String askGrab(String print){
        Scanner stdin = new Scanner(System.in);
        System.out.print(print);
        return stdin.next();
    }
    public static int askGrabInt(String print){
        int val;
        Scanner stdin = new Scanner(System.in);
        System.out.print(print);
        try {
            val = stdin.nextInt();
            return val;
        }
        catch (Exception ignored){}
        return 10;
    }
    public static String askGrab(String print, String[] words){
        Scanner stdin = new Scanner(System.in);
        String text;
        System.out.print(print);
        text = stdin.next();
        for(String word:words){
            if(text.equalsIgnoreCase(word)) return text;
        }
        return "easy listening";
    }
}