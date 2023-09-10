/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlers;

import Vistas.VistaInicio;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.Clip;
import modelos.ModeloAjustes;

/**
 *
 * @author Celia
 */
public class MenuBienvenida extends JFrame implements ActionListener {

    private String nombre = "/Sonidos/mInicio.aiff";
    private VistaInicio vista;
    private ModeloAjustes modeloAjustes;
    private JButton jugar, tutorial, opciones;
    private Clip clip;
    private JLabel imagenBocaCerr, imagenBocaA;
    
    public MenuBienvenida(){
    }
    
    public void iniciarControlador(ModeloAjustes modeloAjustes){
              
        setLayout(null);
        setTitle("Arkanoid");
        
        this.modeloAjustes = modeloAjustes;
        
        modeloAjustes.playmusica(nombre);
                    
                    
        
        vista = new VistaInicio(this, modeloAjustes);
        
        iniciarBotones();
        
        add(vista);
        modeloAjustes.addObserver(vista);

        
        setSize(vista.getSize());
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    }
    
    public void iniciarBotones(){
        
        jugar = new JButton(modeloAjustes.getTexto()[0]);
        jugar.setBounds(125, 300, 240, 60);
        jugar.setFont(modeloAjustes.getLetra());
        jugar.setForeground(Color.white);
        jugar.setBackground(Color.decode("#04398f"));
        jugar.addActionListener(this);
        jugar.setBorder(BorderFactory.createLineBorder(Color.black));
        jugar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        vista.add(jugar);
        
        tutorial = new JButton(modeloAjustes.getTexto()[1]);
        tutorial.setBounds(125, 400, 240, 60);
        tutorial.setFont(modeloAjustes.getLetra());
        tutorial.setForeground(Color.white);
        tutorial.setBackground(Color.decode("#04398f"));
        tutorial.addActionListener(this);
        tutorial.setBorder(BorderFactory.createLineBorder(Color.black));
        tutorial.setCursor(new Cursor(Cursor.HAND_CURSOR));
        vista.add(tutorial);
        
        opciones = new JButton(modeloAjustes.getTexto()[2]);
        opciones.setBounds(125, 500 ,240 ,60);
        opciones.setFont(modeloAjustes.getLetra());
        opciones.setForeground(Color.white);
        opciones.setBackground(Color.decode("#04398f"));
        opciones.addActionListener(this);
        opciones.setBorder(BorderFactory.createLineBorder(Color.black));
        opciones.setCursor(new Cursor(Cursor.HAND_CURSOR));
        vista.add(opciones);
              
    }
    
     
    @Override
    public void actionPerformed(ActionEvent e) {
     
        if (e.getSource() == jugar) {
            MenuJugar menuJugar = new MenuJugar();
            menuJugar.iniciarControlador(modeloAjustes);
            this.dispose();
            setVisible(false);
        }
        
        if (e.getSource() == opciones) {
            
            MenuAjustes opcion = new MenuAjustes();            
            opcion.iniciarControlador(modeloAjustes);
            this.dispose();
            setVisible(false);
        }
        if (e.getSource() == tutorial) {
            
            MenuTutorial tutorial = new MenuTutorial();
            tutorial.iniciarControlador(modeloAjustes);
            this.dispose();
            setVisible(false);
        }
        
        
    }


    public Clip getClip() {
        return clip;
    }
    
}
