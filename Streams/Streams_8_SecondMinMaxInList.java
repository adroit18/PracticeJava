package Streams;

import java.util.*;

public class Streams_8_SecondMinMaxInList {
    public static void main(String args[]) {
        
        ArrayList<Integer> inputList = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            inputList.add((int)(Math.ceil(Math.random() * 10)));
        }

        System.out.println(inputList);

        System.out.println(inputList.stream().distinct().sorted().skip(1).findFirst().orElse(null));
        System.out.println(inputList.stream().distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst().orElse(null));

    }

}
