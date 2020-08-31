package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main extends Application {
    GraphicsContext gc;
    public static Controller controller;
    public void start(Stage stage) {
        final Canvas canvas = new Canvas(1024, 600);
        MediaPlayer mediaPlayer = new MediaPlayer(Config.BACKGROUND_MUSIC);
        mediaPlayer.play();
        mediaPlayer.setVolume(0.5);
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });
        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();
        // Tao scene
        Shop shop = new Shop(root);
        Scene scene = new Scene(root);
        controller = new Controller(gc,root,stage);
        controller.MouseEvent(shop, scene, root);
        controller.Pause(root);
        controller.InforEvent(shop,root);
        //controller.GameOver(stage, scene);

        //Them scene vao stage
        //stage.setScene(scene);
        Menu(stage, scene);
        controller.start();
       // if(GameField.Health<=0) controller.stop();
        stage.show();
    }
    public void Menu(Stage stage, Scene scene){
        Group root = new Group();
        Image img = new Image("file:src/Image/Default size/kien.png");
        ImageView imageView = new ImageView(img);
        imageView.setFitHeight(600);
        imageView.setFitWidth(1024);
        Scene scene1 = new Scene(root, 1024, 600);

        Button NewGame = new Button("New Game");
        Font font = Font.font("Verdana", FontWeight.LIGHT, 25);
        NewGame.setFont(font);
        NewGame.setLayoutY(300);
        NewGame.setLayoutX(400);
        NewGame.setOnAction(actionEvent -> {
            stage.setScene(scene);
        } );

        Button Exit = new Button("Exit");
        Exit.setFont(font);
        Exit.setLayoutX(400);
        Exit.setLayoutY(400);
        Exit.setOnAction(actionEvent -> {
            stage.close();
        } );
        root.getChildren().addAll(imageView,NewGame,Exit);
        stage.setScene(scene1);
    }
    public static void main(String[] args) {
        launch(args);
    }
}