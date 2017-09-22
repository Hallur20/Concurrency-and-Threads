package opg2exercise3;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

//c) Complete the run() method in the RandomNumberProducer class, by producing the required number of random numbers and insert them into the numbersProduced Queue (again, chose the right insert method).
public class RandomNumberProducer implements Runnable {

    Random r = new Random();
    public static final int MAX_NUMBERS_TO_PRODUCE = 100;
    public static final int MAX_RANDOM = 100;

    ArrayBlockingQueue<Integer> numbersProduced;

    public RandomNumberProducer(ArrayBlockingQueue<Integer> numbersProduced) {
        this.numbersProduced = numbersProduced;
    }

    @Override
    public void run() {
        for (int i = 1; i <= MAX_NUMBERS_TO_PRODUCE; i++) {
            int random = (int) ((Math.random() * MAX_RANDOM));
            try {
                numbersProduced.put(random);
            } catch (InterruptedException ex) {
                Logger.getLogger(RandomNumberProducer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //Todo: Produce MAX_NUMBERS_TO_PRODUCE of random numbers between 0 and MAX_RANDOM and
        //      place the numbers in numbersProduced
    }

    //By now, you should know how to produce random numbers, but if not, use/run this as a guide
    public static void main(String[] args) {
        int MAX_RAND = 2;
        for (int i = 0; i < 10; i++) {
            int random = (int) ((Math.random() * MAX_RAND + 1));
            System.out.println(random);
        }
    }

}
