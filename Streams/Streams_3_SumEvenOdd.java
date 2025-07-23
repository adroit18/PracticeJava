package Streams;
import java.util.ArrayList;

public class Streams_3_SumEvenOdd {
    public static void main(String[] args) {
        
        ArrayList<Integer> inputList = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            inputList.add((int)(Math.ceil(Math.random() * 10)));
        }

        double sum_even = inputList.stream().filter(x -> x % 2 == 0).mapToDouble(x -> x.doubleValue()).sum();
        double sum_odd = inputList.stream().filter(x -> x % 2 != 0).mapToDouble(x -> x.doubleValue()).sum();

        System.out.println(inputList + " sum even : " + sum_even + " sum odd : "+ sum_odd);
    }    
}
