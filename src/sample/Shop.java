package sample;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Shop {
    public ImageView imageView1 = new ImageView();
    public ImageView imageView2 = new ImageView();
    public ImageView imageView3 = new ImageView();
    public ImageView imageView4 = new ImageView();
    public ImageView imageView5 = new ImageView();

    public static Text TypeTower = new Text( 64*13, 64* 2.5,"In4 Tower: " );
    public static Text Damge = new Text(64*13.5, 64*3, "Damge: " );
    public static Text Range = new Text(64*13.5, 64*3.5, "Range: " );
    public static Text Price = new Text(64*13.5, 64*4, "Price:  " );
    public int[][] MAP_SPRITES = new int[][] {
            { 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1 },
            { 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1 },
            { 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0 },
            { 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0 },
            { 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0 },
            { 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0 },
            { 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0 },
            { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
    };
    public Shop(Group root){

        Text txt = new Text(64*13,30,"Shop");
        Font font = Font.font("Verdana", FontWeight.LIGHT, 25);
        txt.setFont(font);

        Image img1 = Config.MCGUN_IMAGE_SHOOT;
        this.imageView1 = new ImageView(img1);
        this.imageView1.setY(64);
        this.imageView1.setX(11*64);

        Image img2 = Config.NORMALTOWER_IMAGE_SHOOT;
        this.imageView2 = new ImageView(img2);
        this.imageView2.setX(11*64);
        this.imageView2.setY(64*2);

        Image img3 = Config.SNIPER_IMAGE_SHOOT;
        this.imageView3 = new ImageView(img3);
        this.imageView3.setX(11*64);
        this.imageView3.setY(3*64);

        Image img4 = Config.SELL;
        this.imageView4 = new ImageView(img4);
        this.imageView4.setX(11.3*64);
        this.imageView4.setY(4*64);

        Image img5 = Config.CANCEL;
        this.imageView5 = new ImageView(img5);
        this.imageView5.setX(11.3*64);
        this.imageView5.setY(5*64);

        SetColor(Color.TRANSPARENT);

        root.getChildren().addAll(imageView1,imageView2,imageView3,imageView4, imageView5, txt, TypeTower, Damge, Range, Price);
        System.out.println(this.MAP_SPRITES.length);
    }
    public  Void SetInforTower(String name, int dame, int range, int price){
        TypeTower.setText("In4 Tower: " + name);
        Damge.setText("Damge: "+ dame);
        Range.setText("Range: "+ range);
        Price.setText("Price:  "+ price);
        return null;
    }
    public  Void SetColor(Paint paint){
        TypeTower.setFill(paint);
        Damge.setFill(paint);
        Range.setFill(paint);
        Price.setFill(paint);
        return null;
    }
}
