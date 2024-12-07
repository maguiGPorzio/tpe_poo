package frontend.drawable;

import backend.model.Point;
import backend.model.Rectangle;
import javafx.scene.canvas.GraphicsContext;

public class DrawableRectangle extends Rectangle implements DrawableFigureRectangle{

    private final GraphicsContext gc;
    private final Format format;

    public DrawableRectangle(Point topLeft, Point bottomRight, Format format, GraphicsContext gc){
        super(topLeft, bottomRight);
        this.gc = gc;
        this.format = format;
    }

    public void draw(boolean isSelected){
        drawRectangle(format, gc, isSelected, topLeft, bottomRight);
    }

}
