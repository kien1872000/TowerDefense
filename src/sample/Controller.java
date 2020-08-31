package sample;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicInteger;

public class Controller extends AnimationTimer {
    private GameField gameField;
    private GameStage gameStage;
    private Group root;
    private GraphicsContext gc;
    private Stage stage;
    public Controller(GraphicsContext graphicsContext, Group root, Stage stage) {
        this.root = root;
        this.gc = graphicsContext;
        this.stage = stage;
        gameField = new GameField(graphicsContext,root,stage);
        gameStage = new GameStage();
    }

    @Override
    public void handle(long l) {
        gameField.update();
        gameField.render();
        if(gameField.GameOver()) {
            root.getChildren().removeAll();
            Text over = new Text(500,250,"Game Over");
            Font font = Font.font("Verdana", FontWeight.EXTRA_BOLD, 35);
            over.setFont(font);
            Button button = new Button("Exit");
            button.setFont(font);
            button.setLayoutX(500);
            button.setLayoutY(300);
            button.setOnAction(actionEvent -> {
                stage.close();
            });
            root.getChildren().addAll(button,over);
            stop();
        }
        if(gameField.win()){

            root.getChildren().removeAll();
            Text win = new Text( 500, 250,"Win");
            Button button = new Button("Exit");
            Font font = Font.font("Verdana", FontWeight.LIGHT, 35);
            win.setFont(font);
            button.setFont(font);
            button.setLayoutX(500);
            button.setLayoutY(300);
            button.setOnAction(actionEvent -> {
                stage.close();
            });
            root.getChildren().addAll(button,win);
            stop();
        }

    }

    public void MouseEvent(Shop shop, Scene scene, Group root){
        AtomicInteger type = new AtomicInteger(0);
        EventHandler<MouseEvent> mEvent = mouseEvent -> {
            if(type.get() == 0){
                if(mouseEvent.getTarget() == shop.imageView1) {
                    if (GameField.Gold >= 50) {
                        type.set(1);
                        scene.setCursor(new ImageCursor(Config.MCGUN_IMAGE));
                    }
                }
                if(mouseEvent.getTarget() == shop.imageView2) {
                    if (GameField.Gold >= 30) {
                        type.set(2);
                        scene.setCursor(new ImageCursor(Config.NORMALTOWER_IMAGE));
                    }
                }
                if(mouseEvent.getTarget() == shop.imageView3) {
                    if (GameField.Gold >= 100) {
                        type.set(3);
                        scene.setCursor(new ImageCursor(Config.SNIPER_IMAGE));
                    }
                }
                if(mouseEvent.getTarget() == shop.imageView4){
                    type.set(4);
                    scene.setCursor(new ImageCursor(Config.SELL));
                }
            }
            else if(type.get()==1){
                int x = (int) mouseEvent.getX()/64;
                int y = (int) mouseEvent.getY()/64;
                System.out.println(x + " " + y);
                if( x<11 && y<8 ){
                    if(GameField.Tile_Map[y][x] == 0){
                            Tower tower = new MachineGun(x * 64, y * 64);
                            tower.yPos = y;
                            tower.xPos = x;
                            GameField.type_tower.add(tower);
                            GameField.buyTower(tower);
                            GameField.Tile_Map[y][x] = 2;
                            scene.setCursor(Cursor.DEFAULT);
                            type.set(0);
                    }
                }

            }
            else if(type.get()==2){
                int x = (int) mouseEvent.getX()/64;
                int y = (int) mouseEvent.getY()/64;
                System.out.println(x + " " + y);
                if( x<11 && y<8 ){
                    if(GameField.Tile_Map[y][x] == 0){

                            Tower tower = new NormalTower(x * 64, y * 64);
                            tower.yPos = y;
                            tower.xPos = x;
                            GameField.type_tower.add(tower);
                            GameField.buyTower(tower);
                            GameField.Tile_Map[y][x] = 2;
                            scene.setCursor(Cursor.DEFAULT);
                            type.set(0);

                    }
                }

            }

            else if(type.get()==3){
                int x = (int) mouseEvent.getX()/64;
                int y = (int) mouseEvent.getY()/64;
                System.out.println(x + " " + y);
                if( x<11 && y<8 ){
                    if(GameField.Tile_Map[y][x] == 0){

                            Tower tower = new SniperTower(x * 64, y * 64);
                            tower.yPos = y;
                            tower.xPos = x;
                            GameField.type_tower.add(tower);
                            GameField.buyTower(tower);
                            GameField.Tile_Map[y][x] = 2;
                            scene.setCursor(Cursor.DEFAULT);
                            type.set(0);

                    }
                }
            }
            else if(type.get() == 4){
                int x = (int) mouseEvent.getX()/64;
                int y = (int) mouseEvent.getY()/64;
                if( x<11 && y<8 ){
                    if(GameField.Tile_Map[y][x] == 2){
                        if (!GameField.type_tower.isEmpty())
                        for(int i = 0; i < GameField.type_tower.size(); i++){
                            if(GameField.type_tower.get(i).xPos == x && GameField.type_tower.get(i).yPos == y ){
                                GameField.sellTower(GameField.type_tower.get(i));
                                GameField.type_tower.remove(i);
                                scene.setCursor(Cursor.DEFAULT);
                                type.set(0);
                                GameField.Tile_Map[y][x] = 0;
                            }
                        }
                    }
                }
            }
            if (type.get() != 0){
                if (mouseEvent.getTarget() == shop.imageView5){
                    scene.setCursor(Cursor.DEFAULT);
                    type.set(0);
                }
            }
        };
        root.setOnMouseClicked(mEvent);
    }
    public void InforEvent(Shop shop, Group root){
        EventHandler<MouseEvent> mEvent = mouseEvent -> {
            if(mouseEvent.getTarget() == shop.imageView1){
                shop.SetInforTower("Machine Gun", 5, 150, 50);
                shop.SetColor(Color.RED);
            }
            else if(mouseEvent.getTarget() == shop.imageView2){
                shop.SetInforTower("Normal Tower", 5, 250, 30);
                shop.SetColor(Color.RED);
            }
            else if(mouseEvent.getTarget() == shop.imageView3){
                shop.SetInforTower("Sniper Tower", 35, 400, 100);
                shop.SetColor(Color.RED);
            }
            else{
                shop.SetColor(Color.TRANSPARENT);
            }
        };
        root.setOnMouseMoved(mEvent);
    }
    public void Pause(Group root){

        Button pause = new Button("Pause") ;
        Font font = Font.font("Verdana", FontWeight.LIGHT, 25);
        pause.setFont(font);
        pause.setLayoutX(14*64);
        pause.setLayoutY(64);

        AtomicInteger pause1 = new AtomicInteger(0);
        pause.setOnAction(actionEvent -> {
            if(pause1.get() == 0) {
                this.stop();
                pause1.set(1);
            }
            else if(pause1.get() == 1){
                this.start();
                pause1.set(0);
            }
        });
        root.getChildren().add(pause);
    }

}
