package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Random;

public class Teachers extends Thread {

    private static int numTeacher;
    public static long time = System.currentTimeMillis();
    private DataOutputStream dos;
    public static Random rand = new Random();
    public Teachers(String name, DataOutputStream dos) {
        super(name);
        this.dos=dos;
    }



    public void end() {
        msg("Class is over and teachers go home.");
    }

    public void run() {

        synchronized (this) {
            while(MainServer.c){
                MainServer.m.wakeUpInstructorAndStudents(this, dos);
            }
        }
    }



    private void msg(String s) {
        System.out.println("[" + (System.currentTimeMillis() - Teachers.time) + "]: "+s);
    }

    public void takeBreak() {

            MainServer.m.getInLineTeacher(this,dos);
    }

    public void startClass(String name, DataOutputStream dos) throws IOException {
        msg("Waiting for students come");
        dos.writeUTF(name + "Waiting for students come");
    }
}
