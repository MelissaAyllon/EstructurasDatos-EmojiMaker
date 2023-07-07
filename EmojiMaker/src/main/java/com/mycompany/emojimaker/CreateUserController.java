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
import javafx.stage.Stage;

/**
 *
 * @author melis
 */
public class CreateUserController {

    @FXML
    private TextField nombreUs;
    @FXML
    private PasswordField contUs;
    @FXML
    private Button btnCreaCuenta;

    @FXML
    private void crearCuenta(ActionEvent event) {
        //vamos a agarrar el texto de cada caja
        
        String nombre = nombreUs.getText();
        String contrasena = contUs.getText();
        
        //Obtengo el arrayList de usuarios
        //Pero solo si estan los campos llenos
        while(nombre == null){
            ingresoNegado("nombre");
        }
        
        while(contrasena == null){
            ingresoNegado("contraseña");
        }
        //Se anade a la lista
        App.usuarios.addLast(new Usuario(nombre,contrasena));
        
        //serializo la lista
        App.serializarEstadoActual(App.usuarios);
        
        //Se cierra la ventana
        Stage st = (Stage) nombreUs.getScene().getWindow();
        st.close();
        
        //Aparecer ventana que diga exitoso registro ya puedes iniciar sesion
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registro");
        alert.setHeaderText("Registro Completado Exitosamente");
        alert.setContentText("Puedes inciar sesion");
        alert.showAndWait();
    }
    
    private void ingresoNegado(String error){
        
        //hacer que salga una alerta
        Alert alert = new Alert(Alert.AlertType.ERROR);
        
        alert.setContentText("Debe ingresar " + error) ;
        alert.showAndWait();
        
    }
    
    
}
