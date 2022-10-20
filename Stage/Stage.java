package Stage;
import java.util.ArrayList;
import java.util.List;


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

    public Stage (int sW, int sH) {
        stageWidth = sW;
        stageHeight = sH;
        alienWidth = 10;
        margin = (alienWidth + 5);
        maxPerRow = 5;
        alienCount = 100;
        palyerHeight = 10;
    }
    public void setUp() {
        spawnAliens();
        spawnPlayer();
        setAliensSpeed(alienWidth);
    
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
        Player newPlayer = new Player();
        newPlayer.setX(margin);
        newPlayer.setY(stageHeight - margin - palyerHeight);
    }
    public int getAlienCount () {
        return alienCount;
    }  
    public Alien getAlien (int index) {
        return A.get(index);
    }

}
