/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.io.Serializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author USUARIO
 */

public class Emoji implements Serializable {
   private Eye e;
   private Mouth m;
   private Brow b;
   private Face f;
   private Accesory a;

    private String portada;
    public Emoji(Eye e, Mouth m,Brow b, Face f , String portada ) {
        this.e=e;
        this.m=m;
        this.b=b;
        this.f=f;
        this.portada=portada;
        
    }
    public boolean hasAccesory(){
        return this.a!=null;
    }
    public Accesory getA() {
        return a;
    }

    public void setA(Accesory a) {
        this.a = a;
    }

    public Eye getE() {
        return e;
    }

    public void setE(Eye e) {
        this.e = e;
    }

    public Mouth getM() {
        return m;
    }

    public void setM(Mouth m) {
        this.m = m;
    }

    public Brow getB() {
        return b;
    }

    public void setB(Brow b) {
        this.b = b;
    }

    public Face getF() {
        return f;
    }

    public void setF(Face f) {
        this.f = f;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }
    
    public void printType(){
        System.out.println("emoji sencillo");
    }
    

}
