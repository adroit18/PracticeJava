package Streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Streams_5_Filter {
    public static void main(String[] args) {
        ArrayList<String> inputList = new ArrayList<String>();
        inputList.add("Red");
        inputList.add("Green");
        inputList.add("DBlue");
        inputList.add("Pink");
        inputList.add("Brown");
        System.out.println(inputList);

        List<String> outList = inputList.stream().filter(e ->{
            if(e.startsWith("D")){
                return true;
            }
            return false;
        }).collect(Collectors.toList());

        System.out.println(outList);
    }
}
