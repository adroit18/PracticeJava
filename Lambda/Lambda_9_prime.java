package Lambda;
class Lambda_9_prime{
    public interface Innerprime {
        boolean run(int a);
    }

    public static void main(String[] args) {
        Innerprime innerprime = (int a)->{
            int count = 0;
            for(int i = 1; i<=a; i++){
                if(a%i == 0){
                    count+=1;
                }
            }
            if(count == 2) {return true;}
            else {return false;}
        };
        System.out.println(innerprime.run(17)+"");
        System.out.println(innerprime.run(18)+"");
        System.out.println(innerprime.run(41)+"");
        System.out.println(innerprime.run(2)+"");
    }
}