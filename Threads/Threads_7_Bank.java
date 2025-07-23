package Threads;

public class Threads_7_Bank implements Runnable {

    private static int NUM_OPERATIONS = 100;
    private static Bank bank;
    public static void main(String[] args) throws InterruptedException {
        
        bank = new Bank();

        Thread thread = new Thread(new Threads_7_Bank());
        thread.start();
        thread.join();

        System.out.println(bank.getBalance());
    }  


    public void run(){

        for(int i = 0; i<NUM_OPERATIONS; i++){
            int action = (int)(Math.random() * 10);
            if(action % 2 == 0){
                bank.Debit(10);
            }else{
                bank.Credit(10);
            }
        }

    }

    
    public class Bank{
        private static int balance = 0;
    
        public Bank(){
        }
    
        public void Debit(int amt){
            this.balance = this.balance - amt; 
        }
    
        public void Credit(int amt){
            this.balance = this.balance + amt; 
        }
    
        public int getBalance(){
            return this.balance;
        }
    
    }
}


