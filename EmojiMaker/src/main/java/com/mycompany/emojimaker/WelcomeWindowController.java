package com.mycompany.emojimaker;

import Classes.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class WelcomeWindowController {
    @FXML
    private HBox panelIngreso;
    @FXML 
    private AnchorPane logInContainer;
    @FXML 
    private TextField userNTextF;
    @FXML 
    private PasswordField passwordTF;
    @FXML
    private Button logInButton;
    @FXML
    private Button btnSigIn;

    public void initialize(URL url, ResourceBundle rb) {
       
    }  
    

    @FXML
    private void logIn(ActionEvent event) throws IOException {
      
            if (allowAcces(userNTextF.getText(),passwordTF.getText())){
                App.setRoot("galleryWindow");
            }
            else{
                Alert alerta=new Alert(Alert.AlertType.ERROR);
                alerta.setContentText("Usuario o contraseña incorrectos. Intente denuevo");
                alerta.showAndWait();
            }
            
        }    
        
    
    public boolean  allowAcces(String usern, String pass){
        boolean valorIngreso=false;  
        Usuario usuarioP=new Usuario(usern, pass);
        for (Usuario u: App.usuarios){
            if (u.equals(usuarioP)){
                App.usuarioSeleccionado=u;
                valorIngreso=true;
            }
        }
        return valorIngreso;
    }

    @FXML
    private void signIn(ActionEvent event) {
        //Crear una nueva ventana para CREAR USUARIO
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateUser.fxml"));
        Parent root;
        
        try {
            root = loader.load();
            Scene  s = new Scene(root);
            Stage st = new Stage();
            st.setScene(s);
            st.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
}
