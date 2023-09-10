/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Controlers.MenuJugar;
import java.awt.Color;
import java.awt.Font;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import modelos.ModeloAjustes;

/**
 *
 * @author Celia
 */
public class VistaMenuJugar extends JPanel implements Observer{

    private JLabel imagen, titulo, imagen2, texto;
    private Font letra = new Font("Tahome", Font.BOLD, 16);
    private MenuJugar controlador;
    private ModeloAjustes modeloAjustes;

    
    public VistaMenuJugar(MenuJugar controlador, ModeloAjustes modeloAjustes){
        setLayout(null);
        this.controlador = controlador;
        this.modeloAjustes = modeloAjustes;
        
        imagen2 = new JLabel(new ImageIcon(getClass().getResource("../Imagenes/pieza.png")));
        
          
        anadirImagen();
        aniadirEtiquetas();

        cambiarModo();
             
        
        setSize(700, 740);
        setVisible(true);
        
    }   
    
    public void anadirImagen() {
        imagen = new JLabel(new ImageIcon(getClass().getResource("../Imagenes/Arkanoid.png")));
        imagen.setBounds(65, 60, 357, 246);
        add(imagen);

    }
     public void aniadirEtiquetas(){
           texto = new JLabel(modeloAjustes.getTexto()[22]);
           texto.setFont(modeloAjustes.getLetra());
            if(!modeloAjustes.getModo()){
                texto.setForeground(Color.white);
            }else{
                texto.setForeground(Color.black);
            }
            
            texto.setBounds(450, 100, 250, 320);
            texto.setBackground(Color.red);
            add(texto);
            
            imagen2.setBounds(500, 430, 100, 246);
            add(imagen2);
            Runnable r = new Runnable(){
                public void run(){
                    mover();
                }
            };
            new Thread(r).start();
            
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

        public void mover(){
        int i =0;
        while (i <6) {
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
}


   
