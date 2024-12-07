package frontend.drawable;

import backend.model.Point;
import backend.model.Square;
import javafx.scene.canvas.GraphicsContext;

public class DrawableSquare extends Square implements  DrawableFigureRectangle{
    private final GraphicsContext gc;
    private final Format format;

    public DrawableSquare(Point topLeft, double size, Format format, GraphicsContext gc){
        super(topLeft, size);
        this.gc = gc;
        this.format = format;
    }

    public void draw(boolean isSelected){
        drawRectangle(format, gc, isSelected, topLeft, bottomRight);
    }

}
