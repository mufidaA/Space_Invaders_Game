package Stage;

public class GameObject {
    public int getTopLeftX() {
        return getX() - getWidth()/2;
    }
    public int getTopLeftY() {
        return getY() - getHeight()/2;
    }
    
    public int getBottomRightY() {
        return getY() + getHeight()/2;
    }
    public int getBottomRightX() {
        return getX() + getWidth()/2;
    }
   
    private int width;

    public int getWidth() {
        return width;
    }


    public void setWidth(int width) {
        this.width = width;
    }

    private int height;

    public int getHeight() {
        return height;
    }


    public void setHeight(int height) {
        this.height = height;
    }
    
    private int X;

    public int getX() {
        return X;
    }


    public void setX(int positionX) {
        X = positionX;
    }
   
    private int Y;

    public int getY() {
        return Y;
    }


    public void setY(int positionY) {
        Y = positionY;
    }

}
