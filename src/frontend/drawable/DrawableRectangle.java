package frontend.drawable;

import backend.model.Point;
import backend.model.Rectangle;
import javafx.scene.canvas.GraphicsContext;

public class DrawableRectangle extends Rectangle implements DrawableFigureRectangle{

    public final GraphicsContext gc;

    public DrawableRectangle(Point topLeft, Point bottomRight, Format format, GraphicsContext gc){
        super(topLeft, bottomRight, format);
        this.gc = gc;
    }

    public void draw(boolean isSelected){
        drawRectangle(format, gc, isSelected, topLeft, bottomRight, OFFSET);
    }

}
