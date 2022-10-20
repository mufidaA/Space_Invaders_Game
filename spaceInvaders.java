import java.awt.Canvas;  
import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import Stage.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class spaceInvaders extends Canvas {
    private BufferedImage  playerGrafic;
    private BufferedImage  aliensGrafic;
    private Stage gameStage;
    public spaceInvaders() {
        gameStage = new Stage(1022, 1022);
        addMouseListener(new MouseAdapter() { 
            public void mousePressed(MouseEvent me) { 
            gameStage.setUp();
            paint(getGraphics());
            launchTimer();
            } 
           }); 
        }
    public void launchTimer () {
    int delay = 1000; // delay for 1 sec.
    int period = 500; // repeat every sec.
    Timer timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask() {
        public void run() {
            gameStage.AnimateAliens(1);
            paint(getGraphics());

        }
  
    }, delay, period);
    }
   public static void main(String[] args) {
        JFrame frame = new JFrame("Space Invaders");
        Canvas canvas = new spaceInvaders();
        canvas.setSize(1022, 900);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    
        ((spaceInvaders) canvas).loadGrafics();
    }
 
    public void loadGrafics () {
        try {
            playerGrafic = ImageIO.read(new File("images/download.png"));
            aliensGrafic = ImageIO.read(new File("images/alien.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /* (non-Javadoc)
     * @see java.awt.Canvas#paint(java.awt.Graphics)
     */
    public void paint(Graphics g) {
        g.fillRect(0, 0, 1020, 1020);
        g.drawString("Click to Start Space Invaders", 500, 500);
        g.drawImage( playerGrafic, 610, 610, this);
        for (int i = 0; i < gameStage.getAlienCount(); i++) {
            g.drawImage( aliensGrafic, gameStage.getAlien(i).getX(), gameStage.getAlien(i).getY(), this);
        }
    }   
}
