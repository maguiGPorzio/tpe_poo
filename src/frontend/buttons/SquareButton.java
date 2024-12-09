package frontend.buttons;

import frontend.formatted.Format;
import backend.model.Point;
import frontend.formatted.FormattedFigure;
import frontend.formatted.FormattedSquare;
import javafx.scene.canvas.GraphicsContext;

public class SquareButton extends FigureButton{
    public SquareButton(String action){
        super(action);
    }

    public FormattedFigure generate(Point startPoint, Point endPoint, Format format, GraphicsContext gc, int layer){
        if(startPoint.atLeft(endPoint) && startPoint.above(endPoint)){
            double size = Math.abs(endPoint.getX() - startPoint.getX());
            return new FormattedSquare(startPoint, size, format.duplicate(), gc, layer);
        }
        return null;
    }
}
