import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import Stage.*;

public class spaceInvaders extends Canvas {
    private BufferedImage  playerGrafic;
    private BufferedImage  aliensGrafic;
    private Stage gameStage;
    public spaceInvaders() {
        gameStage = new Stage(1022, 1022);
        gameStage.setUp ();
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
      // g.fillRect(50, 50, 500, 400);
        g.drawImage( playerGrafic, 610, 610, this);
        for (int i = 0; i < gameStage.getAlienCount(); i++) {
            g.drawImage( aliensGrafic, gameStage.getAlien(i).getX(), gameStage.getAlien(i).getY(), this);
        }
    }   
}
