package frontend.buttons;

import frontend.formatted.Format;
import backend.model.Point;
import frontend.formatted.FormattedEllipse;
import frontend.formatted.FormattedFigure;
import javafx.scene.canvas.GraphicsContext;

public class EllipseButton extends FigureButton{
    public EllipseButton(String action){
        super(action);
    }

    public FormattedFigure generate(Point startPoint, Point endPoint, Format format, GraphicsContext gc){
        Point centerPoint = new Point(Math.abs(endPoint.getX() + startPoint.getX()) / 2, (Math.abs((endPoint.getY() + startPoint.getY())) / 2));
        double sMayorAxis = Math.abs(endPoint.getX() - startPoint.getX());
        double sMinorAxis = Math.abs(endPoint.getY() - startPoint.getY());
        return new FormattedEllipse(centerPoint, sMayorAxis, sMinorAxis, format, gc);
    }
}
