/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Controlers.MenuTutorial;
import Controlers.Tutorial3;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import modelos.ModeloAjustes;

/**
 *
 * @author Celia
 */
public class VistaTutorial3 extends JPanel implements Observer {
    private JLabel imagen, titulo, imagen2, texto, label, textoExpandir, textoReducir, textoFrenar, textoAcelerar;
    private JLabel textoVidas, textoBola, textoBola2, textoTitulo;
    private JLabel acelerar, expandir, frenar, reducir, corazon, bolaRoja, bolaVerde;
    private Font letra = new Font("Tahome", Font.BOLD, 16);
    private Tutorial3 controlador;
    private ModeloAjustes modeloAjustes;

    public VistaTutorial3 (Tutorial3 controlador, ModeloAjustes modeloAjustes){
        setLayout(null);
        this.controlador = controlador;
        this.modeloAjustes = modeloAjustes;
        
        imagen2 = new JLabel(new ImageIcon(getClass().getResource("../Imagenes/pieza.png")));
        
        anadirImagen();
        aniadirEtiquetas();

        cambiarModo();
             
        setSize(700, 700);
        setVisible(true);
        
    } 
    
    /*public void anadirImagen() {
        imagen = new JLabel (new ImageIcon(getClass().getResource("../Imagenes/Arkanoid.png")));
        imagen.setBounds(400, 30, 300, 300);
        add(imagen);*/
        //FALTA REDIMENSIONARLO!!!
        
        /*Image img = new ImageIcon(getClass().getResource("../Imagenes/Arkanoid.png")).getImage(); // load the image to a imageIcon 
        Image newimg = img.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        ImageIcon imageIcon = new ImageIcon(newimg);
        imagen.setIcon(imageIcon);*/
        
        /*acelerar = new JLabel(new ImageIcon(getClass().getResource("../Imagenes/acelerar.jpg")));
        acelerar.setBounds(25, 300, 357, 246);//posición determinada
        add(acelerar);
        
        expandir = new JLabel(new ImageIcon(getClass().getResource("../Imagenes/expandir.png")));
        
        expandir.setBounds(125, 400, 357, 246);
        add(expandir);
        
        
        frenar = new JLabel(new ImageIcon(getClass().getResource("../Imagenes/frenar.jpg")));
        frenar.setBounds(25, 500, 357, 246);
        add(frenar);
        
        reducir = new JLabel(new ImageIcon(getClass().getResource("../Imagenes/reducir.jpg")));
        reducir.setBounds(5, 500, 357, 246);
        add(reducir);
    }*/
    
    public void anadirImagen(){
        
        //redimensionarImagen("../Imagenes/Arkanoid.png", 150, 150, imagen, 20,30);
        redimensionarImagen("../Imagenes/expandir.png", 60,30, expandir,370,170);
        redimensionarImagen("../Imagenes/reducir.png", 60,30, reducir,370,230);
        redimensionarImagen("../Imagenes/frenar.png", 60,30, frenar,370,290);
        redimensionarImagen("../Imagenes/acelerar.png", 60,30, acelerar,370,350);
        redimensionarImagen("../Imagenes/bolaRoja.png", 60,60, bolaRoja,370,410);
        redimensionarImagen("../Imagenes/bolaVerde.png", 60,60, bolaVerde,370,470);
        redimensionarImagen("../Imagenes/corazon.png", 60,60, corazon,370,530);
        
}

    private void redimensionarImagen(String imagePath, int newWidth, int newHeight, JLabel label, int H, int W) {
        label = new JLabel();
        ImageIcon originalIcon = new ImageIcon(getClass().getResource(imagePath));
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        label.setIcon(resizedIcon);
        label.setBounds(H, W, newWidth, newHeight);
        add(label);
    }


    public void aniadirEtiquetas(){
        textoTitulo=new JLabel(modeloAjustes.getTexto()[39]);
        Font letra2 = new Font("Britannic Bold", Font.BOLD, 36);
        textoTitulo.setFont(letra2);
        Color colorTexto = Color.decode("#4472c4");
            
        textoExpandir = new JLabel(modeloAjustes.getTexto()[40]);
        textoExpandir.setFont(modeloAjustes.getLetra());
         
        textoReducir = new JLabel(modeloAjustes.getTexto()[41]);
        textoReducir.setFont(modeloAjustes.getLetra());
           
        textoAcelerar = new JLabel(modeloAjustes.getTexto()[42]);
        textoAcelerar.setFont(modeloAjustes.getLetra());
            
        textoFrenar = new JLabel(modeloAjustes.getTexto()[43]);
        textoFrenar.setFont(modeloAjustes.getLetra());
           
        textoBola = new JLabel(modeloAjustes.getTexto()[44]);
        textoBola.setFont(modeloAjustes.getLetra());
            
        textoBola2 = new JLabel(modeloAjustes.getTexto()[45]);
        textoBola2.setFont(modeloAjustes.getLetra());
          
        textoVidas = new JLabel(modeloAjustes.getTexto()[46]);
        textoVidas.setFont(modeloAjustes.getLetra());
              
        if(!modeloAjustes.getModo()){
            textoExpandir.setForeground(Color.white);
            textoReducir.setForeground(Color.white);
            textoAcelerar.setForeground(Color.white);
            textoFrenar.setForeground(Color.white);
            textoBola.setForeground(Color.white);
            textoBola2.setForeground(Color.white);
            textoVidas.setForeground(Color.white);
            textoTitulo.setForeground(Color.white);
                        
        }else{
            textoExpandir.setForeground(Color.black);
            textoReducir.setForeground(Color.black);
            textoAcelerar.setForeground(Color.black);
            textoFrenar.setForeground(Color.black);
            textoBola.setForeground(Color.black);
            textoBola2.setForeground(Color.black);
            textoVidas.setForeground(Color.black);
            textoTitulo.setForeground(colorTexto);
        }
            
        textoTitulo.setBounds(250, 10, 2000, 200);
        add(textoTitulo);
            
        textoExpandir.setBounds(70, 30, 250,320);
        add(textoExpandir);
            
        textoReducir.setBounds(70, 90, 250,320);
        add(textoReducir);
            
        textoFrenar.setBounds(70, 150, 250,320);
        add(textoFrenar);
        
        textoAcelerar.setBounds(70, 210, 250,320);
        add(textoAcelerar); 
            
        textoBola.setBounds(70, 270, 250,320);
        add(textoBola);
          
        textoBola2.setBounds(70, 330, 250,320);
        add(textoBola2);
            
        textoVidas.setBounds(70, 390, 250,320);
        add(textoVidas);
        
        imagen2.setBounds(500, 400, 100, 246);
        add(imagen2);
        
        Runnable r = new Runnable(){
            public void run(){
                mover();
            }
        };
        new Thread(r).start();
            
    }
    
   

    
    
    public void mover(){
        int i =0;
        while (i <4) {
            this.imagen2.setIcon(new ImageIcon(getClass().getResource("../Imagenes/pieza.png")));
            try {
                // Wait for 0.5 second.
                Thread.sleep(500);
            }
            catch (InterruptedException ex) {}
            this.imagen2.setIcon(new ImageIcon(getClass().getResource("../Imagenes/pieza2.png")));
            try {
                // Wait for 0.5 second.
                Thread.sleep(500);
            }
            catch (InterruptedException ex) {}
            i++;
        }
        this.imagen2.setIcon(new ImageIcon(getClass().getResource("../Imagenes/pieza2.png")));
        
    }

    
    public void cambiarModo(){
        if(modeloAjustes.getModo()){
            setBackground(Color.white);
        }else{
            setBackground(Color.darkGray);
        }
    }
    
    @Override
    public void update(java.util.Observable o, Object arg) {
        
    }
    
}
