package sample;

public class BossEnemy extends Enemy {
    public BossEnemy(){
        this.speed = 0.5;
        this.health = 5000;
        this.reward = 100;
        this.fullhealth = 5000;
        this.rotating = -90;
        img = Config.BOSS_IMAGE;
        this.damage = 1;
    }
}
