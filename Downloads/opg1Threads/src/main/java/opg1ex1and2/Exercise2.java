package opg1ex1and2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hvn15
 */
public class Exercise2 {
//a) Are you able to provoke the expected error on your machine?
    //yes, i do get uneven numbers sometimes.
//b) How often does the problem occur?
    //happens almost everytime, i can get lucky and get even number sometimes but that's just random luck.
//c) Use the synchronization techniques you’ve learned today, to make next() method atomic with respect to itself.
    //ok so i added the synchronized keyword to the next() method and now everytime i get even numbers from running main.
    //d) Argue that your solution is correct (argue, don’t prove)
    //I would argue that my solution is correct because i do share the same object which is n, and because of that
    // i would have to put synchronized (preventing thread interference) on my next() method which was requested and done. 

    private int n = 0;

    public synchronized int next() {
        n++;
        n++;
        return n;
    }

    public static void main(String[] args) throws InterruptedException {
        Exercise2 e = new Exercise2();
        e.testThreads();
    }
    
    public void testThreads() throws InterruptedException{
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int res = 0;
                for(int i = 0; i < 10000; i++){
                    res += next();
                }
                System.out.println("Thread1=" + res);
            }
        });
        Thread t2 = new Thread(new Runnable(){
            @Override
            public void run() {
                int res = 0;
                for(int i = 0; i < 10000; i++){
                    res += next();
                }
                System.out.println("Thread2=" + res);
            }
            
        });
        t1.start();
        t2.start();
        
        t1.join();
        t2.join();
    }
}
