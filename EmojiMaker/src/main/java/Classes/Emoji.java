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
    private String eyes_url;
    private double eyesX;
    private double eyesY;
    private double eyesW;
    private double eyesH;
    private String mouth_url;
    private double mouthX;
    private double mouthY;
    private double mouthW;
    private double mouthH;
    private String  brows_url;
    private double browsX;
    private double browsY;
    private double browsH;
    private double browsW;
    private String  face_url;
    private double faceX;
    private double faceY;
    private double faceW;
    private double faceH;
    private String  accesory;
    private double accX;
    private double accY;
    private double accH;
    private double accW;

    private String portada;
    public Emoji(String eyes_url,double eX, double eY ,double eH,double eW, 
            String mouth_url, double mX, double mY,double mH, double mW,
            String brows_url, double bX, double bY,double bH,double bW,
            String face_url, double fX, double fY,double fH, double fw,
            String portada) {
        this.eyesH=eH;
        this.eyesW=eW;
        this.eyesX=eX;
        this.eyesY=eY;
        this.mouthX=mX;
        this.mouthY=mY;
        this.mouthW=mW;
        this.mouthH=mH;
        this.faceX=fX;
        this.faceY=fY;
        this.faceW=fw;
        this.faceH=fH;
        this.browsY=bY;
        this.browsX=bX;
        this.browsW=bW;
        this.browsH=bH;
        this.eyes_url = eyes_url;
        this.mouth_url = mouth_url;
        this.brows_url = brows_url;
        this.face_url = face_url;
        this.portada=portada;
        
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
    

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public Emoji(String eyes_url,double eX, double eY ,double eH,double eW,
            String mouth_url, double mX, double mY,double mH,double mW,
            String brows_url,  double bX, double bY,double bH,double bW,
            String face_url,double fX, double fY,double fH,double fW,
            String accesory, double aX, double aY,double aH,double aW,
            String portada) {
        this(eyes_url, eX, eY, eH, eW, mouth_url, mX, mY, mH, mW, brows_url, bX, bY, bH, bW, face_url, fX, fY, fH, fW, portada);
        this.accesory = accesory;
        this.accH=aH;
        this.accW=aW;
        this.accX=aX;
        this.accY=aY;
    }

    public double getEyesX() {
        return eyesX;
    }

    public void setEyesX(double eyesX) {
        this.eyesX = eyesX;
    }

    public double getEyesY() {
        return eyesY;
    }

    public void setEyesY(double eyesY) {
        this.eyesY = eyesY;
    }

    public double getMouthX() {
        return mouthX;
    }

    public void setMouthX(double mouthX) {
        this.mouthX = mouthX;
    }

    public double getMouthY() {
        return mouthY;
    }

    public void setMouthY(double mouthY) {
        this.mouthY = mouthY;
    }

    public double getBrowsX() {
        return browsX;
    }

    public void setBrowsX(double browsX) {
        this.browsX = browsX;
    }

    public double getBrowsY() {
        return browsY;
    }

    public void setBrowsY(double browsY) {
        this.browsY = browsY;
    }

    public double getFaceX() {
        return faceX;
    }

    public void setFaceX(double faceX) {
        this.faceX = faceX;
    }

    public double getFaceY() {
        return faceY;
    }

    public void setFaceY(double faceY) {
        this.faceY = faceY;
    }

    public double getAccX() {
        return accX;
    }

    public void setAccX(double accX) {
        this.accX = accX;
    }

    public double getAccY() {
        return accY;
    }

    public void setAccY(double accY) {
        this.accY = accY;
    }

    public double getEyesW() {
        return eyesW;
    }

    public void setEyesW(double eyesW) {
        this.eyesW = eyesW;
    }

    public double getEyesH() {
        return eyesH;
    }

    public void setEyesH(double eyesH) {
        this.eyesH = eyesH;
    }

    public double getMouthW() {
        return mouthW;
    }

    public void setMouthW(double mouthW) {
        this.mouthW = mouthW;
    }

    public double getMouthH() {
        return mouthH;
    }

    public void setMouthH(double mouthH) {
        this.mouthH = mouthH;
    }

    public double getBrowsH() {
        return browsH;
    }

    public void setBrowsH(double browsH) {
        this.browsH = browsH;
    }

    public double getBrowsW() {
        return browsW;
    }

    public void setBrowsW(double browsW) {
        this.browsW = browsW;
    }

    public double getFaceW() {
        return faceW;
    }

    public void setFaceW(double faceW) {
        this.faceW = faceW;
    }

    public double getFaceH() {
        return faceH;
    }

    public void setFaceH(double faceH) {
        this.faceH = faceH;
    }

    public double getAccH() {
        return accH;
    }

    public void setAccH(double accH) {
        this.accH = accH;
    }

    public double getAccW() {
        return accW;
    }

    public void setAccW(double accW) {
        this.accW = accW;
    }
    

}
