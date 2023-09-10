/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlers;

import Vistas.VistaTutorial;
import Vistas.VistaTutorial3;
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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import modelos.ModeloAjustes;

/**
 *
 * @author Celia
 */
public class Tutorial3 extends JFrame implements ActionListener {
    private VistaTutorial3 vista;
    private JMenuBar menu;
    private JMenu atras;
    private JButton atras2;
    private JMenuItem volver;
    private ModeloAjustes modeloAjustes;
    private ImageIcon icon13 = new ImageIcon(getClass().getResource("../Imagenes/atras.png"));
    private Image atrasImg = icon13.getImage();
    private ImageIcon atrasIcon = new ImageIcon(atrasImg.getScaledInstance(50, 50, 100));
   
    public Tutorial3(){
    }
    
    
    public void iniciarControlador(ModeloAjustes modeloAjustes){
        
        setLayout(null);
        setTitle("Arkanoid");

        this.modeloAjustes = modeloAjustes;
        
        vista = new VistaTutorial3(this, modeloAjustes);
        
        iniciarMenu();
        iniciarBotones();
        
        add(vista);        
        
        setSize(vista.getSize());
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
    
    
    public void iniciarMenu() {

        menu = new JMenuBar();
        menu.setBounds(0, 0, 700, 25);
        add(menu);

        atras = new JMenu(modeloAjustes.getTexto()[17]);
        atras.setFont(modeloAjustes.getLetra());
        menu.add(atras);
        
        /*atras = new JButton(modeloAjustes.getTexto()[9]);
        atras.setBounds(50, 60, 100, 60);
        atras.setFont(new Font("Tahoma", Font.BOLD, 16));
        atras.setForeground(Color.white);
        atras.setBackground(Color.red);
        atras.addActionListener(this);
        atras.setCursor(new Cursor(Cursor.HAND_CURSOR));
        vista.add(atras);*/

        
        volver = new JMenuItem(modeloAjustes.getTexto()[18]);
        volver.setFont(modeloAjustes.getLetra());
        volver.addActionListener(this);
        
        
        atras.add(volver);
       
    }
    
    public void iniciarBotones(){
        atras2 = new JButton(atrasIcon);
        atras2.setBounds(40, 60, atrasIcon.getIconWidth(), atrasIcon.getIconHeight());
        atras2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        atras2.setBorder(BorderFactory.createEmptyBorder());
        atras2.setContentAreaFilled(false);
        atras2.addActionListener(this);
        vista.add(atras2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == atras2) {
            MenuTutorial tutorial = new MenuTutorial();
            tutorial.iniciarControlador(modeloAjustes);            
            this.dispose();
            setVisible(false);
        }
        
        if(e.getSource() == volver){
            MenuTutorial tutorial = new MenuTutorial();
            tutorial.iniciarControlador(modeloAjustes);            
            this.dispose();
            setVisible(false);
        }
        
    }
    
}
