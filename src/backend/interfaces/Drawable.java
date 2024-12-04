package backend.interfaces;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

@FunctionalInterface
public interface Drawable {
    void draw(GraphicsContext gc, boolean isSelected);
}
