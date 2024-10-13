package utb.fai;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.net.ServerSocket;

public class App {

    public static void main(String[] args) throws IOException {
        if(args.length != 2){
            System.out.println("error while parsing arguments");
            return;
        }
        int port = Integer.parseInt(args[0]);
        //int port = 12345;
        System.out.println(port);

        int max_threads = Integer.parseInt(args[1]);
        //int max_threads = 10;
        System.out.println(max_threads);


        // Implement input parameter processing

        // create new pool to later add clients to
        ExecutorService threadPool = Executors.newFixedThreadPool(max_threads);

        // loop for main running server
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server running on port: " + port);

            while (true) {
                // accept connection
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected");

                // add client to the pool
                threadPool.submit(new ClientThread(clientSocket));
            }

        } catch (IOException e) {
            e.printStackTrace();}}

}