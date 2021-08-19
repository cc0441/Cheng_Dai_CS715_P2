package server;

import java.io.DataOutputStream;
import java.io.IOException;

public class Principal extends Thread {
    public Principal(int i){
        super("Principal");
    }

    public void run(){


        try {
            sleep(100);
            MainServer.m.PrincipleGetIn(this);

            while(!MainServer.m.waitingStudents1.isEmpty() && MainServer.m.max != 3) {
                //sleep(100);
                MainServer.m.getInLineC(this); //decide to go to nurse room or classroom
            }
        } catch (Exception e) {

        }
    }

    private void msg(String s) {
        System.out.println("[" + (System.currentTimeMillis() - MainServer.time) + "]: " + s);
    }

    public void waitingForStudent(String name, DataOutputStream dos) throws IOException {
        msg("Waiting for students come");
        dos.writeUTF(name + "Waiting for students come");
    }

    public void end() {
        msg("Principal goes home.");
    }
}
