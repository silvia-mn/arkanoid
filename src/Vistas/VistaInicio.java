/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Controlers.MenuBienvenida;
import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.*;
import modelos.ModeloAjustes;

/**
 *
 * @author Celia
 */
public class VistaInicio extends JPanel implements Observer{
    private JLabel imagen, titulo, imagen2, texto, label;
    private Font letra = new Font("Tahome", Font.BOLD, 16);
    private MenuBienvenida controlador;
    private ModeloAjustes modeloAjustes = new ModeloAjustes(false, 1,false,false);
    private Graphics g;
    private boolean modo;
    private int i = 0;

    public VistaInicio(MenuBienvenida controlador, ModeloAjustes modeloAjustes) {
        
        setLayout(null);
        this.controlador = controlador;
        this.modeloAjustes = modeloAjustes;
        
        setSize(700, 700);
        setVisible(true);
        
        imagen2 = new JLabel(new ImageIcon(getClass().getResource("../Imagenes/pieza.png")));
        
        anadirImagen();
        aniadirEtiquetas();
        
        
        cambiarModo();  
            
    } 
        
    public void anadirImagen() {
        imagen = new JLabel(new ImageIcon(getClass().getResource("../Imagenes/Arkanoid.png")));
        imagen.setBounds(65, 30, 357, 246);
        add(imagen);
        
    }
    
    public void cambiarModo(){
        if(modeloAjustes.getModo()){
            setBackground(Color.white);
        }else{
            setBackground(Color.darkGray);            
        }
    }

     public void aniadirEtiquetas(){
            texto = new JLabel(modeloAjustes.getTexto()[20]);
            texto.setFont(modeloAjustes.getLetra());
            if(!modeloAjustes.getModo()){
                texto.setForeground(Color.white);
            }else{
                texto.setForeground(Color.black);
            }
            texto.setBounds(450, 100, 250, 320);
            add(texto);
            
            imagen2.setBounds(500, 400, 100, 246);
            add(imagen2);
            Runnable r = new Runnable(){
                public void run(){
                    mover();
                }
            };
            new Thread(r).start();
            
    }
     
    @Override
    public void update(Observable o, Object arg) {
         
        modo = modeloAjustes.getModo();
        cambiarModo();
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
    
}
