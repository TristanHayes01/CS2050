//Tristan Hayes
//Program 6
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;


public class Program6 {
    public static void main(String[] args) throws IOException {
        int[] arrInt = new int[20000];
        int[] arrInt2 = new int[20000];
        List<Integer> listInt = new ArrayList<Integer>();
        BufferedReader bf = new BufferedReader(new FileReader("NumbersInFile.txt"));
        long startTime,endTime;
        long lineCount;
        try (Stream<String> stream = Files.lines(Path.of("NumbersInFile.txt"), StandardCharsets.UTF_8)) {
            lineCount = stream.count();
        }
        for(int x=0;x<lineCount;x++){
            int temp = Integer.parseInt(bf.readLine());
            arrInt[x] = temp;
            arrInt2[x] = temp;
            listInt.add(temp);
        }
        bf.close();
        startTime = System.nanoTime();
        bubbleSortInt(arrInt);
        endTime = System.nanoTime();
        System.out.println("Bubble sort for "+lineCount+" integers took: "+((endTime - startTime) / 1000000)+"ms");
        startTime = System.nanoTime();
        selectionSortInt(arrInt2);
        endTime = System.nanoTime();
        System.out.println("Selection sort for "+lineCount+" integers took: "+((endTime - startTime) / 1000000)+"ms");
        startTime = System.nanoTime();
        Collections.sort(listInt);
        endTime = System.nanoTime();
        System.out.println("Collection sort for "+lineCount+" integers took: "+((endTime - startTime) / 1000000)+"ms");

        //Part 2
        String[] arrString = new String[10000];
        String[] arrString2 = new String[10000];
        List<String> listString = new ArrayList<String>();
        bf = new BufferedReader(new FileReader("StringsinFile"));
        try (Stream<String> stream = Files.lines(Path.of("StringsinFile"), StandardCharsets.UTF_8)) {
            lineCount = stream.count();
        }
        for(int x=0;x<lineCount;x++){
            String temp = bf.readLine();
            arrString[x] = temp;
            arrString2[x] = temp;
            listString.add(temp);
        }
        bf.close();
        startTime = System.nanoTime();
        bubbleSortString(arrString);
        endTime = System.nanoTime();
        System.out.println("Bubble sort for "+lineCount+" strings took: "+((endTime - startTime) / 1000000)+"ms");
        startTime = System.nanoTime();
        selectionSortString(arrString2);
        endTime = System.nanoTime();
        System.out.println("Selection sort for "+lineCount+" strings took: "+((endTime - startTime) / 1000000)+"ms");
        startTime = System.nanoTime();
        Collections.sort(listString);
        endTime = System.nanoTime();
        System.out.println("Collection sort for "+lineCount+" strings took: "+((endTime - startTime) / 1000000)+"ms");
    }
    static void bubbleSortInt(int arr[])
    {
        int n = arr.length;
        int i, j, temp;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {

                    // Swap arr[j] and arr[j+1]
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (swapped == false)
                break;
        }
    }
    static void bubbleSortString(String[] arr)
    {
        int n = arr.length;
        String temp;
        for (int j = 0; j < n - 1; j++) {
            for (int i = j + 1; i < n; i++) {
                if (arr[j].compareTo(arr[i]) > 0) {
                    temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
    }
    static void selectionSortInt(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
        {
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }
    static void selectionSortString(String arr[])
    {
        int n = arr.length;
        for(int i = 0; i < n - 1; i++)
        {
            int min_index = i;
            String minStr = arr[i];
            for(int j = i + 1; j < n; j++)
            {
                if(arr[j].compareTo(minStr) < 0)
                {
                    minStr = arr[j];
                    min_index = j;
                }
            }
            if(min_index != i)
            {
                String temp = arr[min_index];
                arr[min_index] = arr[i];
                arr[i] = temp;
            }
        }
    }
}
