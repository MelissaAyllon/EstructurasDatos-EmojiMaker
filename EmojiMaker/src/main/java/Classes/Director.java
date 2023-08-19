/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author USUARIO
 */
public class Director {
    private static Director instance;
    private String dirName;
    private Director(String dirName) {
        this.dirName=dirName;
    }
    public static Director getInstance(String dirName){
        if (instance==null){
            instance=new Director(dirName);
        }
        return instance;
    }

    public String getDirName() {
        return dirName;
    }
    
    public void constructEmoji(Builder b, Eye e, Mouth m, Brow br, Face f, String p){
        b.setBrows(br);
        b.setEyes(e);
        b.setMouth(m);
        b.setFace(f);
        b.setPortada(p);
    }
     
    public void constructAccesoryEmoji(Builder b, Eye e, Mouth m, Brow br, Face f, String p, Accesory a){
        this.constructEmoji(b, e, m, br, f, p);
        b.setAccesory(a);
    }
}
