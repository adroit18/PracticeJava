package Lambda;
import java.util.ArrayList;
import java.util.Collections;

public class Lambda_5_Sort {
    public interface InnerSort {
        void run(ArrayList<String> arrList);
    }

    public static void main(String[] args) {
        InnerSort innerSort = (ArrayList<String> arrayList) -> {
            Collections.sort(arrayList);
        };

        ArrayList<String> inputList = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            inputList.add(String.valueOf(Math.random() * 10));
        }
        System.out.println(inputList);
        innerSort.run(inputList);
        System.out.println(inputList);
   }
}