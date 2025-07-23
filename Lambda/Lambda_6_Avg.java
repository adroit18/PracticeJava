package Lambda;
import java.util.ArrayList;

public class Lambda_6_Avg {
    public interface InnerAvg {
        int run(ArrayList<Integer> arrList);
    }

    public static void main(String[] args) {
        InnerAvg innerAvg = (ArrayList<Integer> arrayList) -> {
            int sum = 0;
            for(Integer i : arrayList){
                sum+=i;
            }
            return sum/arrayList.size();
        };

        ArrayList<Integer> inputList = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            inputList.add((int)(Math.ceil(Math.random() * 10)));
        }
        System.out.println(inputList);
        System.out.println(innerAvg.run(inputList));
   }
}