package Streams;

import java.util.ArrayList;

public class Streams_1_Avg {
    public static void main(String[] args) {

        ArrayList<Integer> inputList = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            inputList.add((int)(Math.ceil(Math.random() * 10)));
        }

        System.out.println(inputList);
        double avg = inputList.stream().mapToDouble(x -> x.doubleValue()).average().orElse(0.0);
        System.out.println(avg);
    }
}
