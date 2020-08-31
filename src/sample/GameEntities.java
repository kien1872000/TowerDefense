package sample;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class GameEntities implements GameEntity  {
    protected double i,j;
    protected double x, y;
    protected  Image img;
    protected  Image gunImg;
    SnapshotParameters params = new SnapshotParameters();
    @Override
    public abstract void render(GraphicsContext gc);
    @Override
    public abstract void update();
    public Point center(){
        Point p = new Point(this.x+32.0, this.y+32.0);
        return p;
    }
}
