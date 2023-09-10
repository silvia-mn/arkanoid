
package Controlers;

import Vistas.VistaTutorial;
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
import modelos.ModeloAjustes;
import tutorial.TutorialArkanoid;

/**
 *
 * @author Celia
 */
public class MenuTutorial extends JFrame implements ActionListener{
    private VistaTutorial vista;
    private JButton tutorial1, tutorial2, tutorial3, atras2;
    private JMenuBar menu;
    private JMenu atras;
    private JMenuItem volver;
    private ModeloAjustes modeloAjustes;
    private ImageIcon icon13 = new ImageIcon(getClass().getResource("../Imagenes/atras.png"));
    private Image atrasImg = icon13.getImage();
    private ImageIcon atrasIcon = new ImageIcon(atrasImg.getScaledInstance(50, 50, 100));
    

    
    public MenuTutorial(){
        
    }
    
    public void iniciarControlador(ModeloAjustes modeloAjustes){
        
        setLayout(null);
        setTitle("Arkanoid");

        this.modeloAjustes = modeloAjustes;
        
        vista = new VistaTutorial(this, modeloAjustes);
        
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
        tutorial1 = new JButton(modeloAjustes.getTexto()[15]);
        tutorial1.setBounds(125, 300, 240, 60);
        tutorial1.setFont(modeloAjustes.getLetra());
        tutorial1.setForeground(Color.white);
        tutorial1.setBackground(Color.decode("#04398f"));
        tutorial1.addActionListener(this);
        tutorial1.setBorder(BorderFactory.createLineBorder(Color.black));
        tutorial1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        vista.add(tutorial1);
        
        
        
        tutorial2 = new JButton(modeloAjustes.getTexto()[16]);
        tutorial2.setBounds(125, 400, 240, 60);
        tutorial2.setFont(modeloAjustes.getLetra());
        tutorial2.setForeground(Color.white);
        tutorial2.setBackground(Color.decode("#04398f"));
        tutorial2.addActionListener(this);
        tutorial2.setBorder(BorderFactory.createLineBorder(Color.black));
        tutorial2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        vista.add(tutorial2);
        
        
        tutorial3 = new JButton(modeloAjustes.getTexto()[39]);
        tutorial3.setBounds(125, 500, 240, 60);
        tutorial3.setFont(modeloAjustes.getLetra());
        tutorial3.setForeground(Color.white);
        tutorial3.setBackground(Color.decode("#04398f"));
        tutorial3.addActionListener(this);
        tutorial3.setBorder(BorderFactory.createLineBorder(Color.black));
        tutorial3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        vista.add(tutorial3);
        
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

        atras = new JMenu(modeloAjustes.getTexto()[17]);
        atras.setFont(modeloAjustes.getLetra());
        menu.add(atras);

        
        volver = new JMenuItem(modeloAjustes.getTexto()[18]);
        volver.setFont(modeloAjustes.getLetra());
        volver.addActionListener(this);
        
        atras.add(volver);
       
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == atras2) {
            MenuBienvenida bienvenia = new MenuBienvenida();
            bienvenia.iniciarControlador(modeloAjustes);              
            this.dispose();
            setVisible(false);
        }
        
        
        if (e.getSource() == tutorial1) {
           Tutorial1Controlador tutorial = new Tutorial1Controlador();            
            tutorial.iniciarControlador(modeloAjustes);
            this.dispose();
            setVisible(false);
        }
        
        
        if (e.getSource() == tutorial2) {
            TutorialArkanoid juego = new TutorialArkanoid();
            if(modeloAjustes.getTexto()[0]=="JUGAR"){
                juego.setEspaniol(true);
                
            }else{
                juego.setEspaniol(false);
            }
            
            juego.setSonido(modeloAjustes.getHaymusica());
            
            juego.setTeclado(modeloAjustes.getModoTecl());
            Thread hiloJuego = new Thread(juego);
            hiloJuego.start();
            
        }
        
        if (e.getSource() == tutorial3){
            Tutorial3 tutorial3 = new Tutorial3();
            tutorial3.iniciarControlador(modeloAjustes);
            this.dispose();
            setVisible(false);
            
            
        }
                
        if(e.getSource() == volver){
            MenuBienvenida bienvenida = new MenuBienvenida();
            bienvenida.iniciarControlador(modeloAjustes);            
            this.dispose();
            setVisible(false);
        }
    } 
    
    
}
