package sample;

import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {
    private Main snakeGame;


    public GameLoop(Main snk)
    {
        super();
        snakeGame=snk;


    }

    @Override
    public void handle(long l) {

        snakeGame.theSnake.update();
        //System.out.println("Running...");
    }
}
