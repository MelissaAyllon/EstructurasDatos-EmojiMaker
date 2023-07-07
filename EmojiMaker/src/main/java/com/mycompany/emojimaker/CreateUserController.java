/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.emojimaker;

import Classes.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author melis
 */
public class CreateUserController {
    
    @FXML
    private AnchorPane mainPanel;
    @FXML
    private TextField nombreUs;
    @FXML
    private PasswordField contUs;
    @FXML
    private Button btnCreaCuenta;

    @FXML
    private void crearCuenta(ActionEvent event) {
        //vamos a agarrar el texto de cada caja
        if (nombreUs.getText().isBlank() || contUs.getText().isBlank()){
            ingresoNegado("usuario y contraseña");
            nombreUs.clear();
            contUs.clear();
            event.consume();
            
        }
        else{
            Usuario u=new Usuario(nombreUs.getText(), contUs.getText());
            boolean valor=false;
            for (Usuario usu: App.usuarios){
                if (usu.equals(u)){
                    valor=true;
                }
            }
            if (valor==false){
            App.usuarios.addLast(u);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registro");
            alert.setHeaderText("Registro Completado Exitosamente");
            alert.setContentText("Puedes inciar sesion");
            alert.showAndWait();
            nombreUs.clear();
            contUs.clear();
            alert.close();
             Stage st = (Stage) nombreUs.getScene().getWindow();
             st.close();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Usuario ya existe") ;
                alert.showAndWait();
                nombreUs.clear();
                contUs.clear();
                alert.close();
            }
           
            
            
        }
        
        //Se cierra la ventana
       
        
        //Aparecer ventana que diga exitoso registro ya puedes iniciar sesion
        
    }
    
    private void ingresoNegado(String error){
        
        //hacer que salga una alerta
        Alert alert = new Alert(Alert.AlertType.ERROR);
        
        alert.setContentText("Debe ingresar " + error) ;
        alert.showAndWait();
        
    }
    
    
}
