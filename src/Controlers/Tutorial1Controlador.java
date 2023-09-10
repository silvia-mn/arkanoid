/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlers;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import modelos.ModeloAjustes;
import modelos.Tutorial1Modelo;
import Vistas.Tutorial1Vista;
import Vistas.Tutorial2Vista;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

/**
 *
 * @author Celia
 */
public class Tutorial1Controlador extends JFrame {

    private Tutorial1Modelo modelo;
    private ModeloAjustes modeloOpciones;

    private Tutorial1Vista vista1;
    private Tutorial2Vista vista2;
    private JButton atras;
    private ImageIcon icon13 = new ImageIcon(getClass().getResource("../Imagenes/atras.png"));
     private Image atrasImg = icon13.getImage();
      private ImageIcon atrasIcon = new ImageIcon(atrasImg.getScaledInstance(50, 50, 100));

    private int controlador = 0;

    public Tutorial1Controlador() {
    }

    public void iniciarControlador(ModeloAjustes modeloOpciones) {
        this.modeloOpciones = modeloOpciones;

        setLayout(null);
        setTitle("Arkanoid Teclado");
        
        modelo = new Tutorial1Modelo(modeloOpciones);
        
        if(modeloOpciones.getModo()) modelo.setModo(true);
        else modelo.setModo(false);
        
        vista1 = new Tutorial1Vista(this, modelo, modeloOpciones);
        vista2 = new Tutorial2Vista(this, modelo, modeloOpciones);

        iniciarBotones();

        add(vista1);
        add(vista2);
        modelo.addObserver(vista1);
        modelo.addObserver(vista2);

        setSize(700, 660);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.requestFocus();
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent key) {

                int keycode = key.getKeyCode();

                switch (modelo.getTecla()) {
                    case 0:
                        switch (keycode) {
                            case KeyEvent.VK_LEFT:
                                aniadir();
                                break;
                            case KeyEvent.VK_RIGHT:
                                aniadir();
                                break;
                            case KeyEvent.VK_DOWN:
                                aniadir();
                                break;
                            case KeyEvent.VK_UP:
                                aniadir();
                                break;
                        }
                        break;
                    case 1:
                        if(keycode == KeyEvent.VK_UP){
                            aniadir();  
                        } 
                        break;
                        
                    case 2:
                        if(keycode == KeyEvent.VK_DOWN){
                            aniadir();  
                        }
                        break;
                        
                    case 3:
                        if(keycode == KeyEvent.VK_LEFT){
                            aniadir();  
                        }
                        break;
                        
                    case 4:
                        if(keycode == KeyEvent.VK_RIGHT){
                            aniadir();  
                        }
                        break;
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public void mensaje() {

    }

    public void iniciarBotones() {
        atras = new JButton(atrasIcon);
        atras.setBounds(40, 60, atrasIcon.getIconWidth(), atrasIcon.getIconHeight());
        atras.setCursor(new Cursor(Cursor.HAND_CURSOR));
        atras.setBorder(BorderFactory.createEmptyBorder());
        atras.setContentAreaFilled(false);
        
        atras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuTutorial tutorial = new MenuTutorial();
                tutorial.iniciarControlador(modeloOpciones);
                desactivar();
            }
        });
        atras.setCursor(new Cursor(Cursor.HAND_CURSOR));
        vista1.add(atras);
    }

    public void desactivar() {
        this.dispose();
    }

    public void aniadir() {
        if (modelo.getActivarContador()) {

            modelo.addMovimiento();
        }
    }

}
