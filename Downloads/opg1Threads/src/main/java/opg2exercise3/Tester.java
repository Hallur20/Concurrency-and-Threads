package opg2exercise3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
    //a) 
//Before you start you should understand the general idea, behind the exercise as described above by answering the following questions.
    //If we need a “large” collection of random numbers, what is the advantage (if any) of introducing threads to “produce” the numbers?
    
    //threaded programs typically finish faster. Also if we have other threads, those threads dont have to wait for this large collection of numbers to be done.
    
//Why does the exercise suggest 4 producer threads, and is that always the right  number?
    
//im guessing deadlock. Dont really know to be honost why there are 4 producer threads and 1 consumer. I would guess that 4 isnt always the 
//correct number for the amount of producers threads, that would be a weird rule.
    
//Given that the Queue is a BlockingQueue implementation, how would you insert data into the Queue (add(), offer(), put() )
//if it’s limited in capacity, and items are produced much faster than they are produced?
    
//I would use a thread, and in the run method of the thread use .put() to insert the data into the Queue. If it's limited in capacity,
    //and items are produced much faster than they are produced i would make sleep between each put()call in order to keep up.
    
//Given that the Queue is a BlockingQueue implementation, how would you fetch data from the Queue (remove(), poll(),
    //take() ) if Production is slow, compared to how we consume items
    
    //I would use a thread, and in the run method of the thread use .take() to fetch data from the Queue

//b)  Use the code provided in day2.rndnumberprodcon as start code for this exercise. Compile and run the main method in Tester.
public class Tester {

  
  public static void main(String[] args) throws InterruptedException {
    //This represent the Queue in the exercise-figure. Observe the size of the Queue
    ArrayBlockingQueue<Integer> numbers = new ArrayBlockingQueue(5);
    
    ExecutorService es = Executors.newCachedThreadPool();
    //Create and start four producers (P1-P4 in the exercise-figure)
    es.execute(new RandomNumberProducer(numbers));
    es.execute(new RandomNumberProducer(numbers));
    es.execute(new RandomNumberProducer(numbers));
    es.execute(new RandomNumberProducer(numbers));
     
    //Create and start single consumer (C1 in the exercise-figure)
    RandomNumberConsumer consumer = new RandomNumberConsumer(numbers);
    es.execute(consumer);    
    
    es.shutdown();
    es.awaitTermination(10, TimeUnit.SECONDS);
    
    System.out.println("Total of all random numbers: " + consumer.getSumTotal());
    System.out.println("Number of random numbers below 50: " + consumer.getBelow50().size());
    System.out.println("Number of random numbers >= 50: " + consumer.getAboveOr50().size());
  }
}
//f) Run and “verify” the behaviour of the completed program