/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import javafx.scene.image.ImageView;

/**
 *
 * @author melis
 */
public class ImagenProyecto {
    private ImageView imagen;
    private String ruta;

    public ImagenProyecto(ImageView imagen, String ruta) {
        this.imagen = imagen;
        this.ruta = ruta;
    }

    public ImageView getImagen() {
        return imagen;
    }

    public void setImagen(ImageView imagen) {
        this.imagen = imagen;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    
}
