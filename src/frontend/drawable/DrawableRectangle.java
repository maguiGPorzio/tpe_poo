package frontend.drawable;

import backend.Format;
import backend.model.Point;
import backend.model.Rectangle;
import javafx.scene.canvas.GraphicsContext;

public class DrawableRectangle extends Rectangle implements DrawableFigureRectangle{

    public DrawableRectangle(Point topLeft, Point bottomRight, Format format){
        super(topLeft, bottomRight, format);
    }

    public void draw(GraphicsContext gc, boolean isSelected){
        drawRectangle(format, gc, isSelected, topLeft, bottomRight);
    }

    public boolean belongs(Point eventPoint){
        return belongsInRectangle(eventPoint, topLeft, bottomRight);
    }
}
