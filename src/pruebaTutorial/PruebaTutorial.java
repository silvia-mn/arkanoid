//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;

//public class PruebaTutorial extends JFrame {
    // Variables de juego
  /*  private int raquetaX;
    private int pelotaX;
    private int pelotaY;
    private int pelotaDirX;
    private int pelotaDirY;
    private boolean jugando;
    private JPanel gamePanel;
    private JLabel tutorialLabel;
    private JButton startButton;
    private GameThread gameThread;
    
    
    public PruebaTutorial(){
    gamePanel = new JPanel();
        gamePanel.setPreferredSize(new Dimension(800, 600)); // Establecer el tamaño deseado del panel de juego
        gamePanel.setBackground(Color.BLACK);
        add(gamePanel, BorderLayout.CENTER);
        
        setTitle("Arkanoid Tutorial");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Crear el JLabel para mostrar el tutorial
        tutorialLabel = new JLabel("<html><h1>¡Bienvenido al juego Arkanoid!</h1><p>En este juego, debes controlar la raqueta para rebotar la pelota y romper los bloques.</p><p>Utiliza las flechas izquierda y derecha para mover la raqueta.</p><p>¡Diviértete!</p></html>");
        tutorialLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tutorialLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(tutorialLabel);

        // Crear el botón para iniciar el juego
        startButton = new JButton("Iniciar Juego");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
        add(startButton);

        setVisible(true);
    }
    
    private void startGame() {
        // Ocultar el tutorial y mostrar el juego
        tutorialLabel.setVisible(false);
        startButton.setVisible(false);

        // Crear el panel de juego y añadirlo al JFrame
        JPanel gamePanel = new JPanel();
        gamePanel.setPreferredSize(new Dimension(400, 300));
        add(gamePanel);

        // Crear e iniciar el hilo del juego
        gameThread = new GameThread(gamePanel);
        gameThread.start();
    }

    // Resto del código...

    private class GameThread extends Thread {
        // Resto del código...
        private JPanel gamePanel;

        public GameThread(JPanel gamePanel) {
            this.gamePanel = gamePanel;
        }

        public void run() {
            // Inicializar variables de juego
            raquetaX = gamePanel.getWidth() / 2 - Raqueta.RACKET_W / 2;
            pelotaX = gamePanel.getWidth() / 2;
            pelotaY = gamePanel.getHeight() / 2;
            pelotaDirX = 1;
            pelotaDirY = -1;
            jugando = true;

            while (jugando) {
                // Actualizar la posición de la raqueta y la pelota
                updateRaqueta();
                updatePelota();

                // Colisiones con los límites de la pantalla
                if (pelotaX <= 0 || pelotaX >= gamePanel.getWidth() - 1 - Pelota.DIAMETRO) {
                    pelotaDirX *= -1;
                }
                if (pelotaY <= 0) {
                    pelotaDirY *= -1;
                }

                // Colisión con la raqueta
                if (pelotaY >= gamePanel.getHeight() - Raqueta.RACKET_H - Pelota.DIAMETRO &&
                        pelotaX + Pelota.DIAMETRO >= raquetaX &&
                        pelotaX <= raquetaX + Raqueta.RACKET_W) {
                    pelotaDirY *= -1;
                }

                // Colisiones con los bloques (implementar lógica de colisión con los bloques)

                // Dibujar la raqueta y la pelota
                drawGame();

                // Esperar un tiempo para controlar la velocidad del juego
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
       
            }
        }

        private void updateRaqueta() {
            // Actualizar la posición de la raqueta según la entrada del usuario (teclado o ratón)
            // Implementar lógica de movimiento de la raqueta
        }

        private void updatePelota() {
            // Actualizar la posición de la pelota
            pelotaX += pelotaDirX;
            pelotaY += pelotaDirY;
        }

        private void drawGame() {
            // Obtener el contexto gráfico
            Graphics2D g = (Graphics2D) gamePanel.getGraphics();

            // Dibujar el fondo del juego
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, gamePanel.getWidth(), gamePanel.getHeight());
/*
            // Dibujar la raqueta
            g.setColor(Color.WHITE);
            g.fillRect(raquetaX, gamePanel.getHeight() - Raqueta.RACKET_H, Raqueta.RACKET_W, Raqueta.RACKET_H);

            // Dibujar la pelota
            g.setColor(Color.WHITE);
            g.fillOval(pelotaX, pelotaY, Pelota.DIAMETRO, Pelota.DIAMETRO);

            // Dibujar los bloques (implementar lógica para dibujar los bloques)

            // Liberar el contexto gráfico
            g.dispose();
        }

        // Resto del código...
    }

    /*public static void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new PruebaTutorial();
                }
            });
        }
}
*/
