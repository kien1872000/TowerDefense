package sample;

import javafx.scene.Group;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Tower extends GameEntities implements GameTile {
    //public Image gunImg;
    public int xPos;
    public int yPos;
    protected Image shootImg;
    protected double range;
    protected double damage;
    protected double wait_to_shoot;
    protected double rotating;
    protected int i = 0;
    protected boolean isShoot;
    protected int ready_to_shoot;
    protected double time_shoot;
    protected boolean isFist;
    protected int price;
    protected int type_tower;
    protected MediaPlayer mediaPlayer;
    protected List<Bullet> bullets = new ArrayList <Bullet>();
    // public LinkedList

    public void setPos(double x, double y){
        this.x = x;
        this.y = y;
    }
    public Tower(double x, double y) {
        this.isShoot = false;
        this.i = 0;
        this.isFist = true;
        this.x = x;
        this.y = y;
        this.ready_to_shoot = 0;
        this.time_shoot = 0;
        this.rotating = 0;
    }
    public void setIShoot(boolean isShoot){
        this.isShoot = isShoot;
    }
    @Override
    public void render(GraphicsContext gc) {
        // super.render(gc);
        ImageView iv1 = null;
        if(!isShoot)  iv1 = new ImageView(gunImg);
        else iv1 = new ImageView(shootImg);

       ImageView iv2 = new ImageView(img);
        iv1.setRotate(rotating);
        iv2.setRotate(180);
        params.setFill(Color.TRANSPARENT);

        Image rotatedImage1 = iv1.snapshot(params, null);
        Image rotatedImage2 = iv2.snapshot(params, null);
        double delta = (rotatedImage1.getHeight() - 64) / 2;
        gc.drawImage(rotatedImage2, x, y);
        gc.drawImage(rotatedImage1, x - delta, y - delta);

        //System.out.println(a + " "+ b);
        //System.out.println(iv.getRotate());

    }

    public boolean inRange(Enemy enemy) {
        //System.out.println(GameField.distance(this.center().x,this.center().y, enemy.center().x, enemy.y));
        return (GameField.distance(this.center().x, this.center().y, enemy.center().x, enemy.center().y) <= this.range);
    }
    public boolean inSight(Enemy enemy){
        return (GameField.distance(this.center().x, this.center().y, enemy.center().x, enemy.center().y) <= 4*this.range);
    }
    public double Caculate(Enemy enemy) {
        double angle = 0;
        if (this.center().x > enemy.center().x) {
            double ch = GameField.distance(this.center().x, this.center().y, enemy.center().x, enemy.center().y);
            double cgv = Math.abs(this.center().y - enemy.center().y);
            double cos = cgv / ch;
            angle = Math.abs(Math.acos(cos));
            angle = (double) Math.round(angle * 1000) / 1000;
            angle = angle * 180 / Math.PI;
            angle = (double) Math.round(angle * 1000) / 1000;
            if (enemy.center().y > this.center().y) {
                return angle + 180.0;
            } else return 90.0 - angle + 270.0;
        } else {
            double ch = GameField.distance(this.center().x, this.center().y, enemy.center().x, enemy.center().y);
            double cgv = Math.abs(this.center().y - enemy.center().y);
            double cos = cgv / ch;
            angle = Math.abs(Math.acos(cos));
            angle = (double) Math.round(angle * 1000) / 1000;
            angle = angle * 180 / Math.PI;
            angle = (double) Math.round(angle * 1000) / 1000;
            if (enemy.center().y > this.center().y) {
                return 180.0 - angle;
            } else return angle;
        }

    }

    public void update() {
        if (!GameField.enemy.isEmpty()) {
            if(i>=GameField.enemy.size()) {
                i = 0;
                this.rotating = 0;
                this.isShoot = false;
            }
           if (this.inSight(GameField.enemy.get(i))) {
               setBullet();
               if (!GameField.enemy.isEmpty()) {
                  if( !inRange(GameField.enemy.get(i).nextPos(time_shoot))){
                       i++;
                       if (i >= GameField.enemy.size()) {
                           this.rotating = 0;
                           this.isShoot = false;
                           i = 0;
                       }
                   }
               }
           }
        }
        else {
            this.isShoot = false;
            this.rotating = 0;
        }
        clearBullets();
    }
    // public Point getTarge(){
    //   Point p;

    //}
    public double timeMoveOfBullet(Enemy enemy, Bullet bullet) {
        double time =0;
        Enemy e = new Enemy();
        Bullet b = null;
        if(type_tower==0) b = new McGunBullet(bullet.x, bullet.y);
        else if(type_tower==1) b = new NormalTowerBullet(bullet.x, bullet.y);
        else b = new SniperBullet(bullet.x, bullet.y);
        b.setSpeed(bullet.getSpeed());
        e.speed = enemy.speed;
        while(true){
            b.x=this.center().x;
            b.y=this.center().y;
            e.x = enemy.x;
            e.y = enemy.y;
            e.wayPointIndex = enemy.wayPointIndex;
            e.direction = enemy.direction;
            b.setAngle(Caculate(enemy.nextPos(time)));
            for(int i = 0; i<time; i++){
                e.update();
                b.Caculate();
            }
            if(this.inSight(e)){
                if(GameField.distance(e.center().x, e.center().y, b.x, b.y) <=2*b.getSpeed()) return time;
                else time++;
            }
            else return -1;
        }

    }
    public void setBullet() {
           if (this.ready_to_shoot == 0) {
               if (this.inSight(GameField.enemy.get(i))) {
                   Bullet bullet1 = null;
                   if(type_tower==0) bullet1 = new McGunBullet(this.center().x, this.center().y);
                   else if(type_tower==1) bullet1 = new NormalTowerBullet(this.center().x, this.center().y);
                   else bullet1 = new SniperBullet(this.center().x, this.center().y);

                   double time = timeMoveOfBullet(GameField.enemy.get(i), bullet1);
                   // System.out.println(time);
                   time_shoot = time;
                   if(time!=-1){
                       if(this.inRange(GameField.enemy.get(i).nextPos(time))&&!GameField.enemy.get(i).isDead()){
                           mediaPlayer.stop();
                           mediaPlayer.play();
                           this.isShoot = true;
                           bullet1.setAngle(this.Caculate(GameField.enemy.get(i).nextPos(time)));
                           bullet1.setD_target(GameField.distance(GameField.enemy.get(i).nextPos(time).center().x,
                                   GameField.enemy.get(i).nextPos(time).center().y,
                                   bullet1.x,
                                   bullet1.y));
                           bullet1.setFirst();
                           this.bullets.add(bullet1);
                           bullet1.setTarget(GameField.enemy.get(i));
                           this.rotating = bullet1.getAngle();
                       }
                       else {
                           this.isShoot = false;
                           this.rotating = 0;
                       }
                   }
                   this.ready_to_shoot++;
               }
               else {
                   this.isShoot= false;
                   this.rotating=0;
               }
           } else if (this.ready_to_shoot > 0 && this.ready_to_shoot <=this.wait_to_shoot) {
               this.ready_to_shoot++;
           } else {
               this.ready_to_shoot = 0;
           }
    }
    public void updateBullets(){
            for(int j = 0; j<bullets.size(); j++){
                bullets.get(j).update();
            }
    }
    public void renderBullets(GraphicsContext gc){
            for(int j =0; j<bullets.size(); j++){
                bullets.get(j).render(gc);
            }
    }
    public void clearBullets(){
        for(int j =0; j<bullets.size(); j++) {
            if (!bullets.isEmpty()) {
                if (bullets.get(j).isTarget()) {
                   for(int t =0; t<GameField.enemy.size(); t++){
                       if(bullets.get(j).getTarget()==GameField.enemy.get(t)){
                           if(!GameField.enemy.get(t).isDead()){
                               GameField.enemy.get(t).inHit(this.damage);
                               break;
                           }
                       }
                   }
                    bullets.remove(j);
                }
            }
        }
    }

}
