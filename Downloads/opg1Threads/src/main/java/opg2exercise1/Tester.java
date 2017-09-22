package opg2exercise1;

public class Tester {
    //a)
//Run the main method in the Tester class, and make sure you understand conceptually what happens.
//Especially you should note that these lines : tcX.run() probably takes a noticeable amount of time (why?)
    //well it takes the run method in the TagCounter class (which demans a url to the constructor), that
    // with the use of jsoup gets the name of the title of the website, and the amount of times h1, h2, 
    //div, and body is being used on that site. we get these informations from our main thread metod that
    // prints out the get methods needed such as tc1.getTitle etc.
    //tcX.run() takes a noticeable amount of time because its not being parallel with the other txX.run calls.
    //, also theyre not threads. You have to make use of .start in order to use real threads and making them parallel AND FASTER!
    //b)
//Refactor the TagCounter class to extend the Thread class. This should be very simple (why ?)
    //done. It's simple cause all i have to do is write extends Thread next to the TagCounter class name

    //c)
//Change the Tester class to not call run(), but start the three threads (what's the BIG difference?)
//This will most likely mean that all your system.out’s will be empty or null (why?)
//Fix the problem above
    //big diffirence is that now they run simutaniously, and yes they're now all empty or null.
    //because we have not joined
    // the threads. We have to for example do so that the main thread that is calling tc1.join()
    //will stop running and wait for the tc1 thread to finish. And then it's time to tc2 to join and so on...
    //this. I have fixed this (they do not show 0 or null anymore).
    //d)
//Let's see whether we gained anything by executing the three calculations in parallel, or if we could have achieved the same result via sequential execution.
//First lets see how many Kernels your system offers. Add this line to the beginning of your main():
//System.out.println("Avilable Processors: " + Runtime.getRuntime().availableProcessors());
    //Now use the same principle to measure execution time for parallel execution (don't get the end time before all threads has stopped)
//Explain the results
    //well as expected the parallel was faster. Again this is because they run the threads at the same time, as where the sequential ones
    //run not at the same time. Parallel got: 495124312 and Sequential got 698446407.
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Avilable Processors: " + Runtime.getRuntime().availableProcessors());
long start2 = System.nanoTime();
        TagCounter tc1 = new TagCounter("http://www.fck.dk");
        tc1.start();
        TagCounter tc2 = new TagCounter("http://www.google.com");
        tc2.start();
        TagCounter tc3 = new TagCounter("http://politiken.dk/");
        tc3.start();

        tc1.join();
        tc2.join();
        tc3.join();

        System.out.println("Title: " + tc1.getTitle());
        System.out.println("Div's: " + tc1.getDivCount());
        System.out.println("Body's: " + tc1.getBodyCount());

        System.out.println("Title: " + tc2.getTitle());
        System.out.println("Div's: " + tc2.getDivCount());
        System.out.println("Body's: " + tc2.getBodyCount());

        System.out.println("Title: " + tc3.getTitle());
        System.out.println("Div's: " + tc3.getDivCount());
        System.out.println("Body's: " + tc3.getBodyCount());      
        long end2 = System.nanoTime();
        System.out.println("Time Parallel:  " + (end2 - start2));
        
        long start = System.nanoTime();
        tc1.run();
        tc2.run();
        tc3.run();
        long end = System.nanoTime();
        System.out.println("Time Sequential:" + (end - start));
    }
}
