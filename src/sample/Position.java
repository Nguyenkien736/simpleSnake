package sample;

public class Position {
    private int x=0;
    private int y=0;


    public void setPosition(int xLoc,int yLoc)
    {
        x=xLoc;
        y=yLoc;
    }
    public void setPosition(Position pos)
    {
        this.x=pos.x;
        this.y= pos.y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
