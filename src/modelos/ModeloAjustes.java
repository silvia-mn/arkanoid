/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import Vistas.VistaInicio;
import java.awt.Font;
import java.util.Observable;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JLabel;

/**
 *
 * @author Celia
 */
public class ModeloAjustes extends Observable {
    private boolean modo;
    private int tamanio;
    private Font letra = new Font("Tahoma", Font.BOLD, 16);
    private Clip musica;
    private boolean modomusica= false;
    private boolean modoTecl= false;
    private boolean firstClipOpen= true;
    private String name= "/Sonidos/Inicio.wav";
    
    private String[] español =  {"JUGAR", "TUTORIALES", "OPCIONES/AJUSTES", "JUEGO NORMAL",
        "JUEGO SIN PARADAS", "MINIJUEGO", "Atrás", "Volver", "Opciones", "Atrás", "Pequeña", 
        "Mediana", "Grande", "Inglés", "Español", "TUTORIAL TECLADO", "TUTORIAL JUEGO",
        "Atrás", "Volver", "Parar","<html><p>Bienvenido, para  <br/> empezar <br/> pulse <i>JUGAR</i>. </p><br/>"
                    + "<p> Si quiere aprender o  <br/> practicar,  <br/> pulse <i>TUTORIALES</i>. </p><br/>"
                    + "<p> Si quiere modificar <br/> algo, <br/> pulse <i>AJUSTES</i>.</p></html>",
                    "<html><p>Si quiere poner  <br/> o quitar la música <br/> pulse el altavoz. </p><br/>"
                    + "<p> Si quiere cambiar  <br/> el idioma, pulse  <br/><i>Inglés</i> o <i>Español</i>. </p><br/>"
                    + "<p> Si quiere cambiar <br/> el tamaño  de la letra <br/> pulse <i>Pequeña</i>,<br/> <i>Mediana</i> o <i>Grande</i>.</p></html>",
                    "<html><p>Te encuentras en <br/> la opción <i>JUGAR</i>. </p><br/>"
                    + "<p> Si quiere jugar<br/> por niveles pulse<br/> <i>JUEGO NORMAL</i>. </p><br/>"
                    + "<p> Si quiere jugar sin <br/> pausas hasta que <br/>pierdas o ganes todos <br/> los niveles pulse <br/> <i>JUEGO SIN PARADAS</i>.</p></html>",
                    "<html><p>Si quiere que le <br/> explique como <br/> usar el teclado pulse<br/><i> TUTORIAL TECLADO</i>. </p><br/>"
                    + "<p> Si quiere que le <br/> explique como <br/> se juega pulse <br/><i>TUTORIAL JUEGO</i>. </p><br/>"
                    + "<p>Si quiere ver los objetos <br/> que aparecen pulse<br/><i> OBJETOS DEL JUEGO</i>. </p><br/></html>",
                    "<html>¡Vamos a comenzar <br></br> el tutorial!</html>",
                    "En primer lugar vamos <br></br> "
                    + "a aprender a utilizar el teclado",
                    "Como ves acaba de aparecer <br></br> una imagen de unas teclas",
                    "¡Búscalas en tu teclado y púlsalas, <br></br>no pasa nada!",
                    "¡Genial!<br></br>Ahora que sabes donde están prueba <br></br>a pulsar la siguiente tecla",
                    "¡Que bien!<br></br>Probemos ahora con la tecla de la flecha hacia abajo",
                    "¡Guau!<br></br>Prueba ahora con tecla con la<br/>flecha hacia la izquierda",
                    "¡Y esta es la última!<br></br>Pulsa la tecla con la flecha hacia la derecha",
                    "¡Genial! Ya sabes utilizar <br></br> el teclado! Continúa practicando<br></br> con el tutorial!"
                    + "<br></br>(Vuelve y selecciona la opción: Tutorial Juego)",
                    "Modo",
                    "Idioma",
                    "Letra",
                    "Tecl/Rat",
                    "<html><p>Si ves el icono de ratón, en uso TECLADO.</p><br></br>"
                    +"<p>Si ves el icono de teclado, en uso RATÓN.</p></html>", 
                    "<html>    Aquí encontrarás distintos niveles del juego, con distintas dificultades  <br><br/>"
                    + "    Selecciona un nivel para empezar a jugar</html>",
                    "OBJETOS DEL JUEGO",
                    "Expandir Barra",
                    "Reducir Barra",
                    "Acelerar Velocidad Bola",
                    "Frena Velocidad Bola",
                    "Bola del Juego",
                    "Añadir Bola al Juego",
                    "Vidas del Juego"
                    
    };
    
    
    private String[] ingles = {"PLAY", "TUTORIALS", "OPTIONS/SETTINGS", "MAIN GAME", 
        "UNINTERRUPTED GAME", "MINIGAME", "Back", "Back", "Options", "Back", "Little", 
        "Medium", "Big", "English", "Spanish", "KEYBOARD TUTORIAL", "GAME TUTORIAL", 
        "Back", "Back", "Pause","<html><p>Welcome, to start <br/> press <i>PLAY</i>. </p><br/>"
                    + "<p> If you want to learn  <br/> or practice,  <br/> press <i>TUTORIALS</i>. </p><br/>"
                    + "<p> If you want to modify <br/> settings, <br/> press <i>SETTINGS</i>.</p></html>",
                    "<html><p>If you want to play  <br/> or quit music<br/> press the speaker. </p><br/>"
                    + "<p> If you want to change  <br/> the language, press  <br/><i>English</i> or <i>Spanish</i>. </p><br/>"
                    + "<p> If youy want to change <br/> size of text <br/> press <i>Little</i>,<br/> <i>Medium</i> or <i>Big</i>.</p></html>",
                    "<html><p>You`re know in the option <i>PLAY</i>. </p><br/>"
                    +"<p>If you want to play<br/>by levels, click<br/><i>MAIN GAME</i>.</p><br/>"
                    + "<p>If you want to play<br/>without pauses until<br/>you lose or win<br/>all the levels, click<br/><i>UNINTERRUPTED GAME</i>.</p></html>",
                     "<html><p> If you want to learn <br/> how to use the keyboard, <br/> press <br/><i>KEYBOARD TUTORIAL</i>. </p><br/>"
                    + "<p>If you want to <br/> learn how this game <br/> is played, press<br/><i>GAME TUTORIAL</i>. </p><br/>"
                    + "<p>If you want to see <br/>the objects that appear<br/> in the game, click <br/><i>GAME OBJECTS</i>.</p><br/></html>"
                    + "If you want to <br/>",
                    "<html>Let's play <br></br> the tutorial!</html>",
                    "First, let's learn <br></br> "
                    + "to use the keyboard" ,
                    "As you see an image <br></br> of some keys just appeared",
                    "Search for them on your keyboard and press them!",
                    "Awesome !<br></br>Now you know where they are <br></br>try to press this one",
                    "Great !<br></br>Now, let's try with the arrow-down key",
                    "Guau !<br></br>Try now the left-arrow key",
                    "This is the last down!<br></br>Press the right arrow key",
                    "Great! You know how to use  <br></br> the keyboard! Keep practicing<br></br> with the tutorial!"
                    + "<br></br>(Back and select the option: Game Tutorial)",
                    "Mode",
                    "Idiom",
                    "Font",
                    "Keyb/Mouse",
                    "<html><p>If you watch the mouse icon, using KEYBOARD.</p><br></br>"
                    +"<p>If you watch the keyboard icon, using MOUSE.</p></html>",
                    "<html>Here you will find different levels of the game, with varying difficulties.<br><br/>"
                    + "Select a level to start playing.</html>",
                    "GAME OBJECTS",
                    "Expand Bar",
                    "Reduce Bar",
                    "Accelerate Ball Speed",
                    "Decelerate Ball Speed",
                    "Game Ball",
                    "Add Ball to Game",
                    "Game Lives"

    };
    
    private String[] idiomaTexto = español;
    
    
    public ModeloAjustes(Boolean modo, int tamanio, Boolean musica, Boolean teclado){
        this.modo = modo;
        this.tamanio = tamanio;
        this.modomusica = musica;
        this.modoTecl = teclado;
    }    
    
    public void setEspañol(){
        idiomaTexto = español;
        setChanged();
        notifyObservers();
    }
    
    public void setIngles(){
        idiomaTexto = ingles;
        setChanged();
        notifyObservers();
    }
    
    public String[] getTexto(){
        return idiomaTexto;
    }
    
    public Font getLetra(){
        return letra;
    }
    
    public int getTamanio(){
        return tamanio;
    }
    
    public void setTamanio(int tamanio){
        this.tamanio = tamanio;
        
        if(tamanio == 0){
            letra = new Font("Tahoma", Font.BOLD, 12);
        }else{
            if(tamanio == 1){
                letra = new Font("Tahoma", Font.BOLD, 16);
            }else{
                letra = new Font("Tahoma", Font.BOLD, 20);
            }
        }
                
        setChanged();
        notifyObservers();
    }
    
    public void setModo(Boolean modo){
        this.modo = modo;
        
        setChanged();
        notifyObservers();
    }
    
    
    public boolean getModo(){
        return modo;
        
    }
    
    public boolean getModoTecl(){
        return modoTecl;
    }
    
    public void setModoTecl(Boolean teclado){
        this.modoTecl = teclado;
        
        setChanged();
        notifyObservers();
    }
    
    public void playmusica(String nombre){
        
        if(modomusica){
            try{
                if(firstClipOpen){
                    musica= AudioSystem.getClip();
                    musica.open(AudioSystem.getAudioInputStream(getClass().getResource(nombre)));
                    if(!musica.isRunning()){
                        musica.start();                 
                        musica.loop(Clip.LOOP_CONTINUOUSLY);
                    }
                    
                    firstClipOpen=false;
                    
                }
                else{
                    if(!musica.isRunning()){
                        musica.start();                 
                        musica.loop(Clip.LOOP_CONTINUOUSLY);
                    }
                }
            }catch(Exception ex){}
        }
        name = nombre;        
    }
    
    public String stopmusica(){
       musica.stop();
       return name;
    }

    public boolean getHaymusica() {
        return modomusica;
    }
    
    public void setHaymusica(Boolean musica){
        this.modomusica = musica;
        
        setChanged();
        notifyObservers();        
    }
}
