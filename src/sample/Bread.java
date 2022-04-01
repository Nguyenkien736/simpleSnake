package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Bread extends Actor{
    Random random=new Random();
    public Bread()
    {
        super();
        body.add(new Position());
        body.get(0).setPosition(random.nextInt(32),random.nextInt(20));
        setSize(1);
        setShapes();

    }
    public void setShapes()
    {
        if(bodyShapes.size()<body.size())
        {

            int a=body.size()-bodyShapes.size();

            for(int i=0;i<a;i++)
            {
                bodyShapes.add(new Rectangle());

            }
        }
        for(int i=0;i<body.size();i++)
        {
            bodyShapes.get(i).setX(body.get(i).getX()*20);
            bodyShapes.get(i).setY(body.get(i).getY()*20);
            bodyShapes.get(i).setHeight(20);
            bodyShapes.get(i).setWidth(20);
            bodyShapes.get(i).setFill(Color.RED);
        }
    }


    @Override
    public void update() {

    }
}
