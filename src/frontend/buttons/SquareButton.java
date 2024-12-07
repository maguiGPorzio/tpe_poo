package frontend.buttons;

import frontend.drawable.Format;
import backend.model.Figure;
import backend.model.Point;
import frontend.ShadowType;
import frontend.drawable.DrawableSquare;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SquareButton extends FigureButton{
    public SquareButton(String action){
        super(action);
    }

    public FormattedFigure generate(Point startPoint, Point endPoint, Format format, GraphicsContext gc){
        double size = Math.abs(endPoint.getX() - startPoint.getX());
        return new DrawableSquare(startPoint, size, format, gc);
    }
}
