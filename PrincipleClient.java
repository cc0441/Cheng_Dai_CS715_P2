package client;

import server.MainServer;

import java.io.*;
import java.net.Socket;

public class PrincipleClient extends Thread{
    private int port = 1099;
    private String address = "localhost";
    private  int number;

    public PrincipleClient(int i ){
        super("Principle-" + i);
        this.number = i;
    }

    public void run(){
        try{
            Socket socket = new Socket(address, port);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            dos.writeUTF("Principle-" + this.number);
            dos.writeUTF("0");
            msg(dis.readUTF());
            dos.writeUTF("1");
            msg(dis.readUTF());
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public void msg(String message){
        System.out.println(message);
    }
    public static void main (String [] args)
    {
        System.out.println("Connecting to server is successfully");
        //create multiple Defender threads
            new PrincipleClient(MainServer.principle).start();


    }


}
