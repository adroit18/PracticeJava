package Threads;

import java.util.Arrays;
import java.util.HashSet;

public class Threads_3_ArraySort implements Runnable{
    private static int[] arr_input = new int[100];
    private static int num_threads = 4;
    private int sortFrom = 0;
    private int sortTill = 100;

    public Threads_3_ArraySort(int startSortPos, int endSortEndPos){
        sortFrom = startSortPos;
        sortTill = endSortEndPos;
    }
    public static void main(String[] args) {
        CreateAndFillArray();
        PrintArray("Initial array : ");

        int sortStartPos = 0; int sortEndPos = 0;

        for(int i = 0; i< num_threads; i+=1){
    
            sortStartPos = 0; sortEndPos = sortStartPos + 25;
            Threads_3_ArraySort arrSort = new Threads_3_ArraySort(sortStartPos, sortEndPos);
            Thread thread = new Thread(arrSort);
            thread.start();

            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Threads_3_ArraySort arrSort = new Threads_3_ArraySort(0, 100);
        Thread thread = new Thread(arrSort);
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        PrintArray("\nFinal array : ");
    }

    // @Override
    public void run(){
        Arrays.sort(arr_input,sortFrom, sortTill);
    }

    private static void CreateAndFillArray(){
        HashSet<Integer> set = new HashSet<Integer>();
        int i = 0;

        while (i < 100) {
            int num = ((int)(Math.ceil(Math.random() * 200)));
            if(!set.contains(num)){
                arr_input[i++] = num;
            }
        }
    }

    private static void PrintArray(String pre){
        System.out.println(pre);
        for (int i = 0; i < 100; i++) {
            System.out.print(arr_input[i]+",");
        }
    }
}
