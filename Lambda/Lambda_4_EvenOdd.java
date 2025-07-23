package Lambda;
public class Lambda_4_EvenOdd {

    public interface InnerEvenOdd {
        String run(int a);
    }

    public static void main(String args[]){
        InnerEvenOdd evenOdd = (int a) -> {
            if(a % 2 == 0){
                return "Even";
            }
            return "Odd";
        };
        evenOdd.run(2);
        evenOdd.run(3);
        evenOdd.run(5);
        evenOdd.run(7);
    }
}
