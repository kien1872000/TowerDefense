package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Bullet extends GameEntities {
    protected double speed;
    protected double d_target;
    protected double firstX;
    protected double firstY;
    protected double rotate;
    protected double angle;
    protected Enemy target;

    public Bullet(double x, double y) {
        this.rotate = 0;
        this.x = x;
        this.y = y;
        this.firstY= y;
        this.firstX = x;
    }
    public void setTarget(Enemy target){
        this.target = target;
    }
    public Enemy getTarget(){return this.target;}
    public boolean isTarget(){
        return GameField.distance(this.firstX, this.firstY, this.x, this.y)>=this.d_target-3*this.speed-2;
    }

    public double getAngle() {
        return angle;
    }

    public double getSpeed(){return this.speed;}
    public void setSpeed(double speed){this.speed = speed;}
    public void setAngle(double angle){
        this.angle = angle;
    }
    public void setD_target(double d_target){
        this.d_target = d_target;
    }
    public void Caculate(){
        double deltaX = this.speed*Math.sin(this.angle*Math.PI/180.0);
        double deltaY = this.speed*Math.cos(this.angle*Math.PI/180.0);
        this.x = this.x + deltaX;
        this.y = this.y - deltaY;
    }
    public void setFirst(){
        double deltaX = 25*Math.sin(this.angle*Math.PI/180.0);
        double deltaY = 25*Math.cos(this.angle*Math.PI/180.0);
        this.x = this.x + deltaX;
        this.y = this.y - deltaY;
    }
    @Override
    public void render(GraphicsContext gc) {
        ImageView iv = new ImageView(img);
       // rotate+=60;
        iv.setRotate(angle+180);
        params.setFill(Color.TRANSPARENT);
        Image rotatedImage1 = iv.snapshot(params, null);
        double delta = (rotatedImage1.getHeight()-64)/2;
        gc.drawImage(rotatedImage1, x-32-delta, y-32-delta);
    }
    @Override
    public void update(){
        if(this==null) return;
        Caculate();
    }
}
