package frontend.drawable;

import backend.model.Circle;
import backend.model.Point;
import backend.Format;
import javafx.scene.canvas.GraphicsContext;

public class DrawableCircle extends Circle implements DrawableFigureOval{
    private final GraphicsContext gc;

    public DrawableCircle(Point centerPoint, double radius, Format format, GraphicsContext gc){
        super(centerPoint, radius, format);
        this.gc = gc;
    }

    public void draw(boolean isSelected){
        drawOval(format, gc, isSelected, centerPoint, getRadius()*2, getRadius()*2, OVAL_OFFSET);
    }

}
