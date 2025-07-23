package Lambda;
class Lambda_8_factorial{
    interface InnerFactorial{
        long run(long num);
    }
    public static void main(String[] args) {
        long a = 4;

        InnerFactorial innerFactorial = (long num1) -> {
            long prod = 1;
            for(long i = 1; i <= num1; i++){
                prod *= i;
            }
            return prod;
        };

        System.out.println(innerFactorial.run(a));
    }
}