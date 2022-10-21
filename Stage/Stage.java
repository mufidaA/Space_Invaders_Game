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
    private Bullet bullet;

    public int Width(){
        return stageWidth;
    }

    public int Height(){
        return stageHeight;
    }
    public Bullet getBullet() {
        return bullet;
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

    List<Alien> A = new ArrayList<Alien>();

    public void setAlienBulletX() {
        int minBulletX = A.get(A.size() - maxPerRow).getX();
        int maxbulletX = A.get(A.size()).getX();
        Random rand = new Random();
        int bulletX = rand.nextInt(maxbulletX - minBulletX) + minBulletX;
        bullet.setX(bulletX);
        
    }

   public void AnimateBullet() {
         bullet.setY(bullet.getY()+70) ;
    }

  
    public void DropBullet() {
        if (bullet.getY() == stageHeight) {
//redrop
        }
    }

    public Stage (int sW, int sH) {
        stageHeight = sH;
        stageWidth = sW;
        alienWidth = 10;
        margin = (alienWidth + 5);
        maxPerRow = 5;
        alienCount = 50;
        palyerHeight = 10;
        setUp();
    }
    public void setUp() {
        spawnAliens();
        spawnPlayer();
        spawnBullet();
        setAliensSpeed(alienWidth);
        playerStep = 10;
    
    }
    public void spawnAliens() {
    
        for (int i = 0 ; i < alienCount ; i++) {
        Alien moreAlien = new Alien();
        int nextAlienX = stageWidth/3 + i%maxPerRow*(alienWidth + margin);
        int nextAlienY = margin + margin * Math.floorDiv(i,maxPerRow); //round always to lower
        moreAlien.setX(nextAlienX);
        moreAlien.setY(nextAlienY);
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
    public void spawnBullet() {
        bullet = new Bullet();
        bullet.setX(margin);
        bullet.setY(A.get(A.size()-1).getY());
        bullet.setHeight(150);
        bullet.setWidth(150);
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
