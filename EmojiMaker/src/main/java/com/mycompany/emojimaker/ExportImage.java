/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.emojimaker;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javax.imageio.ImageIO;

/**
 *
 * @author USUARIO
 */
public class ExportImage implements ExportStrategy{

    @Override
    public void exportEmoji(AnchorPane ap, String nombreProyecto, String ruta) {
          WritableImage snapshot = ap.snapshot(new SnapshotParameters(), null);
    BufferedImage bufferedImage = SwingFXUtils.fromFXImage(snapshot, null);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    try {
        ImageIO.write(bufferedImage, "png", outputStream);
        byte[] imageData = outputStream.toByteArray();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(imageData);

        String imagePath = ruta +"//"+ nombreProyecto + ".png";
        
        
        //lo guardamos en un archivo que pueda ser enviado cuando se escriba la imagen
        File outputFile = new File(imagePath);
        //se escribe la imagen
        ImageIO.write(bufferedImage, "png", outputFile);
    
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    
}
