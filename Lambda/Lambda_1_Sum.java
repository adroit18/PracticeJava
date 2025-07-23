package Lambda;

interface SumFunc {
    int run(int a, int b);
}

public class Lambda_1_Sum {
    public static void main(String args[]){
        int a = 2; int b = 3;
        SumFunc sum = (a1, b1) -> a1+b1;
        System.out.println(sum.run(a,b));
    }

}