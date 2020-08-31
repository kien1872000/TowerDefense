package sample;


public class SniperBullet extends Bullet{

    public SniperBullet(double x, double y) {
        super(x, y);
        this.speed =15;
        this.img =Config.SNIPER_BULLET_IMAGE;
    }
}
