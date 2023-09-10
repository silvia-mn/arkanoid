package premios;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.ImageIcon;

public class Reductor extends Premio{
	
	public static final int PW = 20;
	
	public static final int PH = 10;
	
	public Reductor(int posx, int posy){
		super(posx,posy,PW,PH);
                redimensionarImagen();
		
                //setFondo(new ImageIcon(this.getClass().getResource("/imagenes/reducir.png")).getImage());
	}

        public void redimensionarImagen() {
            // Cargar la imagen original
            Image imagenOriginal = new ImageIcon(this.getClass().getResource("/imagenes/reducir.png")).getImage();
            // Dimensiones deseadas para la imagen redimensionada
            int nuevoAncho = 40;
            int nuevoAlto = 20;
            // Crear una imagen vacía con las dimensiones deseadas
            BufferedImage imagenRedimensionada = new BufferedImage(nuevoAncho, nuevoAlto, BufferedImage.TYPE_INT_RGB);
            // Redimensionar la imagen original a la nueva imagen
            Graphics2D g = imagenRedimensionada.createGraphics();
            g.drawImage(imagenOriginal, 0, 0, nuevoAncho, nuevoAlto, null);
            g.dispose();
            // Establecer la imagen redimensionada como fondo del premio
            setFondo(imagenRedimensionada);
            
        }
}
