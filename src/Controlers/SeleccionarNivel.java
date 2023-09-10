/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlers;

import Vistas.VistaMenuJugar;
import Vistas.VistaSeleccionarNivel;
import arkanoid.CargarJuego;
import arkanoid.Juego;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import modelos.ModeloAjustes;

/**
 *
 * @author Celia
 */
public class SeleccionarNivel extends JFrame implements ActionListener {
     private VistaSeleccionarNivel vista;
     private boolean controlSonido;
    private JButton n1, n2, n3, n4, n5, n6, n7, n8, n9, atras2;
    private JMenuBar menu;
    private JMenu atras;
    private JMenuItem volver;
    private ModeloAjustes modeloAjustes;
    private ImageIcon icon13 = new ImageIcon(getClass().getResource("../Imagenes/atras.png"));
    private Image atrasImg = icon13.getImage();
    private ImageIcon atrasIcon = new ImageIcon(atrasImg.getScaledInstance(50, 50, 100));

    
    public SeleccionarNivel(){
        
    }
    
    public void iniciarControlador(ModeloAjustes modeloAjustes){
        
        setLayout(null);
        setTitle("Arkanoid");

        this.modeloAjustes = modeloAjustes;
        controlSonido = modeloAjustes.getHaymusica();
        vista = new VistaSeleccionarNivel(this, modeloAjustes);
        
        iniciarBotones();
        iniciarMenu();
        
        add(vista);        
        
        setSize(vista.getSize());
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
    
    public void iniciarBotones(){ 
        atras2 = new JButton(atrasIcon);
        atras2.setBounds(40, 60, atrasIcon.getIconWidth(), atrasIcon.getIconHeight());
        atras2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        atras2.setBorder(BorderFactory.createEmptyBorder());
        atras2.setContentAreaFilled(false);
        atras2.addActionListener(this);
        vista.add(atras2);
        
        n1 = new JButton("1");
        n1.setBounds(120, 350, 50, 50);
        n1.setFont(modeloAjustes.getLetra());
        n1.setForeground(Color.white);
        n1.setBackground(Color.decode("#04398f"));
        n1.addActionListener(this);
        n1.setBorder(BorderFactory.createLineBorder(Color.black));
        n1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        vista.add(n1);
        
       
        n2 = new JButton("2");
        n2.setBounds(221, 350, 50, 50);
        n2.setFont(modeloAjustes.getLetra());
        n2.setForeground(Color.white);
        n2.setBackground(Color.decode("#04398f"));
        n2.addActionListener(this);
        n2.setBorder(BorderFactory.createLineBorder(Color.black));
        n2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        vista.add(n2);
        
        n3 = new JButton("3");
        n3.setBounds(319, 350, 50, 50);
        n3.setFont(modeloAjustes.getLetra());
        n3.setForeground(Color.white);
        n3.setBackground(Color.decode("#04398f"));
        n3.addActionListener(this);
        n3.setBorder(BorderFactory.createLineBorder(Color.black));
        n3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        vista.add(n3);
        
        n4 = new JButton("4");
        n4.setBounds(120, 450, 50, 50);
        n4.setFont(modeloAjustes.getLetra());
        n4.setForeground(Color.white);
        n4.setBackground(Color.decode("#04398f"));
        n4.addActionListener(this);
        n4.setBorder(BorderFactory.createLineBorder(Color.black));
        n4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        vista.add(n4);
        
        n5 = new JButton("5");
        n5.setBounds(221, 450, 50, 50);
        n5.setFont(modeloAjustes.getLetra());
        n5.setForeground(Color.white);
        n5.setBackground(Color.decode("#04398f"));
        n5.addActionListener(this);
        n5.setBorder(BorderFactory.createLineBorder(Color.black));
        n5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        vista.add(n5);
        
        n6 = new JButton("6");
        n6.setBounds(319, 450, 50, 50);
        n6.setFont(modeloAjustes.getLetra());
        n6.setForeground(Color.white);
        n6.setBackground(Color.decode("#04398f"));
        n6.addActionListener(this);
        n6.setBorder(BorderFactory.createLineBorder(Color.black));
        n6.setCursor(new Cursor(Cursor.HAND_CURSOR));
        vista.add(n6);
        
        n7 = new JButton("7");
        n7.setBounds(120, 550, 50, 50);
        n7.setFont(modeloAjustes.getLetra());
        n7.setForeground(Color.white);
        n7.setBackground(Color.decode("#04398f"));
        n7.addActionListener(this);
        n7.setBorder(BorderFactory.createLineBorder(Color.black));
        n7.setCursor(new Cursor(Cursor.HAND_CURSOR));
        vista.add(n7);
        
        n8 = new JButton("8");
        n8.setBounds(221, 550, 50, 50);
        n8.setFont(modeloAjustes.getLetra());
        n8.setForeground(Color.white);
        n8.setBackground(Color.decode("#04398f"));
        n8.addActionListener(this);
        n8.setBorder(BorderFactory.createLineBorder(Color.black));
        n8.setCursor(new Cursor(Cursor.HAND_CURSOR));
        vista.add(n8);
        
        n9 = new JButton("9");
        n9.setBounds(319, 550, 50, 50);
        n9.setFont(modeloAjustes.getLetra());
        n9.setForeground(Color.white);
        n9.setBackground(Color.decode("#04398f"));
        n9.addActionListener(this);
        n9.setBorder(BorderFactory.createLineBorder(Color.black));
        n9.setCursor(new Cursor(Cursor.HAND_CURSOR));
        vista.add(n9);
        
        
    }
    

    
    
    public void iniciarMenu() {

        menu = new JMenuBar();
        menu.setBounds(0, 0, 700, 25);
        add(menu);

        atras = new JMenu(modeloAjustes.getTexto()[6]);
        atras.setFont(modeloAjustes.getLetra());
        menu.add(atras);

        
        volver = new JMenuItem(modeloAjustes.getTexto()[7]);
        volver.setFont(modeloAjustes.getLetra());
        volver.addActionListener(this);
        
        atras.add(volver);

    }
    
    
    @Override
    public void actionPerformed(ActionEvent e){
        
        if (e.getSource() == atras2) {
            MenuJugar tutorial = new MenuJugar();
            tutorial.iniciarControlador(modeloAjustes);            
            this.dispose();
            setVisible(false);
        }
        
        
        
        if (e.getSource() == n1) {
            Juego juego = new Juego();
            juego.setModelo(modeloAjustes);
            juego.setLevel(1);
            Thread hiloJuego = new Thread(juego);
            hiloJuego.start();
}
        
        
        if (e.getSource() == n2) {
            Juego juego = new Juego();
            juego.setModelo(modeloAjustes);
            juego.setLevel(2);
            Thread hiloJuego = new Thread(juego);
            hiloJuego.start();
        }
        
        if (e.getSource() == n3) {
            Juego juego = new Juego();
            juego.setModelo(modeloAjustes);
            juego.setLevel(3);
            Thread hiloJuego = new Thread(juego);
            hiloJuego.start();
            }
        
        
        if (e.getSource() == n4) {
            Juego juego = new Juego();
            juego.setModelo(modeloAjustes);
            juego.setLevel(4);
            Thread hiloJuego = new Thread(juego);
            hiloJuego.start();
            }
        
        if (e.getSource() == n5) {
            Juego juego = new Juego();
            juego.setModelo(modeloAjustes);
            juego.setLevel(5);
            Thread hiloJuego = new Thread(juego);
            hiloJuego.start();
            }
        
        if (e.getSource() == n6) {
            Juego juego = new Juego();
            juego.setModelo(modeloAjustes);
            juego.setLevel(6);
            Thread hiloJuego = new Thread(juego);
            hiloJuego.start();
            }
        
        if (e.getSource() == n7) {
            Juego juego = new Juego();
            juego.setModelo(modeloAjustes);
            juego.setLevel(7);
            Thread hiloJuego = new Thread(juego);
            hiloJuego.start();
            }
        
        if (e.getSource() == n8) {
            Juego juego = new Juego();
            juego.setModelo(modeloAjustes);
            juego.setLevel(8);
            Thread hiloJuego = new Thread(juego);
            hiloJuego.start();
            }
        
        if (e.getSource() == n9) {
            Juego juego = new Juego();
            juego.setModelo(modeloAjustes);
            juego.setLevel(9);
            Thread hiloJuego = new Thread(juego);
            hiloJuego.start();
            }
        
                
        if(e.getSource() == volver){
            MenuJugar jugar = new MenuJugar();
            jugar.iniciarControlador(modeloAjustes);            
            this.dispose();
            setVisible(false);
        }

} 

    
}


