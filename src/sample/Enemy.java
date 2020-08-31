package sample;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Enemy extends GameEntities{
    protected int health;
    protected double speed;
    protected int reward;
    protected int wayPointIndex = 0;
    protected Direction direction;
    protected int fullhealth;
    protected double rotating;
    protected int damage;
    public void setHealth(int health){this.health = health;}
    public int getDamage(){return this.damage;}
    @Override
    public void render(GraphicsContext gc) {
        params.setFill(Color.TRANSPARENT);
        ImageView iv = new ImageView(img);
        iv.setRotate(this.direction.getDegree()+this.rotating);
        Image base = iv.snapshot(params, null);
        gc.drawImage(base, x, y);
        gc.setFill(Color.WHITE);
        gc.fillRect(x+15, y-15, (this.fullhealth)/(4*(this.fullhealth/100)), 3);
        gc.setFill((Color.RED));
        gc.fillRect(x+15, y-15, (this.health)/(4*(this.fullhealth/100)) , 3);
         //gc.setFill(Color.RED);
        //gc.fillOval(GameField.wayPoints[wayPointIndex].x,GameField.wayPoints[wayPointIndex].y,10, 10);

        //gc.setFill(Color.BLUE);
       // gc.fillOval(x+27, y+27,10, 10);
    }
    public Enemy () {
        this.i = 0;
        this.j = 6;
        this.x = this.i * 64 + 32;
        this.y = this.j * 64;
        this.direction = Direction.UP;
    }
    public static final Point[] wayPoints = new Point[] {
            new Point(0 * 64 + 32, 6 * 64 + 00),
            new Point(0 * 64 + 32, 3 * 64 + 32),
            new Point(2 * 64 + 32, 3 * 64 + 32),
            new Point(2 * 64 + 32, 0 * 64 + 32),
            new Point(5 * 64 + 32, 0 * 64 + 32),
            new Point(5 * 64 + 32, 6 * 64 - 32),
            new Point(8 * 64 + 32, 6 * 64 - 32),
            new Point(8 * 64 + 32, 0 * 64 + 32),
            new Point(10 * 64 + 32, 0 * 64 + 32),
    };
    public Enemy nextPos(double time){
        Enemy enemy = new Enemy();
        enemy.x = this.x;
        enemy.y = this.y;
        enemy.wayPointIndex = this.wayPointIndex;
        enemy.direction = this.direction;
        enemy.speed = this.speed;
        for(int i = 0; i<time; i++){
            enemy.update();
    }
       // System.out.println(enemy.x + " " + this.x);


        return enemy;
    }
    public Point getNextWayPoint() {
        if (this.wayPointIndex < this.wayPoints.length - 1)
            return this.wayPoints[++this.wayPointIndex];
        return null;
    }
    public int calculateDirection() {
        // Tinh huong di tiep theo cho Object
        if (wayPointIndex >= this.wayPoints.length) {
            // Enemy den way point cuoi
            return -1;
        }

        Point currentWP = this.wayPoints[wayPointIndex];
        if (GameField.distance(this.x, this.y, currentWP.x, currentWP.y) <= speed) {
            this.x = currentWP.x;
            this.y = currentWP.y;
            Point nextWayPoint = getNextWayPoint();
            if (nextWayPoint == null) return -1;
            double deltaX = nextWayPoint.x - this.x;
            double deltaY = nextWayPoint.y - this.y;
            if (deltaX > this.speed) this.direction = Direction.RIGHT;
            else if (deltaX < -this.speed) this.direction = Direction.LEFT;
            else if (deltaY > this.speed) this.direction = Direction.DOWN;
            else if (deltaY <= -this.speed) this.direction = Direction.UP;
        }
        return 0;
    }
    public void inHit(double n){
        if(this.health>=0){
            this.health -= n;
        }
    }
    public boolean isDead(){
        return this.health<=0;
    }
    public boolean oneHit(double damage){return (this.health>0 && this.health<=damage);}
    @Override
    public void update() {
        if(!GameField.enemy.isEmpty()){
            calculateDirection();
            switch (direction) {
                case UP:
                    this.y -= speed;
                    break;
                case DOWN:
                    this.y += speed;
                    break;
                case LEFT:
                    this.x -= speed;
                    break;
                case RIGHT:
                    this.x += speed;
                    break;
            }
        }
        }
}
