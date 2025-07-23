package Threads;

public class Threads_5_PrimeSum implements Runnable {
    private static int LIMIT = 10000000;
    private int from;
    private int till;
    private static long  sum = 0;

    public Threads_5_PrimeSum(int start, int end){
        from = start;
        till = end;
    }
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        
        int parts = LIMIT / 4;
        int start = 0; int end = parts;
        Thread thread1 = new Thread(new Threads_5_PrimeSum(start, end));
        thread1.start();
        start+=parts; end+=parts;
        Thread thread2 = new Thread(new Threads_5_PrimeSum(start, end));
        thread2.start();
        start+=parts; end+=parts;
        Thread thread3 = new Thread(new Threads_5_PrimeSum(start, end));
        thread3.start();
        start+=parts; end+=parts;
        Thread thread4 = new Thread(new Threads_5_PrimeSum(start, end));
        thread4.start();

        thread1.join();thread2.join();
        thread3.join();thread4.join();

        long endTime = System.currentTimeMillis();
        System.out.println();
        System.out.println("Sum: " +sum);
        System.out.println("Time taken: " + (endTime - startTime) + " milliseconds");

    }

    @Override
    public void run(){
        CalculatePrimeSum();
    }

    public void CalculatePrimeSum(){
        for(int i = from; i<till; i++){
            if(isPrime(i)){
                System.out.print(i+", ");
                sum+=i;
            }
        }
    }

    private boolean isPrime(int number) {
        if (number < 2) {
          return false;
        }
  
        for (int i = 2; i <= Math.sqrt(number); i++) {
          if (number % i == 0) {
            return false;
          }
        }
  
        return true;
      }
}

//35091