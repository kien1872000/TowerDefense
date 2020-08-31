package sample;

import javafx.scene.Group;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;

public class MachineGun extends Tower {

    public MachineGun(double x, double y) {
        super(x, y);
        this.price = 50;
        this.damage = 5;
        this.range = 150;
        this.wait_to_shoot = 5;
        this.img = Config.BODY_MCGUN_IMAGE;
        this.gunImg = Config.MCGUN_IMAGE;
        this.shootImg = Config.MCGUN_IMAGE_SHOOT;
        this.type_tower = 0;
        this.mediaPlayer = new MediaPlayer(Config.MEDIA_SHOOT1);
    }
}
