/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.emojimaker;

import Classes.Emoji;
import Classes.Proyecto;
import TDASimplement.ArrayList;
import TDASimplement.DCLList;
import TDASimplement.NodeDCLL;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.FlowPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.transform.Source;
import pruebas.Main;
import static pruebas.Main.cargar;

/**
 * FXML Controller class
 *
 * @author USUARIO
 */

public class EmojiLienzoController implements Initializable {
    
    @FXML 
    
    private HBox conBtn_Menu;
    @FXML
    private Button btnPrev;
    @FXML
    private Button btnNext;
    @FXML
    private ComboBox comboBoxOpciones;
    @FXML
    private ScrollPane scrollPaneFeatures;
    @FXML
    private VBox contenedorBotones;
    @FXML
    private Button btnGuardar;
    @FXML
    private VBox lienzo;
    @FXML
    private AnchorPane emojiBlock;
    @FXML
    private ImageView emojiFace;
    @FXML
    private ImageView emojiBrows;
    @FXML
    private ImageView emojiEyes;
    @FXML
    private ImageView emojiMouth;
   @FXML
    private ImageView accesoryIv;
    @FXML
    private VBox featuresListContainers;
    @FXML
    private AnchorPane panelPrincipal;
    @FXML
    private HBox contenedorBotonesYLienzo;
    @FXML
    private Button btnExportar;
    @FXML
    private Button deleteFeatButton;
    @FXML
    private FlowPane contenedorScroll;
     @FXML
    private Button newFtButton;
     @FXML
     private TextField titulotxt;
 
   
     
    private DCLList<ImageView> ojos=new DCLList<>();
    private DCLList<ImageView> bocas=new DCLList<>();
    private DCLList<ImageView> caras=new DCLList<>();
    private DCLList<ImageView> cejas=new DCLList<>();
    private DCLList<ImageView> extras=new DCLList<>();
    private NodeDCLL<ImageView> nodoF;
    private ImageView ivSelected;
    private DCLList<ImageView> selectedList;
    private ArrayList<File> directoriosSelected;
    private ArrayList<File> ojosFiles;
    private ArrayList<File> rostrosFiles;
    private ArrayList<File> bocasFiles;
    private ArrayList<File> browsFiles;
    private ArrayList<File> extrasFiles;
    private String destinoPath="";

    
    ObservableList<String> options = FXCollections.observableArrayList(
                "ojos",
                "cara",
                "ceja",
                "accesorios",
                "boca"
            
        );
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ojosFiles=llenarListsOjos();
       rostrosFiles=llenarListsRostros();
       bocasFiles=llenarListsBocas();
       browsFiles=llenarListBrows();
       extrasFiles=llenarListsAccessories();
        DraggableMaker.makeResizable(emojiEyes);
        DraggableMaker.makeResizable(accesoryIv);
        DraggableMaker.makeResizable(emojiBrows);
        DraggableMaker.makeResizable(emojiFace);
        DraggableMaker.makeResizable(emojiMouth);
        
        
        //Llena opciones y evento segun seleccione el usuario
        this.comboBoxOpciones.setItems(options);
        comboBoxOpciones.setOnAction(eh->{
            startComboBox();
        });
        
        //Realiza el cambio segun sea hacia delante o atras
        this.btnNext.setOnAction(eh->{
            runNext();
        });
        this.btnPrev.setOnAction(eh->{
            runPrev();
        });
        this.newFtButton.setOnAction(eh->{
           addFeature();
        });
        this.deleteFeatButton.setOnAction(eh->{
            deleteFeature();});
        
        //guardar proyecto
        this.btnGuardar.setOnAction(eh->{
              if (GalleryWindowController.proyectoSeleccionado!=null){
                  this.titulotxt.setText(GalleryWindowController.proyectoSeleccionado.getProName());
              }
              if (this.titulotxt.getText().isEmpty()){
                     Alert a=new Alert(Alert.AlertType.ERROR);
                     a.setContentText("Debe ingresar un titulo");
                     a.showAndWait();
                     eh.consume();
                   }else{
                  Image portada=new Image("file:"+convertAnchorPaneToImage(emojiBlock, titulotxt.getText(),"src\\main\\resources\\ImagenesProyectos\\"));
                System.out.println(portada.getUrl());
             
                Emoji e;
                if (this.accesoryIv.getImage()!=null){
                      e=new Emoji(emojiEyes.getImage().getUrl(),emojiEyes.getLayoutX(),emojiEyes.getLayoutY(),emojiEyes.getFitHeight(),emojiEyes.getFitWidth(),
                              emojiMouth.getImage().getUrl(),emojiMouth.getLayoutX(),emojiMouth.getLayoutY(),emojiMouth.getFitHeight(),emojiMouth.getFitWidth(), 
                              emojiBrows.getImage().getUrl(),emojiBrows.getLayoutX(),emojiBrows.getLayoutY(),emojiBrows.getFitHeight(),emojiBrows.getFitWidth(), 
                              emojiFace.getImage().getUrl(),emojiFace.getLayoutX(),emojiFace.getLayoutY(),emojiFace.getFitHeight(),emojiFace.getFitWidth()
                              ,accesoryIv.getImage().getUrl(),accesoryIv.getLayoutX(),accesoryIv.getLayoutY(), accesoryIv.getFitHeight(), accesoryIv.getFitWidth()
                              , portada.getUrl());
                }
                else{
                    e=new Emoji(emojiEyes.getImage().getUrl(),emojiEyes.getLayoutX(),emojiEyes.getLayoutY(),emojiEyes.getFitHeight(),emojiEyes.getFitWidth(),
                            emojiMouth.getImage().getUrl(),emojiMouth.getLayoutX(),emojiMouth.getLayoutY(),emojiMouth.getFitHeight(),emojiMouth.getFitWidth(), 
                            emojiBrows.getImage().getUrl(),emojiBrows.getLayoutX(),emojiBrows.getLayoutY(),emojiBrows.getFitHeight(),emojiBrows.getFitWidth(), 
                            emojiFace.getImage().getUrl(),emojiFace.getLayoutX(),emojiFace.getLayoutY(),emojiFace.getFitHeight(),emojiFace.getFitWidth(),
                            portada.getUrl());
                }
               
                
                Proyecto proyecton=new Proyecto(titulotxt.getText(), e);
                if (GalleryWindowController.proyectoSeleccionado==null){
                    App.usuarioSeleccionado.getProyectos().addLast(proyecton);
                }
                else{
                    GalleryWindowController.proyectoSeleccionado.setContent(e);
                    GalleryWindowController.proyectoSeleccionado=null;
                }
                 try {
                App.setRoot("galleryWindow");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
                Stage stage = (Stage) this.btnGuardar.getScene().getWindow();
                stage.close();
            
              }
           
        });
        this.btnExportar.setOnAction(eh->{
             if (GalleryWindowController.proyectoSeleccionado!=null){
                  this.titulotxt.setText(GalleryWindowController.proyectoSeleccionado.getProName());
              }
            if (titulotxt.getText().isEmpty()){
                Alert a=new Alert(Alert.AlertType.ERROR);
                a.setContentText("Debe ingresar un titulo");
                a.showAndWait();
                eh.consume();
            }else{
               DirectoryChooser directoryChooser = new DirectoryChooser();
                directoryChooser.setTitle("Seleccionar carpeta");

                File selectedDirectory = directoryChooser.showDialog(null);
                
                String ruta=selectedDirectory.getAbsolutePath();
                System.out.println(ruta);
                if (selectedDirectory != null) {
                    imagenDirectorio(emojiBlock,titulotxt.getText(),ruta);
                    Alert a=new Alert(Alert.AlertType.CONFIRMATION);
                    a.setContentText("Imagen guaradada en: "+selectedDirectory.getPath());
                    a.showAndWait();
                    
                } else {
                    eh.consume();
                    System.out.println("Ninguna carpeta seleccionada.");
                } 
            }
        
        });
        
      }
         
   
    //meotod que carga panel y setean atributos iniciales 
    public void startComboBox(){
         this.contenedorScroll.getChildren().clear();
            comboMethod();
    }
    //metodo boton next
    public void runNext(){
        if (this.comboBoxOpciones.getValue()==null){
                Alert a=new Alert(Alert.AlertType.ERROR);
                a.setContentText("debe seleccionar un feature primero");
                a.showAndWait();
            }else{
                
                Image i=nodoF.getContent().getImage();
                
                this.ivSelected.setImage(i);
                
                nodoF=nodoF.getNext();

            }
    }
    //metodo boton previous
    public void runPrev(){
        if (this.comboBoxOpciones.getValue()==null){
                Alert a=new Alert(Alert.AlertType.ERROR);
                a.setContentText("debe seleccionar un feature primero");
                a.showAndWait();
            }else{
                nodoF=nodoF.getPrevious();
                Image i=nodoF.getContent().getImage();
                this.ivSelected.setImage(i);
                
                
            }
    }
    //metodo que añade nueva caracteristica 
    public void addFeature(){
         if (this.comboBoxOpciones.getValue()==null){
                Alert a=new Alert(Alert.AlertType.ERROR);
                a.setContentText("debe seleccionar un feature primero");
                a.showAndWait();
            }else{
               JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos PNG", "png");
                fileChooser.setFileFilter(filter);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    
                    String filePath = "file:"+selectedFile.getAbsolutePath();
                    this.directoriosSelected.addLast(selectedFile);
                    System.out.println(filePath);
                    Image i=new Image(filePath);
                    ImageView iv=new ImageView(i);
                    File fn=new File(this.destinoPath);
                    Path origen = Path.of(selectedFile.getAbsolutePath());
                    Path destino = Path.of(this.destinoPath);

                    try {
                        // Mover el archivo al directorio destino
                        Files.move(origen, destino.resolve(origen.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                        System.out.println("Archivo movido exitosamente.");
                    } catch (IOException e) {
                        System.out.println("Ocurrió un error al mover el archivo: " + e.getMessage());
                    }
                    this.selectedList.addLast(iv);
                    this.contenedorScroll.getChildren().clear();
                    cargarFeatures(this.selectedList);
                }
                         
                
            }
    }
    //metodo que borra la caracteristica seleccionada
    public void deleteFeature(){
        if (this.comboBoxOpciones.getValue()==null){
                Alert a=new Alert(Alert.AlertType.ERROR);
                a.setContentText("debe seleccionar un feature primero");
                a.showAndWait();
            }else{
        String [] arra=ivSelected.getImage().getUrl().split(":");
            String p=arra[1]+":/"+arra[2];
             File file = new File(p);
            try {
                Files.delete(file.toPath());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        this.ivSelected.setImage(null);
        
            NodeDCLL<ImageView> sub=nodoF.getNext();    
            this.selectedList.remove(this.nodoF.getContent());
            this.nodoF=sub;
            this.ivSelected.setImage(this.nodoF.getContent().getImage());
            
             
           
            this.contenedorScroll.getChildren().clear();
            cargarFeatures(this.selectedList);
    }
        }
            
    //metodo del comboBox
    public void comboMethod(){
        String seleccionado=comboBoxOpciones.getValue().toString();
            if (seleccionado.equals("ojos")){
                cargarFeatures(ojos);
                this.nodoF=this.ojos.getNode();
                this.ivSelected=this.emojiEyes; //posiciona el imageview de la opcion escogida
                this.selectedList=this.ojos;
                this.directoriosSelected=this.ojosFiles;
                this.destinoPath="C:\\Users\\USUARIO\\OneDrive\\Escritorio\\ProyectoEDD\\EstructurasDatos-EmojiMaker\\EmojiMaker\\src\\main\\java\\imagenes\\eyes";
               
            }
             if (seleccionado.equals("boca")){
                cargarFeatures(bocas);
                this.nodoF=this.bocas.getNode();
                this.ivSelected=this.emojiMouth;
                this.selectedList=this.bocas;
                this.directoriosSelected=this.bocasFiles;
               this.destinoPath="C:\\Users\\USUARIO\\OneDrive\\Escritorio\\ProyectoEDD\\EstructurasDatos-EmojiMaker\\EmojiMaker\\src\\main\\java\\imagenes\\mouth";
                
            }
              if (seleccionado.equals("cara")){
                cargarFeatures(caras);
                this.nodoF=this.caras.getNode();
                this.ivSelected=this.emojiFace;
                this.selectedList=this.caras;
                this.directoriosSelected=this.rostrosFiles;
                this.destinoPath="C:\\Users\\USUARIO\\OneDrive\\Escritorio\\ProyectoEDD\\EstructurasDatos-EmojiMaker\\EmojiMaker\\src\\main\\java\\imagenes\\faces";
                
                
            }
               if (seleccionado.equals("ceja")){
                cargarFeatures(cejas);
                this.nodoF=this.cejas.getNode();
                this.ivSelected=this.emojiBrows;
                this.selectedList=this.cejas;
                this.directoriosSelected=this.browsFiles;
                this.destinoPath="C:\\Users\\USUARIO\\OneDrive\\Escritorio\\ProyectoEDD\\EstructurasDatos-EmojiMaker\\EmojiMaker\\src\\main\\java\\imagenes\\eyebrows";
                
            }
                if (seleccionado.equals("accesorios")){
                cargarFeatures(extras);
                this.nodoF=this.extras.getNode();
                this.ivSelected=this.accesoryIv;
                this.selectedList=this.extras;
                this.directoriosSelected=this.extrasFiles;
                this.destinoPath="C:\\Users\\USUARIO\\OneDrive\\Escritorio\\ProyectoEDD\\EstructurasDatos-EmojiMaker\\EmojiMaker\\src\\main\\java\\imagenes\\accessories";
                
            }
                this.ivSelected.setOnDragOver(eh->{
                    if (eh.getDragboard().hasString()){
                        eh.acceptTransferModes(TransferMode.ANY);
                    }
                });
                this.ivSelected.setOnDragDropped(eh->{
                    String str=eh.getDragboard().getString();
                    this.ivSelected.setImage(new Image(str));
                    for (ImageView iview: selectedList){
                        if (iview.getImage().getUrl().equals(ivSelected.getImage().getUrl())){
                            this.nodoF=selectedList.getNodeByContent(iview);
                        }
                    }
                    
                });
            
    }
    
    //metodo que carga la lista doble enlazada en el panel 
    public void cargarFeatures(DCLList<ImageView> lista){
        for (int i=0;i<lista.size();i++){
            ImageView iv=lista.get(i);
            iv.setFitHeight(60);
            iv.setFitWidth(60);
            iv.setOnMouseClicked(eh->{
                this.ivSelected.setImage(iv.getImage());
              
                nodoF=lista.getNodeByContent(iv);
                
            });
            iv.setOnDragDetected(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
          /* drag was detected, start a drag-and-drop gesture*/
          /* allow any transfer mode */
          Dragboard db = iv.startDragAndDrop(TransferMode.ANY);
          
          /* Put a string on a dragboard */
          ClipboardContent content = new ClipboardContent();
          content.putString(iv.getImage().getUrl());
          db.setContent(content);
         
          db.setDragView(iv.getImage(),40,40);
          event.consume();
      }
  });
            this.contenedorScroll.getChildren().add(iv);
        }
    }
    
    //metodo que llena los double linkedlist
    //Se cambio el arraylist al nuestro
    public ArrayList<File> llenarListsOjos(){
        File directorio = new File("src\\main\\java\\imagenes\\eyes");
        ArrayList<File> imagenes = cargar(directorio);
        for(File file: imagenes){
           
                String ruta=file.getAbsolutePath();
                Image imagen=new Image("file:"+ruta);
                ImageView iv=new ImageView(imagen);
                this.ojos.addLast(iv);
            
        }
        return imagenes;
        }
    public ArrayList<File> llenarListsAccessories(){
        File directorio = new File("src\\main\\java\\imagenes\\accessories");
        ArrayList<File> imagenes = cargar(directorio);
        for(File file: imagenes){
           
                String ruta=file.getAbsolutePath();
                Image imagen=new Image("file:"+ruta);
                ImageView iv=new ImageView(imagen);
                this.extras.addLast(iv);
            
        }
        return imagenes;
        }
        
    public ArrayList<File> llenarListsRostros(){
        File directorio = new File("src\\main\\java\\imagenes\\faces");
        ArrayList<File> imagenes = cargar(directorio);
        for(File file: imagenes){
           
                String ruta=file.getAbsolutePath();
                Image imagen=new Image("file:"+ruta);
                ImageView iv=new ImageView(imagen);
                this.caras.addLast(iv);
                
            
        }
        return  imagenes;
        }
    public ArrayList<File> llenarListBrows(){
        File directorio = new File("src\\main\\java\\imagenes\\eyebrows");
        ArrayList<File> imagenes = cargar(directorio);
        for(File file: imagenes){
           
                String ruta=file.getAbsolutePath();
                Image imagen=new Image("file:"+ruta);
                ImageView iv=new ImageView(imagen);
                this.cejas.addLast(iv);
            
        }
        return imagenes;
         }
        
       public ArrayList<File> llenarListsBocas(){
        File directorio = new File("src\\main\\java\\imagenes\\mouth");
        ArrayList<File> imagenes = cargar(directorio);
        for(File file: imagenes){
           
                String ruta=file.getAbsolutePath();
                Image imagen=new Image("file:"+ruta);
                ImageView iv=new ImageView(imagen);
                this.bocas.addLast(iv);
            
        }
        return imagenes;
        }

   public static String convertAnchorPaneToImage(AnchorPane anchorPane,String nombreProyecto,String ruta) {
    WritableImage snapshot = anchorPane.snapshot(new SnapshotParameters(), null);
    BufferedImage bufferedImage = SwingFXUtils.fromFXImage(snapshot, null);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    try {
        ImageIO.write(bufferedImage, "png", outputStream);
        byte[] imageData = outputStream.toByteArray();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(imageData);
        
        //Directorio donde se guardan las imagenes
        
        
        //Aqui habria que siempre ponerle un nombre unico a cada imagen
        String imagePath = ruta + nombreProyecto + ".png";
        
        //lo guardamos en un archivo que pueda ser enviado cuando se escriba la imagen
        File outputFile = new File(imagePath);
        //se escribe la imagen
        ImageIO.write(bufferedImage, "png", outputFile);
        return imagePath;
    } catch (IOException e) {
        e.printStackTrace();
    }
    return null;
}
   public void imagenDirectorio(AnchorPane ap,String nombreProyecto,String ruta){
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
   public Proyecto crearProyecto(String nombreProyecto, Image portada){
       //tengo que poner cada uno de los url
       if (accesoryIv!=null){
       ImageView ojosUrl=this.getEmojiEyes();
       ImageView bocaUrl = this.getEmojiMouth();
       ImageView cejasUrl = this.getEmojiBrows();
       ImageView caraUrl = this.getEmojiFace();
       ImageView accUrl=this.getAccesoryIv();
       Emoji emoji=new Emoji(emojiEyes.getImage().getUrl(),emojiEyes.getLayoutX(),emojiEyes.getLayoutY(),emojiEyes.getFitHeight(),emojiEyes.getFitWidth(),
                              emojiMouth.getImage().getUrl(),emojiMouth.getLayoutX(),emojiMouth.getLayoutY(),emojiMouth.getFitHeight(),emojiMouth.getFitWidth(), 
                              emojiBrows.getImage().getUrl(),emojiBrows.getLayoutX(),emojiBrows.getLayoutY(),emojiBrows.getFitHeight(),emojiBrows.getFitWidth(), 
                              emojiFace.getImage().getUrl(),emojiFace.getLayoutX(),emojiFace.getLayoutY(),emojiFace.getFitHeight(),emojiFace.getFitWidth()
                              ,accesoryIv.getImage().getUrl(),accesoryIv.getLayoutX(),accesoryIv.getLayoutY(), accesoryIv.getFitHeight(), accesoryIv.getFitWidth()
                              , portada.getUrl());
       //Creo el proyecto
       Proyecto proy = new Proyecto(nombreProyecto,emoji);
       
       return proy; 
       }
       ImageView ojosUrl=this.getEmojiEyes();
       ImageView bocaUrl = this.getEmojiMouth();
       ImageView cejasUrl = this.getEmojiBrows();
       ImageView caraUrl = this.getEmojiFace();
      //esta para el accesorio
//       System.out.println(this.getEmojiEyes().toString());
//       System.out.println(this.getEmojiMouth().toString());
//       System.out.println(this.getEmojiBrows().toString());
//       System.out.println(this.getEmojiFace().toString());
       Emoji emoji = new Emoji(emojiEyes.getImage().getUrl(),emojiEyes.getLayoutX(),emojiEyes.getLayoutY(),emojiEyes.getFitHeight(),emojiEyes.getFitWidth(),
                            emojiMouth.getImage().getUrl(),emojiMouth.getLayoutX(),emojiMouth.getLayoutY(),emojiMouth.getFitHeight(),emojiMouth.getFitWidth(), 
                            emojiBrows.getImage().getUrl(),emojiBrows.getLayoutX(),emojiBrows.getLayoutY(),emojiBrows.getFitHeight(),emojiBrows.getFitWidth(), 
                            emojiFace.getImage().getUrl(),emojiFace.getLayoutX(),emojiFace.getLayoutY(),emojiFace.getFitHeight(),emojiFace.getFitWidth(),
                            portada.getUrl());
       //Creo el proyecto
       Proyecto proy = new Proyecto(nombreProyecto,emoji);
       
       return proy;
   }
   //metodo que se acciona cuando un proyecto es abierto
   public void cargarProyecto(Proyecto proy){
        
            //aqui me pasa el proyecto debo colocar los image view como los tiene
            //cargamos las caracteristicas del emoji/ o podemos ir viendo en la lista
            if (proy.getContent().getAccesory()!=null){
                Image ojosIm = new Image(proy.getContent().getEyes_url());
                Image bocaIm = new Image(proy.getContent().getMouth_url());
                Image cejasIm = new Image(proy.getContent().getBrows_url());
                Image caraIm =new Image(proy.getContent().getFace_url());
                Image accIm = new Image(proy.getContent().getAccesory());
                emojiEyes.setImage(ojosIm);
                emojiEyes.setLayoutX(proy.getContent().getEyesX());
                emojiEyes.setLayoutY(proy.getContent().getEyesY());
                emojiEyes.setFitHeight(proy.getContent().getEyesH());
                emojiEyes.setFitWidth(proy.getContent().getEyesW());
                emojiMouth.setImage(bocaIm);
                emojiMouth.setLayoutX(proy.getContent().getMouthX());
                emojiMouth.setLayoutY(proy.getContent().getMouthY());
                emojiMouth.setFitHeight(proy.getContent().getMouthH());
                emojiMouth.setFitWidth(proy.getContent().getMouthW());
                emojiBrows.setImage(cejasIm);
                emojiBrows.setLayoutX(proy.getContent().getBrowsX());
                emojiBrows.setLayoutY(proy.getContent().getBrowsY());
                emojiBrows.setFitHeight(proy.getContent().getBrowsH());
                emojiBrows.setFitWidth(proy.getContent().getBrowsW());
                emojiFace.setImage(caraIm);
                emojiFace.setLayoutX(proy.getContent().getFaceX());
                emojiFace.setLayoutY(proy.getContent().getFaceY());
                emojiFace.setFitHeight(proy.getContent().getFaceH());
                emojiFace.setFitWidth(proy.getContent().getFaceW());
                accesoryIv.setImage(accIm);
                accesoryIv.setLayoutX(proy.getContent().getAccX());
                accesoryIv.setLayoutY(proy.getContent().getAccY());
                accesoryIv.setFitHeight(proy.getContent().getAccH());
                accesoryIv.setFitWidth(proy.getContent().getAccW());

            titulotxt.setText(proy.getProName());
            }
            Image ojosIm = new Image(proy.getContent().getEyes_url());
            Image bocaIm = new Image(proy.getContent().getMouth_url());
            Image cejasIm = new Image(proy.getContent().getBrows_url());
            Image caraIm =new Image(proy.getContent().getFace_url());

            emojiEyes.setImage(ojosIm);
                emojiEyes.setLayoutX(proy.getContent().getEyesX());
                emojiEyes.setLayoutY(proy.getContent().getEyesY());
                emojiEyes.setFitHeight(proy.getContent().getEyesH());
                emojiEyes.setFitWidth(proy.getContent().getEyesW());
                emojiMouth.setImage(bocaIm);
                emojiMouth.setLayoutX(proy.getContent().getMouthX());
                emojiMouth.setLayoutY(proy.getContent().getMouthY());
                emojiMouth.setFitHeight(proy.getContent().getMouthH());
                emojiMouth.setFitWidth(proy.getContent().getMouthW());
                emojiBrows.setImage(cejasIm);
                emojiBrows.setLayoutX(proy.getContent().getBrowsX());
                emojiBrows.setLayoutY(proy.getContent().getBrowsY());
                emojiBrows.setFitHeight(proy.getContent().getBrowsH());
                emojiBrows.setFitWidth(proy.getContent().getBrowsW());
                emojiFace.setImage(caraIm);
                emojiFace.setLayoutX(proy.getContent().getFaceX());
                emojiFace.setLayoutY(proy.getContent().getFaceY());
                emojiFace.setFitHeight(proy.getContent().getFaceH());
                emojiFace.setFitWidth(proy.getContent().getFaceW());
            //faltaria el accesorio++++=+++++++++
            

            titulotxt.setText(proy.getProName());
 
   }

    public ImageView getEmojiFace() {
        return emojiFace;
    }

    public void setEmojiFace(ImageView emojiFace) {
        this.emojiFace = emojiFace;
    }

    public ImageView getEmojiBrows() {
        return emojiBrows;
    }

    public void setEmojiBrows(ImageView emojiBrows) {
        this.emojiBrows = emojiBrows;
    }

    public ImageView getEmojiEyes() {
        return emojiEyes;
    }

    public void setEmojiEyes(ImageView emojiEyes) {
        this.emojiEyes = emojiEyes;
    }

    public ImageView getEmojiMouth() {
        return emojiMouth;
    }

    public void setEmojiMouth(ImageView emojiMouth) {
        this.emojiMouth = emojiMouth;
    }

    public ImageView getAccesoryIv() {
        return accesoryIv;
    }

    public void setAccesoryIv(ImageView accesoryIv) {
        this.accesoryIv = accesoryIv;
    }

   
   
}
