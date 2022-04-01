package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    boolean up,down=true,left,right;

    AudioClip eatingSound,deadSound;
    URL eatingURL,collideURL;

    Scene introScene;
    Group root;
    Image bgImg;
    ImageView backGround;
    Button restartButton;

    Snake theSnake;
    Bread bait1;
    CastingDirector castingDirector;
    GameLoop gameLoop;



    @Override
    public void start(Stage primaryStage) throws Exception{

        initiateTheGame();
        setUpEventListener();
        primaryStage.setScene(introScene);
        primaryStage.setTitle("Snake");
        primaryStage.show();
        gameLoop.start();




    }
    public void setUpEventListener()
    {
        introScene.setOnKeyPressed((KeyEvent e)->{
            switch (e.getCode()) {
                case A:
                    left=true;
                    up=false;
                    down=false;
                    right=false;
                    break;
                case W:
                    up=true;
                    down=false;
                    right=false;
                    left=false;
                    break;
                case S:
                    up=false;
                    down=true;
                    right=false;
                    left=false;
                    break;
                case D:
                    up=false;
                    down=false;
                    right=true;
                    left=false;
                    break;
            }

        });


    }
    public void initiateTheGame()
    {
        gameLoop=new GameLoop(this);
        restartButton=new Button();
        restartButton.setText("Restart");
        restartButton.setLayoutX(320);
        restartButton.setLayoutY(200);
        restartButton.setOnAction((ActionEvent evnt)->{
            restartTheGame();
        });
        root = new Group();
        backGround = new ImageView();
        theSnake=new Snake(this);
        bait1=new Bread();
        eatingURL=getClass().getResource("/Eatsound.wav");
        collideURL=getClass().getResource("/endSound.wav");
        eatingSound=new AudioClip(eatingURL.toString());
        deadSound=new AudioClip(collideURL.toString());
        castingDirector=new CastingDirector();

        root.getChildren().add(backGround);
        theSnake.drawSnake(root);
        root.getChildren().add(bait1.bodyShapes.get(0));
        root.getChildren().add(restartButton);
        restartButton.setVisible(false);
        castingDirector.addToCollisionCheckList(bait1,theSnake);

        introScene=new Scene(root,640,400, Color.BLACK);

    }
    public void showRestartButton()
    {

        restartButton.setVisible(true);
        //root.getChildren().clear();




    }

    public void restartTheGame()
    {
        root.getChildren().clear();
        up=false;down=true;left=false;right=false;
        root.getChildren().add(restartButton);
        restartButton.setVisible(false);

        theSnake.eraseSnake(root);
        bait1=new Bread();
        theSnake=new Snake(this);
        castingDirector=new CastingDirector();
        theSnake.drawSnake(root);
        root.getChildren().add(bait1.bodyShapes.get(0));
        castingDirector.addToCollisionCheckList(bait1,theSnake);


    }


    public static void main(String[] args) {
        launch(args);
    }
}
