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
    private int topLeftX;
    private int topLeftY;
    private int bottomRightX;
    private int bottomRightY;
    private int playerStep;
    private Player playerShip;
    private int lives;
    private boolean gameOver;

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
    public boolean isGameOver() {
        return gameOver;
    }

    public void aliensBoundingBox() {  
        for  (int i = 0; i < A.size(); i++) {
            int currentX = A.get(i).getX();
            int currentY = A.get(i).getY();
                if (currentX >  bottomRightX) {
                    bottomRightX = currentX;
                }
                else if (currentX < topLeftX) {
                    topLeftX = currentX;
                }
                if (currentY >  bottomRightY) {
                    bottomRightY = currentY;
                }
                else if (currentY < topLeftY) {
                    topLeftY = currentY;
                }   
        }
    }
   
   public void setAliensSpeed(int speed) {
        aliensSpeed = speed;
   }

    public void AnimateAliens (int tick) {
        topLeftX += aliensSpeed;
        bottomRightX += aliensSpeed;
        if (topLeftX <= 0 || bottomRightX >= stageWidth ) {
            topLeftX -= aliensSpeed;
            bottomRightX -= aliensSpeed; 
            aliensSpeed = - aliensSpeed;
            topLeftX += aliensSpeed;
            bottomRightX += aliensSpeed;
        }
        
        for  (int i = 0; i < A.size(); i++) {
            int currentX = A.get(i).getX();
            A.get(i).setX(currentX + aliensSpeed);
        }           
    }

    public void setAlienBulletX(Bullet randomX) {
        int minBulletX = topLeftX;
        int maxbulletX = bottomRightX;
        Random rand = new Random();
        int bulletX = rand.nextInt(maxbulletX - minBulletX) + minBulletX;
        randomX.setX(bulletX);  
    }
    public void shootBullet() {
        Bullet dropped = new Bullet(-70);
        dropped.setY(playerShip.getY()-playerShip.getHeight()/2);
        dropped.setHeight(150);
        dropped.setWidth(150);
        dropped.setX(playerShip.getX()+ playerShip.getWidth()/2);
        B.add(dropped);
    }

   public void UpdateBullet() {
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
        }

        if ( remove ) {
            spawnAlienBullet();
        }

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
        spawnPlayer();
        spawnAlienBullet();
        setAliensSpeed(alienWidth);
        playerStep = 10;
        lives = 3;
        gameOver = false;
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
        aliensBoundingBox();
    }
    public void spawnPlayer() {
        playerShip = new Player();
        playerShip.setX(margin);
        playerShip.setY(Height() - margin - palyerHeight-700);
        playerShip.setHeight(palyerHeight);
        playerShip.setWidth(150);
    }
    public void spawnAlienBullet() {
        Bullet dropped = new Bullet(70);
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
