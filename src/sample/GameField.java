package sample;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public final class GameField {
    public static GraphicsContext gc;
    public static List<Tower> type_tower = new ArrayList<>();
    public static List<Enemy> enemy = new ArrayList<>();
    public static List<Bullet> bullets = new ArrayList <>();
    public static int count_enemy = 0;
    public static int Health = 10;
    public static int Gold = 150;
    public static int Stage=0;
    public static Button start = new Button("Play");
    public static Group root;
    public static MediaPlayer mediaPlayer = new MediaPlayer(Config.BACKGROUND_MUSIC);

    /*public GameField(GameStage gameStage){
        gameStage.setGameEntities();
        this.gameEntities.addAll(enemy);
        this.gameEntities.addAll(type_tower);
    }*/


    public static final String[][] MAP_SPRITES = new String[][] {
            { "024", "024", "003", "047", "047", "047", "004", "024", "003", "047", "047" },
            { "024", "024", "025", "299", "001", "002", "023", "024", "025", "299", "001" },
            { "024", "024", "025", "023", "024", "025", "023", "024", "025", "023", "024" },
            { "003", "047", "048", "023", "024", "025", "023", "024", "025", "023", "024" },
            { "025", "299", "001", "027", "024", "025", "023", "024", "025", "023", "024" },
            { "025", "023", "024", "024", "024", "025", "046", "047", "048", "023", "024" },
            { "025", "023", "024", "024", "024", "026", "001", "001", "001", "027", "024" },
            { "025", "023", "024", "024", "024", "024", "024", "024", "024", "024", "024" }
    };
    public static int[][] Tile_Map = new int[][] {
            { 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1 },
            { 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1 },
            { 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0 },
            { 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0 },
            { 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0 },
            { 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0 },
            { 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0 },
            { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
    };

    public GameField(GraphicsContext graphicsContext, Group root1,Stage stage)
    {
        this.root = root1;
        this.gc = graphicsContext;
        Image img = new Image("file:src/Image/Default size/2.png");
        ImageView imageView = new ImageView(img);
        imageView.setFitHeight(30);
        imageView.setFitWidth(100);
        Font font = Font.font("Verdana", FontWeight.LIGHT, 20);
        Font font1 = Font.font("Verdana", FontWeight.LIGHT, 25);
        start.setFont(font1);
        start.setLayoutX(64*12);
        start.setLayoutY(64*7);
        start.setOnMouseClicked( new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(GameField.enemy.isEmpty()){
                    GameStage.start();
                }
            }
        });
        Button Exit = new Button("Exit");

        Exit.setFont(font);
        Exit.setLayoutY(64*8);
        Exit.setLayoutX(64*12);
        Exit.setOnAction(actionEvent -> {
            stage.close();
        });
        root.getChildren().addAll(start,Exit);
    }

    private static void drawMap() {
        gc.drawImage(Config.MAP_IMAGE, 0, 0);
    }
    public void clearEnemy(){
        for(int i =0; i<GameField.enemy.size(); i++){
            if(GameField.enemy.get(i).isDead()){
                if(!GameField.enemy.isEmpty()){
                    Gold = Gold + enemy.get(i).reward;
                    GameField.enemy.remove(i);
                }
            }
        }
    }
    public  void update() {
        type_tower.forEach(Tower::update);
        clearEnemy();
        Spawner.doSpawn();
        Target.doTarget();
        enemy.forEach(Enemy::update);
        type_tower.forEach(tower -> tower.updateBullets());
       // bullets.forEach(Bullet::update);
    }
    public void render() {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        LoadBackground();
        drawMap();
        enemy.forEach(g -> g.render(gc));
       // bullets.forEach(g->g.render(gc));
        type_tower.forEach(g -> g.render(gc));
        type_tower.forEach(tower -> tower.renderBullets(gc));
        gc.setFill(Color.GRAY);
        gc.fillRect(64*2,64*8, 10*30, 30 );
        gc.setFill(Color.RED);
        gc.fillRect(64*2, 64*8,  Health*30, 30);
        gc.setFill(Color.YELLOW);
        gc.setFont(new Font(20));
        gc.fillText( "Gold: " + Gold + "$",64*2,64*9, 60 );
        gc.setFill(Color.BLACK);
        gc.setFont(new Font(25));
        gc.fillText("Stage: " + GameStage.stage +" /4", 64*12, 64*6.5);
        //GameOver();
    }
    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
    public static void buyTower(Tower tower){
        Gold -= tower.price;
    }
    public static void sellTower(Tower tower){
        Gold += tower.price/2;
    }
    public static void LoadBackground(){
        gc.drawImage(Config.BACKGROUND_IMAGE, 0, 0);
    }

    public static boolean GameOver() {
        return (GameField.Health <= 0);
    }
    public static boolean win(){
        return (GameStage.stage == 4 && GameField.enemy.isEmpty() && Health > 0 );
    }
    public int OverStage(){
        if(GameField.enemy.isEmpty()){
            Stage+=1;
        }
        return Stage;
    }
}

