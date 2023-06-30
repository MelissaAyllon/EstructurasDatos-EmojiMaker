/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import TDASimplement.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author melis
 */

//PROBAR TODO ESTO EN EL MAIN
public class Datos{
    
    //como atributo deberiamos tener el file donde se guarde o al menos enviar a cada atributo el file a serializar/deserealizar
    
    //Aqui deberia estar el metodo para deserealizar (escriben los datos)
    public static <E> E escribirDatos(String path){
        FileInputStream fileEntrada;
        ObjectInputStream objetoEntrada;
        E objeto = null;
        try {
            fileEntrada = new FileInputStream(path);
            objetoEntrada = new ObjectInputStream(fileEntrada);
            objeto = (E) objetoEntrada.readObject();
            
            objetoEntrada.close();
            fileEntrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return objeto;
    }
    
    //Cuando se cierre la ventana de galeria (se serializa, se guardan)
    public static<E> void guardarDatos(String path, E objetoSerializar){
        try {
            FileOutputStream fileSalida = new FileOutputStream(path);
            ObjectOutputStream objetoSalida = new ObjectOutputStream(fileSalida);
            
            objetoSalida.writeObject(objetoSerializar);
            objetoSalida.close();
            fileSalida.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    //Escribir los usuarios en primera instancia lo que ya tengo
    public static ArrayList<Usuario> escribirUsuarios(){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        Usuario uprueba=new Usuario("dtruiz", "dtruiz");
        usuarios.addLast(uprueba);
        
        return usuarios;
    }
    
    //sino podriamos poner que retorna un arraylist<e> si no sale de esta primera manera en la de deserealizar
}
