package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
    public static int students = 10;
    public static int principle = 1;
    public static int nurse = 1;
    public static int teacher = 2;
    private static int port = 3000;
    public static long time = System.currentTimeMillis();
    public static Monitor m = new Monitor();
    public static boolean isSubmit = false;
    public static boolean c = true;

    public MainServer(){
        try{
            System.out.println("Server started");
            ServerSocket server = new ServerSocket(port);
            while(true){
                Socket connection = server.accept();
                new SubServerThread(connection).run();
            }
        }catch(IOException e){
            System.out.println("Unable to listen to port.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args){

        new MainServer();
    }
}
