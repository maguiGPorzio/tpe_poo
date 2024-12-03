package frontend.drawable;

import backend.model.Circle;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawableCircle extends Circle implements DrawableFigureOval{

    public DrawableCircle(Point centerPoint, double radius, boolean shadow, boolean gradient, boolean bevel, Color color1, Color color2){
        super(centerPoint, radius, shadow, gradient, bevel, color1, color2);
    }

    public void draw(GraphicsContext gc, Color lineColor, Color fillColor, boolean isSelected){
        drawOval(gc,lineColor,fillColor, isSelected, centerPoint, getRadius(), getRadius());
    }

    public boolean belongs(Point eventPoint){
        return belongsInOval(eventPoint, centerPoint,1.0,1.0) < getRadius();
    }
}
