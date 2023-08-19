/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author USUARIO
 */
public class EmojiAccesory  extends Emoji{
    private Accesory a;

    public EmojiAccesory(Accesory a, Eye e, Mouth m, Brow b, Face f, String portada) {
        super(e, m, b, f, portada);
        this.a = a;
    }

    public Accesory getA() {
        return a;
    }

    public void setA(Accesory a) {
        this.a = a;
    }
    @Override
    public void printType(){
        System.out.println("emoji con accesorio");
    }
    
}
