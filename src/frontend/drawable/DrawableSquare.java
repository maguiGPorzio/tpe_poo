package frontend.drawable;

import backend.Format;
import backend.model.Point;
import backend.model.Square;
import frontend.ShadowType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawableSquare extends Square implements  DrawableFigureRectangle{

    private final GraphicsContext gc;

    public DrawableSquare(Point topLeft, double size, Format format, GraphicsContext gc){
        super(topLeft, size, format);
        this.gc = gc;
    }
    public void draw(boolean isSelected){
        drawRectangle(format, gc, isSelected, topLeft, bottomRight, OFFSET);
    }
}
