package sample;

import javafx.scene.canvas.GraphicsContext;

public interface GameEntity {
    public abstract void render(GraphicsContext gc);
    public abstract void update();
}
