package frontend.buttons;

import frontend.formatted.Format;
import backend.model.Point;
import frontend.formatted.FormattedFigure;
import frontend.formatted.FormattedRectangle;
import javafx.scene.canvas.GraphicsContext;

public class RectangleButton extends FigureButton{
    public RectangleButton(String action){
        super(action);
    }

    public FormattedFigure generate(Point startPoint, Point endPoint, Format format, GraphicsContext gc){
        if(startPoint.atLeft(endPoint) && startPoint.above(endPoint)){
            return new FormattedRectangle(startPoint, endPoint, format, gc);
        }
        return null;
    }
}
