package Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Stage {
    private int stageWidth;
    private int stageHeight;
    private int alienWidth;
    private int margin;
    private int maxPerRow;
    private int alienCount;
    private int palyerHeight;
    private int aliensSpeed;

    private int alienMinX;
    private int alienMaxX;

    private int playerStep;
    private Player playerShip;
    private int lives;
    private boolean gameOver;
    private int score;

    public int Width(){
        return stageWidth;
    }

    public int Height(){
        return stageHeight;
    }

    List<Alien> A = new ArrayList<Alien>();
    List<Bullet> B = new ArrayList<Bullet>();


    public List<Bullet> getBullet() {
        return B;
    }
    public List<Alien> getAlien() {
        return A;
    }
    public boolean isGameOver() {
        return gameOver;
    }

    public void updateAlienMinMax() {
        alienMinX=Width();
        alienMaxX=0;
        for  (int i = 0; i < A.size(); i++) {
            int aX = A.get(i).getX();
            alienMinX = Math.min(aX, alienMinX);
            alienMaxX = Math.max(aX, alienMinX);
        }
    }
   
   public void setAliensSpeed(int speed) {
        aliensSpeed = speed;
   }

    public void AnimateAliens (double tick) {
        updateAlienMinMax();

        if( (alienMaxX >= Width()-alienWidth && aliensSpeed>0) ||
            (alienMinX <= 0+alienWidth && aliensSpeed<0) )
        {
            aliensSpeed = -aliensSpeed;
        }
       
        for  (int i = 0; i < A.size(); i++) {
            Alien Al = A.get(i);
            Al.setX(Al.getX() + aliensSpeed);
        }           
    }

    public void setAlienBulletX(Bullet randomX) {
        Random rand = new Random();
        int bulletX = Math.min(alienMaxX, Math.max(rand.nextInt(alienMaxX),alienMinX));
        randomX.setX(bulletX);  
    }
    public void shootBullet() {
        Bullet dropped = new Bullet(-10);
        dropped.setY(playerShip.getY()-playerShip.getHeight()-margin);
        dropped.setHeight(6);
        dropped.setWidth(2);
        dropped.setX(playerShip.getX()+ playerShip.getWidth()/2);
        B.add(dropped);
    }

   public void UpdateBullet(int tick) {
         List<Bullet> RB = new ArrayList<Bullet>();
         List<Alien> DA = new ArrayList<Alien>();
         for (int i = 0; i < B.size(); i++) {
            Bullet current = B.get(i);
            current.setY(current.getY()+current.getSpeed()) ;
            if ((current.getY() >= stageHeight) || (current.getY() < 0)){
                RB.add(current);
            }
            for (int j = 0; j < A.size(); j++) {
                    Alien c = A.get(j);
                    if ( hit(c, current.getX(), current.getY())) {
                        RB.add(current);
                        DA.add(c);
                    }
            }
            if (hit (playerShip, current.getX(), current.getY())) {
                 lives--;
                 if (lives == 0) {
                    gameOver = true;
                 }
            }
        }

        Boolean remove = RB.size() > 0;
        for (int i = 0; i < RB.size(); i++) {
            Bullet toRemove = RB.get(i);
            B.remove(toRemove);
        }
       // Boolean removedhotAlien = DA.size() > 0;
        for (int i = 0; i < DA.size(); i++) {
            Alien toRemove = DA.get(i);
            A.remove(toRemove);
            score += 100;
        }

        if ( remove && B.size() < A.size()) {
            spawnAlienBullet();
        }

    }

    public int getScore(){
        return score;
    }

    public boolean hit (GameObject obj, int bulltetX, int bulltetY) {
        if ((bulltetX > obj.getTopLeftX()) && (bulltetX < obj.getBottomRightX()) && 
        (bulltetY > obj.getTopLeftY()) && (bulltetY< obj.getBottomRightY())) {
            return true;
        }
        return false;
    }

    public Stage (int sW, int sH) {
        stageHeight = sH;
        stageWidth = sW;
        alienWidth = 12;
        margin = (alienWidth + 5);
        maxPerRow = 10;
        alienCount = 50;
        palyerHeight = 10;
        setUp();
    }
    public void setUp() {
        A.clear();
        B.clear();
        spawnAliens();
        updateAlienMinMax();
        spawnPlayer();
        spawnAlienBullet();
        setAliensSpeed(alienWidth/3);
        playerStep = playerShip.getWidth()/3;
        lives = 3;
        gameOver = false;
        score = 0;
    }
    public void spawnAliens() {
        for (int i = 0 ; i < alienCount ; i++) {
            Alien moreAlien = new Alien();
            int nextAlienX = stageWidth/3 + i%maxPerRow*(alienWidth + margin);
            int nextAlienY = margin + margin * Math.floorDiv(i,maxPerRow); //round always to lower
            moreAlien.setX(nextAlienX);
            moreAlien.setY(nextAlienY);
            moreAlien.setWidth(alienWidth);
            moreAlien.setHeight(12);
            A.add(moreAlien); 
        }
    }
    public void spawnPlayer() {
        playerShip = new Player();
        playerShip.setX(Width()/2-playerShip.getWidth());
        playerShip.setY(Height() - margin - playerShip.getHeight());
        playerShip.setHeight(palyerHeight);
        playerShip.setWidth(15);
    }
    public void spawnAlienBullet() {
        Bullet dropped = new Bullet(10);
        dropped.setY(A.get(A.size()-1).getY());
        dropped.setHeight(6);
        dropped.setWidth(2);
        setAlienBulletX(dropped);
        B.add(dropped);
    }

    public Player getPlayer() {
        return playerShip;
    }

    public void movePlayer(int dir) {
        int currentPlayerX = playerShip.getX();

            if ((dir < 0) && (currentPlayerX - playerStep > 0)){ 
                playerShip.setX(currentPlayerX - playerStep);
            }
            else if ((dir > 0) && (currentPlayerX + playerStep < stageWidth)) {
                playerShip.setX(currentPlayerX + playerStep);
            }
            
    }

    public int getAlienCount () {
        return A.size();
    }  
    public Alien getAlien (int index) {
        return A.get(index);
    }
}
