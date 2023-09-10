package arkanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Raqueta {

    
	//--------------- Valores públicos ---------------
	
	/** La anchura de la raqueta predeterminada es 50*/
	public static int RACKET_W=50;
	
	/** La altura de la raqueta predeterminada es 15 */
	public static int RACKET_H=15;
        
        /** la distancia que se desplaza cada vez que se pulsan las flechas dcha e izda del teclado */
        public static int DESPL = 5;
	
	//--------------- Atributos ---------------
	
	/** Coordenada X de la raqueta */
	private int racketX;
        

	/** Coordenada Y de la raqueta */
	private int racketY;
	
	private Image fondo_der = null;
	private Image fondo_cen = null;
	private Image fondo_izq = null;
	
	public Raqueta(int coordX, int coordY, int anchoVentana, int altoVentana){
                Raqueta.RACKET_W= 20+30*anchoVentana/277;//lo predeterminado es 20+30*anchoventana/277
                Raqueta.RACKET_H=10+5*altoVentana/450;
		this.racketX = coordX;
		this.racketY = coordY;
		this.fondo_der = new ImageIcon(this.getClass().getResource("/imagenes/raq_r.png")).getImage();
		this.fondo_cen = new ImageIcon(this.getClass().getResource("/imagenes/raq_c.png")).getImage();
		this.fondo_izq = new ImageIcon(this.getClass().getResource("/imagenes/raq_l.png")).getImage();
	}
	
	public int getCoordX(){
		return this.racketX;
	}
	
	public int getCoordY(){
		return this.racketY;
	}
	
	public void setCoordX(int coordX){
		this.racketX = coordX;
	}
        
                
        public double getDesplazamiento(){
        return this.DESPL;
        }

	public void moverDcha(){
            this.racketX = this.racketX+this.DESPL;
        }
        
        public void moverIzda(){
            this.racketX = this.racketX-this.DESPL;
        }
        
        public double[] calcularCentroRaqueta() {
            double centroX = racketX + (RACKET_W / 2);
            double centroY = racketY + (RACKET_H / 2);
            return new double[]{centroX, centroY};
        }

               
	public void setCoordY(int coordY){
		this.racketY = coordY;
	}
	
	public void ampliar(){
		RACKET_W += 20;
		if(RACKET_W >= 200){
			RACKET_W = 200;
		}
	}
	
	public void reduir(){
		RACKET_W -= 20;
		if(RACKET_W <= 14){
			RACKET_W = 14;
		}
	}
	
	public void paint(Graphics gr){
		gr.setColor(Color.blue);
		int tamanyLaterals = Raqueta.RACKET_W - fondo_izq.getWidth(null) - fondo_der.getWidth(null);
		gr.drawImage(fondo_izq, this.getCoordX(), this.getCoordY(), null);
		for(int x = 0; x < tamanyLaterals; x++){
			gr.drawImage(fondo_cen, this.getCoordX()+fondo_izq.getWidth(null)+x, this.getCoordY(), null);
		}
		gr.drawImage(fondo_der, this.getCoordX()+Raqueta.RACKET_W - fondo_der.getWidth(null), this.getCoordY(), null);
	}
}
