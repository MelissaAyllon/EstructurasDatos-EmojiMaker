/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas;

import Classes.Accesory;
import Classes.AccesoryDecorator;
import Classes.AccesoryEmojiBuilder;
import Classes.Brow;
import Classes.Decorator;
import Classes.Director;
import Classes.Emoji;
import Classes.EmojiBuilder;
import Classes.Eye;
import Classes.Face;
import Classes.Mouth;
import TDASimplement.ArrayList;
import TDASimplement.DCLList;

import com.mycompany.emojimaker.EmojiLienzoController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import java.util.Iterator;
import javafx.scene.image.Image;

/**
 *
 * @author noeli
 */
public class Main {
    
    
    public static void main(String[] args) throws MalformedURLException, FileNotFoundException {
        DCLList<String> lista = new DCLList();
        
        ArrayList<Integer> i=new ArrayList<>();
        i.addLast(1);
        i.addLast(3);
        i.addLast(5);
        i.addLast(40);
        i.addLast(100);
        i.addLast(10);
        i.addLast(40);
        for (Integer in: i){
            System.out.println("antes"+in);
        }
        i.remove(2);
        for (Integer in: i){
            System.out.println("despues"+in);
        }
        lista.addLast("1");
        lista.addLast("2");
        lista.addLast("3");
        lista.addLast("4");
        lista.addLast("5");

        //Prueba remove index 
        lista.remove("2");

        Iterator<String> it = lista.iterator();
        
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    
        /*
        PRUEBA DE CARGAR IMAGENES DEL DIRECTORIO 
        */
        
        File directorio = new File("src\\main\\java\\imagenes\\eyes");
        ArrayList<File> imagenes = cargar(directorio);
        System.out.println("hol");
        //esas files pasarla a image
        
        for(File file: imagenes){
            
            System.out.println("Hola");
            //pasar a imagen
        }
        
        Director d=Director.getInstance("Director");
        System.out.println(d.getDirName());
        Director dNuevo=Director.getInstance("DirNuevo");
        System.out.println(dNuevo.getDirName());
        EmojiBuilder eb=new EmojiBuilder();
        d.constructEmoji(eb, new Eye(), new Mouth(), new Brow(), new Face(), "");
        Emoji e=eb.getResult(); 
        //Imprime el tipo de emoji
        e.printType();
        //Verifica si el emoji tiene accesorio 
        System.out.println(e.hasAccesory());
        //Lo paso al decorador
        Decorator decor=new AccesoryDecorator(e,new Accesory());
        //Emoji luego de aplicarle el decorador
        System.out.println(e.hasAccesory());
    
    }
    //Se ha cambiado el arrayList a el nuestro en el import 
    public static ArrayList<File> cargar(File directorio){
        
        File[] arregloImagenes = directorio.listFiles();
        
        ArrayList<File> imagenes = new ArrayList<>();
        
        for(File foto: arregloImagenes){
            imagenes.addLast(foto);
        }
        
        return imagenes;
     }
    
    public static Image crearImagen(File img) throws FileNotFoundException{

        Image image = new Image(new FileInputStream(img));  
        return image;
    }
        
    

}
