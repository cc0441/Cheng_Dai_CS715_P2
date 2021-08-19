package server;

import com.sun.tools.javac.Main;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Random;

public class Student extends Thread {
    public static Random rand = new Random();
    double number1, number2;
    private int numStudents;
    private DataOutputStream dos;
    public String name;
    public static long time = System.currentTimeMillis();

    public Student(String name, DataOutputStream dos) {
        super(name);
        this.dos = dos;
    }


    public void hasSubmitted(String name, DataOutputStream dos) throws IOException {
        number1 = rand.nextDouble();
        if (number1 > 0.15) {  //85% student submit the questionnaire
        msg(this.getName() + " has submitted the health questionnaire.");
        dos.writeUTF(name + " has submitted the health questionnaire.");
        MainServer.isSubmit = true;
        MainServer.m.getInLineQ(name, dos);
    }else {  //15% student forgot to submit the questionnaire.
            msg(this.getName() + " has not submitted the health questionnaire.");
            dos.writeUTF(name + "has not submitted the health questionnaire.");
            MainServer.isSubmit = false;
            MainServer.m.getInLineQ(name, dos);
        }
    }

    public void gonurseRoom(String name, DataOutputStream dos) throws IOException {
        if(MainServer.m.found) {
            msg(this.getName() + " go to nurse room.");
            dos.writeUTF(name + " go to nurse room.");
            MainServer.m.studentGoToNurseRoom(this, dos);
            if(MainServer.m.isPostive) {

                msg(this.getName() + " test is positive and send to go home");
                dos.writeUTF(name + " test is positive and send to go home");
                if (MainServer.m.max == 3) {
                    msg("everybody go home.");
                }
            }else {
                // student test negative and go to class room
                msg(this.getName() + " test is negative and go to classroom.");
                dos.writeUTF(name + " test is negative and go to classroom.");
                MainServer.m.studentGoToClassroom(this, dos);
            }
        }else{
            msg(this.getName() + " go to classroom");
            dos.writeUTF(name + " go to classroom");
            MainServer.m.studentGoToClassroom(this, dos);
        }
    }


    public static void msg(String message) {
        System.out.println(message);
    }

}
