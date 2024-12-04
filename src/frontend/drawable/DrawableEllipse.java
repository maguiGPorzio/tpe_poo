package frontend.drawable;

import backend.model.Ellipse;
import backend.model.Figure;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import backend.Format;

public class DrawableEllipse extends Ellipse implements DrawableFigureOval {

    public DrawableEllipse(Point centerPoint, double sMayorAxis, double sMinorAxis, Format format){
        super(centerPoint, sMayorAxis, sMinorAxis, format);
    }

    public void draw(GraphicsContext gc, boolean isSelected){
        drawOval(format, gc, isSelected, centerPoint, sMayorAxis, sMinorAxis);
    }

}
