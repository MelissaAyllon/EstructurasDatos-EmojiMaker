/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas;

import Classes.Datos;
import TDASimplement.ArrayList;

/**
 *
 * @author melis
 */
public class PruebaIterators {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        strings.addLast("A");
        strings.addLast("b");
        
        Datos.guardarDatos("src/main/resources/objetos.ser", strings);
        
        
        ArrayList<String> string = Datos.escribirDatos("src/main/resources/objetos.ser");
        for(String st: string){
            System.out.println(st);
        }
    }
    
}
