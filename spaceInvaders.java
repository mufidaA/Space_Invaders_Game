import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import Stage.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class spaceInvaders extends Canvas {
    private BufferedImage playerGrafic;
    private BufferedImage aliensGrafic;
    private BufferedImage bulletGrafic;
    private Stage gameStage;
    Boolean gameRunning = false;

    public spaceInvaders() {
        gameStage = new Stage(1022, 1022);
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                paint(getGraphics());
                launchTimer();
                gameRunning = true;
            }
        });
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_LEFT) {
                    gameStage.movePlayer(-1);
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    gameStage.movePlayer(1);
                } else {
                    JOptionPane.showMessageDialog(null, "InfoBox: " +
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    public void launchTimer() {
        int delay = 1000; // delay for 1 sec.
        int period = 500; // repeat every sec.
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                gameStage.AnimateAliens(1);
                gameStage.UpdateBullet();
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

    public void loadGrafics() {
        try {
            playerGrafic = ImageIO.read(new File("images/player1.png"));
            aliensGrafic = ImageIO.read(new File("images/alien.png"));
            bulletGrafic = ImageIO.read(new File("images/shot.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.Canvas#paint(java.awt.Graphics)
     */
    public void paint(Graphics g) {
        g.fillRect(0, 0, gameStage.Width(), gameStage.Height());
        g.setColor(Color.BLACK);
        
        if(!gameRunning)
            g.drawString("Click to Start Space Invaders", 500, 500);
        else{
            g.drawImage(playerGrafic, gameStage.getPlayer().getX(), gameStage.getPlayer().getY(), this);
            for (int i = 0; i < gameStage.getAlienCount(); i++) {
                g.drawImage(aliensGrafic, gameStage.getAlien(i).getX(), gameStage.getAlien(i).getY(), this);
            }
            List<Bullet> bl = gameStage.getBullet();
            for (int i = 0; i < bl.size(); i++){
                Bullet bul = bl.get(i);
                g.drawImage(bulletGrafic, bul.getX(),  bul.getY(), this);
            }
        }
       
    }
}
