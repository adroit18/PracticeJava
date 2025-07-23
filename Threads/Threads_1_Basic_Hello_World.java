package Threads;

public class Threads_1_Basic_Hello_World extends Thread{
    
    public static void main(String[] args) {
        Threads_1_Basic_Hello_World thread = new Threads_1_Basic_Hello_World();
        thread.start();
    }

    @Override
    public void run(){
        System.out.println("Hello World");
    }
}