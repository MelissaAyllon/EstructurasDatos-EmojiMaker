/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author USUARIO
 */
public class Proyecto {
    String proName;
    Emoji content;

    public Proyecto(String proName, Emoji content) {
        this.proName = proName;
        this.content = content;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public Emoji getContent() {
        return content;
    }

    public void setContent(Emoji content) {
        this.content = content;
    }
    
}
