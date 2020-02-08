import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;

public class main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Socket soc = null;
        try {
            soc=new Socket("192.168.4.1",1987);
            System.out.println("Connected");
        } catch (UnknownHostException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        int command = 97;

        byte[] cmdByte=new byte[2];
        cmdByte[0]=(byte)command;
        byte[] temp;

        try {
            temp = "!".getBytes("US-ASCII");
            cmdByte[1]=temp[0];
            System.out.println("Message sent");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("Error");
        }

        try {
            soc.getOutputStream().write(cmdByte);
            System.out.println("Message sent");
        } catch (IOException e1) {
            e1.printStackTrace();
            System.out.println("Error");
        }

    }
}
