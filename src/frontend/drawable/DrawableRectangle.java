package frontend.drawable;

import backend.model.Point;
import backend.model.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class DrawableRectangle extends Rectangle implements DrawableFigureRectangle{

    public DrawableRectangle(Point topLeft, Point bottomRight, boolean shadow, boolean gradient, boolean bevel, Color color1, Color color2){
        super(topLeft, bottomRight, shadow, gradient, bevel, color1, color2);
    }

    public void draw(GraphicsContext gc, Color lineColor, Color fillColor, boolean isSelected){
        drawRectangle(gc,lineColor,fillColor, isSelected, topLeft,bottomRight);
    }

    public boolean belongs(Point eventPoint){
        return belongsInRectangle(eventPoint, topLeft, bottomRight);
    }
}
