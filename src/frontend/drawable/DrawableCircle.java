package frontend.drawable;

import backend.model.Circle;
import backend.model.Point;
import backend.Format;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class DrawableCircle extends Circle<Color> implements DrawableFigureOval{
    private final GraphicsContext gc;

    public DrawableCircle(Point centerPoint, double radius, Format<Color> format, GraphicsContext gc){
        super(centerPoint, radius, format);
        this.gc = gc;
    }

    public void draw(boolean isSelected){
        drawOval(format, gc, isSelected, centerPoint, getRadius()*2, getRadius()*2, 0);
    }

}
