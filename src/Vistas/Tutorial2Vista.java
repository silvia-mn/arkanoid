/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Controlers.Tutorial1Controlador;
import java.awt.Color;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import modelos.ModeloAjustes;
import modelos.Tutorial1Modelo;

/**
 *
 * @author Celia
 */
public class Tutorial2Vista extends JPanel implements Observer{

    private Tutorial1Controlador controlador = new Tutorial1Controlador();
    private Tutorial1Modelo modelo;
    private ModeloAjustes modeloOpciones;
    private JLabel teclas, teclaArriba, teclaAbajo, teclaIzquierda, teclaDerecha;
    
    public Tutorial2Vista(Tutorial1Controlador controlador, Tutorial1Modelo modelo,ModeloAjustes modeloOpciones){
        
        setLayout(null);
        
        this.controlador = controlador;
        this.modelo = modelo;
        this.modeloOpciones = modeloOpciones;
        
        aniadirImagen();
        
        setBounds(325, 0, 400, 660);
        
        setBackground(Color.white);
        if(!modelo.getModo()){ 
            setBackground(Color.darkGray);
        }
        
        setVisible(true); 
        
    }
    
    public void aniadirImagen(){
        teclas = new JLabel(new ImageIcon(getClass().getResource("../Imagenes/teclas.png")));
        
        teclas.setBounds(45, 150, 300, 300);
        teclas.setBorder(BorderFactory.createEmptyBorder());
        add(teclas);
        
        teclas.setVisible(false);
        
    }
    
    @Override
    public void update(Observable o, Object arg) {
               
        if(modelo.getContar() == 1){
           teclas.setVisible(true);
       } 
        
        if(modelo.getContar2() == 0 && modelo.isContinuar()){
            teclas.setVisible(false);
            this.remove(teclas);
            
            teclaArriba = new JLabel (new ImageIcon(getClass().getResource("../Imagenes/teclaArriba.png")));
            
            teclaArriba.setBounds(45, 150, 300, 300);
            teclaArriba.setBorder(BorderFactory.createEmptyBorder());
            add(teclaArriba);
            teclaArriba.setVisible(true);
            
            modelo.setContinuar(false);
        }
        
        if(modelo.getContar2() == 1 && modelo.isContinuar()){
            teclaArriba.setVisible(false);
            this.remove(teclaArriba);
            
            teclaAbajo = new JLabel (new ImageIcon(getClass().getResource("../Imagenes/teclaAbajo.png")));
            
            teclaAbajo.setBounds(45, 150, 300, 300);
            teclaAbajo.setBorder(BorderFactory.createEmptyBorder());
            add(teclaAbajo);
            teclaAbajo.setVisible(true);
            
            modelo.setContinuar(false);
        }
    
        if(modelo.getContar2() == 2 && modelo.isContinuar()){
            teclaAbajo.setVisible(false);
            this.remove(teclaAbajo);
            
            teclaIzquierda = new JLabel (new ImageIcon(getClass().getResource("../Imagenes/teclaIzquierda.png")));
            
            teclaIzquierda.setBounds(45, 150, 300, 300);
            teclaIzquierda.setBorder(BorderFactory.createEmptyBorder());
            add(teclaIzquierda);
            teclaIzquierda.setVisible(true);
            
            modelo.setContinuar(false);
        }
        
        if(modelo.getContar2() == 3 && modelo.isContinuar()){
            teclaIzquierda.setVisible(false);
            this.remove(teclaIzquierda);
            
            teclaDerecha = new JLabel (new ImageIcon(getClass().getResource("../Imagenes/teclaDerecha.png")));
            
            teclaDerecha.setBounds(45, 150, 300, 300);
            teclaDerecha.setBorder(BorderFactory.createEmptyBorder());
            add(teclaDerecha);
            teclaDerecha.setVisible(true);
            
            modelo.setContinuar(false);
        }
        
        if(modelo.getContar2() == 4 && modelo.isContinuar()){
            teclaDerecha.setVisible(false);
            this.remove(teclaDerecha);
        }
    }
    

    
}
