package sample.Controle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import sample.Animacoes.SlideInRight;
import sample.Animacoes.ZoomInDown;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class JanelaControle {


    @FXML
    private MediaView mv;
    private MediaPlayer mediaPlay;

    @FXML
    private AnchorPane control;
    @FXML
    private AnchorPane data;
    @FXML
    private AnchorPane Principal;
    @FXML
    private JFXSlider slider;
    @FXML
    private JFXButton btnAvancar;

    int command;
    Socket soc;


    public void initialize(){
        createConnection();
        try{
            new ZoomInDown(data).play();
            new SlideInRight(control).play();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    public void Sair(){
        closeConnection();
        Platform.exit();
    }

    @FXML
    public void logout(){
        try{
            Stage stage = (Stage)Principal.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../Visao/janelaLogin.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void getVelocidade(){
        System.out.println((int)slider.getValue());
    }

    @FXML
    public void avancar(){
        command = 97;
        writeCommand(command);
        System.out.println("AVANCAR");
    }

    @FXML
    public void parar(){
        command = 98;
        writeCommand(command);
        System.out.println("PARAR");
    }

    private boolean isConnected() {
        // vê se o socket tá conectado
        if(soc!=null) {
            if(soc.isConnected()) {
                return true;
            }
        }
        return false;
    }

    private void closeConnection() {
        // se o socket ta conectado ent fecha conexão
        if(soc!=null) {
            if(soc.isConnected()) {
                try {
                    soc.close();
                    System.out.println("Socket closed");
                } catch (IOException e1) {
                    e1.printStackTrace();
                    System.out.println("Error closing socket");
                }
            }
        }
    }

    private boolean createConnection(){

        if(isConnected()) {
            return true;
        }

        try {
            String ip = "192.168.4.1";
            soc=new Socket(ip,1987);
            System.out.println("Connected");
        } catch (Exception e1) {
            e1.printStackTrace();
            System.out.println("Can not connect");
        }

        return true;
    }
    //ASCII 97 - a
    private void writeCommand(int command) {
        // If no socket is null return
        if(soc==null) {
            System.out.println("soc null");
            return;
        }
        // if socket is connected send command
        if(soc.isConnected()) {
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

}
