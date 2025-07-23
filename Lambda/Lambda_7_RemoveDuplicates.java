package Lambda;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Lambda_7_RemoveDuplicates {
    public interface InnerRemoveDuplicates {
        Set<Integer> run(ArrayList<Integer> arrList);
    }

    public static void main(String[] args) {
        InnerRemoveDuplicates innerRemoveDuplicates = (ArrayList<Integer> arrayList) -> {
            Set<Integer> st = new HashSet<Integer>();
            for(int num : arrayList){
                st.add(num);
            }
            return st;   
        };

        ArrayList<Integer> inputList = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            inputList.add((int)(Math.ceil(Math.random() * 10)));
        }
        System.out.println(inputList);
        System.out.println(innerRemoveDuplicates.run(inputList));
   }
}