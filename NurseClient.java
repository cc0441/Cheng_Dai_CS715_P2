package client;

import server.MainServer;

import java.io.*;
import java.net.Socket;

public class NurseClient extends Thread {
    private int port = 3000;
    private int number;
    private String address = "localhost";

    public NurseClient(int i){
        super("Nurse-" + i);
        this.number = i;
    }

    public void run(){
        try{
            Socket socket = new Socket(address, port);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            dos.writeUTF("Nurse-" + this.number);
            dos.writeUTF("0");
            msg(dis.readUTF());
            dos.writeUTF("1");
            msg(dis.readUTF());



        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void msg(String message){
        System.out.println(message);
    }

    public static void main(String[] args) {
        System.out.println("Connecting to server is successfully");
        //create multiple Student threads
            new NurseClient(MainServer.nurse).start();

    }
}
