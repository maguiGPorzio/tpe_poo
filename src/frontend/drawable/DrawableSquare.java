package frontend.drawable;

import backend.Format;
import backend.model.Point;
import backend.model.Square;
import frontend.ShadowType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawableSquare extends Square implements  DrawableFigureRectangle{

    public DrawableSquare(Point topLeft, double size, Format format){
        super(topLeft, size, format);
    }
    public void draw(GraphicsContext gc, boolean isSelected){
        drawRectangle(format, gc, isSelected, topLeft, bottomRight);
    }

    public boolean belongs(Point eventPoint){
        return belongsInRectangle(eventPoint, topLeft, bottomRight);
    }

}
