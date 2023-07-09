package com.mycompany.emojimaker;


import Classes.Proyecto;
import Classes.Usuario;
import TDASimplement.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * JavaFX App
 */
public class App extends Application {
  public static ArrayList<Usuario> usuarios=deserInfo();
    private static Scene scene;
    public static Usuario usuarioSeleccionado=null;

    @Override
    public void start(Stage stage) throws IOException {      
        scene = new Scene(loadFXML("welcomeWindow"), 700, 600);
        stage.setScene(scene);
        stage.show();

    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
public static void serializarEstadoActual(ArrayList<Usuario> arrayUsuarios) {
    // este metodo va a serializar el estado actual de mi array de persona//
    System.out.println("Serializado");
    try (ObjectOutputStream serializado = new ObjectOutputStream(new FileOutputStream("serializadoUsuarios.ser"))) {
      // Crea el objeto y el archivo donde se va a guardar mi objeto//
      serializado.writeObject(arrayUsuarios);
      // Escribew mi objeto en el archivo serializado//
    } catch (Exception e) {
      System.out.println(e);
    }
    
  }
 public static ArrayList<Usuario> deserInfo() {
    try (ObjectInputStream recuperacion = new ObjectInputStream(new FileInputStream("serializadoUsuarios.ser"))) {
      // Crea el objeto que recupera lo que serialize
      ArrayList<Usuario> recuperado = (ArrayList<Usuario>) recuperacion.readObject();
      return recuperado;
      // RETORNO LO QUE RECUPERE//

    } catch (Exception e) {
      System.out.println(e);

    }
    return null;
  }
    public static Scene getScene() {
        return scene;
    }
    
}

    
