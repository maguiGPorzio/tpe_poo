package frontend.buttons;

import frontend.formatted.Format;
import backend.model.Point;
import frontend.formatted.FormattedCircle;
import frontend.formatted.FormattedFigure;
import javafx.scene.canvas.GraphicsContext;

public class CircleButton extends FigureButton{
    public CircleButton(String action){
        super(action);
    }

    public FormattedFigure generate(Point startPoint, Point endPoint, Format format, GraphicsContext gc){
        double circleRadius = startPoint.distance(endPoint);
        return new FormattedCircle(startPoint, circleRadius, format, gc);
    }
}
