package arkanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import javax.swing.ImageIcon;

import javazoom.jl.decoder.JavaLayerException;

import reproductor.jlap;

public class Pelota {

//--------------- Valores constantes ---------------

/** La anchura de la pelota */
public static int BW=15;

/** La altura de la pelota */
public static int BH=15;

//--------------- Atributos ---------------

/** Coordenada X de la pelota */
private double bx;

/** Coordenada Y de la pelota */
private double by;

/** Incremento en X de la pelota, tras cada cambio de posici�n */
private double bdx;

/** Incremento en Y de la pelota, tras cada cambio de posici�n */
private double bdy;

private boolean baixant = false;

private Image fondo = null;

private jlap mediaRebote = null;

private double defaultBdx = 2;

/** la distancia que se desplaza cada vez que se pulsan las flechas dcha e izda del teclado */
public static int DESPL = 5;

public boolean isStuck=true; // para ver si la pelota está pegada a la raqueta

public Pelota(double posX, double posY, int anchoVentana, int altoVentana){
    Pelota.BW = 10 + anchoVentana*5/300;
                    Pelota.BH = Pelota.BW;
                    this.bx = posX;
    this.by = posY;
    this.bdx = 0;
    this.bdy = 0;
    //this.fondo = new ImageIcon(this.getClass().getResource("/imagenes/bola_trans.png")).getImage();
    BufferedImage img = null;
                    //con esto conseguiremos reescalar la imagen, se utiliza más código para evitar errores con "/imagenes/fondo"+this.level+".png"
                    try {
                        InputStream imgStream = getClass().getResourceAsStream("/imagenes/bola_trans.png");
                        img = ImageIO.read(imgStream);
                    } catch (IOException e) {
                        //podemos poner una excepción, da igual
                    }
                    if (img != null) {
                        Image dimg = img.getScaledInstance(this.BW, this.BH, Image.SCALE_SMOOTH);
                        this.fondo = dimg;
                    }
    try {
        mediaRebote = new jlap("\\UDP\\Arkanoid\\sonidos\\click.mp3");
    } catch (JavaLayerException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
}
}

public double getCoordX(){
return this.bx;
}

public void setCoordX(double posX){
this.bx = posX;
}

public double getCoordY(){
return this.by;
}

public double getMovX(){
return this.bdx;
}

public double getMovY(){
return this.bdy;
}

public void setMovX(double movX){
this.bdx = movX;
}

public void setMovY(double movY){
this.bdy = movY;
}

public boolean isDown(){
return this.baixant;
}

public void setDown(boolean down){
this.baixant = down;
}

public void moverDcha(){
    this.bx = this.bx+this.DESPL;
    }
public void moverIzda(){
    this.bx = this.bx-this.DESPL;
    }

public double[] calcularCentroPelota() {
    double centroX = bx + (BW / 2);
    double centroY = by + (BH / 2);
    return new double[]{centroX, centroY};
}


public boolean getIsStuck(){
    return this.isStuck;
}

public void acelera(){
this.setMovX(this.getMovX()*1.5);
this.setMovY(this.getMovY()*1.5);
this.defaultBdx = defaultBdx * 1.5;
}

public void frena(){
if(this.getMovY() > 1){
this.setMovX(this.getMovX()/1.5);
this.setMovY(this.getMovY()/1.5);
this.defaultBdx = defaultBdx / 1.5;
}
}

public void move(int width, int height) throws JavaLayerException, IOException{
    this.bx += this.bdx;
    this.by += this.bdy;
    if ((this.bx+BW>width && this.bdx > 0)||(this.bx<0 && this.bdx < 0)){
    this.bdx=-this.bdx;
    mediaRebote.play();
    }
    if (this.by+BH>=height || this.by<=30){
    this.bdy=-this.bdy;
    mediaRebote.play();
    }
    if (this.by<=30){
    this.setDown(true);
    mediaRebote.play();
    }
}


public void paint(Graphics gr){
gr.setColor(Color.red);
gr.fillOval((int)this.getCoordX(),(int)this.getCoordY(),BW-1,BH-1);
gr.drawImage(fondo,(int)this.getCoordX(),(int)this.getCoordY(),null);
}

public boolean rebota(int posX, int posY , int width, int height, Ladrillo ladrillo) throws JavaLayerException, IOException{
    if(ladrillo == null){
    if((this.getCoordX() + Pelota.BW/2 >= posX + 2*width/5)&&(this.getCoordX() + Pelota.BW/2 <= posX + 3*width/5)){
    if(this.getMovX() > 0){
    this.setMovX(defaultBdx/4);
    }else{
    this.setMovX(-defaultBdx/4);
    }
    }else if((this.getCoordX() + Pelota.BW/2 >= posX + width/5)&&(this.getCoordX() + Pelota.BW/2 < posX + 2*width/5)){
    this.setMovX(-defaultBdx);
    }else if((this.getCoordX() + Pelota.BW/2 > posX + 3*width/5)&&(this.getCoordX() + Pelota.BW/2 <= posX + 4*width/5)){
    this.setMovX(defaultBdx);
    }else if((this.getCoordX() + Pelota.BW/2 >= posX)&&(this.getCoordX() + Pelota.BW/2 < posX + width/5)){
    this.setMovX(-defaultBdx*1.5);
    }else if((this.getCoordX() + Pelota.BW/2 > posX + 4*width/5)&&(this.getCoordX() + Pelota.BW/2 <= posX + width)){
    this.setMovX(defaultBdx*1.5);
    }else if(this.getCoordX() + Pelota.BW/2 < posX){
    this.setMovX(-defaultBdx*2);
    }else{
    this.setMovX(defaultBdx*2);
    }
    this.setMovY(-this.getMovY());
    mediaRebote.play();
    return true;
    }else{

                            boolean rebote = false;
    if((this.getCoordX()+Pelota.BW > posX-3)&&(this.getCoordX()+Pelota.BW <= posX+3)&&(this.getMovX()>0)){
    rebote = true;
    }else if((this.getCoordX() >= posX+Ladrillo.BlqWidth-3)&&(this.getCoordX() < posX+Ladrillo.BlqWidth+3)&&(this.getMovX()<0)){
    rebote = true;
    }
    if((this.getCoordY() >= posY-3*Pelota.BH/4)&&(this.getCoordY() <= posY+Ladrillo.BlqHeight-Pelota.BH/4)){
    if(rebote){
    this.setMovX(-this.getMovX());
    if(ladrillo.getLifes() > 0){
    ladrillo.setLifes(ladrillo.getLifes()-1);
    }
    if(ladrillo.getLifes() == 0){
    Juego.puntuacion += ladrillo.getPoints();
    }
    return true;
    }
    }else if(((this.getCoordX()+Pelota.BW) >= posX)&&(this.getCoordX() <= posX+Ladrillo.BlqWidth)){
    if(this.isDown()){
    if (((this.getCoordY()+Pelota.BH) >= posY)&&(this.getCoordY() < (posY+Ladrillo.BlqHeight))){
    this.setDown(false);
    this.setMovY(-this.getMovY());
    if(ladrillo.getLifes() > 0){
    ladrillo.setLifes(ladrillo.getLifes()-1);
    }
    if(ladrillo.getLifes() == 0){
    Juego.puntuacion += ladrillo.getPoints();
    }
    return true;
    }
    }else{
    if ((this.getCoordY() <= (posY+Ladrillo.BlqHeight))&&((this.getCoordY()+Pelota.BH) > posY)){
    this.setDown(true);
    this.setMovY(-this.getMovY());
    if(ladrillo.getLifes() > 0){
    ladrillo.setLifes(ladrillo.getLifes()-1);
    }
    if(ladrillo.getLifes() == 0){
    Juego.puntuacion += ladrillo.getPoints();
    }
    return true;
    }
    }
    }
    return false;
    }
}
}