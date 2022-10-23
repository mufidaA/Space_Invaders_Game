package Stage;

public class Bullet extends GameObject {
      private int speed;
      public int getSpeed() {
        return speed;
      }
      
    public Bullet(int spd) {

        //  var playerImg = "images/player.png";
  
          setWidth (2);
  
          setX(0);

          setY(0);

          speed = spd;
      }

  
    }