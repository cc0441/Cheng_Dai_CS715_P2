package client;

import server.MainServer;

import java.io.*;
import java.net.Socket;

public class TeachersClient extends Thread {
    private int port = 3000;
    private int number;
    private String address = "localhost";

    public TeachersClient(int i){
        super("Teacher-" + i);
        this.number = i;
    }

    public void run(){
        try{
            Socket socket = new Socket(address, port);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            dos.writeUTF("Teacher-" + this.number);
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
        for (int i = 0; i < MainServer.teacher; i++) {//create multiple Student threads
            new TeachersClient(i).start();
        }
    }
}
