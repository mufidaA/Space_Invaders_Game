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

    List<Alien> A = new ArrayList<Alien>();

    public Stage (int sW, int sH) {
        stageWidth = sW;
        stageHeight = sH;
        alienWidth = 10;
        margin = (alienWidth + 5);
        maxPerRow = 5;
        alienCount = 500;
        palyerHeight = 10;
    }
    public void setUp() {
        spawnAliens();
        spawnPlayer();
    }
    
    public void spawnAliens() {
        for (int i = 0 ; i < alienCount ; i++) {
        Alien moreAlien = new Alien();
        int nextAlienX = stageWidth/3 + i%maxPerRow*(alienWidth + margin);
        int nextAlienY = margin + margin * Math.floorDiv(i,maxPerRow); //round to lower
        moreAlien.setX(nextAlienX);
        moreAlien.setY(nextAlienY);
        A.add(moreAlien); }
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
