package frontend.drawable;

import backend.model.Circle;
import backend.model.Point;
import backend.Format;
import javafx.scene.canvas.GraphicsContext;

public class DrawableCircle extends Circle implements DrawableFigureOval{

    public DrawableCircle(Point centerPoint, double radius, Format format){
        super(centerPoint, radius, format);
    }

    public void draw(GraphicsContext gc, boolean isSelected){
        drawOval(format, gc, isSelected, centerPoint, getRadius(), getRadius());
    }

    public boolean belongs(Point eventPoint) {
        return belongsInOval(eventPoint, centerPoint,1.0,1.0) < getRadius();
    }


}
