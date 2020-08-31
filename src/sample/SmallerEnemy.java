package sample;

import javafx.scene.image.Image;
import sample.Enemy;

public class SmallerEnemy extends Enemy {

    public SmallerEnemy(){
        //super();
        this.speed = 3;
        this.health = 100;
        this.reward = 20;
        this.fullhealth = 100;
        this.rotating = 0;
        img = Config.SMALLERE_IMAGE;
        this.damage = 1;
    }
}
