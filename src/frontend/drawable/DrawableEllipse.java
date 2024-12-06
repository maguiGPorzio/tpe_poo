package frontend.drawable;

import backend.model.Ellipse;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import backend.Format;

public class DrawableEllipse extends Ellipse implements DrawableFigureOval {

    private final GraphicsContext gc;

    public DrawableEllipse(Point centerPoint, double sMayorAxis, double sMinorAxis, Format format, GraphicsContext gc){
        super(centerPoint, sMayorAxis, sMinorAxis, format);
        this.gc = gc;
    }

    public void draw(boolean isSelected){
        drawOval(format, gc, isSelected, centerPoint, sMayorAxis, sMinorAxis, OVAL_OFFSET);
    }

}
