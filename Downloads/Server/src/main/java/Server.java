import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String args[]) throws Exception {
        try {
            ServerSocket ss = new ServerSocket(3333);
            Socket s = ss.accept();

            PrintWriter toClient = new PrintWriter(s.getOutputStream(), true);
            toClient.println("you succesfully connected!");
            Thread1 thread1 = new Thread1(s);
            thread1.start();

        } catch (IOException ex) {
            System.out.println("error test");
        }
    }
}

class Thread1 extends Thread {

    private final Socket clientSocket;

    public Thread1(Socket s) {
        clientSocket = s;
    }

    @Override
    public void run() {
        System.out.println("Server log: Client connected...");

        try {
            PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            

            while (true) {
                String clientInput = fromClient.readLine();
                toClient.println("you typed: " + clientInput);
                if(clientInput.equals("exit")){
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Server log: Problem with Communication Server...");
        }
    }
}
