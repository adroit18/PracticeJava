package Streams;

import java.util.*;
import java.util.stream.Collectors;

public class Streams_2_ConvertCase {

    public static void main(String[] args) {
        ArrayList<String> strs = new ArrayList<String>();
    
        strs.add("Adriana Jamie");
        strs.add("Felix Uisdean");
        strs.add("Conceicao Palmira");
        strs.add("Jair Camila");
        strs.add("Micaela Rosana");
        
        List<String> strs_uppercase = strs.stream().map(x -> x.toUpperCase()).collect(Collectors.toList());
        List<String> strs_lowercase = strs.stream().map(x -> x.toLowerCase()).collect(Collectors.toList());
        System.out.println(strs);
        System.out.println(strs_uppercase);
        System.out.println(strs_lowercase);
    }
}
