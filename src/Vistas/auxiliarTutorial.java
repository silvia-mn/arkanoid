/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import modelos.ModeloAjustes;
 

public class auxiliarTutorial extends JPanel implements Observer{


       private JLabel label, texto;
       private JLabel imagen, imagen2;
       private Font letra;
       private ModeloAjustes modeloOpciones;
    
       
       public auxiliarTutorial(ModeloAjustes modeloOpciones){
            this.modeloOpciones = modeloOpciones;
            letra = modeloOpciones.getLetra();
            setSize(1000,640);
            label = new JLabel("                                                                                                   ");

         
            

            setLayout(new BorderLayout());
            add(label, BorderLayout.NORTH);
            imagen = new JLabel(new ImageIcon(getClass().getResource("../Imagenes/Logo.png")));

           imagen2 = new JLabel(new ImageIcon(getClass().getResource("../Imagenes/pieza.png")));


            aniadirEtiquetas();
             setBackground(Color.white);

            //add(imagen, BorderLayout.SOUTH);
            /*setLocation(142,31);
            setVisible(false);

            imagen = new JLabel(new ImageIcon(getClass().getResource("../Imagenes/FlechasMov.png")));
            imagen.setBounds(0, 0, 5, 5);
            add(imagen);*/

            requestFocus();
        }
        
        public void cambiarModo(){
        if(modeloOpciones.getModo()){
            setBackground(Color.white);
            this.texto.setForeground(Color.black);
        }else{
            setBackground(Color.darkGray);     
            this.texto.setForeground(Color.white);
        }
    }
        
        public void aniadirEtiquetas(){
            texto = new JLabel();
            texto.setFont(letra);
            texto.setBounds(10, 10, 10, 10);
            texto.setBackground(Color.red);
            texto.setFont(modeloOpciones.getLetra());
            texto.setText(modeloOpciones.getTexto()[33]);
            add(texto);
            
            add(imagen2, BorderLayout.SOUTH);
            
        }

    @Override
    public void update(Observable o, Object o1) {
        
        cambiarModo();
        
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

}

