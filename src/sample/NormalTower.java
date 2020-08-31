package sample;

import javafx.scene.Group;
import javafx.scene.media.MediaPlayer;

public class NormalTower extends Tower {
    public NormalTower(double x, double y){
        super(x, y);
        this.price = 30;
        damage = 5;
        this.range = 250;
        this.wait_to_shoot = 20;
        this.img = Config.BODY_MCGUN_IMAGE;
        this.gunImg = Config.NORMALTOWER_IMAGE;
        this.shootImg = Config.NORMALTOWER_IMAGE_SHOOT;
        this.type_tower = 1;
        this.mediaPlayer = new MediaPlayer(Config.MEDIA_SHOOT1);
    }
}
