package opg1ex1and2;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.LongStream;



//a) Do you observe a need for synchronization techniques on any of the threads in practise, or does your program work by accident?
// the way i have setup the threads, i dont see any need. There is not any object that is being shared among the threads.

//b) Regardless of whether you observe the problem on your machine, we need to handle it.
//What is the problem I’m hinting at, and how can we solve it? (Since I’m such a nice guy, here’s a hint: think about the VolatileExample class from the demo today).
// from looking at the volatileexample you're hinting at, i would guess the problem of a mutable integer (an integer that is changable), where
//if you're not having a synchronization and you're sharing a mutable object, you might end up with a corruption or unexpected behavior.

//c) argue that your solution is correct (argue, don’t prove)
// i would argue that my solution is correct, because it satifised what was asked for, which was 3 diffirent parallel threads with some
// pauses and a shutdown for thread3. Again i do not see any reason for synchronized or reentrylock because there is no shared object. Also
// i dont see any reason for any join() because none of the threads needed to for some reason wait for the complation of another.


public class Exercise1 {

    public static void main(String[] args) throws InterruptedException {
        Thread1 t1 = new Thread1();
        Thread t2 = new Thread2();
        Runnable t3 = new Thread3();
        // remove: // for t1.start() to see the sums of 1 to a billion, it takes a long time...
//        t1.start();
        t2.start();

        ExecutorService Thread3 = Executors.newSingleThreadExecutor();
        Thread3.submit(t3);
        if (!Thread3.awaitTermination(10000, TimeUnit.MILLISECONDS)) {
            System.out.println("Thread 3 exited after 10 seconds");
            Thread3.shutdownNow();
        }
    }
}

class Thread1 extends Thread {

    Long billion = 1000000000000L;
    Long sum = 0L;

    @Override
    public void run() {
        for (int i = 0; i <= billion; i++) {
            for (int j = 1; j <= i; j++) {
                sum = sum + j;
            }
            System.out.println("round: " + i + ", sum:" + sum);
            sum = 0L;
        }
    }

}

class Thread2 extends Thread {

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(i);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Thread2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

class Thread3 implements Runnable {

    @Override
    public void run() {
        boolean run = true;
        int count = 10;
        while (run == true) {
            System.out.println(count);
            count++;
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                run = false;
            }
        }
    }

}
