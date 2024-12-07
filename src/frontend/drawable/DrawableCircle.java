package frontend.drawable;

import backend.model.Circle;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class DrawableCircle extends Circle implements DrawableFigureOval{
    private final GraphicsContext gc;
    private final Format format;

    public DrawableCircle(Point centerPoint, double radius, Format format, GraphicsContext gc){
        super(centerPoint, radius);
        this.gc = gc;
        this.format = format;
    }

    public void draw(boolean isSelected){
        drawOval(format, gc, isSelected, centerPoint, getRadius()*2, getRadius()*2);
    }

}
