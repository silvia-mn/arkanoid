/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Controlers.MenuAjustes;
import java.awt.Color;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import modelos.ModeloAjustes;

/**
 *
 * @author Celia
 */
public class VistaOpciones extends JPanel implements Observer {
    private MenuAjustes controlador = new MenuAjustes();
    private JLabel on, off, ajusteColor, tipoLetra, idioma, imagen2, texto, texto2, jugabilidad;
    private ModeloAjustes modeloAjustes;
    private boolean modo = true;
    private Font letra = new Font("Tahoma", Font.BOLD, 16);
    
    
    public VistaOpciones(MenuAjustes controlador, ModeloAjustes modeloAjustes){
        
        setLayout(null);
        
        this.controlador = controlador;
        this.modeloAjustes = modeloAjustes;
        imagen2 = new JLabel(new ImageIcon(getClass().getResource("../Imagenes/pieza.png")));
        
        anadirEtiquetas();
        aniadirExplicacion();
        
        setSize(750, 700);
        setVisible(true);
        
                
        cambiarAjustes();      
        
    }

    
    public void anadirEtiquetas(){
        
        ajusteColor = new JLabel(modeloAjustes.getTexto()[33]);
        ajusteColor.setBounds(55, 250, 100, 100);
        ajusteColor.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(ajusteColor);
        
        tipoLetra = new JLabel (modeloAjustes.getTexto()[34]);
        tipoLetra.setBounds(55, 300, 100, 100);
        tipoLetra.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(tipoLetra);       
        
        idioma = new JLabel(modeloAjustes.getTexto()[35]);
        idioma.setBounds(55, 350, 100, 100);
        idioma.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(idioma);

        jugabilidad = new JLabel(modeloAjustes.getTexto()[36]);
        jugabilidad.setBounds(55, 400, 100, 100);
        jugabilidad.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(jugabilidad);        
    }
    
    public void cambiarAjustes(){
        if(modeloAjustes.getModo()){
            setBackground(Color.white);
            ajusteColor.setForeground(Color.black);
            idioma.setForeground(Color.black);
            tipoLetra.setForeground(Color.black);
            jugabilidad.setForeground(Color.black);
        }else{
            setBackground(Color.darkGray);
            ajusteColor.setForeground(Color.white);
            idioma.setForeground(Color.white);
            tipoLetra.setForeground(Color.white);
            jugabilidad.setForeground(Color.white);
            
        }
        
        if(modeloAjustes.getTamanio() == 0){
            letra = new Font("Tahoma", Font.BOLD, 12);
        }else{
            if(modeloAjustes.getTamanio() == 1){
                letra = new Font("Tahoma", Font.BOLD, 16);
            }else{
                letra = new Font("Tahoma", Font.BOLD, 20);
            }
        }

        ajusteColor.setFont(letra);
        ajusteColor.setText(modeloAjustes.getTexto()[33]);
        idioma.setFont(letra);
        idioma.setText(modeloAjustes.getTexto()[34]);
        tipoLetra.setFont(letra);
        tipoLetra.setText(modeloAjustes.getTexto()[35]);
        jugabilidad.setFont(letra);
        jugabilidad.setText(modeloAjustes.getTexto()[36]);
        
        texto.setFont(letra);
        texto.setText(modeloAjustes.getTexto()[21]);
        
        texto2.setFont(letra);
        texto2.setText(modeloAjustes.getTexto()[37]);
        
        if(!modeloAjustes.getModo()){
           texto.setForeground(Color.white);
           texto2.setForeground(Color.white);
        }else{
            texto.setForeground(Color.black);
            texto2.setForeground(Color.black);
        }
            
       
          
    }
    
    public void aniadirExplicacion(){
            texto = new JLabel(modeloAjustes.getTexto()[21]);
            
            texto.setBounds(500, 50, 250, 320);
            add(texto);
            
            texto2 = new JLabel(modeloAjustes.getTexto()[37]);
            
            texto2.setBounds(200, 500, 200, 120);
            add(texto2);
            
            imagen2.setBounds(500, 400, 100, 246);
            add(imagen2);
            Runnable r = new Runnable(){
                public void run(){
                    mover();
                }
            };
            new Thread(r).start();
            
    }
    
    @Override
    public void update(Observable o, Object arg) {
        
        cambiarAjustes();        
    }
    
     public void mover(){
        int i =0;
        while (i <4) {
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
