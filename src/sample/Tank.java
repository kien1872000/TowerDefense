package sample;

import javafx.scene.image.Image;
import sample.Enemy;

public class Tank extends Enemy {

    public Tank(){
        //super();
        this.speed = 1;
        this.rotating =0;
        this.health = 800;
        this.fullhealth = 800;
        this.reward = 30;
        img = Config.TANK_IMAGE;
        this.damage = 1;
    }
}
