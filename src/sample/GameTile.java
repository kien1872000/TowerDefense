package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public interface GameTile extends GameEntity {
    @Override
    public void render(GraphicsContext gc) ;



    @Override
    public void update();


}
