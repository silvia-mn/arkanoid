/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlers;

import Vistas.VistaOpciones;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import modelos.ModeloAjustes;

/**
 *
 * @author Celia
 */
public class MenuAjustes extends JFrame implements ActionListener {
    private VistaOpciones vista;
    private JButton sonido, claro, oscuro, atras, pequena, mediana, grande, ingles, espanol, ratBlanco, ratNegro, teclBlanco, teclNegro, teclado;
    private ModeloAjustes modeloAjustes;
    private boolean controlSonido = true;
    private boolean controlTeclado = true;
    private Font letra = new Font("Tahoma", Font.BOLD, 16);

    private ImageIcon icon1 = new ImageIcon(getClass().getResource("../Imagenes/sonido_on.png"));
    private ImageIcon icon2 = new ImageIcon(getClass().getResource("../Imagenes/sonido_off.png"));
    private ImageIcon icon3 = new ImageIcon(getClass().getResource("../Imagenes/modo_claro.png"));
    private ImageIcon icon4 = new ImageIcon(getClass().getResource("../Imagenes/modo_oscuro.png"));
    private ImageIcon icon5 = new ImageIcon(getClass().getResource("../Imagenes/modo_claro_dark.png"));
    private ImageIcon icon6 = new ImageIcon(getClass().getResource("../Imagenes/modo_oscuro_dark.png"));
    private ImageIcon icon7 = new ImageIcon(getClass().getResource("../Imagenes/sonido_on_dark.png"));
    private ImageIcon icon8 = new ImageIcon(getClass().getResource("../Imagenes/sonido_off_dark.png"));
    private ImageIcon icon9 = new ImageIcon(getClass().getResource("../Imagenes/raton_claro.png"));
    private ImageIcon icon10 = new ImageIcon(getClass().getResource("../Imagenes/raton_oscuro.png"));
    private ImageIcon icon11 = new ImageIcon(getClass().getResource("../Imagenes/teclado_claro.png"));
    private ImageIcon icon12 = new ImageIcon(getClass().getResource("../Imagenes/teclado_oscuro.png"));
    private ImageIcon icon13 = new ImageIcon(getClass().getResource("../Imagenes/atras.png"));
    
    private Image sonidoONImg = icon1.getImage();
    private Image sonidoOFFImg = icon2.getImage();
    private Image claroImg = icon3.getImage();
    private Image oscuroImg = icon4.getImage();
    private Image claroDarkImg = icon5.getImage();
    private Image oscuroDarkImg = icon6.getImage();
    private Image sonidoDarkONImg = icon7.getImage();
    private Image sonidoDarkOFFImg = icon8.getImage();
    private Image ratonBlancoImg = icon9.getImage();
    private Image ratonNegroImg = icon10.getImage();
    private Image tecladoBlancoImg = icon11.getImage();
    private Image tecladoNegroImg = icon12.getImage();
    private Image atrasImg = icon13.getImage();
    
    private ImageIcon sonidoONIcon = new ImageIcon(sonidoONImg.getScaledInstance(100, 50, 100));
    private ImageIcon sonidoOFFIcon = new ImageIcon(sonidoOFFImg.getScaledInstance(50, 50, 100));
    private ImageIcon claroIcon = new ImageIcon(claroImg.getScaledInstance(50, 50, 100));
    private ImageIcon oscuroIcon = new ImageIcon(oscuroImg.getScaledInstance(50, 50, 100));
    private ImageIcon claroDarkIcon = new ImageIcon(claroDarkImg.getScaledInstance(50, 50, 100));
    private ImageIcon oscuroDarkIcon = new ImageIcon(oscuroDarkImg.getScaledInstance(50, 50, 100));
    private ImageIcon sonidoDarkONIcon = new ImageIcon(sonidoDarkONImg.getScaledInstance(100, 50, 100));
    private ImageIcon sonidoDarkOFFIcon = new ImageIcon(sonidoDarkOFFImg.getScaledInstance(50, 50, 100));
    private ImageIcon ratonBlancoIcon = new ImageIcon(ratonBlancoImg.getScaledInstance(50, 50, 100));
    private ImageIcon ratonNegroIcon = new ImageIcon(ratonNegroImg.getScaledInstance(50, 50, 100));
    private ImageIcon tecladoBlancoIcon = new ImageIcon(tecladoBlancoImg.getScaledInstance(100, 50, 100));
    private ImageIcon tecladoNegroIcon = new ImageIcon(tecladoNegroImg.getScaledInstance(50, 50, 100));
    private ImageIcon atrasIcon = new ImageIcon(atrasImg.getScaledInstance(50, 50, 100));
    
    private String musica;
    
    public void iniciarControlador(ModeloAjustes modeloAjustes){       
        
        setLayout(null);
        setTitle("Arkanoid");
        
        this.modeloAjustes = modeloAjustes;
        controlSonido = modeloAjustes.getHaymusica();
        controlTeclado = modeloAjustes.getModoTecl();
        
        vista = new VistaOpciones(this, modeloAjustes);
        
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
        
        if(modeloAjustes.getHaymusica() == true){
            sonido = new JButton(sonidoONIcon);
            sonido.setBounds(350, 65, sonidoONIcon.getIconWidth(), sonidoONIcon.getIconHeight());
            sonido.setCursor(new Cursor(Cursor.HAND_CURSOR));
            sonido.setBorder(BorderFactory.createEmptyBorder());
            sonido.setContentAreaFilled(false);
             
            if(modeloAjustes.getModo() == false){
                sonido.setIcon(sonidoDarkONIcon);
            }
            
            sonido.addActionListener(this);
            vista.add(sonido);
        }
        
        if(modeloAjustes.getHaymusica() == false){
            sonido = new JButton(sonidoOFFIcon);
            sonido.setBounds(350, 65, sonidoOFFIcon.getIconWidth(), sonidoOFFIcon.getIconHeight());
            sonido.setCursor(new Cursor(Cursor.HAND_CURSOR));
            sonido.setBorder(BorderFactory.createEmptyBorder());
            sonido.setContentAreaFilled(false);
            
            if(modeloAjustes.getModo() == false){
                sonido.setIcon(sonidoDarkOFFIcon);
            }
            
            sonido.addActionListener(this);
            vista.add(sonido);
        }
        
        if(modeloAjustes.getModoTecl() == true){
            teclado = new JButton(ratonNegroIcon);
            teclado.setBounds(175, 425, ratonNegroIcon.getIconWidth(), ratonNegroIcon.getIconHeight());
            teclado.setCursor(new Cursor(Cursor.HAND_CURSOR));
            teclado.setBorder(BorderFactory.createEmptyBorder());
            teclado.setContentAreaFilled(false);
            
            if(modeloAjustes.getModo() == false){
                teclado.setBounds(175, 425, ratonBlancoIcon.getIconWidth(), ratonBlancoIcon.getIconHeight());
                teclado.setIcon(ratonBlancoIcon);
            }
            teclado.addActionListener(this);
            vista.add(teclado);
        }
        
        if(modeloAjustes.getModoTecl() == false){
            teclado = new JButton(tecladoNegroIcon);
            teclado.setBounds(175, 425, tecladoNegroIcon.getIconWidth(), tecladoNegroIcon.getIconHeight());
            teclado.setCursor(new Cursor(Cursor.HAND_CURSOR));
            teclado.setBorder(BorderFactory.createEmptyBorder());
            teclado.setContentAreaFilled(false);
            
            if(modeloAjustes.getModo() == false){
                teclado.setBounds(175, 425, tecladoBlancoIcon.getIconWidth(), tecladoBlancoIcon.getIconHeight());
                teclado.setIcon(tecladoBlancoIcon);
            }
            teclado.addActionListener(this);
            vista.add(teclado);
        }
        
               

        atras = new JButton(atrasIcon);
        atras.setBounds(40, 60, atrasIcon.getIconWidth(), atrasIcon.getIconHeight());
        atras.setCursor(new Cursor(Cursor.HAND_CURSOR));
        atras.setBorder(BorderFactory.createEmptyBorder());
        atras.setContentAreaFilled(false);
        atras.addActionListener(this);
        vista.add(atras);
        
        pequena = new JButton(modeloAjustes.getTexto()[10]);
        pequena.setBounds(130, 335, 100, 30);
        pequena.setFont(new Font("Tahome", Font.BOLD, 12));
        pequena.setForeground(Color.white);
        pequena.setBackground(Color.decode("#04398f"));
        pequena.addActionListener(this);
        pequena.setCursor(new Cursor(Cursor.HAND_CURSOR));
        vista.add(pequena);
        
        mediana = new JButton(modeloAjustes.getTexto()[11]);
        mediana.setBounds(250, 335, 100, 30);
        mediana.setFont(new Font("Tahome", Font.BOLD, 16));
        mediana.setForeground(Color.white);
        mediana.setBackground(Color.decode("#04398f"));
        mediana.addActionListener(this);
        mediana.setCursor(new Cursor(Cursor.HAND_CURSOR));
        vista.add(mediana);
        
        grande = new JButton(modeloAjustes.getTexto()[12]);
        grande.setBounds(370, 335, 110, 30);
        grande.setFont(new Font("Tahome", Font.BOLD, 20));
        grande.setForeground(Color.white);
        grande.setBackground(Color.decode("#04398f"));
        grande.addActionListener(this);
        grande.setCursor(new Cursor(Cursor.HAND_CURSOR));
        vista.add(grande);
        
        claro = new JButton(claroIcon);
        claro.setBounds(150, 275, claroIcon.getIconWidth(), claroIcon.getIconHeight());
        claro.setCursor(new Cursor(Cursor.HAND_CURSOR));
        claro.setBorder(BorderFactory.createEmptyBorder());
        claro.setContentAreaFilled(false);
        
        if(modeloAjustes.getModo() == false){
            claro.setIcon(claroDarkIcon);
        }
        
        claro.addActionListener(this);
        vista.add(claro);
        
        oscuro = new JButton(oscuroIcon);
        oscuro.setBounds(240, 275, oscuroIcon.getIconWidth(), oscuroIcon.getIconHeight());
        oscuro.setCursor(new Cursor(Cursor.HAND_CURSOR));
        oscuro.setBorder(BorderFactory.createEmptyBorder());
        oscuro.setContentAreaFilled(false);
        
        if(modeloAjustes.getModo() == false){
            oscuro.setIcon(oscuroDarkIcon);
        }
        
        oscuro.addActionListener(this);
        vista.add(oscuro); 
        
        ingles = new JButton(modeloAjustes.getTexto()[13]);
        ingles.setBounds(130, 385, 100, 30);
        ingles.setFont(modeloAjustes.getLetra());
        ingles.setForeground(Color.white);
        ingles.setBackground(Color.decode("#04398f"));
        ingles.addActionListener(this);
        ingles.setCursor(new Cursor(Cursor.HAND_CURSOR));
        vista.add(ingles);
        
                
        espanol = new JButton(modeloAjustes.getTexto()[14]);
        espanol.setBounds(250, 385, 100, 30);
        espanol.setFont(modeloAjustes.getLetra());
        espanol.setForeground(Color.white);
        espanol.setBackground(Color.decode("#04398f"));
        espanol.addActionListener(this);
        espanol.setCursor(new Cursor(Cursor.HAND_CURSOR));
        vista.add(espanol);
        
        cambiarLetra();
    }
        
    public void cambiarLetra(){
        if(modeloAjustes.getTamanio()== 0){
            letra = new Font("Tahoma", Font.BOLD, 12);
        }else{
            if(modeloAjustes.getTamanio() == 1){
                letra = new Font("Tahoma", Font.BOLD, 16);
            }else{
                letra = new Font("Tahoma", Font.BOLD, 20);
            }
        }
        
        atras.setFont(letra);
        ingles.setFont(letra);
        espanol.setFont(letra);
        
    }
    
    public void cambiarIdioma(){
        atras.setText(modeloAjustes.getTexto()[9]);
        pequena.setText(modeloAjustes.getTexto()[10]);
        mediana.setText(modeloAjustes.getTexto()[11]);
        grande.setText(modeloAjustes.getTexto()[12]);
        ingles.setText(modeloAjustes.getTexto()[13]);
        espanol.setText(modeloAjustes.getTexto()[14]);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == atras) {
            MenuBienvenida bienvenida = new MenuBienvenida();
            bienvenida.iniciarControlador(modeloAjustes);
            this.dispose();
            setVisible(false);
        }
        
        if(e.getSource() == sonido){
            if(controlSonido){
                modeloAjustes.setHaymusica(false);
                musica = modeloAjustes.stopmusica();
                
                if(modeloAjustes.getModo() == false){
                    sonido.setIcon(sonidoDarkOFFIcon);
                }else{
                    sonido.setIcon(sonidoOFFIcon);
                }
                controlSonido = false;
                
            }else{
                modeloAjustes.setHaymusica(true);
                modeloAjustes.playmusica(musica);
                
                if(modeloAjustes.getModo() == false){
                    sonido.setIcon(sonidoDarkONIcon);
                }else{
                    sonido.setIcon(sonidoONIcon);
                }
                controlSonido = true;
            }
            
        }
        
        if(e.getSource() == teclado){
            if(controlTeclado){
                modeloAjustes.setModoTecl(false);
                //meter accion
                
                if(modeloAjustes.getModo() == false){
                    teclado.setIcon(tecladoBlancoIcon);
                }else{
                    teclado.setIcon(tecladoNegroIcon);
                }
                controlTeclado = false;
                
            }else{
                modeloAjustes.setModoTecl(true);
                //accion
                
                if(modeloAjustes.getModo() == false){
                    teclado.setIcon(ratonBlancoIcon);
                }else{
                    teclado.setIcon(ratonNegroIcon);
                }
                controlTeclado = true;
            }
            
        }
        
        if(e.getSource() == claro){
            claro.setIcon(claroIcon);
            oscuro.setIcon(oscuroIcon);
            
            if(modeloAjustes.getHaymusica() == true){
                sonido.setIcon(sonidoONIcon);
            }else{
                sonido.setIcon(sonidoOFFIcon);
            }
            
            if(modeloAjustes.getModoTecl() == true){
                teclado.setIcon(ratonNegroIcon);
            } else {
                teclado.setIcon(tecladoNegroIcon);
            }
            
            modeloAjustes.setModo(true);
        }
        
        if(e.getSource() == pequena){
            modeloAjustes.setTamanio(0);
            cambiarLetra();
        }
        
        if(e.getSource() == mediana){
            modeloAjustes.setTamanio(1);
            cambiarLetra();
        }
        
        if(e.getSource() == grande){
            modeloAjustes.setTamanio(2);
            cambiarLetra();
        }
        
        if(e.getSource() == oscuro){
            claro.setIcon(claroDarkIcon);
            oscuro.setIcon(oscuroDarkIcon);
            
            if(modeloAjustes.getHaymusica() == true){
                sonido.setIcon(sonidoDarkONIcon);
            }else{
                sonido.setIcon(sonidoDarkOFFIcon);
            }
            
            if(modeloAjustes.getModoTecl() == true){
                teclado.setIcon(ratonBlancoIcon);
            } else {
                teclado.setBounds(175, 425, tecladoBlancoIcon.getIconWidth(), tecladoBlancoIcon.getIconHeight());
                teclado.setIcon(tecladoBlancoIcon);
            }
            
            modeloAjustes.setModo(false);
        }  
        
        if(e.getSource() == ingles){
            modeloAjustes.setIngles();
            cambiarIdioma();
        }
        
        if(e.getSource() == espanol){
            modeloAjustes.setEspañol();
            cambiarIdioma();
        }
    }

}
