package sample;

public class NormalTowerBullet extends Bullet {
    public NormalTowerBullet(double x, double y) {
        super(x, y);
        this.speed =5;
        this.img =Config.NORMALTOWER_BULLET_IMAGE;
    }
}
