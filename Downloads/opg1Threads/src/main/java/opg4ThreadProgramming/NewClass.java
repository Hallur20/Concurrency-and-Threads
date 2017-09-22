/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opg4ThreadProgramming;

import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @author hvn15
 */
public class NewClass {

    /* Write 2-5 lines of text for each bullet:
When and why will we use Threads in our programs?
    
    we will use threads in our programs mainly when we want the job to be done faster.
    For example if we have some lists and jobs are being executed sequentially it takes alot of 
    until the job is being done, this is where threads are really good.
    
What is the Race Condition Problem and how can you solve it?
    
    it's when two or more threads are able to access shared data and they both try to change it at the same time.
    we can fix this two ways: identifying the method being used with a synchronized keyword, or by manually applying
    it with reentrylock.lock at the start and then at the reentrylock.unlock
    
Explain the Producer/Consumer-problem and how to solve it in modern Java Programs
    
    problem is that the producer and consumer share a common queue. Nothing wrong with that but the rule is that
    the queue can be full or empty, making producer sometimes unable to add and consumer unable to remove.
    This can be fixed with sleep(), consumer can go to sleep if queue is empty, producer can go to sleep if queue is full.
    
Explain what Busy Waiting is and why it's a bad thing in a modern software system.
    
    A process that repeatedly checks if a condition is true. It's a bad thing
    and should be avoided most of the time because it uses processor time that could
    be used on something else, making the whole process slower.
    
Describe Java's BlockingQueue interface, relevant implementations and methods relevant for the producer consumer problem.
    
    "A queue which is thread safe to put into, and take instances from"
    Relevant implementations is implemenets Runnable. The methods relevant for producer consumer
    are .put which  puts a value into the queue, and .take() which takes a value out of the queue (from the head)
    these are also smart because put waits for space and takes wait for available elements.
    
     */
    public static void main(String[] args) throws InterruptedException {
        NewClass n = new NewClass();
        //main thread
        ArrayBlockingQueue<Integer> s1 = new ArrayBlockingQueue(10);
        int[] nums = {4, 5, 8, 12, 21, 22, 34, 35, 36, 37, 42};
        for (int i = 0; i < nums.length; i++) {
            s1.put(nums[i]);
        }
        
    }
    
    private long fib(long n) {
        if ((n == 0) || (n == 1)) {
            return n;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }
}
