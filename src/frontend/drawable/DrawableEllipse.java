package frontend.drawable;

import backend.model.Ellipse;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import backend.Format;
import javafx.scene.paint.Color;


public class DrawableEllipse extends Ellipse<Color> implements DrawableFigureOval {

    private final GraphicsContext gc;

    public DrawableEllipse(Point centerPoint, double sMayorAxis, double sMinorAxis, Format<Color> format, GraphicsContext gc){
        super(centerPoint, sMayorAxis, sMinorAxis, format);
        this.gc = gc;
    }

    public void draw(boolean isSelected){
        drawOval(format, gc, isSelected, centerPoint, sMayorAxis, sMinorAxis, 0);
    }

}
