/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author USUARIO
 */
public class AccesoryDecorator extends Decorator{
    public AccesoryDecorator(Emoji e,  Accesory a){
        super(e);
        agregarAccesorio(a, e);
        
    }
    public void agregarAccesorio(Accesory a,Emoji e){
        e.setA(a);
        System.out.println("Emoji con accesorio");
    }
}
