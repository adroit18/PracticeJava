package Streams;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Streams_4_RemoveDuplicate {
    public static void main(String[] args) {
        ArrayList<Integer> inputList = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            inputList.add((int)(Math.ceil(Math.random() * 10)));
        }

        System.out.println(inputList.stream().distinct().collect(Collectors.toList()));
    }
}
