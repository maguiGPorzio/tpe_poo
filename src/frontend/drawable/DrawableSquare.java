package frontend.drawable;

import backend.model.Point;
import backend.model.Square;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawableSquare extends Square implements  DrawableFigureRectangle{

    public DrawableSquare(Point topLeft, double size, boolean shadow, boolean gradient, boolean bevel, Color color1, Color color2){
        super(topLeft, size, shadow, gradient, bevel, color1, color2);
    }
    public void draw(GraphicsContext gc, Color lineColor, Color fillColor, boolean isSelected){
        drawRectangle(gc,lineColor,fillColor, isSelected, topLeft,bottomRight);
    }

    public boolean belongs(Point eventPoint){
        return belongsInRectangle(eventPoint, topLeft, bottomRight);
    }

}
