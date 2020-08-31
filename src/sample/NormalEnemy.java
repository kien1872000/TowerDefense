package sample;

public class NormalEnemy extends Enemy {
    public NormalEnemy(){
        this.speed = 2;
        this.health = 150;
        this.reward = 10;
        this.fullhealth = 150;
        this.rotating = 0;
        img = Config.NORMALE_IMAGE;
        this.damage = 2;
    }
}
