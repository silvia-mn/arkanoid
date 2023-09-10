/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author Celia
 */
public class Tutorial1Modelo extends Observable{
    
    private int contar = -1, tecla = 0;
    private JLabel texto = new JLabel();
    private boolean activarContador = false, continuar = false;
    private int movimiento = 0, contar2 = -1;
    private boolean modo;
    private String colorTexto = "black";
    private ModeloAjustes modeloOpciones;
    
    public Tutorial1Modelo(ModeloAjustes modeloOpciones){
        this.modeloOpciones = modeloOpciones;
    }
         
    
    public void contador(int segundos){
        Timer timer;
        timer = new Timer(segundos, new ActionListener() {     
            public void actionPerformed(ActionEvent e) {
                contar += 1;
                
                historia();
                setChanged();
                notifyObservers();
            }
        });
        
        timer.start(); 
    }

    

    public void historia(){
        
        if (contar == 0) {
            texto.setText("<html><font color=" + colorTexto + ">" + modeloOpciones.getTexto()[25] +"</font></html>");
        }

        if (contar == 1) {
            texto.setText("<html><font color=" + colorTexto + ">" + modeloOpciones.getTexto()[26] + "</font></html>");
        }

        if (contar == 2) {
            texto.setText("<html><font color=" + colorTexto + ">" + modeloOpciones.getTexto()[27] + "</font></html>");
            activarContador = true;
        }
        
        if (continuar && contar2 == 0) {
            texto.setText("<html><font color=" + colorTexto + ">" + modeloOpciones.getTexto()[28] + "</font></html>");
            movimiento = 0;
            tecla ++;
        }
        
        if(continuar && contar2 == 1){
            texto.setText("<html><font color=" + colorTexto + ">" + modeloOpciones.getTexto()[29] + "</font></html>");
            movimiento = 0;
            tecla ++;
        }
        
        
        if(continuar && contar2 == 2){
            texto.setText("<html><font color=" + colorTexto + ">" + modeloOpciones.getTexto()[30] + "</font></html>");
            movimiento = 0;
            tecla ++;
        }
                
        if(continuar && contar2 == 3){
            texto.setText("<html><font color=" + colorTexto + ">" + modeloOpciones.getTexto()[31] + "</font></html>");
            movimiento = 0;
            tecla ++;
        }
        
        if(continuar && contar2 == 4){
            texto.setText("<html><font color=" + colorTexto + ">" + modeloOpciones.getTexto()[32] + "</font></html>");
            
        }
    }
    
    public void comprobarMovimiento(){
                
        if(movimiento >= 5){
            contar2 ++;
            continuar = true;
            historia();
            setChanged();
            notifyObservers();
            
        }
    }
    
    public void addMovimiento(){
        
        movimiento ++;
        
        comprobarMovimiento();
    }

    public JLabel getTexto() {
        return texto;
    }
    
    public int getContar(){
        return contar;
    }
    
    
    public boolean getActivarContador(){
        return activarContador;
    }

    public boolean isContinuar() {
        return continuar;
    }

    
    public void setContinuar(boolean continuar) {
        this.continuar = continuar;
    }

    public int getContar2() {
        return contar2;
    }

    public int getTecla() {
        return tecla;
    }
         
    public void setModo(boolean modo){
        this.modo = modo;
        
        if(!modo){
            colorTexto = "white";
        }
    }
    
    public boolean getModo(){
        return modo;
    }
}
