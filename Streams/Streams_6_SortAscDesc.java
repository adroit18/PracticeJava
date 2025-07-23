package Streams;

import java.util.*;
import java.util.stream.Collectors;

public class Streams_6_SortAscDesc {
    public static void main(String args[]) {
        ArrayList<String> inputList = new ArrayList<String>();
        inputList.add("Red");
        inputList.add("Green");
        inputList.add("DBlue");
        inputList.add("Pink");
        inputList.add("Brown");
        System.out.println(inputList);
        
        System.out.println(inputList.stream().sorted().collect(Collectors.toList()));
        System.out.println(inputList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()));

    }

}
