package Streams;

import java.util.*;

public class Streams_7_MinMaxInList {
    public static void main(String args[]) {
        
        ArrayList<Integer> inputList = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            inputList.add((int)(Math.ceil(Math.random() * 10)));
        }

        System.out.println(inputList);

        System.out.println(inputList.stream().min((x,y)->Integer.compare(x,y)).orElse(null));
        System.out.println(inputList.stream().max((x,y)->Integer.compare(x,y)).orElse(null));

    }

}
