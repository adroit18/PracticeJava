package Threads;

import java.util.ArrayList;
import java.util.HashSet;

public class Threads_2_EvenOdd implements Runnable{

    private static int[] arr_input = new int[100];
    private static ArrayList<Integer> evenList = new ArrayList<>();
    private static ArrayList<Integer> oddList = new ArrayList<>();

    private int start; 
    private int end;

    public Threads_2_EvenOdd(int from, int till){
        start = from;
        end = till;
    }
    public static void main(String[] args) {
        CreateAndFillArray();
        PrintArray("\nInitial array : ");
        int mid = arr_input.length / 2;

        Thread one = new Thread(new Threads_2_EvenOdd(0, mid));
        Thread two = new Thread(new Threads_2_EvenOdd(mid, arr_input.length));

        one.start(); two.start();
        try {
            one.join();
            two.join();
        } catch (InterruptedException e) {
           e.printStackTrace();
        }

        System.out.println();
        System.out.println(evenList);
        System.out.println();
        System.out.println(oddList);
    }

    @Override
    public void run(){
        FindEvenOdd();
    }

    private void FindEvenOdd(){
       for(int i = start; i< end; i++){
            if(arr_input[i] % 2 == 0){
                evenList.add(arr_input[i]);
            }else{
                oddList.add(arr_input[i]);
            }
       }     
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
