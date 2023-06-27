/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author USUARIO
 */
public class Emoji {
    private String eyes_url;
    private String  mouth_url;
    private String  brows_url;
    private String  face_url;
    private String  accesory; 
    private Image portada=null;
    public Emoji(String eyes_url, String mouth_url, String brows_url, String face_url, Image portada) {
        this.eyes_url = eyes_url;
        this.mouth_url = mouth_url;
        this.brows_url = brows_url;
        this.face_url = face_url;
        this.portada=portada;
        
    }

    public Image getPortada() {
        return portada;
    }

    public void setPortada(Image portada) {
        this.portada = portada;
    }

    public Emoji(String eyes_url, String mouth_url, String brows_url, String face_url,String accesory, Image portada) {
        this(eyes_url, mouth_url, brows_url, face_url,portada);
        this.accesory = accesory;
    }

    public String getEyes_url() {
        return eyes_url;
    }

    public void setEyes_url(String eyes_url) {
        this.eyes_url = eyes_url;
    }

    public String getMouth_url() {
        return mouth_url;
    }

    public void setMouth_url(String mouth_url) {
        this.mouth_url = mouth_url;
    }

    public String getBrows_url() {
        return brows_url;
    }

    public void setBrows_url(String brows_url) {
        this.brows_url = brows_url;
    }

    public String getFace_url() {
        return face_url;
    }

    public void setFace_url(String face_url) {
        this.face_url = face_url;
    }

    public String getAccesory() {
        return accesory;
    }

    public void setAccesory(String accesory) {
        this.accesory = accesory;
    }
    
    
    
}
