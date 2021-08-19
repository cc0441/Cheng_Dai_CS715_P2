package server;

import java.io.DataOutputStream;
import java.io.IOException;

public class Nurse extends Thread {
    public static long time = System.currentTimeMillis();

    public Nurse(int i){
        super("Nurse");
    }

    public void run(){
        synchronized (this){
//

            try{
                sleep(600);
                while(!MainServer.m.waitingStudents2.isEmpty() && MainServer.m.max != 3) {

                    MainServer.m.wakeUPStudent(this);
                }
            }catch (Exception e){

            }
            //}
        }

    }

    public void end() {
        msg("All the students have been tested and nurse go home.");
    }

    private void msg(String s) {
        System.out.println("[" + (System.currentTimeMillis() - Nurse.time) + "]: "+s);
    }

    public void waitingForStudent(String name, DataOutputStream dos) throws IOException {
        msg("Waiting for students come");
        dos.writeUTF(name + "Waiting for students come");

    }
}
