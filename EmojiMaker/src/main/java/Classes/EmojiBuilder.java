/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author USUARIO
 */ 
public class EmojiBuilder implements Builder{
    private Eye e;
    private Mouth m;
    private Brow b;
    private Face f;
   private String portada;
   public EmojiBuilder(){
       
   }
    @Override
    
    public void setFace(Face s) {
     this.f=s;   
    }

    @Override
    public void setEyes(Eye s) {
     this.e=s;
    }

    @Override
    public void setMouth(Mouth s) {
       this.m=s;
    }

    @Override
    public void setBrows(Brow s) {
       this.b=s;
    }

    @Override
    public void setAccesory(Accesory s) {
        
    }
    @Override
    public void setPortada(String p){
        this.portada=p;
    }
    public Emoji getResult(){
        return new Emoji(e, m, b, f, portada);
    }
}
