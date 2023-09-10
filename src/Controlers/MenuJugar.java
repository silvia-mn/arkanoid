
package Controlers;

import Vistas.VistaMenuJugar;
import arkanoid.Juego;
import java.awt.Color;
import java.awt.Cursor;
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
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import modelos.ModeloAjustes;

/**
 *
 * @author Celia
 */
public class MenuJugar extends JFrame implements ActionListener {
    private VistaMenuJugar vista;
    private JButton jugar, juego1, juego2,atras2;
    private JMenuBar menu;
    private JMenu atras;
    private JMenuItem volver;
    private ModeloAjustes modeloAjustes;
    private ImageIcon icon13 = new ImageIcon(getClass().getResource("../Imagenes/atras.png"));
    private Image atrasImg = icon13.getImage();
    private ImageIcon atrasIcon = new ImageIcon(atrasImg.getScaledInstance(50, 50, 100));

    
    public MenuJugar(){
        
    }
    
    public void iniciarControlador(ModeloAjustes modeloAjustes){
        
        setLayout(null);
        setTitle("Arkanoid");

        this.modeloAjustes = modeloAjustes;
        
        vista = new VistaMenuJugar(this, modeloAjustes);
        
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
        jugar = new JButton(modeloAjustes.getTexto()[3]);
        jugar.setBounds(125, 350, 240, 60);
        jugar.setFont(modeloAjustes.getLetra());
        jugar.setForeground(Color.white);
        jugar.setBackground(Color.decode("#04398f"));
        jugar.addActionListener(this);
        jugar.setBorder(BorderFactory.createLineBorder(Color.black));
        jugar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        vista.add(jugar);
        
        
        
        juego1 = new JButton(modeloAjustes.getTexto()[4]);
        juego1.setBounds(125, 450, 240, 60);
        juego1.setFont(modeloAjustes.getLetra());
        juego1.setForeground(Color.white);
        juego1.setBackground(Color.decode("#04398f"));
        juego1.addActionListener(this);
        juego1.setBorder(BorderFactory.createLineBorder(Color.black));
        juego1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        vista.add(juego1);
        
        /*juego2 = new JButton(modeloAjustes.getTexto()[5]);
        juego2.setBounds(125, 500, 240, 60);
        juego2.setFont(modeloAjustes.getLetra());
        juego2.setForeground(Color.white);
        juego2.setBackground(Color.decode("#04398f"));
        juego2.addActionListener(this);
        juego2.setBorder(BorderFactory.createLineBorder(Color.black));
        juego2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        vista.add(juego2);*/
        
        atras2 = new JButton(atrasIcon);
        atras2.setBounds(40, 60, atrasIcon.getIconWidth(), atrasIcon.getIconHeight());
        atras2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        atras2.setBorder(BorderFactory.createEmptyBorder());
        atras2.setContentAreaFilled(false);
        atras2.addActionListener(this);
        vista.add(atras2);
        
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
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == atras2) {
            MenuBienvenida bienvenida = new MenuBienvenida();
            bienvenida.iniciarControlador(modeloAjustes);              
            this.dispose();
            setVisible(false);
        }
        
        if (e.getSource() == juego1) {
            Juego juego = new Juego();
            juego.setModelo(modeloAjustes);
            Thread hiloJuego = new Thread(juego);
            hiloJuego.start();
        }
        
        if (e.getSource() == jugar) {
            try {
                SeleccionarNivel sn = new SeleccionarNivel();
                sn.iniciarControlador(modeloAjustes);
                this.dispose();
                setVisible(false);

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        
        
        
       
        if(e.getSource() == volver){
            MenuBienvenida bienvenida = new MenuBienvenida();
            bienvenida.iniciarControlador(modeloAjustes);            
            this.dispose();
            setVisible(false);
        }
    } 
    
}
