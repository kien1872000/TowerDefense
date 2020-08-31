package sample;

import javafx.scene.image.Image;

import java.io.File;
import  java.lang.Object;
 import javafx.scene.media.Media;
public class Config {
    public static final Image MCGUN_IMAGE = new Image("file:src/Image/Default size/turret_01_mk3.png");
    public static final Image MCGUN_IMAGE_SHOOT = new Image("file:src/Image/Default size/turret_01_mk3_shoot.gif");
    public static final Image NORMALTOWER_IMAGE = new Image("file:src/Image/Default size/turret_01_mk1.png");
    public static final Image NORMALTOWER_IMAGE_SHOOT = new Image("file:src/Image/Default size/turret_01_mk1_shoot.gif");
    public static final Image SNIPER_IMAGE = new Image("file:src/Image/Default size/turret_02_mk4.png");
    public static final Image SNIPER_IMAGE_SHOOT = new Image("file:src/Image/Default size/turret_02_mk4_shoot.gif");

   // public static final Image MCGUN_BULLET_IMAGE = new Image("file:src/Image/Default size/towerDefense_tile298.png ");
    public static final Image MCGUN_BULLET_IMAGE = new Image("file:src/Image/Default size/bulletRed3.png");
    public static final Image NORMALTOWER_BULLET_IMAGE = new Image("file:src/Image/Default size/bulletRed1.png");
    public static final Image SNIPER_BULLET_IMAGE = new Image("file:src/Image/Default size/towerDefense_tile295.png");
    public static final Image BODY_MCGUN_IMAGE = new Image("file:src/Image/Default size/body_halftrack.gif");

    public static final Image SMALLERE_IMAGE = new Image("file:src/Image/Default size/smaler_enemy.png");
    public static final Image NORMALE_IMAGE = new Image("file:src/Image/Default size/normal_enemy.png");
    public static final Image TANK_IMAGE = new Image("file:src/Image/Default size/tank.png");
    public static final Image BOSS_IMAGE = new Image("file:src/Image/Default size/boss.png");

    public static final Image BACKGROUND_IMAGE = new Image("file:src/Image/Default size/Background.png");
    public static final Image MAP_IMAGE = new Image("file:src/Image/Default size/map.png");
    public static final Image SELL = new Image("file:src/Image/Default size/1.png");
    public static final Image CANCEL = new Image("file:src/Image/Default size/3.png");
    //public static final Image BODY_SNIPER_IMAGE = new Image();
    //public static final Image BODY_NORMALTOWER_IMAGE = new Image();
    public static final Media MEDIA_SHOOT1 = new Media(new File("E:\\TF\\TowerDefense\\src\\sounds\\2_t5shot.mp3").toURI().toString());
    public static final Media MEDIA_SHOOT2 = new Media(new File("E:\\TF\\TowerDefense\\src\\sounds\\4_t1shot.mp3").toURI().toString());
    public static final Media BACKGROUND_MUSIC = new Media(new File("E:\\TF\\TowerDefense\\src\\sounds\\8_music.mp3").toURI().toString());
}
