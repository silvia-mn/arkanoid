/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Controlers.Tutorial1Controlador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import modelos.ModeloAjustes;
import modelos.Tutorial1Modelo;

/**
 *
 * @author Celia
 */
public class Tutorial1Vista extends JPanel implements Observer{

    private Tutorial1Controlador controlador = new Tutorial1Controlador();
    private Tutorial1Modelo modelo;
    private JLabel texto, imagen2;
    private Font letra = new Font("Tahoma", Font.BOLD, 16);
    private ModeloAjustes modeloOpciones;
    
    public Tutorial1Vista(Tutorial1Controlador controlador, Tutorial1Modelo modelo, ModeloAjustes modeloOpciones){
        
        setLayout(null);
        
        this.controlador = controlador;
        this.modelo = modelo;
        this.modeloOpciones = modeloOpciones;
        //imagen2 = new JLabel(new ImageIcon(getClass().getResource("../Imagenes/pieza.png")));
        
        aniadirEtiquetas();
        
        
        setSize(700, 660);
        cambiarModo();
        setVisible(true);    
    
    }
    
    public void aniadirEtiquetas(){
        texto = new JLabel(modeloOpciones.getTexto()[24]);
        
        if(!modeloOpciones.getModo()){
            texto.setForeground(Color.white);
        }
        
        texto.setBounds(35, 150, 300, 100);
        texto.setFont(modeloOpciones.getLetra());
        
        add(texto);
        
        imagen2 = new JLabel(new ImageIcon(getClass().getResource("../Imagenes/pieza.png")));
        imagen2.setBounds(70, 250, 300, 300);
        add(imagen2);
        Runnable r = new Runnable(){
            public void run(){
                mover();
            }
        };
        new Thread(r).start();
        
        modelo.contador(3000);
        
    }    
    
    @Override
    public void update(Observable o, Object arg) {
        texto.setText(modelo.getTexto().getText());
        Runnable r = new Runnable(){
            public void run(){
                mover();
            }
        };
        new Thread(r).start();
    }
    
    public void mover(){
        int i =0;
        while (i <2) {
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
        if(modeloOpciones.getModo()){
            setBackground(Color.white);
        }else{
            setBackground(Color.darkGray);
        }
    }
}
