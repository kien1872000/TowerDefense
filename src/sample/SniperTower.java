package sample;

import javafx.scene.Group;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

public class SniperTower extends Tower {

    public SniperTower(double x, double y) {
        super(x, y);
        this.price = 100;
        damage = 35;
        this.range = 400;
        this.wait_to_shoot = 50;
        this.img = Config.BODY_MCGUN_IMAGE;
        this.gunImg = Config.SNIPER_IMAGE;
        this.shootImg = Config.SNIPER_IMAGE_SHOOT;
        this.type_tower = 2;
        this.mediaPlayer = new MediaPlayer(Config.MEDIA_SHOOT2);
    }
}
