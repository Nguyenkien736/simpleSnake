package sample;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;

import java.util.ArrayList;

public abstract class Actor {

    ArrayList<Position> body;
    ArrayList<Rectangle> bodyShapes;
    private ImageView spriteFrame;
    protected SVGPath spriteBound;
    private int size;
    public Actor()
    {
        size=0;
        body=new ArrayList<>();
        bodyShapes=new ArrayList<>();

    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public SVGPath getSpriteBound() {
        return spriteBound;
    }

    public ImageView getSpriteFrame() {
        return spriteFrame;
    }

    public abstract void update();

}
