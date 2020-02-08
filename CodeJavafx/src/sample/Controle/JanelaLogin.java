package sample.Controle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
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
import javafx.stage.StageStyle;
import sample.Animacoes.SlideInRight;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;


public class JanelaLogin {

    @FXML
    private MediaView mv;
    private MediaPlayer mediaPlay;
    @FXML
    private AnchorPane register;
    @FXML
    private AnchorPane sign;
    @FXML
    private AnchorPane Principal;
    @FXML
    private JFXTextField tfLogin;
    @FXML
    private JFXPasswordField tfPass;
    @FXML
    private JFXTextField rLogin;
    @FXML
    private JFXPasswordField rPass;


    public void initialize(){
        try{
            register.setVisible(false);
            new SlideInRight(sign).play();
            File f = new File("binary.mp4");
            Media media = new Media(f.toURI().toString());
            mediaPlay = new MediaPlayer(media);
            //mv.setMediaPlayer(mediaPlay);
            mediaPlay.play();
            mediaPlay.setCycleCount(mediaPlay.INDEFINITE);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void Sair(){
        Platform.exit();
    }

    @FXML
    public void ShowRegister(){
        new SlideInRight(register).play();
        register.setVisible(true);
        sign.setVisible(false);
    }

    @FXML
    public void ShowSign(){
        new SlideInRight(sign).play();
        sign.setVisible(true);
        register.setVisible(false);
        rLogin.setText("");
        rPass.setText("");
    }
    @FXML
    public void Cancel(){
        tfLogin.setText("");
        tfPass.setText("");
        rLogin.setText("");
        rPass.setText("");
    }

    @FXML
    public void Register(){
        //registrar
        new SlideInRight(sign).play();
        sign.setVisible(true);
        register.setVisible(false);
    }

    @FXML
    public void Login(){
        JanelaControle();
    }

    public void JanelaControle(){
        try{
            Stage stage = (Stage)Principal.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../Visao/janelaControle.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}


