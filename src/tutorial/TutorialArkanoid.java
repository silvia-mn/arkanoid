
package tutorial;
import arkanoid.*;
import static arkanoid.Juego.FRAME_H;
import static arkanoid.Juego.FRAME_W;
import static arkanoid.Juego.WAIT_TIME;
import java.awt.Cursor;
import java.awt.Toolkit;
import reproductor.jlap;
import premios.Freno;
import premios.Premio;
import premios.Acelerador;
import premios.MasPelotas;
import premios.Reductor;
import premios.Expansor;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import javazoom.jl.decoder.JavaLayerException;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Component;
import java.util.TimerTask;
//import modelos.ModeloAjustes;


public class TutorialArkanoid extends JPanel implements MouseInputListener, KeyListener, Runnable {

	
	private static final long serialVersionUID = 1L;

	//--------------- Valores constantes ---------------
	
	/** La anchura de la ventana principal */
	public static final int FRAME_W=410; //el valor inicial es 277, un buen valor 410

	/** La altura de la ventana principal */
	public static final int FRAME_H=675; //el valor inicial es 450, un buen valor 675
	
	/** El número de imágenes por segundo. Puede aumentarse
	 o disminuirse para acelerar o ralentizar, respectivamente,
	 la velocidad del juego. */
	public static final int FPS=50; 

	/** El tiempo que debe esperarse tras cada cambio de posición
	 de la bola */
	public static final int WAIT_TIME=1000/FPS;
	
	
	//--------------- Atributos ---------------

	/** Anchura de la zona interior de la ventana en la que se mueve la pelota */
	public int panelW; 
	
	/** Altura de la zona interior de la ventana en la que se mueve la pelota */
	public int panelH; 
	
	/** ArrayList contenedor de las pelotas */
	private ArrayList<Pelota> pelotas = new ArrayList<Pelota>();

	private ArrayList<Premio> premios = new ArrayList<Premio>();
	
	public Raqueta raqueta;
        
	public boolean victory=false;
        
	private ArrayList<Object> pauseX = new ArrayList<Object>();
	
	private ArrayList<Object> pauseY = new ArrayList<Object>();
	
	private ArrayList<Ladrillo> ladrillos = null;
	
	private ArrayList<Time> timeLevels = new ArrayList<Time>();
	
	private static jlap jlPlayer;
	
	private long startTime = 0;
	
	private long finalTime = 0;
	
	public int level = 1;
	
	public int numLevels = 1 ;
	
	private int lifes = 3;
	
	private int timeNextLevel = 0;
	
	public static int puntuacion = 0;
	
	public Image image = null;
	
	private Image fondoVidas = null;

	private Random random;
	
	private Random randomPremios;
        
        // variables para manejar el teclado
        
        private boolean leftKeyPressed = false;
        
        private boolean rightKeyPressed = false;
        
        private static int velocidadDespl = 5;
        private JFrame frame;
        private JPanel panel0;
        private boolean isGamePaused= false;
        //private ModeloAjustes modeloAjustes;
        private boolean run=true;
        public boolean espaniol = true;
        
        public boolean tecladoTutorial;
        
        public boolean sonidoTutorial;



	@Override
        public void run() {
        BufferedImage img = null;
        frame = new JFrame("Arkanoid");
        Pelota pelota = new Pelota(0, 0, FRAME_W, FRAME_H);
        try {
            InputStream imgStream = getClass().getResourceAsStream("/imagenes/corazon.png");
            img = ImageIO.read(imgStream);
        } catch (IOException e) {
            //podemos poner una excepción, da igual
        }
        if (img != null) {
            Image dimg = img.getScaledInstance(Pelota.BW, Pelota.BH, Image.SCALE_SMOOTH);
            this.fondoVidas = dimg;
        }
        iniciarJuego();
    }

	public void setImage(String img) {
	    this.image = new ImageIcon(this.getClass().getResource(img)).getImage();
	}
        //Creamos nueva función setImage que tenga como parámetro un objeto de tipo Image para facilitar los reescalados
        public void setImage(Image img) {
            this.image = img;
        }
	
        public void setLevel(int level) {
        this.level = level;
        this.numLevels= level;
    }
        
         public void setEspaniol(boolean espaniol) {
        this.espaniol = espaniol;
    }
    
        public void setTeclado(boolean tecladoTutorial) {
            this.tecladoTutorial = tecladoTutorial;
        }
        
        public void setSonido(boolean sonidoTutorial){
            this.sonidoTutorial = sonidoTutorial;
        }
	
        public void iniciarJuego() {
        TutorialArkanoid panel = this;
       

        //JFrame frame = new JFrame("Arkanoid");
        try {
            panel.crearFicheros();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        hideMouse(frame);
        frame.setResizable(false);

        //frame.setContentPane(panel);
        frame.setSize(FRAME_W, FRAME_H);
        Dimension screenRes = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((screenRes.width - FRAME_W) / 2, (screenRes.height - FRAME_H) / 2);

        

        panel.raqueta = new Raqueta(FRAME_W / 2 - Raqueta.RACKET_W / 2, 0, FRAME_W, FRAME_H);
        panel.addMouseMotionListener(panel);
        panel.addMouseListener(panel);
        
        
        frame.addKeyListener(panel);
        
        
        frame.add(panel);
        frame.setVisible(true);

        
        try {
            playGame();
        } catch (JavaLayerException | IOException e) {
            e.printStackTrace();
        }
 
    }

	
        
	public void crearFicheros() throws IOException {
		String[] directorios = {"sonidos"};
		String[][] archivos = new String[][]{
			{"weak_ball.mp3","click.mp3","fondo1.mp3","fondo2.mp3","fondo3.mp3","fondo4.mp3","fondo5.mp3","fondo5.mp3", "fondo6.mp3", "fondo7.mp3", "fondo8.mp3", "fondo9.mp3"}
		};
		for(int x = 0; x < directorios.length; x++){
			File directorio = new File("\\UDP\\Arkanoid" + "\\"+directorios[x]);
			directorio.mkdirs();
			directorio.setWritable(true);
			for(int y = 0; y < archivos[x].length; y++){
				String archivo = directorio.getCanonicalPath() + "\\"+archivos[x][y];
		
				File temp = new File(archivo);
				InputStream is = (InputStream) this.getClass().getResourceAsStream("/"+directorios[x]+"/"+archivos[x][y]);
				FileOutputStream archivoDestino = new FileOutputStream(temp);
				byte[] buffer = new byte[512*1024];
				int nbLectura;
		
				while ((nbLectura = is.read(buffer)) != -1)
				archivoDestino.write(buffer, 0, nbLectura);
			}
		}		
	}

	@SuppressWarnings("deprecation")
	public void paint(Graphics gr) {
		// Borramos el interior de la ventana.
		BufferedImage img = null;
                Dimension d = getSize();
		panelW = d.width; 
                panelH = d.height;
                if(this.raqueta == null) {
                raqueta = new Raqueta(FRAME_W/2 - Raqueta.RACKET_W/2,0, FRAME_W, FRAME_H);
                }
		if( this.lifes<0 && !victory){
                        String mensaje="";
                    if(espaniol){
                        mensaje="<html>¡VAYA!"
                                + "<br></br>"
                                + "<br></br>Parece que no se te ha dado muy bien el juego..."
                                + "<br></br>¡No pasa nada! Puedes practicar con este tutorial las veces que haga falta"
                                + "<br></br>"
                                + "<br></br>Si te atreves, ahora que ya sabes un poco jugar, puedes ir a la opción JUGAR del menú"
                                + "<br></br>Allí podrás jugar distintos niveles por separado, o si lo prefieres, todos seguidos"
                                + "<br></br>Si tines alguna duda sobre los objetos que han ido cayendo, ve a OBJETOS DEL JUEGO"
                                + "<br></br>"
                                + "<br></br>Si quieres coger más confianza o algo no te ha quedado claro, puedes"
                                + "<br></br>volver a jugar este nivel guiado desde el principio en la opción TUTORIAL JUEGO"
                                + "<br></br>"
                                + "<br></br>¡Gracias por jugar!</html>";
                        JOptionPane.showMessageDialog(null, mensaje);

                    }else{
                       mensaje = "<html>Oops!"
                                + "<br></br>"
                                + "<br></br>It seems you didn't do very well in the game..."
                                + "<br></br>Don't worry! You can practice with this tutorial as many times as you need"
                                + "<br></br>"
                                + "<br></br>If you dare, now that you know how to play a little, you can go to the PLAY option in the menu"
                                + "<br></br>There you can play different levels separately, or if you prefer, all of them in a row"
                                + "<br></br>If you have any questions about the falling objects, go to GAME OBJECTS"
                                + "<br></br>"
                                + "<br></br>If you want to gain more confidence or something is not clear, you can"
                                + "<br></br>play this level guided from the beginning in the GAME TUTORIAL option"
                                + "<br></br>"
                                + "<br></br>Thank you for playing!</html>";
                       JOptionPane.showMessageDialog(null, mensaje);
                                            
                    }
			gr.setColor(Color.black);
			gr.fillRect(0,0,panelW,panelH);
                        //showMouse();
			this.setImage("/imagenes/Game-over-Transparent-Image.png");
			gr.drawImage(image, (panelW - image.getWidth(null))/2, 20, null);
			Font alerta = new Font("Sans Serif",Font.BOLD,30);
			int heightFinal = 20 + image.getHeight(null) + 20;
			gr.setFont(alerta);
			gr.setColor(Color.red);
			String frases = "GAME OVER...";
			heightFinal += gr.getFontMetrics().getHeight();
			gr.drawString(frases, this.getWidth()/2-gr.getFontMetrics().stringWidth(frases)/2, heightFinal);
			heightFinal = panelH - 100;
			alerta = new Font("Sans Serif",Font.BOLD,20);
                        gr.setColor(Color.white);
			gr.setFont(alerta);
                        frases = "Su puntuacion fue de "+puntuacion +" pts";
			gr.drawString(frases, this.getWidth()/2-gr.getFontMetrics().stringWidth(frases)/2, heightFinal);
			frases = "vuelva a intentarlo";
			heightFinal += gr.getFontMetrics().getHeight();
			gr.drawString(frases, this.getWidth()/2-gr.getFontMetrics().stringWidth(frases)/2, heightFinal);
			frases = "quizas a la proxima campeon";
			heightFinal += gr.getFontMetrics().getHeight();
			alerta = new Font("Sans Serif",Font.BOLD,10);
			gr.setFont(alerta);
			gr.drawString(frases, this.getWidth()/2-gr.getFontMetrics().stringWidth(frases)/2, heightFinal);
		
                        }
                else{
                        
			raqueta.setCoordY(panelH - Raqueta.RACKET_H * 5);
			if(pelotas.isEmpty()){
				pelotas.add(new Pelota(raqueta.getCoordX() + Raqueta.RACKET_W/2 - Pelota.BW/2,raqueta.getCoordY() - Raqueta.RACKET_H, FRAME_W, FRAME_H));
			}
			gr.setColor(Color.white);
			gr.fillRect(0,0,panelW,panelH);
			gr.drawImage(image, 0, 0, null);
			// Pintamos la pelota
			for(Pelota pel : pelotas){
				pel.paint(gr);
			}
			// Pintamos la raqueta
			raqueta.paint(gr);
			// Pintamos los Bloques
			if(this.ladrillos != null){
				for(Ladrillo bloq : this.ladrillos){
					bloq.paint(gr);
				}
			}
			// Pintamos los Premios
			if(this.premios != null){
				for(Premio prem : this.premios){
					prem.pinta(gr);
				}
			}
			// Pintamos fondos y textos
			gr.setColor(Color.black);
			gr.fillRect(0, raqueta.getCoordY()+Raqueta.RACKET_H, panelW, raqueta.getCoordY()+Raqueta.RACKET_H);
			gr.fillRect(0, 0, panelW, 60-Ladrillo.BlqWidth);
			for(int x = 0; x < lifes; x++){
				gr.drawImage(fondoVidas,10 + (x * (Pelota.BW + 2)),15 - (Pelota.BH / 2),null);
			}
			gr.setColor(Color.white);
			Font contador = new Font("Sans Serif",Font.BOLD,13);
			gr.setFont(contador);
			gr.drawString("Score: "+puntuacion,(panelW - (gr.getFontMetrics().stringWidth("Score: "+puntuacion))-10),(FRAME_H-panelH-10)-(gr.getFontMetrics().getHeight()/2)); //ajustada la fórmula
			gr.drawString("Nivel: "+level,10,(panelH -10));
			if(this.ladrillos != null){
				if(this.ladrillos.size()-Ladrillo.Immortales > 0){
					if(this.startTime >0){
						long playTime = System.currentTimeMillis() - this.startTime;
						Time crono = new Time(playTime);
						String tiempo = crono.getMinutes()+":"+crono.getSeconds();
						gr.drawString(tiempo, (panelW - (gr.getFontMetrics().stringWidth(tiempo))-10), (panelH -10));
					}
				
                                }else{
                                        victory = true;
					if(this.startTime > 0){
						if(this.finalTime == 0){
							this.finalTime = System.currentTimeMillis() - this.startTime;
						}
						Time crono = new Time(this.finalTime);
						String tiempo = crono.getMinutes()+":"+crono.getSeconds();
						gr.drawString(tiempo, (panelW - (gr.getFontMetrics().stringWidth(tiempo))-10), (panelH -10));
					}
					Font alerta = new Font("Sans Serif",Font.BOLD,30);
					gr.setFont(alerta);
					gr.setColor(Color.RED);
                                        if(this.level<this.numLevels){
					String completado = "Completado!";
					gr.drawString(completado, this.getWidth()/2-gr.getFontMetrics().stringWidth(completado)/2, 100);
					int nextLevelY = 110 + gr.getFontMetrics().getHeight();
					completado = "Próximo nivel";
					gr.drawString(completado, this.getWidth()/2-gr.getFontMetrics().stringWidth(completado)/2, nextLevelY);
					nextLevelY += 10 + gr.getFontMetrics().getHeight();
					completado = ""+timeNextLevel;
					gr.drawString(completado, this.getWidth()/2-gr.getFontMetrics().stringWidth(completado)/2, nextLevelY);
                                        
                                        
                                        }else{
                                            
                                            String mensaje="";
                                            if(espaniol){
                                                mensaje="<html>¡BIEN JUGADO!"
                                                    + "<br></br>"
                                                    + "<br></br>Ahora que ya sabes como jugar, ve a la opción JUGAR del menú"
                                                    + "<br></br>Allí podrás jugar distintos niveles por separado, o si lo prefieres, todos seguidos"
                                                    + "<br></br>"
                                                    + "<br></br>Si tienes alguna duda sobre los objetos que han ido cayendo, ve a OBJETOS DEL JUEGO"
                                                    + "<br></br>"
                                                    + "<br></br>Si quieres coger más confianza o algo no te ha quedado claro, puedes"
                                                    + "<br></br>volver a jugar este nivel guiado desde el principio en la opción TUTORIAL JUEGO"
                                                    + "<br></br>"
                                                    + "<br></br>¡Gracias por jugar!</html>";
                                                JOptionPane.showMessageDialog(null, mensaje);

                                            }else{
                                                mensaje="<html>WELL PLAYED!"
                                                        + "<br></br>"
                                                        +"<br></br>Now that you know how to play, go to the PLAY option in the menu"
                                                        +"<br></br>There you can play different levels separately, or if you prefer, all of them in a row"
                                                        + "<br></br>"
                                                        +"<br></br>If you have any questions about the objects that have been falling, go to GAME OBJECTS" 
                                                        + "<br></br>"
                                                        + "<br></br>If you want to gain more confidence or something is not clear, you can"
                                                        + "<br></br>play this level guided from the beginning again in the GAME TUTORIAL option"
                                                        + "<br></br>"
                                                        + "<br></br>Thank you for playing!</html>";

                                                JOptionPane.showMessageDialog(null, mensaje);
                                            }
                                            
                                            
                                            
                                            
                                            this.setImage("/imagenes/imagen_victoria.jpeg");
                                            gr.drawImage(image, (panelW - image.getWidth(null))/2, 0, null);
                                            gr.setColor(Color.white);
                                            //Font alerta = new Font("Sans Serif",Font.BOLD,30);
                                            int heightFinal = 20 + image.getHeight(null) + 40;
                                            gr.setFont(alerta);
                                            this.setImage("/imagenes/");
                                            gr.drawImage(image, (panelW - image.getWidth(null))/2, 10, null);
                                            gr.setColor(Color.green);
                                             alerta = new Font("Sans Serif",Font.BOLD,30);
                                            heightFinal = 20 + image.getHeight(null) + 20;
                                            gr.setFont(alerta);


                                            String frases = "YOU WIN!!";
                                            heightFinal += gr.getFontMetrics().getHeight();
                                            gr.drawString(frases, this.getWidth()/2-gr.getFontMetrics().stringWidth(frases)/2, heightFinal);
                                            heightFinal = panelH - 100;
                                        }
				}
			}
                        
		}
	}

	public int playGame() throws JavaLayerException, IOException {
            
		
                BufferedImage img = null;
                //con esto conseguiremos reescalar la imagen, se utiliza más código para evitar errores con "/imagenes/fondo"+this.level+".png"
                try {
                    InputStream imgStream = getClass().getResourceAsStream("../imagenes/nivel"+this.level+".png");
                    img = ImageIO.read(imgStream);
                } catch (IOException e) {
                    //podemos poner una excepción, da igual
                }
                if (img != null) {
                    Image dimg = img.getScaledInstance(FRAME_W, FRAME_H, Image.SCALE_SMOOTH);
                    setImage(dimg);
                }
		jlPlayer = new jlap("\\UDP\\Arkanoid\\sonidos\\fondo"+level+".mp3");
		this.generarBloques();
		long nextTime,currTime;
		int fpsOverflow;
		
		fpsOverflow=0;
		nextTime=System.currentTimeMillis();
                
                
                //pelotas.add(new Pelota(0,0,FRAME_W,FRAME_H));
                int contador=0;
                paint(this.getGraphics());
                
                String mensaje="";
                if(espaniol){
                    mensaje="<html>!Bienvenido al Juego Tutorial!</html>";
                    JOptionPane.showMessageDialog(null, mensaje);
                    mensaje="<html>¡ACABA CON TODOS LOS BLOQUES!"
                        + "<br></br>"
                        + "<br></br>Mueve la raqueta según el movimiento del ratón o teclas, ¡pero sin que la pelota se caiga!"
                        + "<br></br>Usa las teclas si estás en modo teclado, y el ratón si te encuentras en modo ratón"
                        + "<br></br>No pasa nada si no llegas a por ella, aunque no te quedes sin vidas o perderás."
                        + "<br></br>Las vidas se ven arriba a la izquierda."
                        + "<br></br>El nivel se indica abajo a la izquierda."
                        + "<br></br>La puntuación se irá sumando arriba a la derecha."
                        + "<br></br>El tiempo que ha pasado se irá actualizando abajo a la derecha."
                        + "<br></br>Los bloques tendrán dureza distinta según el color."
                        + "<br></br>Cuando rompas un bloque a veces caerán objetos de ellos."
                        + "<br></br>¡Coge aquellos que te beneficien!"
                        + "<br></br>Para más información visita la opción OBJETOS DEL JUEGO"
                        + "<br></br>"
                        
                        + "<br></br>Para empezar a jugar dale a ACEPTAR y pulsa click en el ratón o barra de espaciado si estás en modo teclado."
                        + "<br>Entonces, la bola comenzará a moverse y empezará el juego</br>"
                        + "<br>Si necesitas tomarte una pausa, pulsa ESC[] y se parará el juego</br>"
                        + "<br></br>"
                        + "<br></br>¡Buena Suerte!</html>";
                    JOptionPane.showMessageDialog(null, mensaje);
                    
                }else{
                    String mensajeIngles="<html>Welcome to the Game Tutorial!</html>";
                    JOptionPane.showMessageDialog(null, mensajeIngles);
                    mensajeIngles="<html>DESTROY ALL THE BLOCKS!"
                        + "<br></br>"
                        + "<br></br>Move the paddle with the mouse or arrow keys, but don't let the ball fall!"
                        +"<br></br>Use the keys if you're in keyboard mode, and the mouse if you're in mouse mode."
                        + "<br></br>It's okay if you miss the ball, just make sure you don't run out of lives or you'll lose."
                        + "<br></br>The lives are displayed at the top left."
                        + "<br></br>The level is indicated at the bottom left."
                        + "<br></br>The score will be incrementing at the top right."
                        + "<br></br>The elapsed time will be updated at the bottom right."
                        + "<br></br>The blocks have different levels of durability based on their color."
                        + "<br></br>Occasionally, breaking a block will release power-up items."
                        + "<br></br>Grab the power-ups that benefit you!"
                        + "<br></br>For more information, visit the GAME OBJECTS option."
                        + "<br></br>"
                        +"<br></br>To start playing, click ACCEPT and press the mouse button or the spacebar if you're in keyboard mode."
                        + "<br>The ball will start moving, and the game will begin.</br>"
                        + "<br>If you need to take a break, press ESC[] and the game will pause.</br>"
                        + "<br></br>"
                        + "<br></br>Good luck!</html>";
                
                    JOptionPane.showMessageDialog(null, mensajeIngles);
                }
                
                
		while (true) {      
                  if (!isGamePaused) {  
                    if (leftKeyPressed) {
                        raqueta.moverIzda();
                    }
                    if (rightKeyPressed) {
                        raqueta.moverDcha();
                    }
                    
                    
			// Espera de un tiempo fijo
			currTime = System.currentTimeMillis();
			if (currTime<nextTime)
				try { Thread.sleep(nextTime-currTime); }
				catch (Exception e) {}
			else fpsOverflow++;
			nextTime=currTime+WAIT_TIME;
			// Actualizaci�n de las coordenadas e incrementos de la pelota
			int cont = 0;
			for (Pelota pel : pelotas){
				pel.move(panelW, panelH);
                                
                                double[] centroPelota = pel.calcularCentroPelota();
                                double[] centroRaqueta = raqueta.calcularCentroRaqueta();

                                // Calcular la distancia entre los centros
                                double distancia = calcularDistancia(centroPelota, centroRaqueta);

                                boolean d;
                                if (distancia < 30){
                                    d = true;
                                } else {
                                    d = false;
                                }
                                
                                
                                if (!pel.isDown() && d){
                                    if (leftKeyPressed) {
                                    pel.moverIzda();
                                    }
                                    if (rightKeyPressed) {
                                        pel.moverDcha();
                                    }
                                }
				if(pel.isDown()){
					if(pel.getCoordY() > (raqueta.getCoordY()+Raqueta.RACKET_H)){
						pelotas.remove(cont);
						if(pelotas.isEmpty()){
							this.lifes --;
						}
						if(lifes >= 0 && pelotas.isEmpty()){
							//pelotas.add(new Pelota(raqueta.getCoordX() + Raqueta.RACKET_W/2 - Pelota.BW/2,raqueta.getCoordY() - Raqueta.RACKET_H, FRAME_W, FRAME_H));
						}
						break;
					}
					if (((pel.getCoordX()+Pelota.BW) >= raqueta.getCoordX())&&(pel.getCoordX() <= raqueta.getCoordX()+Raqueta.RACKET_W)&&((pel.getCoordY()+Pelota.BH) >= raqueta.getCoordY())&&(pel.getCoordY() < (raqueta.getCoordY()+Raqueta.RACKET_H))){
						pel.setDown(false);
						pel.rebota(raqueta.getCoordX(), raqueta.getCoordY(), Raqueta.RACKET_W, Raqueta.RACKET_H, null);
					}
				}
				int contBloq = 0;
				for (Ladrillo bloq : ladrillos){
					if(bloq.destruido(pel)){
						if(bloq.getLifes() == 0){
							switch (bloq.getPremio()) {
								case 1:
									premios.add(new Expansor(bloq.getCoordX() + Ladrillo.BlqWidth/2 - Expansor.PW/2, bloq.getCoordY()));
									break;
								case 2:
									premios.add(new MasPelotas(bloq.getCoordX() + Ladrillo.BlqWidth/2 - MasPelotas.BW/2, bloq.getCoordY()));
									break;
								case 3:
									premios.add(new Reductor(bloq.getCoordX() + Ladrillo.BlqWidth/2 - MasPelotas.BW/2, bloq.getCoordY()));
									break;
								case 4:
									premios.add(new Acelerador(bloq.getCoordX() + Ladrillo.BlqWidth/2 - MasPelotas.BW/2, bloq.getCoordY()));
									break;
								case 5:
									premios.add(new Freno(bloq.getCoordX() + Ladrillo.BlqWidth/2 - MasPelotas.BW/2, bloq.getCoordY()));
									break;
								default:
									break;
							}
							ladrillos.remove(contBloq);
						}
						break;
					}
					contBloq ++;
				}
				cont ++;
			}
			cont = 0;
			if(!ladrillos.isEmpty()){
				for(Premio prem : premios){
					prem.move();
					if(prem.recibido(raqueta)){
						if (prem instanceof Expansor) {
							raqueta.ampliar();
						}else if(prem instanceof MasPelotas){
							this.pelotas.add(new Pelota(raqueta.getCoordX() + Raqueta.RACKET_W/2 - Pelota.BW/2,raqueta.getCoordY() - Raqueta.RACKET_H, FRAME_W, FRAME_H));
						}else if(prem instanceof Reductor){
							raqueta.reduir();
						}else if(prem instanceof Acelerador){
							for(Pelota pelota : pelotas){
								pelota.acelera();
							}
						}else if(prem instanceof Freno){
							for(Pelota pelota : pelotas){
								pelota.frena();
							}
						}
						premios.remove(cont);
						break;
					}else if(prem.getPosY() + prem.getWidth() >= raqueta.getCoordY() + Raqueta.RACKET_H){
						premios.remove(cont);
						break;
					}
					cont++;
				}
			}
			// Repintado de la ventana para actualizar su contenido
                        if (pelotas.size()==0){
                            contador++;
                            System.out.println(contador);
                        }
                        //paint(this.getGraphics());
			repaint();
			if(ladrillos.size()-Ladrillo.Immortales == 0 || this.lifes < 0){
				timeLevels.add(new Time(System.currentTimeMillis() - this.startTime));
				this.startTime = 0;
                                
				break;
			}
                  }
                  else{
                      isGamePaused = true;
        // Mostrar el diálogo de pausa y opciones
        int choice = JOptionPane.showOptionDialog(this,
                "Juego pausado. ¿Deseas continuar jugando?",
                "Pausa",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Continuar", "Salir"},
                "Continuar");
        

        if (choice == JOptionPane.YES_OPTION) {
            // El usuario eligió continuar jugando
            isGamePaused = false;
            if (!pauseX.isEmpty()){
			int cont = 0;
			for (@SuppressWarnings("unused") Object pausX : pauseX){
				pelotas.get(cont).setMovX(Float.parseFloat(pauseX.get(cont).toString()));
				pelotas.get(cont).setMovY(Float.parseFloat(pauseY.get(cont).toString()));
				cont ++;
			}
		}
        } else {
            // El usuario eligió salir del juego
            frame.dispose();
            return 0;

        }
    }
                  
		}
		premios.clear();
		Raqueta.RACKET_W = 50;
		raqueta = new Raqueta(FRAME_W/2 - Raqueta.RACKET_W/2,0, FRAME_W, FRAME_H);
		jlPlayer.player.close();
		if(this.level < this.numLevels && this.lifes >= 0){
			jlap.iniciar = false;
			for(int y = 0; y < 5; y++){
				timeNextLevel = 5-y;
				repaint();
				try { Thread.sleep(1000); }
				catch (Exception e) {}
			}
			this.pelotas.clear();
			this.pelotas.add(new Pelota(raqueta.getCoordX() + Raqueta.RACKET_W/2 - Pelota.BW/2,raqueta.getCoordY() - Raqueta.RACKET_H, FRAME_W, FRAME_H));
			this.level += 1;
			this.ladrillos = null;
			this.playGame();
		}else if(this.level == this.numLevels){
			//this.level++;
			repaint();
		}
                return 0;
	}
        

        @Override
        public void mouseDragged(MouseEvent evt){
	}

        @Override
	public void mouseMoved(MouseEvent evt){
		if(!tecladoTutorial){
                    raqueta.setCoordX(evt.getX() - Raqueta.RACKET_W/2);
                
		if (raqueta.getCoordX()<0) raqueta.setCoordX(0);
		if (raqueta.getCoordX()+Raqueta.RACKET_W>=panelW) raqueta.setCoordX(panelW-Raqueta.RACKET_W);
		int cont = 0;
		for (Pelota pel : pelotas){
			if(pel.getMovX() == 0 && pel.getMovY() == 0){
				pel.setCoordX(evt.getX() - Pelota.BW/2);
				if (raqueta.getCoordX()<=0) pel.setCoordX(Raqueta.RACKET_W/2 - Pelota.BW/2);
				if (raqueta.getCoordX()+Raqueta.RACKET_W>=panelW) pel.setCoordX(panelW - Raqueta.RACKET_W/2 - Pelota.BW/2);
			}
			else if(pel.isDown()){
				if(pel.getCoordY() > (raqueta.getCoordY()+Raqueta.RACKET_H)){
					pelotas.remove(cont);
					if(pelotas.isEmpty()){
						this.lifes --;
					}
					if(lifes >= 0 && pelotas.isEmpty()){
						pelotas.add(new Pelota(raqueta.getCoordX() + Raqueta.RACKET_W/2 - Pelota.BW/2,raqueta.getCoordY() - Raqueta.RACKET_H, FRAME_W, FRAME_H));
					}
					break;
				}
				if (((pel.getCoordX()+Pelota.BW) >= raqueta.getCoordX())&&(pel.getCoordX() <= raqueta.getCoordX()+Raqueta.RACKET_W)&&((pel.getCoordY()+Pelota.BH) >= raqueta.getCoordY())&&(pel.getCoordY() < (raqueta.getCoordY()+Raqueta.RACKET_H))){
					pel.setDown(false);
					try {
						pel.rebota(raqueta.getCoordX(), raqueta.getCoordY(), Raqueta.RACKET_W, Raqueta.RACKET_H, null);
					} catch (JavaLayerException | IOException e) {
						
					}
				}
			}
			cont ++;
		}
            }
        }
        
        
         private static double calcularDistancia(double[] punto1, double[] punto2) {
            double deltaX = punto2[0] - punto1[0];
            double deltaY = punto2[1] - punto1[1];
            return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        }
        

        
	@Override
	public void mouseClicked(MouseEvent arg0) {
            if (!tecladoTutorial){
		for (Pelota pel : pelotas){
			if(pel.getMovX() == 0 && pel.getMovY() == 0){
				pel.setMovX(0.0);
				pel.setMovY(-3.0);
			}
		}
            
		if(this.startTime == 0){
			this.startTime = System.currentTimeMillis();
		}
            }
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		if (!pauseX.isEmpty()){
			int cont = 0;
			for (@SuppressWarnings("unused") Object pausX : pauseX){
				pelotas.get(cont).setMovX(Float.parseFloat(pauseX.get(cont).toString()));
				pelotas.get(cont).setMovY(Float.parseFloat(pauseY.get(cont).toString()));
				cont ++;
			}
		}
	}   

	@Override
	public void mouseExited(MouseEvent arg0) {
		pauseX.removeAll(pauseX);
		pauseY.removeAll(pauseY);
		for (Pelota pelota : pelotas){
			pauseX.add(pelota.getMovX());
			pauseY.add(pelota.getMovY());
			pelota.setMovX(0);
			pelota.setMovY(0);
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
	
	private static void hideMouse(JFrame frame) {
		BufferedImage emptyImg = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		emptyImg.setRGB(0, 0, 0xFFFFFF);
		Cursor myCursor = Toolkit.getDefaultToolkit().
		createCustomCursor(emptyImg, new Point(0, 0), "invisible");
		frame.setCursor(myCursor);
	}
        // Función para mostrar el cursor
       /* public void showMouse() {
            Cursor defaultCursor = Cursor.getDefaultCursor();
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            toolkit.getSystemEventQueue().getMostRecentEventTime();  // Solución de tiempo de ejecución para actualizar el cursor
            toolkit.getSystemEventQueue().postEvent(new CursorEvent(defaultCursor, toolkit.getSystemEventQueue().getMostRecentEventTime(), 0, 0, 0, null, 0));
        }*/
        
          
        @Override
        public void keyReleased(KeyEvent e) {
            // Cuando se suelten las teclas de direccion derecha e izda
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                leftKeyPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                rightKeyPressed = false;
            }            
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }
   
        // Movimiento raqueta con el teclado
        @Override
        public void keyPressed(KeyEvent e){
            if (raqueta.getCoordX()<0) raqueta.setCoordX(0);
	    if (raqueta.getCoordX()+Raqueta.RACKET_W>=panelW) raqueta.setCoordX(panelW-Raqueta.RACKET_W);
                
                
            if (e.getKeyCode() == KeyEvent.VK_RIGHT && tecladoTutorial){
                rightKeyPressed = true;                
                }
                
            if (e.getKeyCode() == KeyEvent.VK_LEFT && tecladoTutorial){
                leftKeyPressed = true;                                
            };
            int keyCode = e.getKeyCode();
            if (keyCode == KeyEvent.VK_ESCAPE) {
                pauseX.removeAll(pauseX);
                pauseY.removeAll(pauseY);
                isGamePaused = true;
            }
            
            if (e.getKeyCode() == KeyEvent.VK_SPACE) { //para que la pelota se suelte con la tecla espacio
                if (tecladoTutorial){
		for (Pelota pel : pelotas){
			if(pel.getMovX() == 0 && pel.getMovY() == 0){
				pel.setMovX(0.0);
				pel.setMovY(-3.0);
			}
		}
            
		if(this.startTime == 0){
			this.startTime = System.currentTimeMillis();
		}
            }
        }
    }
              
        
        
	
	private void generarBloques(){
		int constBloques[][] = null;
		switch (level) {
		case 1:
			constBloques = new int[][]{
                                {0,1,0,1,-1,1,0,1,0},
                                {1,0,2,0,3,0,2,0,1},
                                
			};
			break;
		default:
			break;
		}
		if(constBloques != null){
			this.ladrillos = new ArrayList<Ladrillo>();
			Ladrillo.Immortales = 0;
                        Ladrillo ladrillo= new Ladrillo(0,Ladrillo.posYinicial, 1 , FRAME_W, FRAME_H); //Inicializamos un ladrillo para que se calcule BlqHeight y BlqWidth
			for(int x = 0; x < constBloques.length; x++){
				for(int y = 0; y < constBloques[x].length; y++){
      					if(constBloques[x][y] != 0){
                                            this.ladrillos.add(new Ladrillo((Ladrillo.BlqWidth * y),(Ladrillo.BlqHeight * x)+Ladrillo.posYinicial, constBloques[x][y], FRAME_W, FRAME_H));
					}
					if(constBloques[x][y] < 0){
						Ladrillo.Immortales ++;
					}
				}
			}
			random = new Random();
			randomPremios = new Random();
			int number = 0;
			int premio = 0;
			for(int x = 0; x < ladrillos.size()/3; x++){
				premio = Math.abs( randomPremios.nextInt() % 5) + 1;
				number = Math.abs( random.nextInt() % ladrillos.size());
				ladrillos.get(number).setPremio(premio);
			}
		}
        }
}