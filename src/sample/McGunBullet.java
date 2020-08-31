package sample;

import javafx.scene.media.MediaPlayer;

public class McGunBullet extends Bullet {
    public McGunBullet(double x, double y) {
        super(x, y);
        this.speed =7;
        this.img =Config.MCGUN_BULLET_IMAGE;
    }
}
