package sample;

import javafx.geometry.HPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

public class Snake extends Actor{
    public static final int RIGHT_BOUNDARY=31;
    public static final int LEFT_BOUNDARY=0;
    public static final int UP_BOUNDARY=0;
    public static final int DOWN_BOUNDARY=19;

    private Direction direct=Direction.Down;
    private int length;
    private int foodLeft=0;
    private boolean isEatting;
    private boolean isDead;
    private int fraction=0;
    private Main snakeGame;
    public Snake(Main snkGame){
        super();
        snakeGame=snkGame;
        length=2;
        body.add(new Position());
        body.add(new Position());
        body.get(0).setPosition(0,0);
        body.get(1).setPosition(0,1);

        setShapes();


    }
    public void eraseSnake(Group pane)
    {
        for(int i=0;i<length;i++)
        {
            pane.getChildren().remove(bodyShapes.get(i));
        }
    }
    public void drawSnake(Group pane)
    {
        for(int i=0;i<length;i++)
        {
            pane.getChildren().add(bodyShapes.get(i));

        }

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
            bodyShapes.get(i).setFill(Color.GREEN);
        }
    }




    @Override public void update()
    {
        if(snakeGame.right)direct=Direction.Right;
        if(snakeGame.left)direct=Direction.Left;
        if(snakeGame.up)direct=Direction.UP;
        if(snakeGame.down)direct=Direction.Down;
        eraseSnake(snakeGame.root);


        if(fraction>=60) {
            if(!isDead)

            checkCollision();

            fraction=0;

        }
        else{
            fraction++;
        }
       // if(isDead)
        //{
         //   snakeGame.castingDirector.REMOVED_ACTOR.add(this);
       // }


        removeActor();
        setBoundaries();
        if(isDead)
        {
            drawSnake(snakeGame.root);

            snakeGame.showRestartButton();

        }

        setShapes();
        if(!isDead)
        drawSnake(snakeGame.root);


    }
    public void setBoundaries()
    {
        if(body.get(0).getX()>=RIGHT_BOUNDARY)body.get(0).setX(RIGHT_BOUNDARY);
        if(body.get(0).getX()<=LEFT_BOUNDARY)body.get(0).setX(LEFT_BOUNDARY);
        if(body.get(0).getY()>=DOWN_BOUNDARY)body.get(0).setY(DOWN_BOUNDARY);

        if(body.get(0).getY()<=UP_BOUNDARY)body.get(0).setY(UP_BOUNDARY);

    }
    public void removeActor()
    {
        boolean check=false;
        for(Actor actor:snakeGame.castingDirector.REMOVED_ACTOR)
        {
            if(actor instanceof Bread) check=true;
            snakeGame.castingDirector.COLLISION_CHECKLIST.remove(actor);
            snakeGame.castingDirector.removeFromCurrentCast(actor);
            for(int j=0;j<actor.bodyShapes.size();j++)
            {
                snakeGame.root.getChildren().remove(actor.bodyShapes.get(j));
            }

        }
        snakeGame.castingDirector.resetRemovedActors();
        if(check&&!isDead) {
            Bread baits = new Bread();
            snakeGame.castingDirector.COLLISION_CHECKLIST.add(baits);
            snakeGame.castingDirector.CURRENT_CAST.add(baits);
            for (int i = 0; i < baits.getSize(); i++) {
                snakeGame.root.getChildren().add(baits.bodyShapes.get(i));
            }
        }
    }
    public void checkCollision()
    {

        for(int i=0;i<snakeGame.castingDirector.COLLISION_CHECKLIST.size();i++)
        {

            if(this.collide(snakeGame.castingDirector.COLLISION_CHECKLIST.get(i))) {
                snakeGame.deadSound.play();

                if (snakeGame.castingDirector.COLLISION_CHECKLIST.get(i) instanceof Bread) {

                    isEatting = true;
                    foodLeft = snakeGame.castingDirector.COLLISION_CHECKLIST.size();
                }
                if(snakeGame.castingDirector.COLLISION_CHECKLIST.get(i) instanceof Snake)
                {
                    System.out.println("Meet");
                    isDead=true;
                }
            }
        }
        if(foodLeft==0) isEatting=false;
        if(!isDead) {
            if (isEatting) {
                extend();
                snakeGame.eatingSound.play();
            } else move();
        }
    }
    public boolean collide(Actor object) {
        if(this==object) {


            for (int i = 1; i < length; i++) {
                if (body.get(0).getX()==object.body.get(i).getX()&&body.get(0).getY()==object.body.get(i).getY()){
                    //snakeGame.castingDirector.REMOVED_ACTOR.add(object);
                    System.out.println("Meet");
                    return true;
                }
            }
        }
        else for(int i=0;i<object.getSize();i++) {
            {
                if (body.get(0).getX()==object.body.get(i).getX()&&body.get(0).getY()==object.body.get(i).getY()) {
                    snakeGame.castingDirector.REMOVED_ACTOR.add(object);

                    return true;
                }
            }

        }
        return false;
    }
    public void extend()
    {
        Position lastPos;
        lastPos=move();
        body.add(new Position());
        body.get(body.size()-1).setPosition(lastPos);

        //System.out.println(body.get(body.size()).getX());
        //body.get(body.size()-1).setPosition(lastPos);
        length++;
        foodLeft--;

    }
    public Position move()
    {
        Position res;
        res=body.get(body.size()-1);
        int xloc=body.get(0).getX(),yloc=body.get(0).getY();

        switch (direct)
        {
            case UP:
                yloc=body.get(0).getY()-1;
                break;
            case Down:
                yloc=body.get(0).getY()+1;
                break;
            case Left:
                xloc=body.get(0).getX()-1;
                break;
            case Right:
                xloc=body.get(0).getX()+1;
                break;
        }

       // body.add(new Position());
        //length++;
        for(int i=length-1;i>=1;i--)
        {
            body.get(i).setPosition(body.get(i-1));

        }
        body.get(0).setPosition(xloc,yloc);
        return res;
    }
    public void eating(Bread bait)
    {
        if(bait.getSize()>foodLeft)
        {
            extend();
        }
    }

}
