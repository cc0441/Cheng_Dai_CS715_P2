package server;

import client.PrincipleClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SubServerThread extends Thread {
    private Socket socket;
    private String name;
    private int methodCount;
    private String threadtype;
    private static final String Student = "Student";
    private static final String Teacher = "Teacher";
    private static final String Nurse = "Nurse";
    private static final String Principal = "Principal";
    DataOutputStream dos;
    DataInputStream dis;
    Student s;
    Teachers t;
    Nurse n;
    Principal p;

    public SubServerThread(Socket connection) {
        this.socket = connection;
    }

    public void run() {
        try {
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            this.name = dis.readUTF();
            this.threadtype = name.substring(0, name.indexOf("-"));
            if(this.threadtype.equals(Student)){
                s = new Student(name, dos);
            }
            methodCount = Integer.parseInt(dis.readUTF());
            runMethod(methodCount);
            //methodCount = Integer.parseInt(dis.readUTF());
            //runMethod(methodCount);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void runMethod(int num) throws IOException {
        if (threadtype.equals(Student)) {
            //System.out.println(s);
            switch (num) {
                case 0:
                    s.hasSubmitted(name, dos);
                    break;
                case 1:
                    s.gonurseRoom(name, dos);
                    break;
            }
        }
        else if(threadtype.equals(Teacher)){
            switch (num){
                case 0: t.startClass(name,dos);
                  //  break;
                case 1: t.takeBreak();
                    break;
                case 2: t.end();
            }
        }
        else if (threadtype.equals(Nurse)) {
            switch (num) {
                case 0:
                    n.waitingForStudent(name, dos);
                    break;
                case 1:
                    n.end();
                    break;
            }
        }

        else{
            switch (num){
                case 0:
                    p.waitingForStudent(name,dos);
                    break;
                case 1:
                    p.end();
                    break;
            }
        }
        }
    }
