package frontend.drawable;

import backend.model.Ellipse;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class DrawableEllipse extends Ellipse implements DrawableFigureOval {
    private final Format format;
    private final GraphicsContext gc;

    public DrawableEllipse(Point centerPoint, double sMayorAxis, double sMinorAxis, Format format, GraphicsContext gc){
        super(centerPoint, sMayorAxis, sMinorAxis);
        this.gc = gc;
        this.format = format;
    }

    public void draw(boolean isSelected){
        drawOval(format, gc, isSelected, centerPoint, sMayorAxis, sMinorAxis);
    }

}
